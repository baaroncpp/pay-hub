package com.jajjamind.payvault.core.api.users;

import com.jajjamind.payvault.core.api.users.models.Group;
import com.jajjamind.payvault.core.api.users.models.Role;
import com.jajjamind.payvault.core.service.user.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author akena
 * 26/01/2021
 * 13:40
 **/
@RestController
@RequestMapping("/role")
public class RolesAndPermissionsApi {

    @Autowired
    public RolesService rolesService;

    @PostMapping("/group")
    public Group addGroup(Group groupAuthority){
        return rolesService.addGroupAuthority(groupAuthority);
    }

    @GetMapping
    public List<Role> getRolesAssignable(){
        return rolesService.getAllRolesAssignable();
    }

    @GetMapping("/group/{name}")
    public Group getGroupByName(@PathVariable("name") String name){
        return rolesService.getGroupAuthorityByName(name);
    }

    @PostMapping("/assign/{userId}")
    public Role assignRoleToUser(@PathVariable("userId") Long userId,@RequestBody Role role){
        return rolesService.assignRoleToUser(role,userId);
    }

    @PostMapping("group/assign/{userId}")
    public Group assignUserToGroup(@PathVariable("userId") Long userId,@RequestBody Group group){
        return rolesService.assignUserToGroup(group,userId);
    }

    @PostMapping("group/un-assign/{userId}")
    public Void removeUserFromGroup(@PathVariable("userId") Long userId,@RequestBody Group group){
        return rolesService.unAssignUserFromGroup(group,userId);
    }

    @PutMapping("/group")
    public Group updateGroupAuthority(@RequestBody Group group){
        return rolesService.updateGroupAuthority(group);
    }

}
