package com.roihunter.facebook.dao;

import com.roihunter.facebook.model.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPhotoDao extends JpaRepository<UserPhoto, Long> {

	void deleteByUserInfoFbId(String fbId);


}
