import unittest
from time import sleep
from selenium import webdriver
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support.expected_conditions import visibility_of_element_located
from selenium.webdriver.common.by import By


class FirstPythonTest(unittest.TestCase):

    driver = None

    def setUp(self):
        print("#### Before")
        # to execute this test launch locally installed chromedriver.exe before test execution
        self.driver = webdriver.Remote(
			command_executor = 'http://localhost:9515',
			desired_capabilities = {
			'browserName': 'chrome',
			'javascriptEnabled': True
			})

    def tearDown(self):
        print("#### After")
        self.driver.quit()

    def testSeleniumPython(self):
        self.driver.get("http://the-internet.herokuapp.com/hovers")
        
        self.highlightElement('figure', 5, 'red')
        
        avatar = self.driver.find_element_by_xpath("//div[@class='figure'][1]")
        caption = self.driver.find_element_by_xpath("//div[@class='figcaption'][1]")

        ActionChains(self.driver).move_to_element(avatar).perform()
        
        wait = WebDriverWait(self.driver, timeout=5)
        wait.until(visibility_of_element_located((By.XPATH, "//div[@class='figcaption'][1]")))

        self.highlightElement('figcaption', 2, 'blue')
        
        self.assertTrue(caption.is_displayed(), 'Caption element is NOT showed!')
      
    def highlightElement(self, class_name, duration, color):
        """Select a WebElement from its class and highlight for requested time."""
        element = self.driver.find_element_by_class_name(class_name)
        original_style = element.get_attribute('style')
        self.driver.execute_script(
            'arguments[0].setAttribute(arguments[1], arguments[2])', 
            element, 
            'style', 
            "border: 2px solid "+str(color)+"; border-style: dashed;")
        if int(duration) > 0:
            sleep(duration)
        self.driver.execute_script(
            'arguments[0].setAttribute(arguments[1], arguments[2])', 
            element, 
            'style', 
            str(original_style))

if __name__ == "__main__":
    unittest.main()