package com.roihunter.facebook;

public interface UserService {

	UserDataResponse saveUserData(String token, String fbId);

	UserPhotosResponse getUserPhotos(String token, String fbId, int size);
}
