package com.jajjamind.payvault.core.service.user;

import com.jajjamind.payvault.core.api.users.models.TermsAndConditions;
import com.jajjamind.payvault.core.api.users.models.User;
import com.jajjamind.payvault.core.api.users.models.UserMeta;
import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.jpa.models.user.JooqUserRepository;
import com.jajjamind.payvault.core.service.BaseApiService;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * @author akena
 * 26/01/2021
 * 12:29
 **/
public interface UserService extends BaseApiService<User> {

    TermsAndConditions getTermsOfUse();
    List<UserMeta> getAllUsers();
    List<UserMeta> getUsersForApproval();
    void rejectUser(Long id);
    void approveUser(Long id);
    void resetPassword(String oldPassword, String newPassword);
    RecordList queryForUsers(MultiValueMap map);
    Boolean isUsernameTaken(String username);
}
