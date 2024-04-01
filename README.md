# WEB Test Automation framework

## Description

- This framework is designed to automate WEB UI actions that is performed on browsers like chrome, firefox, safari.
- This is implemented using JAVA as a core language and Selenium as a Browser automation library

## How to setup

- Clone the repository :  ``` git clone https://github.com/reponame ```
- Import the project using any IDE like intelliJ, Eclipse, VS code
- Open terminal in the project root directory
- Run : ``` mvn clean test ```

## How to run

### Running test case

- Via IntelliJ - During development only
    - Click on Run button against each Test method
    - Click on Run button against class to run all tests in the same class
- Via TestNG file - During developement only
    - Running sanity suite
        - Right click on sanity.xml and run
    - Running regress suite
        - Right click on regression.xml and run
- Via maven command line - to run via CI/CD
    - To run all tests for regression test
        - ``` mvn clean test ```
    - To run only sanity tests
        - ``` mvn clean test -Dgroups=sanity ```
    - To run tests belongs to specific feature
        - ``` mvn clean test -Dgroups=login,products,payment ```
    - To run all tests but exclude a particular feature or group
        - ``` mvn clean test -DexcludedGroups=login ```
    - To run all tests but not in parallel
        - ``` mvn clean test -DparallelMode=none ```
    - To run Tests on a selected environment , Browser and Browser configuration
        - ``` mvn clean test -DENVIRONMENT=QA -DBROWSER_NAME=chrome -DIS_HEADLESS=false```

## How to check and update maven dependencies

- Check for updates ( Does not modify) : ```mvn versions:use-releases```
- Update to latest releases  : ```mvn versions:use-latest-releases```

## Test case writing guidelines

## Steps

1. Create test class under src/test/java/packagename
2. Ex: ``` src/test/login.LoginPageTest ```
3. Always follow a meanigful naming standard to write package, class, method, variable , file names
4. Once class is created, extend it with ``` BaseTest.java ```
5. Provide required paramters such as groups, description for each test methods
6. Use data provider if needed
7. Examples:
   ``` java
    class LoginPageTest extends BaseTest{
        @Test(description="Describe the test here",
        groups={"sanity", "login"})
        void verifyUserIsAbleToLoginWithStandardcredentials(){
                // write code here
        }

    }
   ```





