# SeleniumThreeBindings

This is a comparative example of three famous language bindings for Selenium. Repository is structured in three folders and each one contains one test automation project based on Selenium. Each project is ready to be executed and it contains a test suite with one test case. Eache test case performs below steps:

* Create Chrome WebDriver instance (mandatory Chrome/Chromium installed and webdriver implementation)
* Loading tested web page
* Simulate mouse over event on first card shown on the left
* Verify visibility of card name
* During test, interaction with element is highlighted with red/blue dashed lines

## sample_java
It's based on Maven as project descriptor, to execute test case use command
> mvn clean test

## sample_python
It's based on local python interpreter, to execute test case use command
> python -m unittest test\FirstPythonTest.py

PS to be correclty used install Selenium library in python interpreter (e.g. via pip)

## sample_robot
It based on local python interpreter and robot library, to execute test case use command
> python -m robot -d results test\FirstRobotTest.robot

PS to be correctly used install Robot Framework Selenium library in python interpreter (e.g. via pip)
Moreover before execute test case, launch manually chrome webdriver
