package com.nhnacademy.domain.user;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString(exclude = {"id", "password"})
public class GeneralUser implements User {

    @NonNull
    private String id;
    @NonNull
    private String password;
    @NonNull
    private String name;
    private String profileFileName = "";

}
