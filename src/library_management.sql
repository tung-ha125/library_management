-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 11, 2023 at 10:08 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library_management`
--

-- --------------------------------------------------------

--
-- Table structure for table `adminaccount`
--

CREATE TABLE `adminaccount` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `adminaccount`
--

INSERT INTO `adminaccount` (`username`, `password`) VALUES
('admin1', 'letmein'),
('admin2', 'letmein'),
('admin3', 'letmein'),
('admin4', 'letmein'),
('admin5', 'letmein'),
('admin6', 'letmein'),
('admin7', 'letmein'),
('admin8', 'letmein'),
('admin9', 'letmein'),
('admin10', 'letmein');

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `id` varchar(200) NOT NULL,
  `title` varchar(200) NOT NULL,
  `author` varchar(200) NOT NULL,
  `publisher` varchar(100) NOT NULL,
  `intcode` varchar(100) NOT NULL,
  `available` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `title`, `author`, `publisher`, `intcode`, `available`) VALUES
('B001', 'The Great Gats', 'F. Scott Fitzgerald', 'Scribner', '9780743273564', 1),
('B002', 'To Kill a Mockingbird', 'Harper Lee', 'J. B. Lippincott & Co.', '9780061120084', 0),
('B003', 'One Hundred Years of Solitude', 'Gabriel Garcia Marquez', 'Harper & Row', '9780060531041', 16),
('B004', 'The Catcher in the Rye', 'J.D. Salinger', 'Little, Brown and Company', '9780316769488', 1),
('B005', 'Animal Farm', 'George Orwell', 'Secker and Warburg', '9780151002177', 41),
('B006', 'Brave New World', 'Aldous Huxley', 'Chatto & Windus', '9780060929879', 3),
('B007', 'The Diary of a Young Girl', 'Anne Frank', 'Contact Publishing', '9780553296983', 3),
('B008', 'Pride and Prejudice', 'Jane Austen', 'T. Egerton, Whitehall', '9780486284736', 7),
('B009', 'Wuthering Heights', 'Emily Bronte', 'Thomas Cautley Newby', '9780141439556', 4),
('B010', 'The Hobbit', 'J.R.R. Tolkien', 'George Allen & Unwin', '9780547928227', 6),
('B011', 'The Lord of the Rings', 'J.R.R. Tolkien', 'George Allen & Unwin', '9780395489321', 4),
('B012', 'The Hitchhiker\'s Guide to the Galaxy', 'Douglas Adams', 'Pan Books', '9780345391803', 9),
('B013', '1984', 'George Orwell', 'Secker and Warburg', '9780547249643', 11),
('B014', 'The War of the Worlds', 'H.G. Wells', 'William Heinemann', '9780307747857', 5),
('B015', 'The Time Machine', 'H.G. Wells', 'William Heinemann', '9780349009358', 2),
('B016', 'Frankenstein', 'Mary Shelley', 'Lackington, Hughes, Harding, Mavor & Jones', '9780141439471', 3),
('B017', 'Dracula', 'Bram Stoker', 'Archibald Constable and Company', '9780486411095', 3),
('B018', 'The Picture of Dorian Gray', 'Oscar Wilde', 'Ward, Lock and Company', '9780141439570', 1),
('B019', 'The Adventures of Tom Sawyer', 'Mark Twain', 'Chatto & Windus', '9781853260117', 4),
('B020', 'The Adventures of Huckleberry Finn', 'Mark Twain', 'Chatto & Windus', '9780486403496', 2),
('B021', 'The Cat in the Hat', 'Dr. Seuss', 'Random House', '9780394800011', 3),
('B022', 'The Giving Tree', 'Shel Silverstein', 'Harper & Row', '9780060256654', 2),
('B023', 'Where the Wild Things Are', 'Maurice Sendak', 'Harper & Row', '9780060254926', 5),
('B024', 'The Very Hungry Caterpillar', 'Eric Carle', 'World Publishing Company', '9780399208539', 1),
('B025', 'Goodnight Moon', 'Margaret Wise Brown', 'Harper & Row', '9780694003617', 4),
('B026', 'Green Eggs and Ham', 'Dr. Seuss', 'Random House', '9780394800165', 2),
('B027', 'Oh, the Places You\'ll Go!', 'Dr. Seuss', 'Random House', '9780679805274', 3),
('B028', 'The Lorax', 'Dr. Seuss', 'Random House', '9780394823379', 6),
('B029', 'Charlotte\'s Web', 'E.B. White', 'Harper & Brothers', '9780064400558', 2),
('B030', 'Little House on the Prairie', 'Laura Ingalls Wilder', 'Harper & Brothers', '9780060264307', 4),
('B031', 'The Secret Garden', 'Frances Hodgson Burnett', 'Frederick A. Stokes', '9781503261966', 2),
('B032', 'A Little Princess', 'Frances Hodgson Burnett', 'Charles Scribner\'s Sons', '9781495329748', 3),
('B033', 'The Wind in the Willows', 'Kenneth Grahame', 'Methuen & Co.', '9780689713104', 1),
('B034', 'The Adventures of Sherlock Holmes', 'Arthur Conan Doyle', 'George Newnes Ltd.', '9780141199009', 5),
('B035', 'The Hobbit', 'J.R.R. Tolkien', 'HarperCollins', '9780007118359', 2),
('B036', 'The Lord of the Rings', 'J.R.R. Tolkien', 'HarperCollins', '9780007322497', 4),
('B037', 'The Chronicles of Narnia', 'C.S. Lewis', 'Geoffrey Bles', '9780007100248', 3),
('B038', 'Harry Potter and the Philosopher\'s Stone', 'J.K. Rowling', 'Bloomsbury Publishing', '9780747532743', 1),
('B039', 'Harry Potter and the Chamber of Secrets', 'J.K. Rowling', 'Bloomsbury Publishing', '9780747538486', 2),
('B040', 'Harry Potter and the Prisoner of Azkaban', 'J.K. Rowling', 'Bloomsbury Publishing', '9780747542155', 3),
('B041', 'Harry Potter and the Goblet of Fire', 'J.K. Rowling', 'Bloomsbury Publishing', '9780747546245', 2),
('B042', 'Harry Potter and the Order of Phoenix', 'J.K. Rowling', 'Bloomsbury Publishing', '9780747551003', 1),
('B043', 'Hunter', 'William J', 'World', '9780694003618', 0),
('B044', 'Friends', 'tung', 'fsdfsd', '324432', 4),
('B045', 'rrfsdfsd', 'fsdafasf', 'fdsfsdf', '423423423', 4);

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `Name` varchar(50) NOT NULL,
  `MemberId` int(11) NOT NULL,
  `Mobile` varchar(15) NOT NULL,
  `Email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`Name`, `MemberId`, `Mobile`, `Email`) VALUES
