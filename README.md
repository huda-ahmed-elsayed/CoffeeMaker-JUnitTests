# CoffeeMaker-JUnit

## Overview
CoffeeMaker-JUnit is a Java-based application simulating a coffee maker machine. It allows users to manage recipes, inventory, and coffee purchases with proper validation and exception handling. The project includes comprehensive JUnit tests to ensure reliability and correctness.

---

## Features
- **Recipe Management:** Add, edit, and delete coffee recipes.
- **Inventory Management:** Add and check inventory for coffee, milk, sugar, and chocolate.
- **Coffee Preparation:** Make coffee, process payment, and calculate change.
- **Console-based User Interface:** Interact with the CoffeeMaker through a text-based menu.
- **Exception Handling:** Gracefully handles invalid input, inventory, and recipe errors.

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
  - `coffeemaker.exception.*` – Java package containing the CoffeeMaker Exception source code.  
- `src/test/java` – Contains JUnit test code.  
  - `coffeemakertests.CoffeeMakerTest` – JUnit tests for the CoffeeMaker class (update as needed).  

---

## Technologies
- Java 8+
- JUnit 4/5
- Gradle for build automation
- JaCoCo for code coverage

