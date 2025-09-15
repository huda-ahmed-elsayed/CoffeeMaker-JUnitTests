# CoffeeMaker-JUnit

## Overview
CoffeeMaker-JUnit is a Java-based application that simulates a coffee maker machine. The project includes a fully functional system under test (SUT) and comprehensive JUnit tests to ensure reliability and correctness. The application allows users to manage recipes, inventory, and coffee purchases with proper input validation and exception handling.

This project was developed as part of a software engineering course and demonstrates object-oriented design, exception handling, and test-driven development.

---

## Features
- **Recipe Management:** Add, edit, and delete coffee recipes with proper validation.
- **Inventory Management:** Add and check inventory levels for coffee, milk, sugar, and chocolate.
- **Coffee Preparation:** Make coffee, process payment, and calculate change.
- **User Interface:** Console-based interface for interacting with the CoffeeMaker.
- **Exception Handling:** Handles invalid inputs and inventory/recipe errors gracefully.

---

## Project Structure
CoffeeMaker-JUnit/
│
├── build.gradle # Gradle build file for compilation, testing, and coverage
├── gradlew # Gradle wrapper for UNIX/Linux
├── gradlew.bat # Gradle wrapper for Windows
├── README.md # Project documentation
├── Requirements-CoffeeMaker.pdf
├── ClassDiagram-CoffeeMaker.pdf
├── SequenceDiagram-CoffeeMaker.pdf
│
├── src/
│ ├── main/java/edu/ncsu/csc326/coffeemaker/
│ │ ├── CoffeeMaker.java
│ │ ├── Main.java
│ │ ├── Recipe.java
│ │ ├── RecipeBook.java
│ │ ├── Inventory.java
│ │ └── exceptions/
│ │ ├── InventoryException.java
│ │ └── RecipeException.java
│ │
│ └── test/java/edu/ncsu/csc326/coffeemaker/
│ └── CoffeeMakerTest.java

yaml
Copy code

> **Note:** The `build/` directory is generated during build and testing. It is not included in version control.

---

## Technologies
- Java 8+
- JUnit 4/5
- Gradle for build automation and dependency management
