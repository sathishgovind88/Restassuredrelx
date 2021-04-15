Feature: Users should able to make the posts and comments

Scenario: Users should able to make the posts and comments in jsonplaceholder 
	Given A list of users making posts and comments
	When I Add the users
	Then Verify the user is added
	When Post to the users
	Then Verify the post is added
	When Comment to the posts
	Then Verify the comment is added
	When Remove the posts
	Then Verify the post is removed
	When Remove the comments
	Then Verify the comment is removed