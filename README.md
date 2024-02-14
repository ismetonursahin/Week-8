# Tourism Agency Project

## Overview
This Java-based Tourism Agency project facilitates the digital management Tourism agency operations. It includes functionalities such as hotel management, period management, room management, pricing, room search, and reservation operations.

## Users
The system allows login for Admin and Agency roles.

## Features

### 1. Admin and Employee Management:
- Admins can add, edit, or delete employees in the system.
- Admins can manage the details of existing employees.

### 2. Hotel Management:
- Employees can add new hotels to the system.
- Details like Hotel Name, Address, Email, Phone, Star Rating, and Facility Features can be specified.
- Hotel search, addition, and deletion functionalities are available.

### 3. Period Management:
- Historical periods for each hotel can be added and deleted.

### 4. Room Management:
- Employees can add, edit, or delete rooms for reservations.
- Room types (Single, Double, Suite, etc.) and features (Bed Count, TV, Minibar, etc.) can be defined.

### 5. Pricing:
- Nightly prices for rooms are determined based on the period, pension type, and the number of adults and children.

### 6. Room Search and Reservation Operations:
- Employees can search for available rooms based on date range, region, hotel, and guest information.
- Rooms are listed with pricing details.
- Reservations can be made, and the total amount is calculated based on the selected options.
- Reservation completion reduces the stock of the respective room.

### 7. Reservation List:
- Employees can list and delete reservations made.

## Technologies Used
- Java
- Swing (GUI)
- PostgreSQL (Database)
- JDBC (Java Database Connectivity)

## Project Structure
- **Business:** Manages the core functionality of the application, including business processes and managers.
- **DAO (Data Access Object):** Handles data access operations, database interactions, and query processing.
- **Core:** Contains utility classes and basic elements providing essential functions.
- **Entity:** Represents entities in the application, often associated with ORM techniques.
- **Enums:** Contains constants and enum classes for representing different elements.
- **View:** Manages UI components, windows, and user interaction.

## How to Run
1. Clone the project to your computer.
2. Execute the `tourism_agency.sql` file in PostgreSQL.
3. Open the project files in a Java IDE.
4. Run the `App` class to start the application.

## Notes
- Further updates will be implemented.
- For a demo of the project, [click here](https://www.loom.com/share/d49ce4b9bdfa444f94b1bea7167a767b).

