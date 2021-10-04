package com.aoiygg.webmempapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "notepad")
public class Notepad {

    @Id
    @Column(name = "notepad_id")
    @GeneratedValue
    private long notepadId;

    private String title;

    private String body;

    @Column(name = "mail_address")
    private String mailAddress;
}
