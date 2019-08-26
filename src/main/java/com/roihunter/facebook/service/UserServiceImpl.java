package com.roihunter.facebook.service;

import com.roihunter.facebook.client.FacebookClient;
import com.roihunter.facebook.dao.UserInfoDao;
import com.roihunter.facebook.dao.UserPhotoDao;
import com.roihunter.facebook.exception.GeneralException;
import com.roihunter.facebook.model.UserInfo;
import com.roihunter.facebook.model.dto.UserPhotoDto;
import com.roihunter.facebook.model.dto.UserPhotoResponseDto;
import com.roihunter.facebook.model.response.ResultStatus;
import com.roihunter.facebook.model.response.UserDataResponse;
import com.roihunter.facebook.model.response.UserInfoResponse;
import com.roihunter.facebook.model.response.UserPhotosResponse;
import com.roihunter.facebook.transformer.FacebookBeanMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

	private FacebookClient facebookClient;

	private final UserInfoDao userInfoDao;

	private final UserPhotoDao userPhotoDao;

	private final FacebookBeanMapper mapper;


	public UserServiceImpl(FacebookClient facebookClient, final UserInfoDao userInfoDao, final FacebookBeanMapper mapper,
						   UserPhotoDao userPhotoDao) {
		this.facebookClient = facebookClient;
		this.userInfoDao = userInfoDao;
		this.userPhotoDao = userPhotoDao;
		this.mapper = mapper;
	}

	@Transactional
	@Override
	public void saveUserData(String token, String fbId, int size) {

		UserDataResponse userDataResponse = facebookClient.getData(fbId, token);

		UserInfo userInfo = mapper.toUserInfo(userDataResponse);
		if (userInfoDao.findByFbId(fbId).isPresent()) {
			userInfo.setId(userInfoDao.findByFbId(fbId).get().getId());
			userPhotoDao.deleteByUserInfoFbId(fbId);
		}

		userInfo = userInfoDao.save(userInfo);

		UserPhotosResponse userPhotosResponse = facebookClient.getPhotos(fbId, token, size);

		for (UserPhotoDto photo : userPhotosResponse.getData())

			userInfo.getPhotos().add(mapper.toUserPhoto(photo, userInfo.getId()));

		userInfoDao.save(userInfo);
	}

	@Override
	public void deleteUser(String fbId) throws GeneralException {
		userInfoDao.delete(this.findByFbId(fbId));
	}

	@Override
	public UserInfo findByFbId(String fbId) throws GeneralException {
		return userInfoDao.findByFbId(fbId).orElseThrow(() ->
				new GeneralException(ResultStatus.USER_NOT_FOUND, "user not found"));
	}

	@Override
	public UserInfoResponse getUserInfo(String fbId) throws GeneralException {

		return mapper.toUserInfoResponse(this.findByFbId(fbId));
	}

	@Override
	public List<UserPhotoResponseDto> getUserPhoto(String fbId) throws GeneralException {
		return mapper.toListUserPhotos(this.findByFbId(fbId).getPhotos());
	}

}
