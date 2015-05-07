Feature: Remove Player

	Both players and employees should be able to remove players from the game

	Scenario Outline: Removing a player from a game with one player
		Given there is one player in the game
		When player "<player>" is removed
		Then playercount should not decrease

	Examples:
		| player 	|
		| 1			|
		| 2			|
		| -1		|
		| 3			|
		| 6			|
		| 7			|

	Scenario Outline: Removing a player from a game with two players
		Given there are two players in the game
		When player "<player>" of the two is removed
		Then playercount should decrease to one if a valid player was selected for removal

	Examples:
		| player 	|
		| 1			|
		| 2			|
		| -1		|
		| 3			|
		| 7			|

	Scenario Outline: Removing a player from a game with six players
		Given there are six players in the game
		When player "<player>" of the six is removed
		Then playercount should decrease to five if a valid player was selected for removal

	Examples:
		| player 	|
		| 1			|
		| 2			|
		| -1		|
		| 3			|
		| 7			|