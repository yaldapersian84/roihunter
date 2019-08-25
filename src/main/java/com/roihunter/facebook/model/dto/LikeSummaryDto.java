package com.roihunter.facebook.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class LikeSummaryDto {

    @JsonProperty("total_count")
    private int totalCount;

    @JsonProperty("can_like")
    private boolean canLike;

    @JsonProperty("has_liked")
    private boolean hasLiked;

}
