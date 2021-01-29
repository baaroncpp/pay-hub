package com.payhub.mobilemoney.mtn.jpa.entities;

import com.payhub.mobilemoney.mtn.jpa.entities.Transaction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_transaction_approvals", schema = "mtn_mobile_money")
public class TransactionApproval {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @Column(name = "transacitonid", nullable = false)
    private Transaction transaction;

    @Column(name = "approvedon" , nullable = false)
    private Date approvedOn;

    public TransactionApproval() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Date getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(Date approvedOn) {
        this.approvedOn = approvedOn;
    }

}
