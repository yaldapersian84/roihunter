package com.roihunter.facebook.model;

import com.roihunter.facebook.model.UserInfo;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Setter
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userPhotos")
public class UserPhoto implements Serializable {
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "user_photo_images"
            , joinColumns = @JoinColumn(name = "user_photo_id")
    )
    @Column(name = "user_image", nullable = false)
    private Set<String> images = new HashSet<>();


}