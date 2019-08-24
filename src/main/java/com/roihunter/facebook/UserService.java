package com.roihunter.facebook;

import com.roihunter.facebook.exception.PspGeneralException;

import java.util.List;

public interface UserService {
    UserInfo findByFbId(String fbId)  throws PspGeneralException;

    UserInfoResponse getUserInfo(String fbId) throws PspGeneralException;

    List<UserPhotoResponseDto> getUserPhoto(String fbId) throws PspGeneralException;

    void saveUserData(String token, String fbId, int size);

    void deleteUser(String fbId) throws PspGeneralException;
}
