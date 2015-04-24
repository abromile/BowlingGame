import java.util.InputMismatchException;
import java.util.Scanner;

public class Lane 
{
	private static PlayerManager playerManager;
	public static Scanner scan = new Scanner(System.in);
	private static Board board;

	/**
	 * just displays the startup menu
	 */
	public static void displayWelcomeMenu()
	{
		System.out.println("Welcome to the Stevens Bowling Alley!");
		System.out.println();
		System.out.println("s: start a new game");
		System.out.println("q: quit");
	}

	/**
	 * determines and does what needs to be done based on given command
	 * @param cmd; command to be interpreted
	 */
	public static void runWelcomeMenuCommand(String cmd)
	{	
		//start new game
		if (cmd.equals("s"))
		{
			//must have at least one player
			System.out.println("Enter Player 1 name: ");
			String newName = scan.next();
			playerManager.addPlayer(newName);
			runGame();
		}
		//if user wants to quit
		if (cmd.equals("q"))
		{
			System.out.println("Goodbye!");
			System.exit(0);
		}
	}

	public static void printBanner(int curr_player, int curr_frame)
	{
		System.out.println("");
		System.out.print("PLAYER: ");
		System.out.print(playerManager.getPlayerAt(curr_player));
		System.out.println("   FRAME: " + (curr_frame+1));
		System.out.println("----------------------------------------");
	}

	/**
	 * runs the first 9 frames with the standard format
	 * @param curr_player; index of current player
	 * @param curr_frame; index of current frame
	 */
	public static void runFrame(int curr_player, int curr_frame)
	{
		int throw1 = 0, throw2 = 0;

		printBanner(curr_player, curr_frame);

		//FIRST THROW
		System.out.println("Press 'm' for menu or enter current throw. 1");
		System.out.println("Throw 1: ");
		String cmd = scan.next();

		if (cmd.equals("m"))
		{
			while(cmd.equals("m"))
			{
				startMenu();
				printBanner(curr_player, curr_frame);
				System.out.println("Press 'm' for menu or enter current throw. 2");
				System.out.println("Throw 1: ");
				cmd = scan.next();
			}
			throw1 = Integer.parseInt(cmd);
			//test
			//System.out.println("changing throw 1 1");
			playerManager.setThrow1m(curr_player, curr_frame, throw1);
		}
		else
		{
			try
			{
				throw1 = Integer.parseInt(cmd);
				playerManager.setThrow1m(curr_player, curr_frame, throw1);
			}
			catch (InputMismatchException e)
			{
				System.out.println("***Press 'm' for menu or enter current throw. ");
			}
		}

		//test
		//playerManager.getPlayer(curr_player).setThrow2(curr_frame, 0);
		calculateScore(curr_player);
		board.printBoard(playerManager.getPlayerList());

		if (throw1 == 10)
		{
			playerManager.getPlayer(curr_player).setThrow2(curr_frame, 0);
			System.out.println("STRIKE!");
		}
		else
		{
			//SECOND THROW
			printBanner(curr_player, curr_frame);
			System.out.println("Press 'm' for menu or enter current throw. 1");
			System.out.println("Throw 2: ");
			cmd = scan.next();

			if (cmd.equals("m"))
			{
				while(cmd.equals("m"))
				{
					startMenu();
					printBanner(curr_player, curr_frame);
					System.out.println("Press 'm' for menu or enter current throw. 2");
					System.out.println("Throw 2: ");
					cmd = scan.next();
				}
				throw2 = Integer.parseInt(cmd);
				//test
				//System.out.println("changing throw 2");
				playerManager.getPlayer(curr_player).setThrow2(curr_frame, throw2);
			}
			else
			{
				try
				{
					throw2 = Integer.parseInt(cmd);
					//test
					//System.out.println("changing throw 2");
					playerManager.getPlayer(curr_player).setThrow2(curr_frame, throw2);
				}
				catch (InputMismatchException e)
				{
					System.out.println("***Press 'm' for menu or enter current throw. ");
				}
			}

			if ((throw1 + throw2) == 10)
			{
				System.out.println("SPARE!");
			}

			calculateScore(curr_player);
			board.printBoard(playerManager.getPlayerList());
		}
	}

