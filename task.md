# swf.a WCC SPŠEi Kratochvílova 2023/2024

## Lesson 10 - Sonar Cube and Sonar Lint

* Explain what Sonar Cube is
* Explain what Sonar Lint is
    * Install Sonar Lint plugin into IntelliJ IDEA.
    * Shortly - Sonar, JaCoCo and Code Coverage
* Below are 10 Java class examples with typical issues that SonarLint should report.
    * Example 1:
  ````java
  public class MagicNumberClass {
    public void calculate() {
      int salary = 40 * 80; // Non-compliant; magic numbers found.
      // SonarLint might suggest to define them as named constants for better readability.
      int salary = 40 * 80;
    }
  }
  ````

    * Example 2:
  ````java
  import java.io.BufferedReader;
  import java.io.FileReader;
  import java.io.IOException;

  public class UnclosedResources {
    public void readFile(String path) throws IOException { // Exception 'IOException' is never thrown in the method
      BufferedReader reader = new BufferedReader(new FileReader(path));
      String firstLine = reader.readLine(); // Non-compliant; resource leak, reader not closed.
      // SonarLint would suggest a try-with-resources statement to ensure closure of the stream.
    }
  }
  ````
    * Example 3:
  ````java
  public class EqualityCheck {
    public boolean stringCompare(String a, String b) {
      return a == b; // Non-compliant; reference equality used instead of content equality.
      // SonarLint would point out the need to use .equals() for string comparison.
    }
  }
  ````
    * Example 4:
  ````java
  public class UnusedVariable {
    public void doSomething() {
        int unusedNumber = 10; // Non-compliant; unused local variables should be removed.
        // SonarLint would flag this as dead code.
        System.out.println("Hello");
    }
  }
  ````
    * Example 5:
  ````java
  public class ExceptionSwallow {
    public void loadFile(String path) {
      try {
          // Some file loading code...
          Files.open(Path.of(path));
      } catch (Exception e) {
          // Non-compliant; exception is swallowed and not logged.
          // SonarLint would flag this and suggest at least logging the exception.
      }
    }
  }
  ````

    * Example 6:
   ````java
  public class HardcodedCredentials {
    public void connect() {
      String username = "admin"; // Non-compliant; hardcoded credential - username.
      String password = "pass1234"; // Non-compliant; hardcoded credential - password.
      // SonarLint would flag this as a security hotspot and suggest externalizing credentials.
    }
  }
  ````

    * Example 7:
  ````java
    public class CyclomaticComplexity {
      // Non-compliant; this method has too high cyclomatic complexity.
      // SonarLint would suggest refactoring to reduce complexity.
      public void tooComplexMethod(int a) {
          if (a > 1) {
              // Some code
              if (a > 2) {
                  // Some more code
                  if (a > 3) {
                      // Some more code
                      if (a > 4) {
                          // Even more code...
                      }
                  }
              }
          }
      }
  }
  ````

    * Example 8:
  ````java
  public class NullPointerRisk {
    public void printLength(String str) {
        // str may be null!    
        if (str.length() > 0) { // Non-compliant; potential NullPointerException.
            // SonarLint would suggest null checking before accessing the method.
            System.out.println("String length: " + str.length());
        }
    }
  }
  ````

    * Example 9:
  ````java
  public class namingConventions {
      public int MY_CONSTANT = 42; // Non-compliant; constants should be static final and uppercase.
      public int MY_VALUE = 12; // Non-compliant; the same + wrong constant name.
      public void addvalue(int a, int B) { // Non-compliant; method parameters should be camelCase.
          // SonarLint would flag this for violating naming conventions.
      }
  }
  ````

    * Example 10:
  ````java
  import java.util.Date;
  import java.util.Calendar;
  
  public class DeprecatedAPIUsage {
    public void checkDate() {
      Date newDate = new Date(2023, Calendar.JANUARY, 1); // Non-compliant; deprecated constructor.
      // SonarLint would flag this and suggest using Calendar or java.time.LocalDate instead.
    }
  }
  ````

### Practise

* Create a simple Spring Boot CRUD application based on Person class.
    * Create a new project with the name ``lesson10``. Open [spring-boot-project/spring-boot-starters/spring-boot-starter-web](https://start.spring.io/); depends on:
        * spring-boot-starter-data-jpa
        * spring-boot-starter-web
        * h2 database
    * Fill in ``application.properties`` with:
        * spring.datasource.driverClassName=org.h2.Driver
        * spring.datasource.username=sa
        * spring.datasource.password=test
        * spring.datasource.url=jdbc:h2:file:/Users/dzabensky/tmp/lesson10/person
        * spring.h2.console.path=/h2-console
        * spring.h2.console.settings.trace=true
        * spring.h2.console.enabled=true
    * Use ``com.uu.course`` as a group name, ``lesson10`` as an artifact version.
    * Add Spring Web dependency.
    * Generate a new project
        * First, create an ``@Entity`` named ``Person`` class with the attributes ``id`` (with ``@Id`` annotation), ``name``, ``surname``, and ``age``. Add setters and getters, hash code and equals, add toString. Add appropriate javadoc.
    * Move class under ``com.uu.course.lesson10`` package.
    * Then, create a JPA repository for Person; use the ``@Repository`` annotation; add ``findById``, ``findAll``, ``findAllByName`` methods.
    * Create a @Service PersonService class that provides methods for creating, reading, updating, and deleting Person objects:
        * hint: add method ``addPerson``, add javadoc
        * hint: add method ``getPerson``, add javadoc
        * hint: add method ``updatePerson``, add javadoc
        * hint: add method ``deletePerson``, add javadoc
        * hint: add method ``getAllPersons``, add javadoc
        * hint: add method ``getPersonById``, add javadoc
        * hint: add method ``getPersonsByName`` to search person by ``name`` and ``surname``, add javadoc
        * class will use PersonRepository provided above
    * Finally, create a simple CRUD controller ``PersonController`` using the ``PersonService`` class for ``Person`` (create, read, update, delete). All operations should be available via REST endpoints under "/person" API. Do not forget to add proper javadoc to all methods.
    * Create a test case for the Person repository using ``@DataJpaTest`` annotation. Test all repository methods.
    * Create a test case for the Person service using ``@SpringBootTest`` annotation. Test all service methods.
    * Create a test case for the Person controller using ``@WebMvcTest`` annotation. Test all controller methods.
    * Run Sonar Lint and check the results and code coverage.
    * Open [H2 Console](https://localhost:8080/h2-console) and check the database.

### Goal 10

Introduce Sonar Cube and Sonar Lint. 

