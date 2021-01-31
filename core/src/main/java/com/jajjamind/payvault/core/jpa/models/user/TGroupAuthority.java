package com.jajjamind.payvault.core.jpa.models.user;

import javax.persistence.*;

/**
 * @author akena
 * 25/11/2020
 * 11:12
 **/
@Entity
@Table(name = "t_group_authority",schema = "core")
public class TGroupAuthority  {
    private Integer id;
    private TGroup group;
    private String authority;

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    @JoinColumn(name = "group_id",insertable = true,updatable = false)
    @OneToOne
    public TGroup getGroup() {
        return group;
    }

    public void setGroup(TGroup group) {
        this.group = group;
    }

    @Column(name = "authority")
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
