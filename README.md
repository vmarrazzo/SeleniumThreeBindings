# SeleniumThreeBindings

This is a comparative example of three famous language bindings for Selenium. Repository is structured in three folders and each one contains the same automated test case based on Selenium. Each project is ready to be executed with a single command line. Eache test case performs below steps:

* Create Chrome WebDriver instance (it's mandatory to have Chrome/Chromium installed and its WebDriver implementation)
* Open web page @ http://the-internet.herokuapp.com/hovers
* Simulate mouse over event on first card shown on the left
* Verify visibility of card name
* During test, interaction with element is highlighted with red/blue dashed lines

## sample_java
It's a Maven based project and to be executed it's required to have Maven installed on command line.
> mvn clean test

## sample_python
It's based on local python interpreter with both Selenium and unittest library installed.
> python -m unittest test\FirstPythonTest.py

## sample_robot
It's based on local Robot Framework installed as command line, to be executed test case use command
> robot test\FirstRobotTest.robot

PS to be correctly used install Robot Framework and Selenium library in python interpreter (e.g. via pip).