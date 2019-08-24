package com.roihunter.facebook;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
public class UserInfoResponse implements Serializable {

    private String name;

    private String gender;

    private String profilePic;

    private String fbId;

}
