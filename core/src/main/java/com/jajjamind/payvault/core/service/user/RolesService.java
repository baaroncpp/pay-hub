package com.jajjamind.payvault.core.service.user;

import com.jajjamind.payvault.core.api.users.models.Group;
import com.jajjamind.payvault.core.api.users.models.Role;
import com.jajjamind.payvault.core.api.users.models.RolesAndGroups;

import java.util.List;

/**
 * @author akena
 * 26/01/2021
 * 13:48
 **/
public interface RolesService {

    Group addGroupAuthority(Group groupAuthority);
    Group getGroupAuthorityByName(String name);
    List<Role> getAllRolesAssignable();
    Role assignRoleToUser(String roleName,Long userId);
    void unAssignRoleFromUser(String roleName,Long userId);
    Group updateGroupAuthority(Group groupAuthority);
    void assignUserToGroup(Integer group,Long userId);
    void unAssignUserFromGroup(Integer group, Long userId);
    List<Group> getAllGroups();
    RolesAndGroups getUserRolesAndGroups(String username);

}
