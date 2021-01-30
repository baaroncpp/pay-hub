package com.jajjamind.payvault.core.service.user;

import com.jajjamind.payvault.core.api.users.models.Group;
import com.jajjamind.payvault.core.api.users.models.Role;

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
    Role assignRoleToUser(Role role,Long userId);
    Group updateGroupAuthority(Group groupAuthority);
    Group assignUserToGroup(Group group,Long userId);
    Void unAssignUserFromGroup(Group group, Long userId);
}
