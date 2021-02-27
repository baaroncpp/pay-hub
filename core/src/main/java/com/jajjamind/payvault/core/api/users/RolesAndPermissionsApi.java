package com.jajjamind.payvault.core.api.users;

import com.jajjamind.payvault.core.api.users.models.Group;
import com.jajjamind.payvault.core.api.users.models.Role;
import com.jajjamind.payvault.core.api.users.models.RolesAndGroups;
import com.jajjamind.payvault.core.service.user.RolesService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * @author akena
 * 26/01/2021
 * 13:40
 **/
@Tag(name = "Roles",description = "Manage user access permissions in system")
@RolesAllowed({"ROLE_ADMIN.WRITE","ROLE_PERMISSION.WRITE"})
@RestController
@RequestMapping("/role")
public class RolesAndPermissionsApi {

    @Autowired
    public RolesService rolesService;


    @PostMapping("/group")
    public Group addGroup(@RequestBody Group groupAuthority){
        return rolesService.addGroupAuthority(groupAuthority);
    }

    @RolesAllowed("ROLE_PERMISSION.READ")
    @GetMapping("/group")
    public List<Group> getGroups(){
        return rolesService.getAllGroups();
    }

    @RolesAllowed("ROLE_PERMISSION.READ")
    @GetMapping
    public List<Role> getRolesAssignable(){
        return rolesService.getAllRolesAssignable();
    }

    @RolesAllowed("ROLE_PERMISSION.READ")
    @GetMapping("/group/{name}")
    public Group getGroupByName(@PathVariable("name") String name){
        return rolesService.getGroupAuthorityByName(name);
    }

    @RolesAllowed("ROLE_PERMISSION.READ")
    @GetMapping("/user/{username}")
    public RolesAndGroups getUserRolesAndGroups(@PathVariable("username") String name){
        return rolesService.getUserRolesAndGroups(name);
    }

    @PostMapping("/assign/{userId}/{roleName}")
    public Role assignRoleToUser(@PathVariable("userId") Long userId, @PathVariable("roleName") String role){
        return rolesService.assignRoleToUser(role,userId);
    }

    @PostMapping("/un-assign/{userId}/{roleName}")
    public void unAssignRoleToUser(@PathVariable("userId") Long userId, @PathVariable("roleName") String role){
         rolesService.unAssignRoleFromUser(role,userId);
    }


    @PostMapping(value = "group/assign/{userId}/{groupId}",produces = "application/text")
    public void assignUserToGroup(@PathVariable("userId") Long userId,@PathVariable("groupId") Integer groupId){
         rolesService.assignUserToGroup(groupId,userId);
    }

    @PostMapping(value = "group/un-assign/{userId}/{groupId}",produces = "application/text")
    public void removeUserFromGroup(@PathVariable("userId") Long userId,@PathVariable Integer groupId){
        rolesService.unAssignUserFromGroup(groupId,userId);

    }

    @PutMapping("/group")
    public Group updateGroupAuthority(@RequestBody Group group){
        return rolesService.updateGroupAuthority(group);
    }

}
