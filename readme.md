/* Scripts to Create MySQL Database Phonebook */


CREATE SCHEMA `Phonebook`;

CREATE TABLE `Users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(20) NOT NULL DEFAULT '',
  `first_name` varchar(30) DEFAULT NULL,
  `middle_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `Contacts` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `users_id` int(11) unsigned NOT NULL,
  `first_name` varchar(30) NOT NULL DEFAULT '',
  `middle_name` varchar(30) NOT NULL DEFAULT '',
  `last_name` varchar(30) NOT NULL DEFAULT '',
  `mobile_number` varchar(13) NOT NULL DEFAULT '',
  `phone_number` varchar(13) DEFAULT NULL,
  `address` varchar(70) DEFAULT '',
  `email` varchar(30) DEFAULT '',
  PRIMARY KEY (`id`),
  KEY `users_id_fk` (`users_id`),
  CONSTRAINT `users_id_fk` FOREIGN KEY (`users_id`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;




