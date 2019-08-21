package com.roihunter.facebook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoDao extends JpaRepository<UserInfo, Long> {

	Optional<UserInfo> findByFbId(String fbId);


}
