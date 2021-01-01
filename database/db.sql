CREATE DATABASE shopping;
USE shopping;

CREATE TABLE tbl_role (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `role_name` VARCHAR(100) CHARACTER SET `utf8` NOT NULL,
	`role_code` VARCHAR(100) NOT NULL,
    `des` VARCHAR(200)
)
ENGINE InnoDB,
DEFAULT CHARACTER SET = utf8,
COLLATE = utf8_unicode_ci;

CREATE TABLE tbl_user (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `fullname` VARCHAR(100) CHARACTER SET `utf8` NOT NULL,
	`username` VARCHAR(100) NOT NULL,
    `password` VARCHAR(1000) NOT NULL,
    `status` INT NOT NULL,
	`role_id` INT NOT NULL,
	`address` VARCHAR(200),
	`email` VARCHAR(100),
	`phone` VARCHAR(20),
    CONSTRAINT `user_role` FOREIGN KEY (`role_id`) REFERENCES `shopping`.`tbl_role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE InnoDB,
DEFAULT CHARACTER SET = utf8,
COLLATE = utf8_unicode_ci;

CREATE TABLE tbl_product (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `product_name` VARCHAR(150) CHARACTER SET `utf8` NOT NULL,
    `default_source` VARCHAR(200) NOT NULL,
    `price` FLOAT NOT NULL,
	`des` TEXT CHARACTER SET `utf8` NOT NULL
)
ENGINE InnoDB,
DEFAULT CHARACTER SET = utf8,
COLLATE = utf8_unicode_ci;

CREATE TABLE tbl_color (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `color_name` VARCHAR(150) CHARACTER SET `utf8` NOT NULL,
	`color_code` VARCHAR(100) NOT NULL
)
ENGINE InnoDB,
DEFAULT CHARACTER SET = utf8,
COLLATE = utf8_unicode_ci;

CREATE TABLE tbl_color_source (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `product_id` INT NOT NULL,
    `color_id` INT NOT NULL,
    `source` VARCHAR(100) NOT NULL,
	CONSTRAINT `product_product_color` FOREIGN KEY (`product_id`) REFERENCES `shopping`.`tbl_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `color_product_color` FOREIGN KEY (`color_id`) REFERENCES `shopping`.`tbl_color` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE InnoDB,
DEFAULT CHARACTER SET = utf8,
COLLATE = utf8_unicode_ci;

CREATE TABLE tbl_order (
	id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    is_waiting INT,
    order_time TIMESTAMP NOT NULL,
    is_received INT,
    receive_time TIMESTAMP,
    is_delivered INT,
    deliver_time TIMESTAMP,
    is_confirm INT,
    confirm_time TIMESTAMP
)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


CREATE TABLE tbl_booked_product (
	`id` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `user_id` INT NOT NULL,
    `product_id` INT NOT NULL,
    `color_source_id` INT NOT NULL,
    `saleoff` FLOAT,
    `time` TIMESTAMP,
    `quantity` INT NOT NULL,
    `order_id` INT NULL,
	CONSTRAINT `product_color_booked_product` FOREIGN KEY (`product_color_id`) REFERENCES `shopping`.`tbl_product_color` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `user_booked_product` FOREIGN KEY (`user_id`) REFERENCES `shopping`.`tbl_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `order_booked_product` FOREIGN KEY (`order_id`) REFERENCES `shopping`.`tbl_order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)
ENGINE InnoDB,
DEFAULT CHARACTER SET = utf8,
COLLATE = utf8_unicode_ci;
