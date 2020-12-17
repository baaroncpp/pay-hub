package com.jajjamind.payvault.core.jpa.models.otp;

import com.jajjamind.payvault.core.jpa.models.BaseEntityLong;
import com.jajjamind.payvault.core.jpa.models.agent.TAgent;
import com.jajjamind.payvault.core.jpa.models.enums.OtpStatusEnum;
import com.jajjamind.payvault.core.jpa.models.enums.OtpTypeEnum;
import com.jajjamind.payvault.core.jpa.models.user.TUser;

import javax.persistence.*;

/**
 * @author akena
 * 13/12/2020
 * 12:32
 **/
@Entity
@Table(name = "t_otp",schema = "core")
public class TOtp extends BaseEntityLong {

    private OtpTypeEnum type;
    private OtpStatusEnum status;
    private String code;
    private TUser user;
    private TAgent agent;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    public OtpTypeEnum getType() {
        return type;
    }


    public void setType(OtpTypeEnum type) {
        this.type = type;
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    public OtpStatusEnum getStatus() {
        return status;
    }

    public void setStatus(OtpStatusEnum status) {
        this.status = status;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @JoinColumn(name = "user_id",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }

    @JoinColumn(name = "agent_id",referencedColumnName = "id",insertable = false,updatable = false)
    @OneToOne(fetch = FetchType.LAZY)
    public TAgent getAgent() {
        return agent;
    }

    public void setAgent(TAgent agent) {
        this.agent = agent;
    }
}
