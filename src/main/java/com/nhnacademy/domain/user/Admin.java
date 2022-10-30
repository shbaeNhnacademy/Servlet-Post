package com.nhnacademy.domain.user;


import lombok.*;

@Data
public class Admin implements User{
    @NonNull
    private String id;
    @NonNull
    private String password;
    @NonNull
    private String name;

    private String profileFileName;
}
