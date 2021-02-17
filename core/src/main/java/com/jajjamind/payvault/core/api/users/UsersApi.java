package com.jajjamind.payvault.core.api.users;

import com.jajjamind.payvault.core.BaseApi;
import com.jajjamind.payvault.core.api.users.models.TermsAndConditions;
import com.jajjamind.payvault.core.api.users.models.User;
import com.jajjamind.payvault.core.api.users.models.UserMeta;
import com.jajjamind.payvault.core.jpa.models.RecordList;
import com.jajjamind.payvault.core.service.user.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.QueryMap;

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
    @PostMapping("/approval/{id}/accept")
    public void approveUser(@PathVariable("id") Long userId){
         userService.approveUser(userId);

    }
    @RolesAllowed("ROLE_USER.CHECKER")
    @PostMapping("/approval/{id}/reject")
    public void rejectUser(@PathVariable("id") Long userId){
        userService.rejectUser(userId);

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

    @RolesAllowed("ROLE_ADMIN.READ")
    @PostMapping("/{username}/check")
    public Boolean isUsernameTaken(@PathVariable("username") String username){
        return userService.isUsernameTaken(username);
    }


    /**
     * Important params include
     * sortOrder
     * sortBy
     * showList
     * limit
     * offset
     * requiredColumns
     */

    @ApiImplicitParams(
            value = {@ApiImplicitParam(name = "sortOrder", value = "ASC or DESC. Specify how the search result should be ordered", allowableValues = "DESC,ASC", dataType = "String"),
                    @ApiImplicitParam(name = "sortBy", value = "Column by which the results should be sorted"),
                    @ApiImplicitParam(name = "showList",value = "Indicate if the result of the query should be result. If set to false, only the count of results will be returned",allowableValues = "true,false"),
                    @ApiImplicitParam(name = "limit",value = "The total number of records that can be returned in single query. This is defaulted to 50"),
                    @ApiImplicitParam(name = "offset",value = "The next point in query count that the next results should be fetched from"),
                    @ApiImplicitParam(name = "requireColumns",value = "A comma separated list of columns that should be returned. The name of columns must be exactly as defined in API"),
                    @ApiImplicitParam(name = "filters",value = "Key value pairs that match the name of columns for which the filtering is being done",required = false)
            }
    )
    @GetMapping(value = "/query",produces = APPLICATION_JSON)
    public RecordList queryUsers( @RequestParam MultiValueMap<String,?> requestParams){

        return userService.queryForUsers(requestParams);
    }

}
