package com.roihunter.facebook;

public interface UserService {

	UserDataResponse saveUserDate(String token, String fbId);

	UserPhotosResponse getUserPhotos(String token, String fbId);
}