('Mary Johns', 2, '534534535', 'john177@gmail.com'),
('David', 3, '3456789013', 'david.lee1@example.com'),
('Emily Chen', 4, '4567890123', 'emily.chen@example.com'),
('James Davis', 5, '5678901234', 'jame.davis@example.com'),
('Michael ', 7, '09001008', 'michael.brown@example.com'),
('Rachel Smith', 8, '8901234567', 'rachel.smith@example.com'),
('Samuel Lee', 9, '9012345678', 'samuel.lee@example.com'),
('Sophia Davis', 10, '0123456789', 'sophia.davis@example.com'),
('William Chen', 11, '9876543210', 'william.chen@example.com'),
('Olivia Brown', 12, '8765432109', 'olivia.brown@example.com'),
('Daniel Lee', 13, '7654321098', 'daniel.lee@example.com'),
('Ava Smith', 14, '6543210987', 'ava.smith@example.com'),
('Ethan Johnson', 15, '5432109876', 'ethan.johnson@example.com'),
('Emma Lee', 16, '4321098765', 'emma.lee@example.com'),
('Jacob Chen', 17, '3210987654', 'jacob.chen@example.com'),
('Isabella Davis', 18, '2109876543', 'isabella.davis@example.com'),
('Noah Smith', 19, '1098765432', 'noah.smith@example.com'),
('Grace Lee', 20, '0987654321', 'grace.lee@example.com'),
('Ha Son Tung', 21, '0982469176', '21020398@vnu.edu.vn'),
('Tung', 23, '4234324', 'hasontung177@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `rentedbooks`
--

CREATE TABLE `rentedbooks` (
  `MemberId` int(11) DEFAULT NULL,
  `BookId` varchar(200) DEFAULT NULL,
  `RentedTime` date DEFAULT curdate(),
  `ReturnedTime` date DEFAULT (`RentedTime` + interval 120 day)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `rentedbooks`
--

INSERT INTO `rentedbooks` (`MemberId`, `BookId`, `RentedTime`, `ReturnedTime`) VALUES
(2, 'B011', '2022-04-01', '2023-09-09'),
(2, 'B018', '2022-04-06', '2023-09-09'),
(3, 'B010', '2022-03-27', '2023-09-09'),
(4, 'B002', '2022-03-22', '2023-09-09'),
(4, 'B007', '2022-04-03', '2023-09-09'),
(4, 'B013', '2022-04-08', '2023-09-09'),
(5, 'B021', '2022-04-07', '2023-09-09'),
(7, 'B020', '2022-04-09', '2023-09-09'),
(20, 'B020', '2023-04-17', '2023-09-09'),
(3, 'B001', '2023-04-20', '2023-09-09'),
(20, 'B005', '2023-04-20', '2023-09-09'),
(20, 'B001', '2023-04-20', '2023-09-09'),
(19, 'B001', '2023-04-20', '2023-09-09'),
(2, 'B002', '2023-04-20', '2023-09-09'),
(5, 'B020', '2023-04-20', '2023-09-09'),
(5, 'B021', '2023-04-20', '2023-09-09'),
(7, 'B003', '2023-04-22', '2023-09-09'),
(7, 'B005', '2023-04-22', '2023-09-09'),
(5, 'B020', '2023-05-12', '2023-05-12'),
(3, 'B003', '2023-05-12', '2023-09-09'),
(3, 'B004', '2023-05-12', '2023-09-09'),
(5, 'B004', '2023-05-12', '2023-09-09'),
(2, 'B005', '2023-05-12', '2023-09-09'),
(10, 'B005', '2023-05-12', '2023-09-09'),
(5, 'B001', '2023-05-12', '2023-09-09'),
(10, 'B005', '2023-05-12', '2023-09-09'),
(10, 'B005', '2023-05-12', '2023-09-09'),
(10, 'B005', '2023-05-12', '2023-09-09'),
(5, 'B005', '2023-05-12', '2023-09-09'),
(10, 'B005', '2023-05-12', '2023-09-09'),
(10, 'B005', '2023-05-12', '2023-09-09'),
(10, 'B005', '2023-05-12', '2023-09-09'),
(5, 'B005', '2023-05-12', '2023-09-09'),
(7, 'B005', '2023-05-12', '2023-09-09'),
(7, 'B005', '2023-05-12', '2023-09-09'),
(10, 'B005', '2023-05-12', '2023-09-09'),
(10, 'B005', '2023-05-12', '2023-09-09'),
(10, 'B005', '2023-05-12', '2023-09-09'),
(10, 'B005', '2023-05-12', '2023-09-09'),
(8, 'B005', '2023-05-12', '2023-09-09'),
(8, 'B005', '2023-05-12', '2023-09-09');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`MemberId`);

--
-- Indexes for table `rentedbooks`
--
ALTER TABLE `rentedbooks`
  ADD KEY `MemberId` (`MemberId`),
  ADD KEY `BookId` (`BookId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `MemberId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `rentedbooks`
--
ALTER TABLE `rentedbooks`
  ADD CONSTRAINT `rentedbooks_ibfk_1` FOREIGN KEY (`MemberId`) REFERENCES `member` (`MemberId`),
  ADD CONSTRAINT `rentedbooks_ibfk_2` FOREIGN KEY (`BookId`) REFERENCES `book` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
