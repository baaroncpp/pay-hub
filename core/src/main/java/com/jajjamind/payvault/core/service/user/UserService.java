package com.jajjamind.payvault.core.service.user;

import com.jajjamind.payvault.core.api.users.models.TermsAndConditions;
import com.jajjamind.payvault.core.api.users.models.User;
import com.jajjamind.payvault.core.api.users.models.UserMeta;
import com.jajjamind.payvault.core.service.BaseApiService;

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
    Boolean rejectUser(Long id);
    Boolean approveUser(Long id);

}
