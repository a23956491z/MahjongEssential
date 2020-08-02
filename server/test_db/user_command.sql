CREATE DATABASE IF NOT EXISTS  database_MJE_test;

CREATE TABLE IF NOT EXISTS `user_attribute`(
   `account_number` VARCHAR(24) NOT NULL UNIQUE,
   `password` VARCHAR(24) NOT NULL,
   `account_name` VARCHAR(40),
   PRIMARY KEY ( `account_number` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `role_attribute`(
   `role_id` INT NOT NULL AUTO_INCREMENT,
   `role_name` VARCHAR(24) NOT NULL,
   `account_number` VARCHAR(24) NOT NULL,
   `score_balance` INT DEFAULT 0,
   CONSTRAINT u_account_role UNIQUE(account_number, role_name)
   FOREIGN KEY(account_number) REFERENCES user_attribute(account_number) ,
   PRIMARY KEY ( `role_id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE IF NOT EXISTS `match_information`(
     `match_serial_number` BIGINT NOT NULL AUTO_INCREMENT,
     `match_created_date` DATETIME DEFAULT CURRENT_TIMESTAMP,
     `account_number` VARCHAR(24) NOT NULL,
     FOREIGN KEY(account_number) REFERENCES user_attribute(account_number) ,
     PRIMARY KEY ( `match_serial_number` )
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE IF NOT EXISTS `match_record`(
     `match_serial_number` BIGINT NOT NULL,
     `round_number` INT NOT NULL,
     `role_name` VARCHAR(24) NOT NULL,
     `score_change` INT NOT NULL,
     CONSTRAINT u_match_round_name UNIQUE(match_serial_number, round_number, role_name),
     FOREIGN KEY(match_serial_number) REFERENCES match_information(match_serial_number),
     PRIMARY KEY ( `match_serial_number` )
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;



/********** testing_data following **********/
 DELETE FROM user_attribute;

 INSERT INTO user_attribute(account_number ,password)
    VALUES('i dont have a name', '87878');
 INSERT INTO user_attribute(account_number ,password, account_name)
    VALUES('test_acc_1', '0000', 'abby'),
          ('test_acc_2', '1234', 'mike');

 SELECT * FROM user_attribute;

 /*change password*/
 UPDATE user_attribute
    SET password = '30678'
    WHERE account_number = 'test_acc_1';

/*change user name*/
UPDATE user_attribute
   SET password = 'miiiiike'
   WHERE account_number = 'test_acc_2';


 SELECT * FROM user_attribute;

 /*inset new role*/
 INSERT INTO role_attribute(account_number, role_name)
     VALUES('test_acc_1', 'abby'),
           ('test_acc_1', 'ruby'),
           ('test_acc_1', 'diana'),
           ('test_acc_2', 'mikey');
 INSERT INTO role_attribute(account_number, role_name)
     VALUES('test_acc_1', 'mikey');
 SELECT * FROM role_attribute;

 /*checkout an account's roles*/
 SELECT role_name
     FROM role_attribute
     WHERE account_number = 'test_acc_1';

 /*delete an account's role*/
 DELETE FROM role_attribute
     WHERE account_number = 'test_acc_1'
         AND role_name = 'ruby';
 SELECT * FROM role_attribute;



/*create match*/
    /*match infor*/
INSERT INTO match_information(account_number)
    VALUE('test_acc_1');

    /*match record*/

SET @last_match = (SELECT match_serial_number FROM match_information ORDER BY match_serial_number DESC LIMIT 1);
INSERT INTO match_record (match_serial_number, round_number, role_name, score_change)
    VALUES (@last_match, 1,'abby', -5);
INSERT INTO match_record (match_serial_number, round_number, role_name, score_change)
    VALUES (@last_match, 2,'ruby', 3),
           (@last_match, 2,'diana', -3),
           (@last_match, 3,'mickey', +10),
           (@last_match, 3,'diana', -3),
           (@last_match, 3,'ruby', -3),
           (@last_match, 3,'abby', -4);

SELECT * FROM match_record;

ALTER TABLE match_record
    ADD FOREIGN KEY (`match_serial_number`) REFERENCES `match_information` (`match_serial_number`);

ALTER TABLE match_record
    DROP FOREIGN KEY `match_record_ibfk_1`;




HashMap<String, List<Integer>> roleName_matchRecord;

map<String, int[]> mp;
mp["nick"][1]
