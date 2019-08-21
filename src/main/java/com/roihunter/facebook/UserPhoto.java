package com.roihunter.facebook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userPhotoss")
public class UserPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name = "name", length = 5000)
    private String name;

    @Column(name = "fb_id")
    private String fbId;

    @Column(name = "created_time")
    private String createdTime;

    @Column(name = "album_name")
    private String albumName;

    @Column(name = "link")
    private String link;

    @Column(name = "likes")
    private int likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="fk_info_id", nullable=false
            , foreignKey = @ForeignKey(name = "user_photo_fk_info_id"))
    private UserInfo userInfo;


}