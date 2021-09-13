package com.aoiygg.webmempapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "auth_email")
public class AuthMailAddress {

    @Id
    private String uuid;
    @Column(name = "mail_address")
    private String mailAddress;

    public AuthMailAddress() {}

    public AuthMailAddress(String uuid, String mailAddress) {
        this.uuid = uuid;
        this.mailAddress = mailAddress;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
