package com.roihunter.facebook;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPhotoDao extends JpaRepository<UserPhoto, Long> {

	Optional<UserPhoto> findByName(String name);


}
