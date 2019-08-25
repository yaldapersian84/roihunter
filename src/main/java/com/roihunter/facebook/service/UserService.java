package com.roihunter.facebook.service;

import com.roihunter.facebook.model.UserInfo;
import com.roihunter.facebook.exception.PspGeneralException;
import com.roihunter.facebook.model.dto.UserPhotoResponseDto;
import com.roihunter.facebook.model.response.UserInfoResponse;

import java.util.List;

public interface UserService {
    UserInfo findByFbId(String fbId)  throws PspGeneralException;

    UserInfoResponse getUserInfo(String fbId) throws PspGeneralException;

    List<UserPhotoResponseDto> getUserPhoto(String fbId) throws PspGeneralException;

    void saveUserData(String token, String fbId, int size);

    void deleteUser(String fbId) throws PspGeneralException;
}
