# SeleniumThreeBindings

This is a comparative example of three famous launguage bindings for Selenium. Repository is structured as three folder and each one container one test automation project based on Selenium. Each project contains one test case that performs below steps:

* Create Chrome WebDriver instance (mandatory Chrome/Chromium installed and webdriver implementation)
* Loading tested url web page
* Simulate a mouse over first card on the left
* Verify visibility of card name
* During test interaction with element is highlighted with red/blue colors 

## sample_java
It's based on Maven as project descriptor, to execute test case use command
> mvn clean test

## sample_python
It's based on local installed python shell and unittest library, to execute test case use command
> python -m unittest test\FirstPythonTest.py

PS to be correclty used install Selenium library in python interpreter (e.g. use pip)

## sample_robot
It based on local installed python shell and robot library, to execute test case use command
> python -m robot -d results test\FirstRobotTest.robot

PS to be correctly used install Robot Framework Selenium library in python interpreter (e.g. use pip)
Moreover launch manually chrome webdriver implementation locally
