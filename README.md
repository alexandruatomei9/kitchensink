# kitchensink
Spring Boot Kitchensink Example

This application represents a [Spring Boot](https://spring.io/projects/spring-boot) version of the [Kitchensink JBoss EAP Quickstart](https://github.com/jboss-developer/jboss-eap-quickstarts/tree/8.0.x/kitchensink) and it uses [Thymeleaf](https://www.thymeleaf.org/) for the presentation layer and an in-memory H2 database for persistence.

### Prerequisites
**Java 21** should be installed on the target machine. You can check it with:
```bash
java -version
```

If Java 21 is not installed, please install it, then proceed with the next steps.

### Steps to Run the Application

1. Clone the repository using the command below:
```bash
git clone <repository-url>
````

2. **Navigate to the Project Directory**: Once the project is transferred, open a terminal and navigate to the root directory of the project (where **mvnw** and **pom.xml** are located):
```bash
cd /path/to/project
```

3. **Give Execution Permission to mvnw**: If running on a Unix-based system (like Linux or macOS), you might need to make mvnw executable:
```bash
chmod +x mvnw
```

4. **Run the App Using the Maven Wrapper**: Run the app using the mvnw command:
```bash
./mvnw spring-boot:run
```
For Windows, use:
```bash
mvnw.cmd spring-boot:run
```

5. **Access the App**: By default, Spring Boot apps run on port 8080. Open a browser and navigate to http://localhost:8080 to access the app.

