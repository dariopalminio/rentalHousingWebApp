-- ***************************************************************************************
-- Argentina Rental Housing Backend (Management System for building rentals)
-- Sistema para gestión de alquileres inmobiliarios
-- License: GPLv3 (http://www.gnu.org/copyleft/gpl.html)
-- Author: Dario Palminio
-- ***************************************************************************************

INSERT INTO rentalhousing_db.person (first_name, last_name, dni_type, dni_code, address, city, email, mobile, phone) 
	VALUES("Dario", "Palminio", "DNI", "25000123", "Colon 1881 5C", "Cordoba", "daro@hotmail.com", "123000123", "");
INSERT INTO rentalhousing_db.person (first_name, last_name, dni_type, dni_code, address, city, email, mobile, phone) 
	VALUES("Mary", "Choy", "DNI", "6000123", "27 de Abril 1797 Torre B 3A", "Cordoba", "daro@hotmail.com", "123000123", "");
INSERT INTO rentalhousing_db.person (first_name, last_name, dni_type, dni_code, address, city, email, mobile, phone) 
	VALUES("Karina", "Losano", "DNI", "23456123", "Maipu 132 4A", "Cordoba", "daro@hotmail.com", "123000123", "");
	
INSERT INTO rentalhousing_db.house (address, city, house_type, proprietary_id) 
	VALUES("27 de Abri 1797 Torre B 3A", "Cordoba", "VF", 7);
INSERT INTO rentalhousing_db.house (address, city, house_type, proprietary_id) 
	VALUES("Vicente Lopez y Planes 1990 1A", "Comodoro Rivadavia", "VF", 7);

  
INSERT INTO rentalhousing_db.users (username, password, person_id, enabled) 
	VALUES("admin", "admin", 7, true);
INSERT INTO rentalhousing_db.users (username, password, person_id, enabled) 
	VALUES("daro", "123", 8, true);
	
INSERT INTO rentalhousing_db.roles (username, ROLE) 
	VALUES("admin", "ROLE_USER");
INSERT INTO rentalhousing_db.roles (username, ROLE)
	VALUES ("admin", "ROLE_ADMIN");
INSERT INTO rentalhousing_db.roles (username, ROLE) 
	VALUES("daro", "ROLE_USER");
    
    
	
	
