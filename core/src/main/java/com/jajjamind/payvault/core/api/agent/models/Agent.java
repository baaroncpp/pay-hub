package com.jajjamind.payvault.core.api.agent.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jajjamind.commons.utils.Validate;
import com.jajjamind.payvault.core.api.users.models.TermsAndConditions;
import com.jajjamind.payvault.core.api.users.models.UserMeta;
import com.jajjamind.payvault.core.jpa.models.enums.AgentTypeEnum;
import com.jajjamind.payvault.core.jpa.models.enums.ApprovalEnum;
import com.jajjamind.payvault.core.jpa.models.enums.StatusEnum;

import java.util.Date;

/**
 * @author akena
 * 18/01/2021
 * 03:34
 **/
@lombok.Data
@JsonIgnoreProperties(value = "pin",ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Agent {

    private Long id;
    private AgentTypeEnum type;
    private String approvedBy;
    private String externalId;
    private String pin;
    private String username;
    private Date activatedOn;
    private Boolean nonLocked;
    private Boolean nonDisabled;
    private Boolean nonLockedPin;
    private Date pinLastUpdatedOn;
    private String lastPinLockReason;
    private Company company;
    private UserMeta userMeta;
    private Agent enrolledBy;
    private TermsAndConditions termsAndConditions;
    private Date reactivatedOn;
    private StatusEnum status;
    private ApprovalEnum approvalStatus;
    private Date createdOn;
    private Date modifiedOn;
    private String createdBy;
    private String modifiedBy;

    public void validate(){
        userMeta.validate();
        Validate.notNull(company,"Agent must belong to a company");
        Validate.notNull(username,"Username is required");
        Validate.notNull(termsAndConditions, "Terms of use is required");

    }
}
