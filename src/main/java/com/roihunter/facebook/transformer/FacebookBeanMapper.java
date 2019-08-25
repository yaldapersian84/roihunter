package com.roihunter.facebook.transformer;

import com.roihunter.facebook.model.UserInfo;
import com.roihunter.facebook.model.UserPhoto;
import com.roihunter.facebook.model.dto.ImageDto;
import com.roihunter.facebook.model.dto.UserPhotoDto;
import com.roihunter.facebook.model.dto.UserPhotoResponseDto;
import com.roihunter.facebook.model.response.UserDataResponse;
import com.roihunter.facebook.model.response.UserInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

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
            @Mapping(target = "images", source = "response.images"),

    })
    UserPhoto toUserPhoto(UserPhotoDto response, Long userInfoId);

    default String imageDtoToString(ImageDto imageDto) {
        return imageDto.getSource();
    }
    UserInfoResponse toUserInfoResponse(UserInfo info);

    List<UserPhotoResponseDto> toListUserPhotos(Set<UserPhoto> photos);

}
