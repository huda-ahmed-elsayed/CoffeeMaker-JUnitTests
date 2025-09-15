# CoffeeMaker-JUnit

## Overview
CoffeeMaker-JUnit is a Java-based application that simulates a coffee maker machine. The project includes a fully functional system under test (SUT) and comprehensive JUnit tests to ensure reliability and correctness. The application allows users to manage recipes, inventory, and coffee purchases with proper input validation and exception handling.

---

## Features
- **Recipe Management:** Add, edit, and delete coffee recipes with proper validation.
- **Inventory Management:** Add and check inventory levels for coffee, milk, sugar, and chocolate.
- **Coffee Preparation:** Make coffee, process payment, and calculate change.
- **User Interface:** Console-based interface for interacting with the CoffeeMaker.
- **Exception Handling:** Handles invalid inputs and inventory/recipe errors gracefully.

---

## Project Structure
- `build.gradle` – Build file for compiling the SUT and tests, executing tests, and measuring coverage.  
- `gradlew` – Gradle wrapper script for UNIX/Linux systems.  
- `gradlew.bat` – Gradle wrapper script for Windows systems.  
- `Requirements-CoffeeMaker.pdf` – Requirements document for the CoffeeMaker project.  
- `ClassDiagram-CoffeeMaker.pdf` – UML class diagram of the CoffeeMaker software.  
- `SequenceDiagram-CoffeeMaker.pdf` – Sequence diagram showing object interactions.  
- `src/main/java` – Contains the system under test (SUT; the CoffeeMaker code) and all dependencies.  
  - `coffeemaker.*` – Java package containing the CoffeeMaker source code.
  - `coffeemaker.exceptions.*` – Java package containing the CoffeeMaker Exception source code.  
- `src/test/java` – Contains JUnit test code.  
  - `coffeemakertests.CoffeeMakerTest` – JUnit tests for the CoffeeMaker class (update as needed).   

> **Note:** The build/ directory is generated during build and testing. It is not included in version control.

---

## Technologies
- Java 8+
- JUnit 4/5
- Gradle for build automation and dependency management
