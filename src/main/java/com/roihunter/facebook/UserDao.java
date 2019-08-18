package com.roihunter.facebook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDao extends JpaRepository<UserInfo, Long> {

	Optional<UserInfo> findByName(String name);


}
