package com.jajjamind.payvault.core.jpa.models.product;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author akena
 * 15/02/2021
 * 14:02
 **/
@Table(name = "t_provider",schema = "core")
@Entity
public class TProvider implements Serializable {

    private String id;
    private String name;
    private String note;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
