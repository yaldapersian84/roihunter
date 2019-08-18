package com.roihunter.facebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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


	public UserInfo(String name) {
		this.name = name;
	}
}