	/**
	 * runs the last frame with the extra ball
	 * @param curr_player; index of current player
	 * @param curr_frame; index of current frame
	 */
	public static void runLastFrame(int curr_player, int curr_frame)
	{
		int throw1 = 0,
				throw2 = 0,
				extrathrow = 0;

		printBanner(curr_player, curr_frame);

		//FIRST THROW
		System.out.println("Press 'm' for menu or enter current throw. 1");
		System.out.println("Throw 1: ");
		String cmd = scan.next();

		if (cmd.equals("m"))
		{
			while(cmd.equals("m"))
			{
				startMenu();
				printBanner(curr_player, curr_frame);
				System.out.println("Press 'm' for menu or enter current throw. 2");
				System.out.println("Throw 1: ");
				cmd = scan.next();
			}
			throw1 = Integer.parseInt(cmd);
			//test
			//System.out.println("changing throw 1 1");
			playerManager.setThrow1m(curr_player, curr_frame, throw1);
		}
		else
		{
			try
			{
				throw1 = Integer.parseInt(cmd);
				playerManager.setThrow1m(curr_player, curr_frame, throw1);
			}
			catch (InputMismatchException e)
			{
				System.out.println("***Press 'm' for menu or enter current throw. ");
			}
		}

		//test
		//playerManager.getPlayer(curr_player).setThrow2(curr_frame, 0);
		calculateScore(curr_player);
		board.printBoard(playerManager.getPlayerList());
		if (throw1 == 10)
		{
			playerManager.getPlayer(curr_player).setThrow2(curr_frame, 0);
			System.out.println("STRIKE!");
		}

		//SECOND THROW
		printBanner(curr_player, curr_frame);
		System.out.println("Press 'm' for menu or enter current throw. 1");
		System.out.println("Throw 2: ");
		cmd = scan.next();

		if (cmd.equals("m"))
		{
			while(cmd.equals("m"))
			{
				startMenu();
				printBanner(curr_player, curr_frame);
				System.out.println("Press 'm' for menu or enter current throw. 2");
				System.out.println("Throw 2: ");
				cmd = scan.next();
			}
			throw2 = Integer.parseInt(cmd);
			//test
			//System.out.println("changing throw 2");
			playerManager.getPlayer(curr_player).setThrow2(curr_frame, throw2);
		}
		else
		{
			try
			{
				throw2 = Integer.parseInt(cmd);
				//test
				//System.out.println("changing throw 2");
				playerManager.getPlayer(curr_player).setThrow2(curr_frame, throw2);
			}
			catch (InputMismatchException e)
			{
				System.out.println("***Press 'm' for menu or enter current throw. ");
			}
		}
		if(throw2 == 10)
		{
			System.out.println("STRIKE!");
		}
		else if(throw1 + throw2 == 10)
		{
			System.out.println("SPARE!");
		}
		calculateScore(curr_player);
		board.printBoard(playerManager.getPlayerList());

		//EXTRA THROW
		if(throw1 == 10 || throw2 == 10 || throw1 + throw2 == 10)
		{
			printBanner(curr_player, curr_frame);
			System.out.println("Press 'm' for menu or enter current throw.");
			System.out.println("Throw 3: ");
			cmd = scan.next();

			if (cmd.equals("m"))
			{
				while(cmd.equals("m"))
				{
					startMenu();
					printBanner(curr_player, curr_frame);
					System.out.println("Press 'm' for menu or enter current throw.");
					System.out.println("Throw 3: ");
					cmd = scan.next();
				}
				extrathrow = Integer.parseInt(cmd);
				//test
				//System.out.println("changing throw 2");
				playerManager.getPlayer(curr_player).setExtraBall(extrathrow);
			}
			else
			{
				try
				{
					extrathrow = Integer.parseInt(cmd);
					//test
					//System.out.println("changing throw 2");
					playerManager.getPlayer(curr_player).setExtraBall(extrathrow);
				}
				catch (InputMismatchException e)
				{
					System.out.println("***Press 'm' for menu or enter current throw. ");
				}
			}
			if(throw2 < 10 && throw2 + extrathrow == 10)
			{
				System.out.println("SPARE!");
			}
			else if(extrathrow == 10)
			{
				System.out.println("STRIKE!");
			}
		}
		calculateScore(curr_player);
		board.printBoard(playerManager.getPlayerList());
	}

