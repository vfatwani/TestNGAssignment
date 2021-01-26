Framework:  Selenium-POM-TestNG-Maven
TestNG framework with POM design technique in selenium using Java as scripting language. Maven is used for dependency management and continuous development. TestNG is used to maintain test cases.

Package CommonLibs-
1. Implementation
	1.1 Common Driver- Code to invoke the browser
	1.2 ElementControl class- Contain all the method which interact with web elements. This class contains custom methods to perform user action like click element, sendKeys, etc.
2. Common utils-
    1. Config utils- Configuration.
    2. ReportUtils- To create extent report and add logs
    3. ScreeShotUtils- To capture screenshots if test case fails.

Package Pages - 
1. BasePage- To create instance of ElementControl class
2. WikiPage- Contain all the WebElements of the web page and also contains page methods which perform operations on those WebElements.

Package  Src/test/Java/test/
com.wiki.tests - To write all the tests.
1. BaseTests-it contains all the methods before and after Test.
2. WikiPageTest- Contains all tests and assert statements.


Config - Contains all the key value pair for configuration
Drivers- contains the chrome driver.exe
Reports- Contains the html report.
Screenshots - contains all the failure test screenshot
testXMlFiles- contains the xml file 

Steps to clone execute the tests
git clone https://github.com/vfatwani/TestNGAssignment.git
mvn clean test
