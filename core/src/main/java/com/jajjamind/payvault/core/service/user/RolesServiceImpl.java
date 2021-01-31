package com.jajjamind.payvault.core.service.user;

import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.users.models.Group;
import com.jajjamind.payvault.core.api.users.models.Role;
import com.jajjamind.payvault.core.jpa.models.user.*;
import com.jajjamind.payvault.core.repository.security.UserRepository;
import com.jajjamind.payvault.core.repository.user.*;
import com.jajjamind.payvault.core.utils.AuditService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author akena
 * 26/01/2021
 * 13:49
 **/
@Service
public class RolesServiceImpl implements RolesService{

    @Autowired
    public GroupRepository groupRepository;

    @Autowired
    public RoleRepository roleRepository;

    @Autowired
    public GroupAuthorityRepository groupAuthorityRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserAuthorityRepository userAuthorityRepository;

    @Autowired
    public GroupMemberRepository groupMemberRepository;

    @Autowired
    public AuditService auditService;

    @Transactional
    @Override
    public Group addGroupAuthority(Group groupAuthority) {
        groupAuthority.validate();

        //CheckThatNog group exists with name
        Optional<TGroup> existing = groupRepository.findByName(groupAuthority.getName());
        Validate.isTrue(existing.isPresent(),"Group with name %s",groupAuthority.getName());

        //Check that roles exist
        final List<String> roles = groupAuthority.getRoleNames().stream().distinct().collect(Collectors.toList());

        validateThatRolesToAddExist(roles);

        //Create the group
        final TGroup group = new TGroup();
        group.setName(groupAuthority.getName());
        group.setNote(groupAuthority.getNote());

        groupRepository.save(group);

        Optional<TGroup> createdGroup = groupRepository.findByName(groupAuthority.getName());
        Validate.isTrue(createdGroup.isPresent(),"Group with name %s already exists",groupAuthority.getName());


        final TGroupAuthority tGroupAuthority = new TGroupAuthority();
        tGroupAuthority.setAuthority(roles.stream().reduce(",",String::concat));
        tGroupAuthority.setGroup(createdGroup.get());

        groupAuthorityRepository.save(tGroupAuthority);

        return groupAuthority;
    }

    @Override
    public Group getGroupAuthorityByName(String name) {
        Optional<TGroup> groupAuthority = groupRepository.findByName(name);
        Validate.isTrue(groupAuthority.isPresent(),"Group with name %s not found",name);

        TGroup group = groupAuthority.get();
        return getGroupFromTGroup(group);
    }

    @Override
    public List<Role> getAllRolesAssignable() {
        Iterable<TRole> tRole =roleRepository.findAll();
        List<Role> role = new ArrayList<>();

        tRole.forEach(t -> {
            final Role r = new Role();
            BeanUtils.copyProperties(t,r);
            role.add(r);
        });

        return role;
    }

    @Override
    public Role assignRoleToUser(Role role, Long userId) {

        Optional<TUser> userOptional = userRepository.findById(userId);
        Validate.isTrue(userOptional.isPresent(),"User with id %id not found",userId);

        final TUser user = userOptional.get();
        validateUserDetailsForRoleAssignment(user);

        Optional<TRole> roleOptional = roleRepository.findByName(role.getName());
        Validate.isTrue(roleOptional.isPresent(),"Role with name %s does not exist",role.getName());

        final TUserAuthority authority = user.getUserAuthority();
        final List<String> listOfAuthorities = Arrays.asList(authority.getAuthority().split(","));

        Validate.isTrue(!listOfAuthorities.contains(role.getName()),"User already assigned role %s ",role.getName());

        authority.setAuthority(authority.getAuthority().concat(",").concat(role.getName()));
        userAuthorityRepository.save(authority);

        return role;
    }