	/**
	 * checks frames for each player
	 */
	public static void runGame()
	{	
		//first 9 frames
		for(int curr_frame = 0; curr_frame < 9; curr_frame++)
		{
			for(int curr_player = 0; curr_player < playerManager.playerCount(); curr_player++)
			{
				runFrame(curr_player, curr_frame);
			}
		}

		//last frame
		for(int curr_player = 0; curr_player < playerManager.playerCount(); curr_player++)
		{
			runLastFrame(curr_player, 9);
		}
	}

	/**
	 * prints the menu, gets the command, and runs the command
	 */
	public static void startMenu()
	{
		Scanner scan = new Scanner(System.in);
		displayMenu();
		String command = scan.next();
		runCommand(command);
	}

	/**
	 * just displays the menu
	 */
	public static void displayMenu()
	{
		System.out.println("a: add player");
		System.out.println("r: remove player");
		System.out.println("e: edit throw");
		System.out.println("p: edit player name");
		System.out.println("b: back to game");
	}

	/**
	 * determines what to do and does current command
	 * @param cmd; command to be done
	 */
	public static void runCommand(String cmd)
	{
		int ethrow, eframe;
		int player_to_change;

		//ADD PLAYER
		if (cmd.equals("a"))
		{
			if (playerManager.playerCount() == 6)
			{
				System.out.println("You can not add any more players.");
			}

			else
			{
				System.out.println("Enter new player's name: ");
				String newName = scan.next();
				playerManager.addPlayer(newName);
				board.printBoard(playerManager.getPlayerList());
			}
		}

		//REMOVE PLAYER
		else if (cmd.equals("r"))
		{
			if (playerManager.playerCount() == 1)
			{
				System.out.println("Must have at least one player.");
			}

			else
			{
				System.out.println("Which player? (Enter a number 1-6)");
				int player_to_remove = scan.nextInt();
				playerManager.removePlayer(player_to_remove - 1);
				board.printBoard(playerManager.getPlayerList());
			}
		}

		//EDIT THROW
		else if (cmd.equals("e"))
		{
			System.out.println("Which player? (Enter a number 1-6)");
			int player = scan.nextInt();
			System.out.println("Which frame? (Enter a number 1-10)");
			eframe = scan.nextInt();
			if (eframe != 10)
			{
				System.out.println("Which throw? (Enter 1 or 2)");
				ethrow = scan.nextInt();
			}
			else 
			{
				System.out.println("Which throw? (Enter 1 or 2 or 3)");
				ethrow = scan.nextInt();
			}
			System.out.println("What is the new score? ");
			int score = scan.nextInt();
			if (ethrow == 1)
			{
				playerManager.getPlayer(player - 1).setThrow1(eframe - 1, score);
			}
			if (ethrow == 2)
			{
				playerManager.getPlayer(player - 1).setThrow2(eframe - 1, score);
			}
			board.printBoard(playerManager.getPlayerList());

		}

		//EDIT PLAYER NAME
		else if (cmd.equals("p"))
		{
			System.out.println("Which player? (Enter a number 1-6)");
			player_to_change = scan.nextInt();
			System.out.println("What is the new name? ");
			playerManager.editName(player_to_change - 1, scan.next());
			board.printBoard(playerManager.getPlayerList());
		}

		//RETURN TO GAME
		else if (cmd.equals("b"))
		{
			return;
		}

		//command not recognized
		else
		{
			System.out.println("Command not recognized.");
		}

	}

