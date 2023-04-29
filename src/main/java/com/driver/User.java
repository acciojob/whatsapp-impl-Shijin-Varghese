package com.driver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String name;
    private String mobile;
    public User(String name, String mobile) {
        this.name = name;
        this.mobile = mobile;
    }

    public User(String name) {
        this.name = name;
    }

}
