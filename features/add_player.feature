Feature: Add Player

	Both players and employees should be able to add players to the game

	Scenario Outline: Adding a player to a game with one player
		Given there is one player in the game
		When player two with name "<name>" is added
		Then player two with the correct name should now be in the game
			And the player count should increase to two as long as the name is valid

	Examples:
		| name        |
		| Jeremiah    |
		| 1234567890  |
		| 12345678901 |

	Scenario Outline: Adding a player to a game with two players
		Given there are two players in the game
		When player three with name "<name>" is added
		Then player three with the correct name should now be in the game
			And the player count should increase to three as long as the name is valid

	Examples:
		| name        |
		| Jeremiah    |
		| 1234567890  |
		| 12345678901 |

	Scenario Outline: Adding a player to a game with six players
		Given there are six players in the game
		When player seven with name "<name>" is added
		Then player seven with the correct name should not be in the game
			And the player count should remain the same

	Examples:
		| name        |
		| Jeremiah    |
		| 1234567890  |
		| 12345678901 |