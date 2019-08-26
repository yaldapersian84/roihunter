package com.roihunter.facebook.service;

import com.roihunter.facebook.model.UserInfo;
import com.roihunter.facebook.exception.GeneralException;
import com.roihunter.facebook.model.dto.UserPhotoResponseDto;
import com.roihunter.facebook.model.response.UserInfoResponse;

import java.util.List;

public interface UserService {
    UserInfo findByFbId(String fbId)  throws GeneralException;

    UserInfoResponse getUserInfo(String fbId) throws GeneralException;

    List<UserPhotoResponseDto> getUserPhoto(String fbId) throws GeneralException;

    void saveUserData(String token, String fbId, int size);

    void deleteUser(String fbId) throws GeneralException;
}
