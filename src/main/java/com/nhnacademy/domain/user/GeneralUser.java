package com.nhnacademy.domain.user;

import lombok.Data;
import lombok.NonNull;

@Data
public class GeneralUser implements User{

    @NonNull
    private String id;
    @NonNull
    private String password;
    @NonNull
    private String name;
    private String profileFileName; // TODO /profile?id=[profileFileName] 이렇게 쓰이나?

}
