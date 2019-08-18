package com.roihunter.facebook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	private FacebookClient facebookClient;

	private final UserDao userDao;


	public UserServiceImpl(FacebookClient facebookClient, final UserDao userDao) {
		this.facebookClient = facebookClient;
		this.userDao = userDao;
	}

	@Override
	public UserDataResponse saveUserDate(String token, String fbId) {

		UserDataResponse response = facebookClient.getData(fbId, token);
		UserInfo userInfo = new UserInfo();
		userInfo.setFbId(response.getId());
		userInfo.setName(response.getName());
		userInfo.setGender(response.getGender());

		userInfo.setProfilePic(response.getPicture().getData().getUrl());

		userDao.save(userInfo);

		return response;

	}

	@Override
	public UserPhotosResponse getUserPhotos(String token, String fbId) {
		return facebookClient.getPhotos(fbId, token);
	}

}
