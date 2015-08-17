-- ***************************************************************************************
-- Argentina Rental Housing Backend (Management System for building rentals)
-- Sistema para gestión de alquileres inmobiliarios
-- License: GPLv3 (http://www.gnu.org/copyleft/gpl.html)
-- Author: Dario Palminio
-- ***************************************************************************************

-- DELETE ALL TABLES
DROP TABLE rentalhousing_db.house;
DROP TABLE rentalhousing_db.person;
DROP TABLE rentalhousing_db.contract;
DROP TABLE rentalhousing_db.pay;
DROP TABLE rentalhousing_db.pay_item;
DROP TABLE rentalhousing_db.roles; 
DROP TABLE rentalhousing_db.users;
-- DELETE ALL RECIPE
drop schema rentalhousing_db; 
drop user daro; 
drop user userdb;
FLUSH PRIVILEGES;

--ALTER TABLE rentalhousing_db.person DROP gender
