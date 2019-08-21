package com.roihunter.facebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userInfos")
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "gender")
	private String gender;

	@Column(name = "profile_pic")
	private String profilePic;

	@Column(name = "fb_id", unique = true)
	private String fbId;


	@OneToMany(mappedBy = "userInfo")
	private Set<UserPhoto> photos = new HashSet<>();


	public UserInfo(String name) {
		this.name = name;
	}
}