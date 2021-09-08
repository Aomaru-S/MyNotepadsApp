package com.aoiygg.webmempapp.model;

import javax.persistence.*;

@Entity
@Table(name = "notepad")
public class Notepad {

    @Id
    @Column (name = "notepad_id")
    @GeneratedValue
    private long notepadId;
    private String title;
    private String body;
    @Column (name = "mail_address")
    private String mailAddress;

    public long getNotepadId() {
        return notepadId;
    }

    public void setNotepadId(long notepadId) {
        this.notepadId = notepadId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @Override
    public String toString() {
        return "Notepad{" +
                "notepadId=" + notepadId +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", mailAddress='" + mailAddress + '\'' +
                '}';
    }
}
