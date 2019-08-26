-- ----------------------------------------------------------------------------
-- Schema db_fb_user_mng
-- ----------------------------------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_fb_user_mng` ;

-- ----------------------------------------------------------------------------
-- Table user_infos
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_infos` (
                                                              `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                              `fb_id` VARCHAR(255) NULL DEFAULT NULL,
                                                              `gender` VARCHAR(255) NULL DEFAULT NULL,
                                                              `name` VARCHAR(255) NULL DEFAULT NULL,
                                                              `profile_pic` VARCHAR(255) NULL DEFAULT NULL,
                                                              PRIMARY KEY (`id`),
                                                              UNIQUE INDEX `UK_50gyi0plo35jox882hpwumpdp` (`fb_id` ASC))
    ENGINE = MyISAM
    DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table user_photo_images
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_photo_images` (
                                                                     `user_photo_id` BIGINT(20) NOT NULL,
                                                                     `user_image` VARCHAR(255) NOT NULL,
                                                                     PRIMARY KEY (`user_photo_id`, `user_image`))
    ENGINE = MyISAM
    DEFAULT CHARACTER SET = utf8;

-- ----------------------------------------------------------------------------
-- Table user_photos
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `user_photos` (
                                                               `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
                                                               `album_name` VARCHAR(255) NULL DEFAULT NULL,
                                                               `created_time` VARCHAR(255) NULL DEFAULT NULL,
                                                               `fb_id` VARCHAR(255) NULL DEFAULT NULL,
                                                               `likes` INT(11) NULL DEFAULT NULL,
                                                               `link` VARCHAR(255) NULL DEFAULT NULL,
                                                               `name` LONGTEXT NULL DEFAULT NULL,
                                                               `fk_info_id` BIGINT(20) NOT NULL,
                                                               PRIMARY KEY (`id`),
                                                               INDEX `user_photo_fk_info_id` (`fk_info_id` ASC))
    ENGINE = MyISAM
    DEFAULT CHARACTER SET = utf8;
SET FOREIGN_KEY_CHECKS = 1;