    @Transactional
    @Override
    public Group updateGroupAuthority(Group groupAuthority) {

        groupAuthority.validate();
        Validate.notNull(groupAuthority.getId(),"Group to update must have a ID");

        Optional<TGroup> existingGroup = groupRepository.findById(groupAuthority.getId());
        Validate.isTrue(existingGroup.isPresent(),"Group with ID %s could not be found",groupAuthority.getId());

        final TGroup group = existingGroup.get();

        final List<String> roles = groupAuthority.getRoleNames().stream().distinct().collect(Collectors.toList());

        validateThatRolesToAddExist(roles);

        final TGroupAuthority authority = group.getGroupAuthority();
        authority.setAuthority(roles.stream().reduce(",",String::concat));

        groupAuthorityRepository.save(authority);

        if(!groupAuthority.getName().equalsIgnoreCase(group.getName())){

            Optional<TGroup> createdGroup = groupRepository.findByName(groupAuthority.getName());
            Validate.isTrue(createdGroup.isPresent(),"Group with name %s already exists",groupAuthority.getName());
        }else{
            group.setName(groupAuthority.getName());
        }

        group.setNote(groupAuthority.getNote());

        auditService.stampIntegerEntity(group);

        groupRepository.save(group);

        return groupAuthority;
    }

    @Override
    public Group assignUserToGroup(Group group, Long userId) {

        Validate.notNull(group.getId(),"Group ID is required");
        Validate.notNull(userId,"User id is required");

        Optional<TGroup> groupOptional = groupRepository.findById(group.getId());
        Validate.isTrue(groupOptional.isPresent(),"Group with ID %s does not exist",group.getId());

        Optional<TUser> userOptional = userRepository.findById(userId);
        Validate.isTrue(userOptional.isPresent(),"User with ID %s not found",userId);

        final TUser user = userOptional.get();
        validateUserDetailsForRoleAssignment(user);

        Optional<TGroupMember> groupMember = groupMemberRepository.findByUsernameAndGroup(user.getUsername(),groupOptional.get().getId());
        Validate.isTrue(!groupMember.isPresent(),"User with ID %s is already assigned to group %s",userId,groupOptional.get().getId());

        final TGroupMember member = new TGroupMember();
        member.setGroupId(groupOptional.get().getId());
        member.setUsername(user.getUsername());

        auditService.stampIntegerEntity(member);

        groupMemberRepository.save(member);

        return group;
    }

    @Override
    public Void unAssignUserFromGroup(Group group, Long userId) {
        Optional<TUser> userOptional = userRepository.findById(userId);
        Validate.isTrue(userOptional.isPresent(),"User with ID %s not found",userId);

        final TUser user = userOptional.get();

        Optional<TGroupMember> groupMember = groupMemberRepository.findByUsernameAndGroup(user.getUsername(),group.getId());
        Validate.isTrue(groupMember.isPresent(),"User is not assigned to group with ID %s ",group.getId());

        groupMemberRepository.delete(groupMember.get());

        return null;
    }

    private Group getGroupFromTGroup(TGroup tGroup){
        final Group group = new Group();

        group.setId(tGroup.getId());
        group.setName(tGroup.getName());
        group.setNote(tGroup.getNote());

        List<TRole> roles = roleRepository.getCountOfRolesThatMatchList(Arrays.asList(tGroup.getGroupAuthority().getAuthority().split(",")));
        List<Role> list = new ArrayList<>();
        roles.stream().forEach(t -> {
            Role role = new Role();
            BeanUtils.copyProperties(t,role);
            list.add(role);
        });

        group.setRoles(list);

        return group;

    }

    private void validateUserDetailsForRoleAssignment(TUser user){
        Validate.isTrue(!user.isAccountExpired(),"User account has expired, role cannot be assigned");
        Validate.isTrue(!user.isAccountLocked(),"User account is locked, role cannot be assigned");
    }

    private void validateThatRolesToAddExist(List<String> roles){
        final List<TRole> rolesFound = roleRepository.getCountOfRolesThatMatchList(roles);
        final List<TRole> notFound = rolesFound.stream().filter(t -> !roles.contains(t.getName()))
                .collect(Collectors.toList());

        Validate.isTrue(notFound.isEmpty(),"Roles %s not found", notFound.stream().map(TRole::getName).reduce(",",String::concat));
    }
}
