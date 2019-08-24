package com.roihunter.facebook;

import com.roihunter.facebook.exception.PspGeneralException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private FacebookClient facebookClient;

    private final UserInfoDao userInfoDao;

    private final FacebookBeanMapper mapper;


    public UserServiceImpl(FacebookClient facebookClient, final UserInfoDao userInfoDao, final FacebookBeanMapper mapper) {
        this.facebookClient = facebookClient;
        this.userInfoDao = userInfoDao;
        this.mapper = mapper;
    }

    @Override
    public void saveUserData(String token, String fbId, int size) {
        UserDataResponse userDataResponse = facebookClient.getData(fbId, token);

        UserInfo userInfo = userInfoDao.save(mapper.toUserInfo(userDataResponse));

        UserPhotosResponse userPhotosResponse = facebookClient.getPhotos(fbId, token, size);

        userPhotosResponse.getData().stream().forEach(photo -> {
            userInfo.getPhotos().add(mapper.toUserPhoto(photo, userInfo.getId()));
        });
        userInfoDao.save(userInfo);
    }

    @Override
    public void deleteUser(String fbId) throws PspGeneralException {
        userInfoDao.delete(this.findByFbId(fbId));
    }

    @Override
    public UserInfo findByFbId(String fbId) throws PspGeneralException {
        return userInfoDao.findByFbId(fbId).orElseThrow(() ->
                new PspGeneralException(ResultStatus.BAD_REQUEST, "efss"));
    }

    @Override
    public UserInfoResponse getUserInfo(String fbId) throws PspGeneralException {

        return mapper.toUserInfoResponse(this.findByFbId(fbId));
    }

    @Override
    public List<UserPhotoResponseDto> getUserPhoto(String fbId) throws PspGeneralException {
        return mapper.toListUserPhotos(this.findByFbId(fbId).getPhotos());
    }

}
