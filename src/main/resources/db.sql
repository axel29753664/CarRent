CREATE DATABASE IF NOT EXISTS `companydb`
  DEFAULT CHARACTER SET utf8;
USE `companydb`;

/*<---------Car table----------->*/
DROP TABLE IF EXISTS `cars`;
CREATE TABLE `cars` (
  `number_plate` VARCHAR(10) NOT NULL,
  `mark`         VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`number_plate`),
  UNIQUE KEY `cars_number_plate_uindex` (`number_plate`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*<---------Employee table----------->*/
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `personal_code` BIGINT(20) NOT NULL,
  `name`          VARCHAR(50) DEFAULT NULL,
  PRIMARY KEY (`personal_code`),
  UNIQUE KEY `employees_personal_code_uindex` (`personal_code`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*<---------Rent table----------->*/
DROP TABLE IF EXISTS `rent`;
CREATE TABLE `rent` (
  `id`                     BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `employee_personal_code` BIGINT(20)  NOT NULL,
  `car_number_plate`       VARCHAR(10) NOT NULL,
  `rent_start`             DATETIME    NOT NULL,
  `rent_end`               DATETIME    NOT NULL,
  PRIMARY KEY (`id`),
  KEY `rent_cars_number_plate_fk` (`car_number_plate`),
  KEY `rent_employees_personal_code_fk` (`employee_personal_code`),
  CONSTRAINT `rent_cars_number_plate_fk` FOREIGN KEY (`car_number_plate`) REFERENCES `cars` (`number_plate`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `rent_employees_personal_code_fk` FOREIGN KEY (`employee_personal_code`) REFERENCES `employees` (`personal_code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

/*<------init tables ------->*/

INSERT INTO `companydb`.`cars` (`number_plate`, `mark`) VALUES ('AA-1234', 'audi');
INSERT INTO `companydb`.`cars` (`number_plate`, `mark`) VALUES ('BB-4321', 'bmw');
INSERT INTO `companydb`.`cars` (`number_plate`, `mark`) VALUES ('CC-000', 'volvo');

INSERT INTO `companydb`.`employees` (`personal_code`, `name`) VALUES ('111111111111', 'John');
INSERT INTO `companydb`.`employees` (`personal_code`, `name`) VALUES ('222222222222', 'Bob');
INSERT INTO `companydb`.`employees` (`personal_code`, `name`) VALUES ('333333333333', 'Adam');

INSERT INTO `companydb`.`rent` (`employee_personal_code`, `car_number_plate`, `rent_start`, `rent_end`)
VALUES ('111111111111', 'AA-1234', '2017-03-07 00:00:00', '2017-03-07 01:00:00');
INSERT INTO `companydb`.`rent` (`employee_personal_code`, `car_number_plate`, `rent_start`, `rent_end`)
VALUES ('111111111111', 'AA-1234', '2017-03-10 10:00:00', '2017-03-11 10:00:00');
INSERT INTO `companydb`.`rent` (`employee_personal_code`, `car_number_plate`, `rent_start`, `rent_end`)
VALUES ('111111111111', 'BB-4321', '2017-03-10 10:00:00', '2017-03-11 10:00:00');
INSERT INTO `companydb`.`rent` (`employee_personal_code`, `car_number_plate`, `rent_start`, `rent_end`)
VALUES ('222222222222', 'AA-1234', '2017-03-8 10:00:00', '2017-03-8 11:00:00');
INSERT INTO `companydb`.`rent` (`employee_personal_code`, `car_number_plate`, `rent_start`, `rent_end`)
VALUES ('222222222222', 'BB-4321', '2017-03-7 10:00:00', '2017-03-8 11:00:00');