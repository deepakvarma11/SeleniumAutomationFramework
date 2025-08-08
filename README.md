# Selenium Automation Framework

## Overview
This project is a modular Selenium WebDriver automation framework using TestNG and Allure for advanced reporting. It supports Page Object Model, reusable utilities, and rich test reporting with screenshots and step annotations.

---

## Key Features

- **Page Object Model (POM):** Clean separation of test logic and UI interactions for maintainability.
- **Reusable Utilities:** Common functions for driver management, waiting, logging, property reading, and screenshots.
- **Allure Reporting:** Advanced test reports with step annotations, attachments (screenshots, logs, request details), and easy navigation.
- **TestNG Integration:** Flexible test execution, grouping, and suite management.
- **Exception Handling:** Custom exceptions for robust error management.
- **Configurable:** Easily switch environments and logging levels via property files.
- **Listeners:** Automated screenshot capture and reporting on test failures.
- **Modular Structure:** Organized codebase for scalability and team collaboration.
- **GitHub Actions Integration:** Automated CI/CD pipeline runs tests and generates Allure reports on every push or pull request.
- **Allure Report on GitHub Pages:** Allure test reports are published to GitHub Pages for easy online access and sharing.

---

## OOPS Concepts Used

- **Encapsulation:** Classes encapsulate data and behavior, exposing only necessary methods (e.g., driver management, screenshot helper, page objects).
- **Inheritance:** Interfaces (e.g., `ILoginPage`, `IProductPage`, `IdriverManagement`) are implemented by concrete classes, enabling code reuse and extension.
- **Polymorphism:** Interfaces and method overriding allow different implementations to be used interchangeably (e.g., multiple page objects).
- **Abstraction:** Interfaces and abstract classes define contracts for functionality without exposing implementation details.
- **Composition:** Classes use other classes as members to build complex functionality (e.g., page objects using utility classes).
- **Exception Handling:** Custom exception classes provide robust error management and abstraction of error sources.
- **Modularity:** The framework is organized into logical packages for easy maintenance and scalability.

---

## Project Structure

- `src/main/java/constants/` - Test group/type constants
- `src/main/java/pageObject/` - Page Object Model interfaces and implementations
- `src/main/java/utils/` - Utilities (logging, property reading, screenshot helper, driver management, etc.)
- `src/test/java/tests/` - Test classes (e.g., `LoginTest`, `ProductTest`, `BaseTest`)
- `src/test/java/utils/` - Test listeners (e.g., Allure integration)
- `src/main/resources/` - Config files (`config.properties`, `log4j2.xml`)
- `src/test/resources/` - Allure config (`allure.properties`), test suites
- `target/` - Build output, Allure results, TestNG reports

---

## Setup Instructions

1. **Clone the repository**
2. **Install dependencies**
   - Ensure Java and Maven are installed
   - Run: `mvn clean install`
3. **Configure properties**
   - Edit `src/main/resources/config.properties` for environment settings
   - Logging: `src/main/resources/log4j2.xml`
   - Allure: `src/test/resources/allure.properties`

---

## Running Tests

- Run all tests:
  ```
  mvn test
  ```
- Run specific suite:
  ```
  mvn test -DsuiteXmlFile=src/test/resources/TestSuites/YourSuite.xml
  ```

---

## Allure Reporting

### How to Clean and Generate New Report
- Clean previous results:
  ```
  mvn allure:clean
  ```
- Run tests:
  ```
  mvn test
  ```
- Generate report:
  ```
  mvn allure:report
  ```
- Serve report in browser:
  ```
  mvn allure:serve
  ```

### Customizing Report Output Folder
- By default, Allure uses `target/allure-results/` for raw results.
- To change output folder, set in `allure.properties`:
  ```
  allure.results.directory=target/allure-results
  allure.report.directory=target/allure-report
  ```

---

## Screenshot Attachment in Allure

- Use `@Attachment` annotation in a helper method:
  ```java
  @Attachment(value = "Screenshot", type = "image/png")
  public byte[] attachScreenshot() {
      return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }
  ```
- Call this method in your test or listener on failure.

---

## Step Annotation in Allure

- Annotate methods with `@Step("Description")` for step visibility in Allure.
- Ensure Allure TestNG adapter is included in `pom.xml`.

---

## Example: Attaching Request Details

```java
@Attachment(value = "Request Details", type = "text/plain")
public String attachRequestDetails(String request) {
    return request;
}
```
- This attaches the provided string (e.g., API request details) as a plain text file in the Allure report.

---

## Example: Failure Screenshot Attachment

```java
@Attachment(value = "Failure Screenshot", type = "image/png")
public byte[] captureFailureScreenshot(WebDriver driver) {
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
}
```
- Call this in your test or listener when a test fails.

---

## Troubleshooting

- **Screenshots not attached:**
  - Ensure `@Attachment` is used and method is called
  - Check Allure dependencies in `pom.xml`
- **Report not generated in target folder:**
  - Check `allure.properties` for correct directory
  - Use Maven commands as above
- **@Step annotation not showing:**
  - Ensure correct Allure TestNG adapter
  - Use `@Step` on public methods

---

## Parallel Testing

- **TestNG Parallel Execution:** The framework supports parallel test execution using TestNG's suite configuration. You can run tests, classes, or methods in parallel by setting the `parallel` attribute in your TestNG XML suite files.
- **Thread-Safe WebDriver:** WebDriver instances are managed using `ThreadLocal` to ensure thread safety during parallel runs.
- **Scalability:** Parallel execution reduces overall test execution time and allows for efficient resource utilization.
- **How to Use:**
  - Edit your TestNG suite XML (e.g., `src/test/resources/TestSuites/YourSuite.xml`) and set:
    ```xml
    <suite name="Suite" parallel="tests" thread-count="4">
      ...
    </suite>
    ```
  - Adjust `thread-count` as needed for your environment.

---

## GitHub Actions & Allure Report on GitHub Pages

- **CI/CD Integration:** The framework uses GitHub Actions to automatically run tests on every push or pull request. Tests are executed using Maven, and Allure results are generated.
- **Allure Report Publishing:** After test execution, the Allure report is published to GitHub Pages, making it accessible online for easy sharing and review.
- **How it works:**
  - Workflow runs tests and generates Allure results in `target/allure-results`.
  - Allure report is built and deployed to the `gh-pages` branch using GitHub Actions.
  - The report is available at `https://<your-github-username>.github.io/<your-repo-name>/`.

---

## References

- [Allure Documentation](https://docs.qameta.io/allure/)
- [TestNG Documentation](https://testng.org/doc/)
- [Selenium WebDriver](https://www.selenium.dev/documentation/)

---

## Contact
For questions or support, contact the project maintainer.
