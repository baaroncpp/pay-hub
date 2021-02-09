package com.jajjamind.payvault.core.api.users;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.users.models.TermsAndConditions;
import com.jajjamind.payvault.core.api.users.models.User;
import com.jajjamind.payvault.core.api.users.models.UserMeta;
import com.jajjamind.payvault.core.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author akena
 * 18/01/2021
 * 03:37
 **/
@Tag(name = "Users",description = "Create system users for PayHub portal")
@RestController
@RequestMapping("/users")
public class UsersApi implements BaseApi<User> {

    @Autowired
    public UserService userService;

    @RolesAllowed("ROLE_USER.WRITE")
    @Override
    public User add(@RequestBody User user) {

        return userService.add(user);
    }

    @RolesAllowed("ROLE_USER.READ")
    @Override
    public User get(@PathVariable("id") Long id) {

        return userService.get(id);
    }

    @RolesAllowed("ROLE_USER.WRITE")
    @Override
    public User update(@RequestBody User user) {
        return userService.update(user);
    }

    @RolesAllowed("ROLE_USER.WRITE")
    @Override
    public User delete(long id) {
        return userService.delete(id);
    }

    @Override
    public List<User> getAll() {
        throw new UnsupportedOperationException();
    }

    @RolesAllowed({"ROLE_USER.WRITE","ROLE_USER.READ"})
    @GetMapping(value = "/users/meta/all",produces = APPLICATION_JSON)
    public List<UserMeta> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/termsofuse")
    public TermsAndConditions getTermsOfUse(){
        return userService.getTermsOfUse();
    }

    @RolesAllowed("ROLE_USER.CHECKER")
    @PostMapping("/approval/accept")
    public Boolean approveUser(Long approvalId){
        return userService.approveUser(approvalId);

    }
    @RolesAllowed("ROLE_USER.CHECKER")
    @PostMapping("/approval/reject")
    public Boolean rejectUser(Long approvalId){
        return userService.rejectUser(approvalId);

    }
    @RolesAllowed("ROLE_USER.CHECKER")
    @GetMapping("/approval")
    public List<UserMeta> getUsersForApproval(){
        return userService.getUsersForApproval();
    }

    @PostMapping("/password/reset")
    public void resetPassword(@RequestParam("oldPassword") String oldPassword,@RequestParam("newPassword") String newPassword){
        userService.resetPassword(oldPassword,newPassword);
    }

}
