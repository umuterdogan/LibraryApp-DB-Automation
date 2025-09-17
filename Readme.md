# LibraryApp Database Automation (Java · JDBC · MySQL · Cucumber · JUnit · Maven)

This repository presents a Database Test Automation project focused on validating **data integrity** and **consistency** directly at the database level.  
It is designed as part of a QA Automation portfolio to showcase **DB testing skills** with Java, SQL, and JDBC.

---

## This repo demonstrates:
- Database validation with **JDBC** and **MySQL**
- Writing and executing **SQL queries** (DDL & DML) for testing
- Assertions with **JUnit 4** to validate query results
- Behavior-Driven tests with **Cucumber** feature files
- Reporting with **Cucumber HTML** and **Pretty Reports**
- **JUnit/Surefire reports** (XML) for CI/CD portability
- Parallel execution setup via **Maven Surefire plugin**

---

## Tech Stack
- **Java 17**, **Maven**
- **MySQL** database
- **JDBC** for DB connectivity
- **SQL** queries (DDL/DML validation)
- **JUnit 4** for assertions
- **Cucumber JVM 7** (BDD support)
- **Selenium WebDriver** (for UI-DB integration)
- **WebDriverManager** (browser management)
- **JavaFaker** (test data generation)

---

## Repository structure (high-level)
src/test/
├── java/com/libraryApp/
│ ├── pages/ # Page Object Model classes
│ ├── steps/ # Cucumber step definitions
│ ├── utility/ # DB utilities and config readers
│ └── runner/ # Cucumber test runners
├── resources/
│ ├── features/ # Gherkin feature files
│ ├── query/ # SQL query files
│ └── pngs/ # Documentation images
└── config.properties # Environment configuration

---

## Key capabilities
- **Reusable DB Utility** to connect and query MySQL
- **UI-DB Synchronization** validation between web interface and database
- **Data Integrity** testing for CRUD operations
- **Complex Query Testing** (aggregations, joins, business logic)
- **Comprehensive Reporting** (HTML, XML, Pretty Reports)
- **Screenshot Capture** on test failures
- **Environment Configuration** via properties file
- **Automatic Rerun** file generation for failed scenarios

---

## Reporting

### Available Reports
- **Cucumber HTML Report** → `target/cucumber-report.html`
- **Pretty Reports (plugin)** → `target/cucumber/`
- **Surefire Reports (JUnit XML)** → `target/surefire-reports/TEST-*.xml`
- **Rerun File** → `target/rerun.txt` (for failed scenarios)

### Report Features
- Detailed test execution results
- Screenshots on failures
- Step-by-step execution logs
- CI/CD compatible XML reports
- Pretty formatted HTML reports

### Note on Failed Test
- One scenario in this project is **intentionally designed to fail** in order to demonstrate how failure is reported.
- The purpose is to showcase how **Cucumber HTML Reports**, **Pretty Reports**, and **Surefire reports** capture and display failed steps.
- In a real test suite (with correct locators and queries), all tests are expected to pass successfully.

---

## Example scenarios
- Validate that newly created users are stored in the DB
- Verify record deletion is reflected in the DB
- Check total number of records matches expected values
- Assert business rules (e.g., unique email addresses)

---

## Configuration and secrets
- Database credentials and URL are configured in `config.properties`
- All credentials (UI and DB) have been **replaced with dummy values** in the `config.properties` file.
- **All credentials and environment URLs have been sanitized** for security
- The original database connection (`library2` schema on MySQL) has been **removed** from this repository for privacy reasons.
- In real setups, credentials should be provided via environment variables or CI/CD secrets

---

## Contributing
This project is part of a QA Automation portfolio. For questions or suggestions, please open an issue or contact the repository owner.

---

## How to run
Run tests with Maven:

```bash
mvn clean test
