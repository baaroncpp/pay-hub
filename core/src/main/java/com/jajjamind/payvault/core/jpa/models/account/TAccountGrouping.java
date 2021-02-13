package com.jajjamind.payvault.core.jpa.models.account;

import com.jajjamind.payvault.core.jpa.models.AuditedEntity;
import com.jajjamind.payvault.core.jpa.models.enums.AccountTypeEnum;

import javax.persistence.*;

/**
 * @author akena
 * 13/12/2020
 * 13:28
 **/
@Entity
@Table(name = "t_account_grouping",schema = "core")
public class TAccountGrouping  extends AuditedEntity {

    private String name;
    private String note;
    private AccountTypeEnum groupType;
    private boolean canBulkLiquidate;

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "can_bulk_liquidate")
    public boolean isCanBulkLiquidate() {
        return canBulkLiquidate;
    }

    public void setCanBulkLiquidate(boolean canBulkLiquidate) {
        this.canBulkLiquidate = canBulkLiquidate;
    }

    @Column(name = "group_type")
    @Enumerated(EnumType.STRING)
    public AccountTypeEnum getGroupType() {
        return groupType;
    }

    public void setGroupType(AccountTypeEnum groupType) {
        this.groupType = groupType;
    }
}
