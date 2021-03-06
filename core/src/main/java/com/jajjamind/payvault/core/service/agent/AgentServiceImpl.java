package com.jajjamind.payvault.core.service.agent;

import com.jajjamind.commons.exceptions.BadRequestException;
import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.RealTimeUtil;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.agent.models.Agent;
import com.jajjamind.payvault.core.api.agent.models.Company;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.api.users.models.Approval;
import com.jajjamind.payvault.core.api.users.models.TermsAndConditions;
import com.jajjamind.payvault.core.api.users.models.UserMeta;
import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.jpa.models.agent.*;
import com.jajjamind.payvault.core.jpa.models.enums.AgentTypeEnum;
import com.jajjamind.payvault.core.jpa.models.enums.ApprovalEnum;
import com.jajjamind.payvault.core.jpa.models.enums.CountryEnum;
import com.jajjamind.payvault.core.jpa.models.user.TUser;
import com.jajjamind.payvault.core.jpa.models.user.TUserMeta;
import com.jajjamind.payvault.core.repository.agent.*;
import com.jajjamind.payvault.core.repository.user.UserMetaRepository;
import com.jajjamind.payvault.core.security.models.LoggedInUser;
import com.jajjamind.payvault.core.service.user.UserMetaServiceImpl;
import com.jajjamind.payvault.core.service.utilities.Sms;
import com.jajjamind.payvault.core.service.utilities.SmsService;
import com.jajjamind.payvault.core.utils.AuditService;
import com.jajjamind.payvault.core.utils.BeanUtilsCustom;
import com.jajjamind.payvault.core.utils.ValidateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import javax.transaction.Transactional;
import java.util.*;

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
    public TermsAndConditionsRepository termsAndConditionsRepository;

    @Autowired
    public AgentPreviousPinRepository agentPreviousPinRepository;


    @Autowired
    public SmsService smsService;


    @Override
    public boolean isUserNameTaken(String username) {
        return agentRepository.checkThatUsernameIsNotTaken(username);
    }

    @Override
    public Agent getCurrentLoggedInAgent(String agentExternalId) {

        TAgent agent = validateRequestAgentMatchesLoggedInAgent(agentExternalId);

        Agent a = new Agent();
        BeanUtilsCustom.copyProperties(agent,a);

        UserMeta userMeta = new UserMeta();
        BeanUtilsCustom.copyProperties(agent.getUserMeta(),userMeta);

        Company company = new Company();
        BeanUtilsCustom.copyProperties(agent.getCompany(),company);

        a.setUserMeta(userMeta);
        a.setCompany(company);

        return a;
    }

    @Transactional
    @Override
    public void resetAgentPassword(String externalId, String oldPassword, String newPassword) {

        Validate.isTrue(newPassword.length() == 4,"Pin length must be 4 in length");
        TAgent agent = validateRequestAgentMatchesLoggedInAgent(externalId);
        Validate.isTrue(agent.getNonLockedPin(),"Your PIN has been blocked, please contact administrator");
        Validate.isTrue(passwordEncoder.matches(oldPassword,newPassword),"Password reset failed, old password is incorrect");

        if(!agent.getInitialPasswordReset()){

            String allowedDifferenceString = System.getProperty("agent.password.reset.delay","86400000");

            Integer allowedDifference = null;
            try{
                allowedDifference = Integer.valueOf(allowedDifferenceString);
            }catch (ClassCastException ex){
                ex.printStackTrace();
                allowedDifference = 86400000;
            }

            Long now =DateTimeUtil.getCurrentUTCTime().getTime();
            Long then = agent.getPinLastedUpdatedOn() == null ? agent.getCreatedOn().getTime() : agent.getPinLastedUpdatedOn().getTime();


            if(now - then > allowedDifference){

                agent.setNonLockedPin(Boolean.FALSE);
                agent.setNonDisabled(Boolean.FALSE);

                agent.setModifiedOn(DateTimeUtil.getCurrentUTCTime());

                agentRepository.save(agent);

                Validate.isTrue(Boolean.FALSE,"The time required to reset pin has expired, please contact support");

            }

        }

        final String passwordReset = agent.getPin();
        agent.setInitialPasswordReset(Boolean.TRUE);
        agent.setPinLastedUpdatedOn(DateTimeUtil.getCurrentUTCTime());
        agent.setPin(passwordEncoder.encode(newPassword));

        agent.setModifiedOn(DateTimeUtil.getCurrentUTCTime());

        agentRepository.save(agent);

        TAgentPreviousPin previousPin = new TAgentPreviousPin();
        previousPin.setAgent(agent);
        previousPin.setNote("Password reset by agent");
        previousPin.setRemovalTime(DateTimeUtil.getCurrentUTCTime());
        previousPin.setPin(passwordReset);
        auditService.stampLongEntity(previousPin);

        agentPreviousPinRepository.save(previousPin);

    }

    private TAgent validateRequestAgentMatchesLoggedInAgent(String agentExternalId){
        LoggedInUser user = auditService.getLoggedInUser();

        TAgent agent = agentRepository.findAgentWithAllDetails(user.getId()).orElseThrow(() -> new BadRequestException("Failed to retrieve agent details, logged in ID does not match system id"));

        Validate.isTrue(agentExternalId.equalsIgnoreCase(agent.getExternalId()),"Agent data mismatch. Please contact administrator");

        return agent;
    }


    @Override
    public Agent addSuperAgent(Agent agent) {
        agent.validate();
        Validate.isTrue(agent.getType().equals(AgentTypeEnum.SUPER_AGENT),"Agent is not a super agent");

        return addAgent(agent);
    }

    @Override
    public List<Agent> getSuperAgents() {

        List<TAgent> sa = agentRepository.findSuperAgents();
        List<Agent> superAgent = new ArrayList<>();

        sa.forEach(t -> {
            Agent k = new Agent();
            UserMeta meta = new UserMeta();
            BeanUtilsCustom.copyProperties(t.getUserMeta(),meta);
            BeanUtilsCustom.copyProperties(t,k);
            k.setUserMeta(meta);
            superAgent.add(k);
        });
        return superAgent;
    }

    @Override
    @Transactional
    public Agent add(Agent agent) {

        agent.validate();
        Validate.isTrue(agent.getType().equals(AgentTypeEnum.ORDINARY_AGENT),"Agent is not a ordinary agent");
        Validate.notNull(agent.getEnrolledBy(),"The enrolling agent must be indicated");
        Validate.notNull(agent.getEnrolledBy().getId(),"Agent Id of enrolling agent is missing");


        TAgent enrollingAgent = agentRepository.findById(agent.getEnrolledBy().getId()).orElseThrow(() -> new BadRequestException("Enrolling agent with ID %s not found",agent.getEnrolledBy().getId()));
        Validate.isTrue(enrollingAgent.getApprovalStatus().equals(ApprovalEnum.APPROVED),"Enrolling agent is not approved");
        Validate.isTrue(enrollingAgent.getType().equals(AgentTypeEnum.SUPER_AGENT),"Enrolling agent is not a super agent");
        Validate.isTrue(enrollingAgent.getNonLocked(),"Enrolling agent account has been locked");
        Validate.isTrue(enrollingAgent.getNonDisabled(),"Enrolling agent account has been disabled");

        return addAgent(agent);

    }

    @Transactional
    private Agent addAgent(Agent agent){

        checkThatUserNameIsNotAlreadyTaken(agent.getUsername());

        final UserMeta userMeta = agent.getUserMeta();
        checkThatAgentDoesNotExistAlready(userMeta,agent.getCompany().getBusinessName());

        final CountryEnum country = ValidateUtils.validateAndGetCountry(userMeta.getCountryCode().getIsoAlpha2());

        final TCompany company = validateAndGetCompany(agent.getCompany(),userMeta.getCountryCode().getIsoAlpha2());

        final  TAgent tAgent = new TAgent();
        BeanUtilsCustom.copyProperties(agent,tAgent);

        tAgent.setCompany(company);
        tAgent.setApprovalStatus(ApprovalEnum.PENDING);
        tAgent.setNonLocked(Boolean.TRUE);
        tAgent.setNonDisabled(Boolean.TRUE);
        tAgent.setNonLockedPin(Boolean.TRUE);
        tAgent.setInitialPasswordReset(Boolean.FALSE);


        final TTermsAndConditions termsAndConditions = new TTermsAndConditions();
        termsAndConditions.setId(
                agent.getTermsAndConditions().getId());
        tAgent.setTermsAndConditions(termsAndConditions);

        final String externalId = RealTimeUtil.externalId(userMeta.getPhoneNumber());
        tAgent.setExternalId(externalId);


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

        createApprovalObject(savedAgent);

        agent.setExternalId(externalId);
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

    private void createApprovalObject(TAgent agentId){
        TAgentApproval approval = new TAgentApproval();
        approval.setStatus(ApprovalEnum.PENDING);
        approval.setAgent(agentId);
        approval.setApprovalCount(0);

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
                company.getRegistrationCountry().getIsoAlpha2(),agentCountry);

        return company;
    }


    private String encodeUserPin(String pin){
        return passwordEncoder.encode(pin);
    }

    private void checkThatAgentDoesNotExistAlready(UserMeta userMeta,String companyName){
        Validate.isTrue(!agentRepository.checkThatAgentMatchesAny(userMeta.getFirstName(),
                userMeta.getLastName(),userMeta.getIdentificationNumber(),companyName),"A company with similar details already exists in the system");

    }

    private void checkThatUserNameIsNotAlreadyTaken(String userName){
        Validate.isTrue(agentRepository.checkThatUsernameIsNotTaken(userName), "Username %s is already taken",userName);
    }


    @Override
    public RecordList<JooqAgentRepository.Result> queryForAgents(MultiValueMap k) {
        return jooqAgentRepository.listAndCount(k);
    }

    @Override
    public RecordList getAgentsPendingApproval(MultiValueMap map) {

        map.put(JooqAgentRepository.FIELD_APPROVAL_STATUS,Arrays.asList("PENDING"));

        return jooqAgentRepository.listAndCount(map);

    }

    @Override
    public TermsAndConditions getTermsOfService() {
        Optional<com.jajjamind.payvault.core.jpa.models.agent.TTermsAndConditions> termsOfUse = termsAndConditionsRepository.getActiveTermsAndConditionsForAgent();
        Validate.isTrue(termsOfUse.isPresent(),"Terms of use could not found");

        com.jajjamind.payvault.core.jpa.models.agent.TTermsAndConditions tt = termsOfUse.get();
        final TermsAndConditions ttf = new TermsAndConditions();
        BeanUtils.copyProperties(tt,ttf);

        return ttf;
    }

    @Transactional
    @Override
    public void approveOrRejectAgentCreation(Approval mApproval) {
        final ApprovalEnum status = mApproval.getStatus();
        Validate.isTrue(!status.equals(ApprovalEnum.REJECTED) ||
                !mApproval.equals(ApprovalEnum.APPROVED),ErrorMessageConstants.APPROVAL_STATUS_UNKNOWN,status);

        TAgentApproval approval = agentApprovalRepository.findByIdWithAgent(mApproval.getId())
            .orElseThrow(() -> new BadRequestException("No pending approval found with given ID"));


        Validate.isTrue(approval.getApprovalCount() < 2,"Agent creation has been approved");
        Validate.isTrue(approval.getStatus().equals(ApprovalEnum.PENDING),"Agent creation has been approved");

        final TAgent agent = approval.getAgent();

        Validate.isTrue(agent.getApprovalStatus().equals(ApprovalEnum.PENDING),"Agent has been approved");

        final LoggedInUser user = auditService.getLoggedInUser();
        final TUser approvingUser = new TUser();
        approvingUser.setId(user.getId());

        if(approval.getApprover1() == null){
            approval.setApprover1(approvingUser);
            approval.setApprovalCount(1);
            approval.setNote(mApproval.getNote());
            approval.setFirstApproveOn(DateTimeUtil.getCurrentUTCTime());
        }else {
            approval.setApprover2(approvingUser);
            approval.setNote2(mApproval.getNote());
            approval.setApprovalCount(approval.getApprovalCount()+1);
            approval.setSecondApproveOn(DateTimeUtil.getCurrentUTCTime());
        }

        auditService.stampAuditedEntity(approval);
        agentApprovalRepository.save(approval);

        if(approval.getApprovalCount() == 2 && status.equals(ApprovalEnum.APPROVED))
        {

            final String passcode = RealTimeUtil.getFourDigitPasscode();

            agent.setPin(encodeUserPin(passcode));
            agent.setApprovalStatus(ApprovalEnum.APPROVED);
            auditService.stampAuditedEntity(agent);
            agentRepository.save(agent);

            TUserMeta userMeta = userMetaRepository.findByAgentId(agent.getId()).orElseThrow(() -> new BadRequestException("Failed to retrieve agent additional details"));

            final String smsMessage = smsService.getSMSFromTemplate(getSMSContent(userMeta.getPhoneNumber(),passcode), Sms.Name.AGENT_REGISTERED);
            final String smsMessageMasked =  smsService.getSMSFromTemplate(getSMSContent(userMeta.getPhoneNumber(),Sms.MASK), Sms.Name.AGENT_REGISTERED);

            //TODO This should be asynchronous
            smsService.sendSms(smsMessage,smsMessageMasked,userMeta.getPhoneNumber(),Boolean.FALSE);

        }

        if(status.equals(ApprovalEnum.REJECTED)){
            approval.setStatus(ApprovalEnum.REJECTED);
            agentApprovalRepository.save(approval);

            agent.setApprovalStatus(ApprovalEnum.REJECTED);
            agent.setUsername(agent.getUsername()+"_deleted_"+agent.getId());
            auditService.stampAuditedEntity(agent);

            agentRepository.save(agent);
        }

    }

    @Override
    public void regenerateAgentPassword(Long agentId) {
       final TAgent agent = agentRepository.findById(agentId).orElseThrow(() -> new BadRequestException("Agent with ID %s not found in system",agentId));

        agent.setInitialPasswordReset(Boolean.FALSE);
        agent.setNonLockedPin(Boolean.TRUE);
        agent.setNonDisabled(Boolean.TRUE);
        agent.setPinLastedUpdatedOn(DateTimeUtil.getCurrentUTCTime());

        final String passcode = RealTimeUtil.getFourDigitPasscode();
        agent.setPin(encodeUserPin(passcode));

        auditService.stampAuditedEntity(agent);

        TUserMeta userMeta = userMetaRepository.findByAgentId(agent.getId()).orElseThrow(() -> new BadRequestException("Failed to retrieve agent additional details"));

        final String smsMessage = smsService.getSMSFromTemplate(getSMSContent(userMeta.getPhoneNumber(),passcode), Sms.Name.PIN_RESET);
        final String smsMessageMasked =  smsService.getSMSFromTemplate(getSMSContent(userMeta.getPhoneNumber(),Sms.MASK), Sms.Name.PIN_RESET);

        //TODO This should be asynchronous
        smsService.sendSms(smsMessage,smsMessageMasked,userMeta.getPhoneNumber(),Boolean.FALSE);

    }

    private Map<String,String> getSMSContent(String msisdn, String pin){
        final Map<String,String> placeHolders = new HashMap<>();
        placeHolders.put("MSISDN",msisdn);
        placeHolders.put("PIN",pin);

        return placeHolders;
    }

}
