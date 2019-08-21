package com.roihunter.facebook;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface FacebookBeanMapper {

    @Mappings({
            @Mapping(target = "name", source = "response.name"),
            @Mapping(target = "gender", source = "response.gender"),
            @Mapping(target = "profilePic", source = "response.picture.data.url"),
            @Mapping(target = "fbId", source = "response.id"),

    })
    UserInfo toUserInfo(UserDataResponse response);

    @Mappings({
            @Mapping(target = "name", source = "response.name"),
            @Mapping(target = "fbId", source = "response.id"),
            @Mapping(target = "createdTime", source = "response.createdTime"),
            @Mapping(target = "albumName", source = "response.album.name"),
            @Mapping(target = "link", source = "response.link"),
            @Mapping(target = "likes", source = "response.likes.summary.totalCount"),
            @Mapping(target = "userInfo.id", source = "userInfoId"),

    })
    UserPhoto toUserPhoto(UserPhotoDto response, Long userInfoId);




}
