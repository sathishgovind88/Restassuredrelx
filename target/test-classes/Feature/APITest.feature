Feature: Create new users and update them

Scenario: New users should be created and updated 
	Given List of all users
	When Add the users
	Then Verify the user is added
	When Update the user details	
	When Get specific user details
	