package com.jajjamind.payvault.core.service.user;

import com.jajjamind.commons.time.DateTimeUtil;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.agent.models.Country;
import com.jajjamind.payvault.core.api.constants.ErrorMessageConstants;
import com.jajjamind.payvault.core.api.users.models.TermsAndConditions;
import com.jajjamind.payvault.core.api.users.models.User;
import com.jajjamind.payvault.core.api.users.models.UserAuthority;
import com.jajjamind.payvault.core.api.users.models.UserMeta;
import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.jpa.models.agent.TCountry;
import com.jajjamind.payvault.core.jpa.models.agent.TTermsAndConditions;
import com.jajjamind.payvault.core.jpa.models.enums.ApprovalEnum;
import com.jajjamind.payvault.core.jpa.models.user.*;
import com.jajjamind.payvault.core.repository.agent.CountryRepository;
import com.jajjamind.payvault.core.repository.agent.TermsAndConditionsRepository;
import com.jajjamind.payvault.core.repository.security.UserRepository;
import com.jajjamind.payvault.core.repository.user.UserApprovalRepository;
import com.jajjamind.payvault.core.repository.user.UserMetaRepository;
import com.jajjamind.payvault.core.repository.user.UserPreviousPasswordRepository;
import com.jajjamind.payvault.core.security.models.LoggedInUser;
import com.jajjamind.payvault.core.utils.AuditService;
import com.jajjamind.payvault.core.utils.BeanUtilsCustom;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author akena
 * 26/01/2021
 * 12:29
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public AuditService auditService;

    @Autowired
    public TermsAndConditionsRepository termsAndConditionsRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public UserMetaRepository userMetaRepository;

    @Autowired
    public CountryRepository countryRepository;

    @Autowired
    public UserApprovalRepository userApprovalRepository;

    @Autowired
    public UserPreviousPasswordRepository previousPasswordRepository;

    @Autowired
    public JooqUserRepository jooqUserRepository;


    @Transactional
    @Override
    public User add(User user) {

        user.validate();
        Optional<TUser> anyUser = userRepository.findByUsername(user.getUsername());
        Validate.isTrue(!anyUser.isPresent(),"User with username %s already exists",user.getUsername());

        Optional<TCountry> country = countryRepository.findByAlpha2Code(user.getUserMeta().getCountryCode().getIsoAlpha2());
        Validate.isTrue(country.isPresent(), ErrorMessageConstants.COUNTRY_PROVIDED_DOES_NOT_FOUND,user.getUserMeta().getCountryCode().getIsoAlpha2());

        final TUser tUser = getTUserFromUserObject(user);
        auditService.stampLongEntity(tUser);

        userRepository.save(tUser);

        Optional<TUser> newUser = userRepository.findByUsername(user.getUsername());
        Validate.isTrue(newUser.isPresent(),"User creation failed");

        final TUser tNewUser = newUser.get();
        final UserMeta requestMeta = user.getUserMeta();

        final TUserMeta tUserMeta = new TUserMeta();


        BeanUtilsCustom.copyProperties(requestMeta, tUserMeta);

        final TTermsAndConditions termsAndConditions = new TTermsAndConditions();
        termsAndConditions.setId(
                user.getTermsOfUse().getId());

        tUserMeta.setTTermsAndConditions(termsAndConditions);
        tUserMeta.setUserId(tNewUser.getId());
        tUserMeta.setCountryCode(country.get());
        tUserMeta.setAgentId(null);

        auditService.stampAuditedEntity(tUserMeta);
        userMetaRepository.save(tUserMeta);

        //Generate an approval object
        final TUserApproval userApproval = new TUserApproval();
        userApproval.setStatus(ApprovalEnum.PENDING);
        userApproval.setUserId(tUser.getId());

        auditService.stampAuditedEntity(userApproval);
        userApprovalRepository.save(userApproval);

        return user;

    }

    private TUser getTUserFromUserObject(User user){
        final TUser tUser = new TUser();
        tUser.setUsername(user.getUsername());
        tUser.setPassword(passwordEncoder.encode(user.getNewPassword()));
        tUser.setAccountExpired(Boolean.FALSE);
        tUser.setAccountLocked(Boolean.FALSE);
        tUser.setCredentialExpired(Boolean.FALSE);
        tUser.setApproved(Boolean.FALSE);
        tUser.setDeleted(Boolean.FALSE);

        return tUser;
    }


    @Override
    public User get(Long id) {

        Optional<TUser> toUser = userRepository.findUserById(id);
        Validate.isTrue(toUser.isPresent(),ErrorMessageConstants.USER_WITH_ID_NOT_FOUND,id);

        return  getUserObjectFromTUser(toUser.get());
    }

    private User getUserObjectFromTUser(TUser tUser) {

        final User user = new User();
        BeanUtils.copyProperties(tUser,user);

        final UserMeta userMeta = new UserMeta();
        BeanUtils.copyProperties(tUser.getUserMeta(),userMeta);

        final Country country = new Country();
        BeanUtils.copyProperties(tUser.getUserMeta().getCountryCode(),country);

        final UserAuthority userAuthority = new UserAuthority();
        BeanUtils.copyProperties(tUser.getUserAuthority(),userAuthority);

        userMeta.setCountryCode(country);
        user.setUserMeta(userMeta);
        user.setUserAuthority(userAuthority);

        return user;
    }

    @Transactional
    @Override
    public User update(User user) {
        user.validate();
        Validate.notNull(user.getId(),"User id to update cannot be null");

        Optional<TUser> userToUpdate = userRepository.findUserById(user.getId());

        Validate.isTrue(userToUpdate.isPresent(),ErrorMessageConstants.USER_WITH_ID_NOT_FOUND,user.getId());

        TUser oldUser = userToUpdate.get();
        updateUserCommonInfo(oldUser,user);

        TUserMeta userMeta = oldUser.getUserMeta();
        BeanUtils.copyProperties(userMeta,user.getUserMeta());

        updateUserMeta(userMeta,oldUser.getUserMeta());

        userRepository.save(oldUser);
        userMetaRepository.save(userMeta);

        return get(user.getId());
    }

    private void updateUserCommonInfo(TUser oldUser, User newUser){
        oldUser.setAccountLocked(newUser.getAccountLocked());
        oldUser.setAccountExpired(newUser.getAccountExpired());
        auditService.stampLongEntity(oldUser);
    }

    private void updateUserMeta(TUserMeta newUserMeta, TUserMeta oldUserMeta){
        newUserMeta.setId(oldUserMeta.getId());
        newUserMeta.setUserId(oldUserMeta.getUserId());
        newUserMeta.setAgentId(oldUserMeta.getAgentId());

        Optional<TCountry> country = countryRepository.findByAlpha2Code(oldUserMeta.getCountryCode().getIsoAlpha2());
        Validate.isTrue(country.isPresent(), ErrorMessageConstants.COUNTRY_PROVIDED_DOES_NOT_FOUND,oldUserMeta.getCountryCode().getIsoAlpha2());
        newUserMeta.setCountryCode(country.get());

        auditService.stampAuditedEntity(newUserMeta);

    }

    @Override
    public User delete(Long id) {

        Optional<TUser> userToUpdate = userRepository.findUserById(id);

        Validate.isTrue(userToUpdate.isPresent(),ErrorMessageConstants.USER_WITH_ID_NOT_FOUND,id);

        TUser user = userToUpdate.get();
        user.setDeleted(Boolean.TRUE);

        auditService.stampLongEntity(user);

        userRepository.save(user);

        return get(id);
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException();
    }

    public List<UserMeta> getAllUsers(){
        List<TUserMeta> users = userMetaRepository.findAllWithUserId();

        List<UserMeta> toReturn = new ArrayList<>();
        users.stream().forEach(t ->{
            UserMeta meta = new UserMeta();
            BeanUtils.copyProperties(t,meta);
            toReturn.add(meta);
        });

        return toReturn;
    }

    @Override
    public List<UserMeta> getUsersForApproval() {
        List<TUserMeta>  pendingApproval = userMetaRepository.findAllWithUsersPendingApproval();

        List<UserMeta> userMetaList = new ArrayList<>();
        pendingApproval.forEach(t -> {
            UserMeta userMeta = new UserMeta();
            BeanUtilsCustom.copyProperties(t,userMeta);

            Country c = new Country();
            BeanUtilsCustom.copyProperties(t.getCountryCode(),c);
            userMeta.setCountryCode(c);

            userMetaList.add(userMeta);
        });

        return userMetaList;
    }

    @Override
    public void rejectUser(Long id) {

        final TUserApproval userApproval = getPendingUserApproval(id);

        final TUser user = getUserForApproval(userApproval.getUserId());

        userApproval.setStatus(ApprovalEnum.REJECTED);
        auditService.stampAuditedEntity(userApproval);

        userApprovalRepository.save(userApproval);

        user.setApproved(Boolean.FALSE);
        user.setDeleted(Boolean.TRUE);
        user.setUsername(user.getUsername()+"_"+user.getId()+"_reject");

        LoggedInUser checker = auditService.getLoggedInUser();
        user.setApprovedBy(checker.getId());

        auditService.stampLongEntity(user);
        userRepository.save(user);

    }

    @Transactional
    @Override
    public void approveUser(Long id) {

        final TUserApproval userApproval = getPendingUserApproval(id);

        final TUser user = getUserForApproval(userApproval.getUserId());

        userApproval.setStatus(ApprovalEnum.APPROVED);
        auditService.stampAuditedEntity(userApproval);

        userApprovalRepository.save(userApproval);

        user.setApproved(Boolean.TRUE);

        LoggedInUser checker = auditService.getLoggedInUser();
        user.setApprovedBy(checker.getId());

        auditService.stampLongEntity(user);
        userRepository.save(user);

    }

    @Transactional
    @Override
    public void resetPassword(String oldPassword, String newPassword) {
        User tuser = new User();
        tuser.validatePassword(newPassword);
        LoggedInUser user = auditService.getLoggedInUser();
        Validate.notNull(user,"Failed to locate user");

        Optional<TUser> userOptional = userRepository.findUserById(user.getId());
        Validate.isPresent(userOptional,"No valid system user found");

        TUser mUser = userOptional.get();
        Validate.isTrue(mUser.isApproved(),"User is not approved for system access");
        Validate.isTrue(!mUser.isAccountExpired(),"User account has expired");
        Validate.isTrue(!mUser.getDeleted(),"Account with does not exist");
        Validate.isTrue(!mUser.isAccountLocked(),"Account has been locked");

        if(passwordEncoder.matches(oldPassword,mUser.getPassword())){
            final String oldPasswordToSave = mUser.getPassword();
            mUser.setPassword(passwordEncoder.encode(newPassword));
            mUser.setInitialPasswordReset(Boolean.TRUE);

            auditService.stampLongEntity(mUser);
            userRepository.save(mUser);

            TUserPreviousPassword previousPassword = new TUserPreviousPassword();
            previousPassword.setPassword(oldPasswordToSave);
            previousPassword.setUser(mUser);
            previousPassword.setNote("Password Reset");
            previousPassword.setRemovalTime(DateTimeUtil.getCurrentUTCTime());

            auditService.stampLongEntity(previousPassword);
            previousPasswordRepository.save(previousPassword);
        }else{
            throw new IllegalStateException("Old password could not be verified");
        }
    }

    @Override
    public RecordList queryForUsers(MultiValueMap map) {
        if(!map.containsKey(JooqUserRepository.FIELD_APPROVED)){
            map.put(JooqUserRepository.FIELD_APPROVED, Arrays.asList("YES"));
        }

        return jooqUserRepository.listAndCount(map);
    }

    @Override
    public Boolean isUsernameTaken(String username) {
        Optional<TUser> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return true;
        }
        return false;
    }


    private TUserApproval getPendingUserApproval(Long id){
        Optional<TUserApproval> approval = userApprovalRepository.findByUserIdForApproval(id);

        Validate.isTrue(approval.isPresent(),"No pending approval found for user");
        final TUserApproval userApproval = approval.get();

        Validate.isTrue(userApproval.getStatus().equals(ApprovalEnum.PENDING),"No pending approval found for user");

        return userApproval;
    }

    private TUser getUserForApproval(Long id){
        Optional<TUser> pendingApproval = userRepository.findUserById(id);
        Validate.isTrue(pendingApproval.isPresent(),"User details with id %s not found",id);

        final TUser user = pendingApproval.get();
        Validate.isTrue(!user.isApproved(),"User is already approved");
        Validate.isTrue(!user.getDeleted(),"User has been deleted already");

        return user;
    }


    @Override
    public TermsAndConditions getTermsOfUse() {
        Optional<com.jajjamind.payvault.core.jpa.models.agent.TTermsAndConditions> termsOfUse = termsAndConditionsRepository.getActiveTermsAndConditionsForUser();
        Validate.isTrue(termsOfUse.isPresent(),"Terms of use could not found");

        com.jajjamind.payvault.core.jpa.models.agent.TTermsAndConditions tt = termsOfUse.get();
        final TermsAndConditions ttf = new TermsAndConditions();
        BeanUtils.copyProperties(tt,ttf);

        return ttf;
    }


}
