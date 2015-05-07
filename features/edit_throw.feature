Feature: Edit Throw

	Both players and employees should be able to edit players' throws

	Scenario Outline: Editing a player's throw
		Given player "<player>" is to have their throw edited
		When throw "<throw>" of frame "<frame>" for the player is changed to "<score>"
		Then the player's throw should reflect the desired changes as long as the input was valid

	Examples:
		| player | throw | frame | score |
		|   1    |   1   |   1   |   5   |
		|   1    |   2   |   1   |   5   |
		|   1    |   1   |   5   |   5   |
		|   1    |   2   |   5   |   5   |
		|   1    |   1   |   1   |   10  |
		|   1    |   1   |   1   |   0   |
		|   2    |   1   |   1   |   5   |
		|   2    |   1   |   1   |   10  |
		|   2    |   1   |   1   |   0   |
		|   -1   |   1   |   1   |   5   |
		|   1    |   -1  |   -1  |   5   |
		|   1    |   1   |   1   |   -1  |
		|   1    |   1   |   1   |   11  |
		|   7    |   1   |   1   |   1   |
		|   1    |   3   |   10  |   1   |
		|   1    |   3   |   10  |   11  |