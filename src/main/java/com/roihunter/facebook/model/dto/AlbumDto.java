package com.roihunter.facebook.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class AlbumDto {

    private String id;

    private String name;

    @JsonProperty("created_time")
    private String createdTime;

}
