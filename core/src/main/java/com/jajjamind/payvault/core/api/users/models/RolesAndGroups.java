package com.jajjamind.payvault.core.api.users.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

/**
 * @author akena
 * 24/02/2021
 * 11:36
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@lombok.Data
public class RolesAndGroups {
    private List<Role> roles;
    private List<Group> groups;
}
