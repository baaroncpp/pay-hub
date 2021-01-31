package com.jajjamind.payvault.core.api.users;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.users.models.TermsAndConditions;
import com.jajjamind.payvault.core.api.users.models.User;
import com.jajjamind.payvault.core.api.users.models.UserMeta;
import com.jajjamind.payvault.core.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author akena
 * 18/01/2021
 * 03:37
 **/
@RestController
@RequestMapping("/users")
public class UsersApi implements BaseApi<User> {

    @Autowired
    public UserService userService;

    @Override
    public User add(User user) {

        return userService.add(user);
    }

    @Override
    public User get(Long id) {

        return userService.get(id);
    }

    @Override
    public User update(User user) {
        return userService.update(user);
    }

    @Override
    public User delete(long id) {
        return userService.delete(id);
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException();
    }

    @GetMapping(value = "/users/meta/all",produces = APPLICATION_JSON)
    public List<UserMeta> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/termsofuse")
    public TermsAndConditions getTermsOfUse(){
        return userService.getTermsOfUse();
    }

    @PostMapping("/approval/accept")
    public Boolean approveUser(Long approvalId){
        return userService.approveUser(approvalId);

    }

    @PostMapping("/approval/reject")
    public Boolean rejectUser(Long approvalId){
        return userService.rejectUser(approvalId);

    }

    @GetMapping("/approval")
    public List<UserMeta> getUsersForApproval(){
        return userService.getUsersForApproval();
    }

}
