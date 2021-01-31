package com.jajjamind.payvault.core.service.agent;

import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.agent.models.Agent;
import com.jajjamind.payvault.core.api.agent.models.Company;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.api.users.models.UserMeta;
import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.jpa.models.agent.TAgent;
import com.jajjamind.payvault.core.jpa.models.agent.TAgentApproval;
import com.jajjamind.payvault.core.jpa.models.agent.TCompany;
import com.jajjamind.payvault.core.jpa.models.agent.TCountry;
import com.jajjamind.payvault.core.jpa.models.enums.CountryEnum;
import com.jajjamind.payvault.core.jpa.models.user.TUserMeta;
import com.jajjamind.payvault.core.repository.agent.*;
import com.jajjamind.payvault.core.repository.user.UserMetaRepository;
import com.jajjamind.payvault.core.service.user.UserMetaServiceImpl;
import com.jajjamind.payvault.core.utils.AuditService;
import com.jajjamind.payvault.core.utils.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author akena
 * 19/01/2021
 * 02:54
 **/
@Service
public class AgentServiceImpl implements AgentService{

    @Autowired
    public AgentRepository agentRepository;

    @Autowired
    public AuditService auditService;

    @Autowired
    public CompanyRepository companyRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserMetaRepository userMetaRepository;

    @Autowired
    public CountryRepository countryRepository;

    @Autowired
    public UserMetaServiceImpl userMetaService;

    @Autowired
    public JooqAgentRepository jooqAgentRepository;

    @Autowired
    public AgentApprovalRepository agentApprovalRepository;

    @Override
    @Transactional
    public Agent add(Agent agent) {

        agent.validate();

        checkThatUserNameIsNotAlreadyTaken(agent.getUsername());

        final UserMeta userMeta = agent.getUserMeta();
        checkThatAgentDoesNotExistAlready(userMeta,agent.getCompany().getBusinessName());

        final CountryEnum country = ValidateUtils.validateAndGetCountry(userMeta.getCountryCode().getIsoAlpha2());

        final TCompany company = validateAndGetCompany(agent.getCompany(),userMeta.getCountryCode().getIsoAlpha2());

        final  TAgent tAgent = new TAgent();
        BeanUtils.copyProperties(agent,tAgent);

        tAgent.setCompany(company);
        tAgent.setPin(encodeUserPin(agent.getPin()));

        auditService.stampAuditedEntity(tAgent);
        agentRepository.save(tAgent);

        Optional<TAgent> oSavedAgent = agentRepository.findByUserName(agent.getUsername());

        Validate.isTrue(oSavedAgent.isPresent(),"Failed to create agent details");

        final TAgent savedAgent = oSavedAgent.get();

        final TUserMeta oUserMeta = new TUserMeta();
        BeanUtils.copyProperties(agent.getUserMeta(),oUserMeta);

        oUserMeta.setAgentId(savedAgent.getId());

        Optional<TCountry> tCountry = countryRepository.findByAlpha2Code(country.getIso2Code());
        Validate.isTrue(tCountry.isPresent(),ErrorMessageConstants.COUNTRY_PROVIDED_DOES_NOT_FOUND,country.getIso2Code());

        oUserMeta.setCountryCode(tCountry.get());

        auditService.stampAuditedEntity(oUserMeta);
        userMetaRepository.save(oUserMeta);

        createApprovalObject(savedAgent.getId());


        return agent;
    }

    @Override
    public Agent get(Long id) {

        return null;
    }

    @Transactional
    @Override
    public Agent update(Agent agent) {
        agent.validate();
        Validate.notNull(agent.getId(),"Agent ID cannot be null");
        Optional<TAgent> oldAgentOptional = agentRepository.findById(agent.getId());

        Validate.isTrue(oldAgentOptional.isPresent(),"Agent with id %s could not be located ",agent.getId());
        final TAgent oldAgent = oldAgentOptional.get();

        final TAgent updatedAgent = new TAgent();
        BeanUtils.copyProperties(agent,updatedAgent);

        restoreOldAgentProperties(updatedAgent,oldAgent);

        auditService.stampAuditedEntity(updatedAgent);
        agentRepository.save(updatedAgent);

        final Optional<TUserMeta> userMeta = Optional.of(oldAgent.getUserMeta());

        if(userMeta.isPresent()){

            final TUserMeta mUserMeta =  userMetaService.updateUserMetaProperties(agent.getUserMeta(), oldAgent.getUserMeta());
            auditService.stampAuditedEntity(mUserMeta);

            userMetaRepository.save(mUserMeta);
        }


        return agent;
    }

    private void restoreOldAgentProperties(TAgent updatedAgent, TAgent oAgent){
        updatedAgent.setId(oAgent.getId());
        updatedAgent.setPin(oAgent.getPin());
        updatedAgent.setUsername(oAgent.getUsername());
    }

    private void createApprovalObject(Long agentId){
        TAgentApproval approval = new TAgentApproval();
        approval.setId(agentId);

        auditService.stampAuditedEntity(approval);

        agentApprovalRepository.save(approval);

    }

    @Override
    public Agent delete(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Agent> getAll() {
        throw  new UnsupportedOperationException();
    }


    private TCompany validateAndGetCompany(Company c,String agentCountry){
        Optional<TCompany> tCompany = companyRepository.findById(c.getId());
        Validate.isTrue(tCompany.isPresent(), ErrorMessageConstants.COMPANY_WITH_ID_COULD_NOT_BE_FOUND,c.getId());

        TCompany company = tCompany.get();
        Validate.isTrue(company.getRegistrationCountry().getIsoAlpha2().equals(agentCountry),ErrorMessageConstants.COUNTRY_PROVIDED_DOES_NOT_MATCH,
                c.getRegistrationCountry().getIsoAlpha2(),agentCountry);

        return company;
    }


    private String encodeUserPin(String pin){
        return passwordEncoder.encode(pin);
    }

    private void checkThatAgentDoesNotExistAlready(UserMeta userMeta,String companyName){
        Validate.isTrue(!agentRepository.checkThatAgentMatchesAny(userMeta.getFirstName(),userMeta.getLastName(),userMeta.getIdentificationNumber(),companyName),"A company with similar details already exists in the system");

    }

    private void checkThatUserNameIsNotAlreadyTaken(String userName){
        Validate.isTrue(agentRepository.checkThatUsernameIsNotTaken(userName), "Username %s is already taken");
    }


    @Override
    public RecordList<JooqAgentRepository.Result> queryForAgents(MultiValueMap k) {
        return jooqAgentRepository.listAndCount(k);
    }

    @Override
    public void approveAgentCreation(String agentId, String comment) {
        ///Yet to be done
    }
}
