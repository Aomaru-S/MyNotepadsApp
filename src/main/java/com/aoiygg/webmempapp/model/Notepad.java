package com.aoiygg.webmempapp.model;

import javax.persistence.*;

@Entity
@Table(name = "notepad")
public class Notepad {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String body;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "Notepad{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
