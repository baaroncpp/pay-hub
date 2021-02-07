package com.jajjamind.payvault.core.api.users.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.payvault.core.jpa.models.enums.ApprovalEnum;
import lombok.Data;

/**
 * @author akena
 * 02/02/2021
 * 01:06
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Approval {

    private Long id;
    private String note;
    private ApprovalEnum status;

}
