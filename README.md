# BetMastersProject
This is my latest implemented Java web app. It's a fully functional site for betting companies.
I have used the MVC model. Back-end is implemented in Spring Boot and Spring Security. Front-end is mostly served
by Thymeleaf. The database is running on a MYSQL local server. I have also used Stripe for online payments and a couple of JS scripts
to give a more responsive and fresh look on fetched data. Admin users have their own tab so that they can configure user's details.


In order for this project to run, you firstly need to create a MYSQL database.
Use the following script to create it automaticallly.

~~~mysql
CREATE DATABASE groupproject;

USE groupproject;

DROP TABLE IF EXISTS role;

CREATE TABLE role (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS user;

CREATE TABLE user (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  email varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  is_active boolean,
  UNIQUE KEY UKob8kqyqqgmefl0aco34akdtpe (email),
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS user;

CREATE TABLE users_roles (
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL,
  KEY (role_id),
  KEY (user_id),
  CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (id),
  CONSTRAINT FOREIGN KEY (role_id) REFERENCES role (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SELECT * FROM user;

DROP table role;

INSERT INTO `groupproject`.`role` (`name`) VALUES ('ROLE_USER');
INSERT INTO `groupproject`.`role` (`name`) VALUES ('ROLE_PREMIUM_USER');
INSERT INTO `groupproject`.`role` (`name`) VALUES ('ROLE_ADMIN');


DROP PROCEDURE IF EXISTS validate_user;

-- procedure for email validity
DELIMITER $$
CREATE PROCEDURE validate_user(
	IN email VARCHAR(128)
)
BEGIN
	IF NOT(SELECT email REGEXP '^[^@]+@[^@]+\.[^@]{2,}$') THEN
		SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Wrong email';
	END IF;
END$$
DELIMITER ;

-- trigger to check the email validity
DROP TRIGGER IF EXISTS validate_user_insert;

DELIMITER $$
CREATE TRIGGER validate_user_insert
BEFORE INSERT ON user FOR EACH ROW
BEGIN
	CALL validate_user(NEW.email);
END$$
DELIMITER ;

INSERT INTO user(id,email,first_name,last_name,password) VALUES (89,'skata','aek','paok','1234');

DROP TABLE IF EXISTS user_audit;

-- CREATE TABLE FOR 
CREATE TABLE user_audit (
  id bigint(20) NOT NULL,
  email varchar(255) DEFAULT NULL,
  first_name varchar(255) DEFAULT NULL,
  last_name varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  action_type varchar(50) NOT NULL,
  action_date datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- trigger to keep a record on user_audit of new insertions of a users on user table with timestamp
DROP TRIGGER IF EXISTS user_after_insert;
DELIMITER //
CREATE TRIGGER user_after_insert
	AFTER INSERT ON user
	FOR EACH ROW
BEGIN
	INSERT INTO user_audit VALUES
	(NEW.id,NEW.email,NEW.first_name,NEW.last_name,NEW.password,'INSERTED', NOW());
END //

DROP TRIGGER IF EXISTS user_after_delete;

CREATE TRIGGER user_after_delete
	AFTER DELETE ON user
    FOR EACH ROW
BEGIN
	INSERT INTO user_audit VALUES
    (OLD.id,OLD.email,OLD.first_name,OLD.last_name,OLD.password,'DELETED', NOW());
END //

~~~

The above MYSQL script uses triggers and procedures to check for email validity and to keep an extra record of deleted accounts. 

---

## Application properties 

Fill in the following attributes from your MYSQL database:
~~~java
spring.datasource.url =
spring.datasource.username
spring.datasource.password
~~~~

I have used a client to send emails to the newly created accounts so fill in these attributes also:
~~~java
spring.mail.username
spring.mail.password 
~~~

You will most likely also need to create a Stripe account and import  
~~~bash
STRIPE_PUBLIC_KEY
STRIPE_SECRET_KEY
~~~
