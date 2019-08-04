*** Setting ***
   
Library    SeleniumLibrary

*** Variables ***
${BROWSER}	Chrome
${URL2TEST}	http://the-internet.herokuapp.com/hovers
# to execute this test launch locally installed chromedriver.exe before test execution
${GRID_URL}	http://localhost:9515

*** Test Cases ***
Test Selenium Robot
	[Setup]	Open Browser To Landing Page
	Highlight Element	figure	5	red
	Mouse Over		//div[@class='figure'][1]
	Wait Until Element Is Visible		//div[@class='figcaption'][1] 
	Highlight Element	figcaption	2	blue
	Element Should Be Visible	//div[@class='figcaption'][1]
	[Teardown]	Close Browser

*** Keywords ***
Open Browser To Landing Page
	Open Browser	${URL2TEST}	${BROWSER}	remote_url=${GRID_URL}
	Title Should Be	The Internet

Highlight Element
	[Documentation]	Select a WebElement from its class and highlight for requested time.
	[Arguments]		${class_name}		${duration}			${color}
	${locator_find}=	Catenate	SEPARATOR=	class:	${class_name}
	${original_style}=	Get Element Attribute	${locator_find}	style
	${new_style}=	Catenate	border: 2px solid 	${color}	; border-style: dashed;
	Execute Javascript	document.getElementsByClassName('${class_name}')[0].setAttribute('style', '${new_style}')
	Run Keyword If	${duration} > 0	Sleep	${duration}
	Execute Javascript	document.getElementsByClassName("${class_name}")[0].setAttribute('style', '${original_style}')
