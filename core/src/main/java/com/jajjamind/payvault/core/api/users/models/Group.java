package com.jajjamind.payvault.core.api.users.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.commons.utils.Validate;

import java.util.List;

/**
 * @author akena
 * 26/01/2021
 * 13:43
 **/
@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Group {

    private Integer id;
    private List<Role> roles;
    private List<String> roleNames;
    private String note;
    private String name;

    public void validate(){
        Validate.notEmpty(this.name,"Group authority name is required");
        Validate.isTrue(!roleNames.isEmpty(),"Roles to be assigned cannot be empty");
        Validate.notEmpty(note,"A description of the group is required");
    }
}