	/**
	 * calculates and changes the score of a player
	 * @param player; player whose score to change
	 */
	public static void calculateScore(int player)
	{
		playerManager.getPlayer(player).setScore(0);
		for (int i = 0; i < 9; i++) //for each frame
		{
			//if they got a strike
			if (playerManager.getPlayer(player).getThrow1(i) == 10)
			{
				//add 10 to the score, plus the next 2 throws
				//if the player got another strike, don't use next frame, go two frames ahead
				if (playerManager.getPlayer(player).getThrow1(i+1) == 10)
				{
					if (i == 8)
					{
						playerManager.getPlayer(player).setScore(playerManager.getPlayer(player).getScore() + 10 + playerManager.getPlayer(player).getThrow1(i+1) + playerManager.getPlayer(player).getThrow2(i+1));
					}
					else
					{
						playerManager.getPlayer(player).setScore(playerManager.getPlayer(player).getScore() + 10 + playerManager.getPlayer(player).getThrow1(i+1) + playerManager.getPlayer(player).getThrow1(i+2));
					}
				}
				//if they didn't get a strike, do it normally
				else
				{
					playerManager.getPlayer(player).setScore(playerManager.getPlayer(player).getScore() + 10 + playerManager.getPlayer(player).getThrow1(i+1) + playerManager.getPlayer(player).getThrow2(i+1));
				}
				//test/////
				//System.out.print("Strike case FRAME ");
				//System.out.println(i+1);
				//System.out.println(playerManager.getPlayer(player).getScore()); //score
				//System.out.println(playerManager.getPlayer(player).getThrow1(i+1)); //throw1 of next frame
				//System.out.println(playerManager.getPlayer(player).getThrow2(i+1)); //throw2 of next frame
				// plus 10
			}
			//if throw1 and 2 equal 10 (spare)
			else if ((playerManager.getPlayer(player).getThrow1(i) + playerManager.getPlayer(player).getThrow2(i)) == 10)
			{
				//add 10 to the score, plus the next throw
				playerManager.getPlayer(player).setScore(playerManager.getPlayer(player).getScore() + 10 + playerManager.getPlayer(player).getThrow1(i+1));
				//test////
				//System.out.print("Spare case FRAME ");
				//System.out.println(i+1);
				//System.out.println(playerManager.getPlayer(player).getScore()); //score
				//System.out.println(playerManager.getPlayer(player).getThrow1(i+1)); //throw1 of next frame
				//plus 10
			}
			// if there was no strike or spare, add the two throws to the score 
			else
			{
				playerManager.getPlayer(player).setScore(playerManager.getPlayer(player).getScore() + playerManager.getPlayer(player).getThrow1(i) + playerManager.getPlayer(player).getThrow2(i));
				//test////
				//System.out.print("Normal case FRAME ");
				//System.out.println(i+1);
				//System.out.println(playerManager.getPlayer(player).getScore()); //score
				//System.out.println(playerManager.getPlayer(player).getThrow1(i)); //throw1 of current frame
				//System.out.println(playerManager.getPlayer(player).getThrow2(i)); //throw2 of current frame
			}
		}

		//this is for the score of the last frame since it's different

		//if they got a strike
		if (playerManager.getPlayer(player).getThrow1(9) == 10)
		{
			//add 10 + next 2 balls
			playerManager.getPlayer(player).setScore(playerManager.getPlayer(player).getScore() + 10 + playerManager.getPlayer(player).getThrow2(9) + playerManager.getPlayer(player).getExtraBall());
		}

		//if they got a spare
		else if ((playerManager.getPlayer(player).getThrow1(9) + playerManager.getPlayer(player).getThrow2(9)) == 10)
		{
			//add 10 + next ball
			playerManager.getPlayer(player).setScore(playerManager.getPlayer(player).getScore() + 10 + playerManager.getPlayer(player).getThrow2(9));
		}

		//if they don't get an extra ball
		else
		{
			playerManager.getPlayer(player).setScore(playerManager.getPlayer(player).getScore() + playerManager.getPlayer(player).getThrow1(9) + playerManager.getPlayer(player).getThrow2(9));
		}
	}

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		playerManager = new PlayerManager();
		board = new Board();

		//set up for new game

		//Main menu
		displayWelcomeMenu();
		//interpret commands
		runWelcomeMenuCommand(scan.next());
	}

}

