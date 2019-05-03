CREATE DATABASE  IF NOT EXISTS `cityapp-directory`;
USE `cityapp-directory`;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `state`;

CREATE TABLE `state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `state` varchar(45) DEFAULT NULL,
  `abbrev` varchar(45) DEFAULT NULL,
  `neighbors` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Data for table `employee`
--

INSERT INTO `state` VALUES 
	(1, 'Alabama','AL', 'TN GA FL MS'),
	(2, 'Alaska','AK', ''),
	(3, 'Arizona','AZ', 'NM UT NV CA'),
	(4, 'Arkansas','AR', 'LA MO TN MS TX OK'),
	(5, 'California','CA', 'OR NV AZ'),
	(6, 'Colorado','CO', 'WY NE KS OK NM UT AZ'),
	(7, 'Connecticut','CT', 'NY MA RI'),
	(8, 'Delaware','DE', 'PA MD NJ'),
	(9, 'Florida','FL', 'GA AL'),
	(10, 'Georgia','GA', 'AL FL TN'),
	(11, 'Hawaii','HI', ''),
	(12, 'Idaho','ID', 'MT NV OR UT WA WY'),
	(13, 'Illinois','IL', 'IN IA MI KY MO WI'),
	(14, 'Indiana','IN', 'IL KY MI OH'),
	(15, 'Iowa','IA', 'IL MN MO NE SD WI'),
	(16, 'Kansas','KS', 'CO MO NE OK'),
	(17, 'Kentucky','KY', 'IL IN MO OH TN VA WV'),
	(18, 'Louisiana','LA', 'AR MS TX'),
	(19, 'Maine','ME', 'NH'),
	(20, 'Maryland','MD', 'DE PA VA WV'),
	(21, 'Massachusetts','MA', 'CT NH NY RI VT'),
	(22, 'Michigan','MI', 'IL IN MN OH WI'),
	(23, 'Minnesota','MN', 'IA MI ND SD WI'),
	(24, 'Mississippi','MS', 'AL AR LA TN'),
	(25, 'Missouri','MO', 'AR IL IA KS NY NE OK TN'),
	(26, 'Montana','MT', 'ID ND SD WY'),
	(27, 'Nebraska','NE', 'CO IA KS MO SD WY'),
	(28, 'Nevada','NV', 'AZ CA ID OR UT'),
	(29, 'New Hampshire','NH', 'ME MA VT'),
	(30, 'New Jersey','NJ', 'DE NY PA'),
	(31, 'New Mexico','NM', 'AZ CO OK TX UT'),
	(32, 'New York','NY', 'CT MA NJ PA RI VT'),
	(33, 'North Carolina','NC', 'GA SC TN VA'),
	(34, 'North Dakota','ND', 'MN MT SD'),
	(35, 'Ohio','OH', 'IN KT MI PA WV'),
	(36, 'Oklahoma','OK', 'AR CO KS MO NM TX'),
	(37, 'Oregon','OR', 'CA ID NV WA'),
	(38, 'Pennsylvania','PA', 'DE MD NJ NY OH WV'),
	(39, 'Rhode Island','RI', 'CT MA NY'),
	(40, 'South Carolina','SC', 'GA NC'),
	(41, 'South Dakota','SD', 'IA MN MT NE ND WY'),
	(42, 'Tennessee','TN', 'AL AR GA KT MS MO NC VA'),
	(43, 'Texas','TX', 'AR LA NM OK'),
	(44, 'Utah','UT', 'AZ CO ID NV NM WY'),
	(45, 'Vermont','VT', 'MA NH NY'),
	(46, 'Virginia','VA', 'KT MD NC TN WV'),
	(47, 'Washington','WA', 'ID OR'),
	(48, 'West Virginia','WV', 'KT MD OH PA VA'),
	(49, 'Wisconsin','WI', 'IL IA MI MN'),
	(50, 'Wyoming','WY', 'CO ID MT NE SD UT');

