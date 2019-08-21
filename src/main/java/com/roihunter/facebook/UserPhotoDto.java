package com.roihunter.facebook;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
public class UserPhotoDto {

    private String id;

    private String name;

    private String link;

    private AlbumDto album;

    @JsonProperty("created_time")
    private String createdTime;

    private LikesDto likes;

    private List<ImageDto> images;
}
