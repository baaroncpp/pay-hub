package com.jajjamind.payvault.core.service.agent;

import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.agent.models.Agent;
import com.jajjamind.payvault.core.api.agent.models.Company;
import com.jajjamind.payvault.core.api.constants.Constants;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.api.users.models.UserMeta;
import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.jpa.models.agent.TAgent;
import com.jajjamind.payvault.core.jpa.models.agent.TAgentApproval;
import com.jajjamind.payvault.core.jpa.models.agent.TCompany;
import com.jajjamind.payvault.core.jpa.models.agent.TCountry;
import com.jajjamind.payvault.core.jpa.models.config.TConfiguration;
import com.jajjamind.payvault.core.jpa.models.enums.ApprovalEnum;
import com.jajjamind.payvault.core.jpa.models.enums.CountryEnum;
import com.jajjamind.payvault.core.jpa.models.user.TUser;
import com.jajjamind.payvault.core.jpa.models.user.TUserMeta;
import com.jajjamind.payvault.core.repository.agent.*;
import com.jajjamind.payvault.core.repository.config.ConfigurationRepository;
import com.jajjamind.payvault.core.repository.user.UserMetaRepository;
import com.jajjamind.payvault.core.security.models.LoggedInUser;
import com.jajjamind.payvault.core.service.user.UserMetaServiceImpl;
import com.jajjamind.payvault.core.service.utilities.SmsMessage;
import com.jajjamind.payvault.core.service.utilities.SmsService;
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

    @Autowired
    public ConfigurationRepository configurationRepository;

    @Autowired
    public SmsService smsService;

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

        final TUserMeta mUserMeta =  userMetaService.updateUserMetaProperties(agent.getUserMeta(), oldAgent.getUserMeta());
        auditService.stampAuditedEntity(mUserMeta);

        userMetaRepository.save(mUserMeta);


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

    @Transactional
    @Override
    public void approveOrRejectAgentCreation(Long id, String status,String comment) {
        final Optional<TAgentApproval> agentApproval = agentApprovalRepository.findByIdWithAgent(id);

        Validate.isTrue(agentApproval.isPresent(),"No pending approval found with given ID");

        final TAgentApproval approval = agentApproval.get();

        Validate.isTrue(approval.getApprovalCount() < 2,"Agent creation has been approved");
        Validate.isTrue(approval.getStatus().equals(ApprovalEnum.PENDING),"Agent creation has been approved");

        final TAgent agent = approval.getAgent();

        Validate.isTrue(!agent.getApprovalStatus().equals(ApprovalEnum.PENDING),"Agent has been approved");

        final LoggedInUser user = auditService.getLoggedInUser();
        final TUser approvingUser = new TUser();
        approvingUser.setId(user.getId());

        if(approval.getApprover1() == null){
            approval.setApprover1(approvingUser);
            approval.setApprovalCount(1);
            approval.setNote(comment);
            approval.setFirstApproveOn(DateTimeUtil.getCurrentUTCTime());
        }else {
            approval.setApprover2(approvingUser);
            approval.setNote2(comment);
            approval.setApprovalCount(approval.getApprovalCount()+1);
            approval.setSecondApproveOn(DateTimeUtil.getCurrentUTCTime());
        }

        auditService.stampAuditedEntity(approval);
        agentApprovalRepository.save(approval);

        if(approval.getApprovalCount() == 2 && status.equalsIgnoreCase("approve"))
        {
            agent.setApprovalStatus(ApprovalEnum.APPROVED);
            auditService.stampAuditedEntity(agent);

            agentRepository.save(agent);
            smsService.sendSmsMessage(agentCreationSMS(agent.getId().intValue()));

        }

        if(status.equalsIgnoreCase("reject")){
            approval.setStatus(ApprovalEnum.REJECTED);
            agentApprovalRepository.save(approval);

            agent.setApprovalStatus(ApprovalEnum.REJECTED);
            auditService.stampAuditedEntity(agent);

            agentRepository.save(agent);
        }


    }


    //Not so sure if to save copy on my side -- May have to
    private SmsMessage agentCreationSMS(Integer  agent){

        Optional<TConfiguration> smsConfig = configurationRepository.findByName(Constants.GET_AGENT_SMS_CONFIG_NAME);
        Validate.isTrue(smsConfig.isPresent(),"Failed to get client SMS notification message");

        final Optional<TUserMeta> mta = userMetaRepository.findByAgentId(agent);

        final TConfiguration config = smsConfig.get();
        final SmsMessage message = new SmsMessage();

        message.setDeliverTo(List.of(mta.get().getPhoneNumber()));
        message.setMessage(String.format(config.getValue(),mta.get().getFirstName().concat(" ").concat(mta.get().getLastName())));
        message.setRequireDeliveryReceipt(Boolean.FALSE);

        return message;
    }
}
