University Management System – Phase One Capstone

## Overview

This project is a **console-based University Management System** developed in Java.
It allows managing students, courses, enrollments, tuition calculations, and grades.

The system demonstrates important Object-Oriented Programming concepts such as:

* Encapsulation
* Inheritance
* Abstract classes
* Polymorphism
* Exception handling
* File persistence (CSV storage)

The program runs through a **menu-based console interface**.

---

## Features

### Student Management

The system allows registration of two types of students:

**Undergraduate Students**

* Pay tuition using a **flat rate**

**Graduate Students**

* Pay tuition using **per credit rate + research fee**

---

### Course Management

Users can create courses with:

* Course code
* Course name
* Number of credits
* Maximum number of students

---

### Enrollment

Students can enroll in courses.
The system prevents:

* Enrolling the same student twice
* Exceeding the course capacity

Custom exceptions used:

* `CourseFullException`
* `StudentAlreadyEnrolledException`

---

### Grades

Grades can be added for enrolled courses.

Grades are stored as numeric values (0–100) and converted into **letter grades**:

| Score    | Letter |
| -------- | ------ |
| 80–100   | A      |
| 70–79    | B      |
| 60–69    | C      |
| 50–59    | D      |
| Below 50 | F      |

---

### Tuition Calculation

Tuition is calculated differently depending on the student type.

Undergraduate:

```
Tuition = Flat Rate
```

Graduate:

```
Tuition = (Credits × Per Credit Rate) + Research Fee
```

---

### Dean's List

Students with **GPA greater than 3.5** appear on the Dean's List.

---

### Data Persistence

All data is saved using **CSV files** so that information is not lost when the program closes.

Files used:

```
data/students.csv
data/courses.csv
data/enrollments.csv
```

The system automatically loads saved data when the program starts.

---

## Technologies Used

* Java
* Object-Oriented Programming
* Collections (ArrayList, Map)
* File Handling
* CSV Storage

---

## Project Structure

```
src/
 ├── model/
 │    ├── Person
 │    ├── Student
 │    ├── UndergraduateStudent
 │    ├── GraduateStudent
 │    ├── Instructor
 │    └── Course
 │
 ├── manager/
 │    └── UniversityManager
 │
 ├── persistence/
 │    └── FileManager
 │
 ├── exceptions/
 │    ├── CourseFullException
 │    └── StudentAlreadyEnrolledException
 │
 └── Main.java
```

---

## How to Run

1. Clone the repository

```
git clone https://github.com/Jeannette1453/Phase-One-Capstone-Project.git
```

2. Open the project in IntelliJ IDEA.

3. Run the **Main.java** file.

---

## Author

Uwimbabazi Jeannette
Software Engineering Student
