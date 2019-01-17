import unittest
from selenium import webdriver
from time import sleep
from selenium.webdriver.common.action_chains import ActionChains
from selenium.webdriver.support.wait import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.by import By


class FirstPythonTest(unittest.TestCase):

    driver = None

    def setUp(self):
        print("#### Before")
        self.driver = webdriver.Chrome('<FILL_WITH_CORRECT_PATH>\\chromedriver.exe')


    def tearDown(self):
        print("#### After")
        self.driver.quit()

    def testName(self):
        self.driver.get("http://the-internet.herokuapp.com/hovers")
        
        avatar = self.driver.find_element_by_class_name('figure')
        self.highlightElement(avatar, 5, "red");
        
        ActionChains(self.driver).move_to_element(avatar).perform()
        
        wait = WebDriverWait(self.driver, timeout=5)
        wait.until(EC.visibility_of_element_located((By.CLASS_NAME, 'figcaption')))

        caption = self.driver.find_element_by_class_name('figcaption')
        self.highlightElement(avatar, 2, "blue");
        
        self.assertTrue(caption.is_displayed(), 'Caption element is NOT showed!')
      
    def highlightElement(self, element, duration, color):
        """This routine highlight WebElement for requested time."""
        original_style = element.get_attribute('style')
        self.driver.execute_script('arguments[0].setAttribute(arguments[1], arguments[2])', element, 'style', "border: 2px solid "+str(color)+"; border-style: dashed;")
        if int(duration) > 0:
            sleep(duration)
            self.driver.execute_script('arguments[0].setAttribute(arguments[1], arguments[2])', element, 'style', str(original_style))

if __name__ == "__main__":
    #import sys;sys.argv = ['', 'Test.testName']
    unittest.main()