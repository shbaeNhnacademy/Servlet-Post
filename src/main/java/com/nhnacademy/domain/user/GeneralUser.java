package com.nhnacademy.domain.user;

import lombok.Data;
import lombok.NonNull;
import lombok.ToString;

@Data
@ToString(exclude = {"id", "password", "profilePath"})
public class GeneralUser implements User {

    @NonNull
    private String id;
    @NonNull
    private String password;
    @NonNull
    private String name;
    private String profileFileName; // TODO /profile?id=[profileFileName] 이렇게 쓰이나?
    private String profilePath;

}
