-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 25, 2024 at 06:09 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aoopassignment`
--

-- --------------------------------------------------------

--
-- Table structure for table `motorcycles`
--

CREATE TABLE `motorcycles` (
  `mv_id` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  `color` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `make` varchar(50) DEFAULT NULL,
  `engine_capacity` int(11) DEFAULT NULL,
  `owner` varchar(100) DEFAULT NULL,
  `mileage` int(11) DEFAULT NULL,
  `nice_ride_quality` tinyint(1) DEFAULT NULL,
  `cool_exhaust_sound` tinyint(1) DEFAULT NULL,
  `has_sidecar` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `motorcycles`
--

INSERT INTO `motorcycles` (`mv_id`, `weight`, `color`, `model`, `make`, `engine_capacity`, `owner`, `mileage`, `nice_ride_quality`, `cool_exhaust_sound`, `has_sidecar`) VALUES
(4, 185, 'Green', 'Pulsar-N250', 'Bajaj Motors', 250, 'Iron Man', 600, 0, 1, 1),
(7, 220, 'Red', 'Ninja', 'Kawasaki', 650, 'Bob Smith', 1200, 1, 1, 0),
(12345, 2750, 'Lime Green', 'Bullet', 'Royal Enfield', 500, 'Thomas', 350, 1, 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `tractors`
--

CREATE TABLE `tractors` (
  `mv_id` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  `color` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `make` varchar(50) DEFAULT NULL,
  `engine_capacity` int(11) DEFAULT NULL,
  `owner` varchar(100) DEFAULT NULL,
  `mileage` int(11) DEFAULT NULL,
  `is_four_wheel_drive` tinyint(1) DEFAULT NULL,
  `has_front_loader` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tractors`
--

INSERT INTO `tractors` (`mv_id`, `weight`, `color`, `model`, `make`, `engine_capacity`, `owner`, `mileage`, `is_four_wheel_drive`, `has_front_loader`) VALUES
(5, 3000, 'Green', '6R 195', 'John Deere', 4000, 'Daemon Targaryen', 20000, 1, 1),
(8, 2800, 'Blue', '5075E', 'John Deere', 3500, 'Charlie Brown', 15000, 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `trucks`
--

CREATE TABLE `trucks` (
  `mv_id` int(11) NOT NULL,
  `weight` int(11) NOT NULL,
  `color` varchar(50) DEFAULT NULL,
  `model` varchar(50) DEFAULT NULL,
  `make` varchar(50) DEFAULT NULL,
  `engine_capacity` int(11) DEFAULT NULL,
  `owner` varchar(100) DEFAULT NULL,
  `mileage` int(11) DEFAULT NULL,
  `passenger_capacity` int(11) DEFAULT NULL,
  `number_of_wheels` int(11) DEFAULT NULL,
  `towing_capacity` int(11) DEFAULT NULL,
  `load_capacity` double DEFAULT NULL,
  `current_load` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `trucks`
--

INSERT INTO `trucks` (`mv_id`, `weight`, `color`, `model`, `make`, `engine_capacity`, `owner`, `mileage`, `passenger_capacity`, `number_of_wheels`, `towing_capacity`, `load_capacity`, `current_load`) VALUES
(1, 8000, 'rfrrrf', 'Actros', 'Mercedes Benz', 12800, 'ccrcrc', 50000, 3, 18, 30000, 25000, NULL),
(2, 8000, 'Yellow', 'TGX', 'MAN', 10518, 'Updated Owner', 25000, 3, 6, 23000, 15000, NULL),
(6, 7500, 'Black', 'Volvo FH', 'Volvo', 13000, 'Thomas Shelby', 30000, 2, 6, 25000, 20000, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `vehicles`
--

CREATE TABLE `vehicles` (
  `id` int(11) NOT NULL,
  `type` varchar(255) NOT NULL,
  `weight` int(11) NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `model` varchar(255) NOT NULL,
  `make` varchar(255) NOT NULL,
  `engine_capacity` int(11) NOT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `mileage` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `motorcycles`
--
ALTER TABLE `motorcycles`
  ADD PRIMARY KEY (`mv_id`);

--
-- Indexes for table `tractors`
--
ALTER TABLE `tractors`
  ADD PRIMARY KEY (`mv_id`);

--
-- Indexes for table `trucks`
--
ALTER TABLE `trucks`
  ADD PRIMARY KEY (`mv_id`);

--
-- Indexes for table `vehicles`
--
ALTER TABLE `vehicles`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
