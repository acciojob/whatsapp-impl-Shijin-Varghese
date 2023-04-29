package com.driver;

import lombok.AllArgsConstructor;

import java.util.Date;
@AllArgsConstructor
public class Message {
    private int id;
    private String content;
    private Date timestamp;

    public Message(int id, String content, Date timestamp) {
        this.id = id;
        this.content = content;
        this.timestamp = timestamp;
    }
}
