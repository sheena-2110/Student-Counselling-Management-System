# Student Counselling System

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [Database Schema](#database-schema)
- [Screenshots](#screenshots)
- [Contributing](#contributing)
- [License](#license)

---

## Introduction
The **Student Counselling System** is a Java Swing-based application that helps students find universities based on selected criteria such as category, distance learning, HEC recognition, and city.

---

## Features
- **Welcome Screen** with a start button.
- **User Registration Form** to input name, username, password, and email.
- **Additional Info Form** where users select preferences.
- **Database Integration** using MySQL to store and retrieve data.
- **Save & Access Options** for selecting university filters and retrieving matching universities.
- **Interactive UI** designed using Java Swing components.

---

## Technologies Used
- **Java (JDK 21+)**
- **Swing (GUI Framework)**
- **MySQL (Database)**
- **JDBC (Java Database Connectivity)**
- **IntelliJ IDEA / Eclipse (IDE)**

---

## Project Structure
```
StudentCounsellingSystem/
│-- src/
│   │-- WelcomeScreen.java
│   │-- RegistrationForm.java
│   │-- AdditionalInfoForm.java
│-- database/
│   │-- oopproject.sql
│-- README.md
│-- LICENSE
```

---

## Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/StudentCounsellingSystem.git
   ```
2. Open the project in an IDE (IntelliJ/Eclipse).
3. Install MySQL and create a database:
   ```sql
   CREATE DATABASE oopproject;
   ```
4. Import the provided `oopproject.sql` file into MySQL.
5. Update database credentials in the Java files:
   ```java
   Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oopproject", "root", "password");
   ```
6. Compile and run the program:
   ```sh
   javac WelcomeScreen.java
   java WelcomeScreen
   ```

---

## Usage
1. **Run the program** - The Welcome Screen appears.
2. Click **Start Now** to go to the Registration Form.
3. **Register** with your details.
4. Fill in additional information and **Save**.
5. Click **Access** to retrieve universities matching the selected filters.

---

## Database Schema
Table: **universities**
| Column Name       | Data Type   | Description                          |
|------------------|------------|--------------------------------------|
| id               | INT (PK)    | Auto-increment primary key          |
| name             | VARCHAR(255)| University name                      |
| category         | VARCHAR(50) | Public or Private                   |
| distance_learning| VARCHAR(10) | Yes or No                            |
| hec_recognition  | VARCHAR(10) | Yes or No                            |
| city             | VARCHAR(100)| City name                            |

Table: **selections**
| Column Name       | Data Type   | Description                          |
|------------------|------------|--------------------------------------|
| id               | INT (PK)    | Auto-increment primary key          |
| full_name        | VARCHAR(255)| User's full name                     |
| username         | VARCHAR(100)| Username                             |
| email            | VARCHAR(255)| Email                                |
| category         | VARCHAR(50) | Selected category                    |
| distance_learning| VARCHAR(10) | Selected distance learning option   |
| hec_recognition  | VARCHAR(10) | Selected HEC recognition option     |
| city             | VARCHAR(100)| Selected city                        |

---

## Screenshots
### Welcome Screen
![Welcome Screen](https://via.placeholder.com/600x400)

### Registration Form
![Registration Form](https://via.placeholder.com/600x400)

### Additional Info Form
![Additional Info Form](https://via.placeholder.com/600x400)

---

## Contributing
Contributions are welcome! Please fork the repository and submit a pull request with your changes.

---

## License
This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.
