package com.roihunter.facebook.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;


@Setter
@Getter
@ToString
public class UserPhotoResponseDto implements Serializable {

    private String name;

    private String createdTime;

    private String albumName;

    private String link;

    private int likes;

    private List<String> images;


}
