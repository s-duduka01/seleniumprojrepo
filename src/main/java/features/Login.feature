Feature: Application login

Scenario: Login with valid credentails
Given Open any browser
And Navigated to the page
When  I enter emailaddress as "sairam@gmail.com" and password as "omsairam" into the feilds
And user clicks on login button
Then verify is able to successfully login

#Github
# password -sunitha@1988
#Username - Sunitha