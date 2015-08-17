-- ***************************************************************************************
-- Argentina Rental Housing Backend (Management System for building rentals)
-- Sistema para gestión de alquileres inmobiliarios
-- License: GPLv3 (http://www.gnu.org/copyleft/gpl.html)
-- Author: Dario Palminio
-- ***************************************************************************************

-- mysql -u daro -p
-- daropass
-- mysql -u userdb -p
-- password
-- mysql -u root -p
-- mysqlpass

UPDATE mysql.user SET Password=PASSWORD('mysqlpass') WHERE User='root';

CREATE DATABASE rentalhousing_db default character set utf8 default collate utf8_general_ci;

GRANT USAGE ON *.* TO daro@localhost IDENTIFIED BY 'daropass';
GRANT ALL PRIVILEGES ON rentalhousing_db.* TO daro@localhost;



CREATE TABLE rentalhousing_db.person (
    person_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(40) NOT NULL,
    last_name VARCHAR(40) NOT NULL,
    dni_type VARCHAR(3) NOT NULL,
    dni_code VARCHAR(40) NOT NULL,
    address VARCHAR(250) NOT NULL,
    city VARCHAR(40) NOT NULL,
    email VARCHAR(45) DEFAULT NULL,
    mobile VARCHAR(15) DEFAULT NULL,
    phone VARCHAR(15) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--ALTER TABLE rentalhousing_db.person ADD email VARCHAR(45);


  
CREATE TABLE rentalhousing_db.house(
   house_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
   address VARCHAR(250) NOT NULL,
   city VARCHAR(40) NOT NULL,
   house_type VARCHAR(20) NOT NULL,
   proprietary_id INT NOT NULL,
   FOREIGN KEY (proprietary_id) REFERENCES rentalhousing_db.person(person_id)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;


CREATE TABLE rentalhousing_db.contract(
   contract_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
   house_id INT NOT NULL,
   householder_id INT NOT NULL,   
   renters_id INT NOT NULL,
   guarantor_id INT NOT NULL,
   date_start DATE NOT NULL,
   date_end DATE NOT NULL,
   days_to_expire SMALLINT NOT NULL,
   active tinyint(1) DEFAULT NULL,   
   must_pay_expenses tinyint(1) DEFAULT NULL,
   must_pay_services tinyint(1) DEFAULT NULL,
   deposit_of_security_total DOUBLE NOT NULL,
   deposit_of_security_returned tinyint(1) DEFAULT NULL,
   contract_compliance tinyint(1) DEFAULT NULL,
   FOREIGN KEY (house_id) REFERENCES rentalhousing_db.house(house_id),
   FOREIGN KEY (householder_id) REFERENCES rentalhousing_db.person(person_id),
   FOREIGN KEY (renters_id) REFERENCES rentalhousing_db.person(person_id),
   FOREIGN KEY (guarantor_id) REFERENCES rentalhousing_db.person(person_id)
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE rentalhousing_db.pay(
   pay_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
   contract_id INT(11) NOT NULL,   
   months_date DATE NOT NULL,
   expires_date DATE NOT NULL,
   paid tinyint(1) DEFAULT NULL,
   expired tinyint(1) DEFAULT NULL,
   FOREIGN KEY (contract_id) REFERENCES rentalhousing_db.contract(contract_id)
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE rentalhousing_db.pay_item(
   item_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
   pay_id INT(11) NOT NULL,
   amount DOUBLE NOT NULL,
   item_category CHAR(3)NOT NULL, --## ("MES", "SER", "EXP", "DEP")
   FOREIGN KEY (pay_id) REFERENCES rentalhousing_db.pay(pay_id)
)ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

CREATE TABLE rentalhousing_db.users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  person_id INT NOT NULL,
  enabled TINYINT NOT NULL DEFAULT 1 ,
  PRIMARY KEY (username),
  FOREIGN KEY (person_id) REFERENCES rentalhousing_db.person(person_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE rentalhousing_db.roles (
  role_id INT(11) NOT NULL AUTO_INCREMENT,
  username VARCHAR(45) NOT NULL,
  role VARCHAR(45) NOT NULL,
  PRIMARY KEY (role_id),
  UNIQUE KEY uni_username_role (ROLE,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES rentalhousing_db.users(username)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--'ROLE_ADMIN', 'ROLE_RENTERS', 'ROLE_PROPRIETARY', 'ROLE_AGENT'
