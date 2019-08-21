package com.roihunter.facebook;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private FacebookClient facebookClient;

    private final UserInfoDao userInfoDao;

    private final UserPhotoDao userPhotoDao;

    private final FacebookBeanMapper mapper;


    public UserServiceImpl(FacebookClient facebookClient, final UserInfoDao userInfoDao,
                           final UserPhotoDao userPhotoDao, final FacebookBeanMapper mapper) {
        this.facebookClient = facebookClient;
        this.userPhotoDao = userPhotoDao;
        this.userInfoDao = userInfoDao;
        this.mapper = mapper;
    }

    @Override
    public UserDataResponse saveUserData(String token, String fbId) {


        UserDataResponse response = facebookClient.getData(fbId, token);

        userInfoDao.save(mapper.toUserInfo(response));

        return response;
//		return null;

    }

    @Override
    public UserPhotosResponse getUserPhotos(String token, String fbId, int size) {
        UserDataResponse response = facebookClient.getData(fbId, token);

        UserInfo userInfo =userInfoDao.save(mapper.toUserInfo(response));

        UserPhotosResponse response2 = facebookClient.getPhotos(fbId, token, size);

        response2.getData().stream().forEach(photo -> {
            userPhotoDao.save(mapper.toUserPhoto(photo, userInfo.getId()));
        });

//        userDao.save(mapper.toUserInfo(response));

        return response2;
    }

}
