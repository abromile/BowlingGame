Feature: Edit Name

	Both players and employees should be able to edit the names of players

	Scenario Outline: Editing a player's name
		Given player "<player>" is to have their name edited
		When the Edit Name option is selected
			And the name is changed by the user to "<name>"
		Then the player's name should now be the new name as long as the player and name are valid

	Examples:
		| player  |    name     |
		|    1    | Jeremiah    |
		|    1    |      h      |
		|    1    | xooxooxoox  |
		|    1    | 12345678910	|
		|    2    | Jeremiah    |
		|    2    | 12345678910	|
		|    7    | Jeremiah    |
		|    -1   | Jeremiah    |
		|    7    | 12345678910	|