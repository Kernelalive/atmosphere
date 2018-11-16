--
-- Database: `atmosphere`
--
Use `atmosphere`;

--
-- Dumping data for table `processingtypecategory`
--

INSERT INTO `processingtypecategory` (`id`, `description`, `name`) VALUES
(10, NULL, 'Internal Policies'),
(11, NULL, 'Data Processing'),
(12, 'Includes everything that has to do with day-to-day company and employee obligations as per individual contracts!', 'Administrative'),
(13, NULL, 'Marketing');

--
-- Dumping data for table `datasubject`
--

INSERT INTO `datasubject` (`id`, `name`) VALUES
(8, 'Employee'),
(9, 'Employer');

--
-- Dumping data for table `legalentitycategory`
--

INSERT INTO `legalentitycategory` (`id`, `name`) VALUES
(1, 'Natural Person'),
(2, 'Legal Person '),
(3, 'Public Authority'),
(4, 'Agency'),
(5, 'Other');

--
-- Dumping data for table `legalgroundtype`
--

INSERT INTO `legalgroundtype` (`id`, `name`, `weight`) VALUES
(1, 'Consent given by data subject', 1),
(2, 'Contract performance (after or during steps to enter a contract)', 2),
(3, 'Legal obligation of the controller', 3),
(4, 'Vital interests of the data subject or of another natural person', 4),
(5, 'Public interest or exercise of official authority vested in the controller', 5),
(6, 'Legitimate interest-(s) of controller and/or third parties', 6);

--
-- Dumping data for table `legalground`
--

INSERT INTO `legalground` (`id`, `comments`, `name`, `legalgroundtypeid`) VALUES
(9, 'Employees should have access on certain computer resources in order to perform as per their contract statement. As a result, the company needs to maintain a couple of information to control access in a secure and centralized manner.', 'Internal Access Policy (System Resources)', 2),
(10, NULL, 'Payroll', 2);

--
-- Dumping data for table `piicategory`
--

INSERT INTO `piicategory` (`id`, `name`) VALUES
(4, 'Biometric Data'),
(2, 'Financial'),
(3, 'Health Data'),
(1, 'Personal'),
(6, 'Political'),
(7, 'Social'),
(5, 'Tracking Identifiers');

--
-- Dumping data for table `pii`
--

INSERT INTO `pii` (`id`, `identification`, `name`, `type`, `categoryid`) VALUES
(1, 'LINKABLE', 'Full Name', 'N', 1),
(2, 'LINKABLE', 'Firstname', 'N', 1),
(3, 'LINKABLE', 'Lastname', 'N', 1),
(4, 'LINKABLE', 'Initial', 'N', 1),
(5, 'LINKABLE', 'Mother’s Name', 'N', 1),
(6, 'LINKABLE', 'Father’s Name', 'N', 1),
(7, 'LINKABLE', 'Age', 'N', 1),
(8, 'LINKABLE', 'Birthday', 'N', 1),
(9, 'LINKABLE', 'Sex', 'N', 1),
(10, 'LINKABLE', 'Sexual Orientation', 'S', 1),
(11, 'LINKED', 'Email', 'N', 1),
(12, 'LINKED', 'Phone', 'N', 1),
(13, 'LINKED', 'Mobile', 'N', 1),
(14, 'LINKABLE', 'Home Address', 'N', 1),
(15, 'LINKABLE', 'Work Address', 'N', 1),
(16, 'LINKED', 'Passport', 'N', 1),
(17, 'LINKED', 'National ID', 'N', 1),
(18, 'LINKED', 'Social Security Number', 'N', 1),
(19, 'LINKED', 'Driving License', 'N', 1),
(20, 'LINKABLE', 'Political Views', 'S', 6),
(21, 'LINKABLE', 'Facebook Profile', 'N', 7),
(22, 'LINKED', 'Facebook Profile (VERIFIED)', 'N', 7),
(23, 'LINKABLE', 'Twitter Account', 'N', 7),
(24, 'LINKED', 'Twitter Account (VERIFIED)', 'N', 7),
(25, 'LINKABLE', 'Instagram Account', 'N', 7),
(26, 'LINKED', 'Instagram Account (VERIFIED)', 'N', 7),
(27, 'LINKABLE', 'IP address', 'N', 5),
(28, 'LINKABLE', 'Location', 'N', 5),
(29, 'LINKED', 'Mobile Device', 'N', 5),
(30, 'LINKABLE', 'Race', 'S', 1),
(31, 'LINKABLE', 'Ethnic Origin', 'S', 1),
(32, 'LINKABLE', 'Religious Beliefs', 'S', 1),
(33, 'LINKABLE', 'Philosophical Beliefs', 'S', 1),
(34, 'LINKED', 'Trade Union Membership', 'S', 1),
(35, 'LINKABLE', 'DNA', 'S', 4);

--
-- Dumping data for table `legalentity`
--

INSERT INTO `legalentity` (`id`, `address`, `country`, `name`, `regno`, `vat`, `zip`, `categoryid`) VALUES
(10, 'Thessalias & Etolias', 'GR', 'Ubitech', NULL, NULL, '15231', 5),
(12, '205 Archbishop Makarios Avenue, 3030 Limassol', 'CY', 'Ubitech Cyprus', NULL, NULL, NULL, 5),
(14, '754, Calle Paraná Piso 11B, C1017AAP CABA (Ciudad Autónoma de Buenos Aires)', 'AR', 'Ubitech Argentina', NULL, NULL, NULL, 5);

--
-- Dumping data for table `datasubjectpii`
--

INSERT INTO `datasubjectpii` (`datasubjectid`, `piiid`) VALUES
(9, 7),
(9, 8),
(9, 11),
(9, 6),
(9, 2),
(9, 14),
(9, 4),
(9, 3),
(9, 13),
(9, 17),
(9, 16),
(9, 12),
(9, 9),
(9, 15),
(8, 7),
(8, 8),
(8, 19),
(8, 11),
(8, 6),
(8, 2),
(8, 14),
(8, 3),
(8, 5),
(8, 17),
(8, 16),
(8, 12),
(8, 9),
(8, 15),
(8, 27),
(8, 28),
(8, 29),
(8, 30);