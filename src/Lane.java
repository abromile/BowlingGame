import java.io.ByteArrayInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Lane 
{
	private static PlayerManager playerManager;
	public static Scanner scan;
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
			String newName = scan.nextLine();
			while (newName.length() > 10)
			{
				System.out.println("Name must be 10 or less characters.");
				System.out.println("Enter Player 1 name: ");
				newName = scan.nextLine();
			}
			playerManager.addPlayer(newName);
			runGame();
		}
		//if user wants to quit
		else if (cmd.equals("q"))
		{
			System.out.println("Goodbye!");
			System.exit(0);
		}
		else
		{
			System.out.println("You have entered an invalid command. ");
			displayWelcomeMenu();
			String command = scan.nextLine();
			runWelcomeMenuCommand(command);
		}
	}

	public static void printBanner(int curr_player, int curr_frame)
	{
		System.out.println("");
		System.out.print("PLAYER: ");
		System.out.print(playerManager.getPlayerName(curr_player));
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
		int 	throw1 = 0,
				throw2 = 0;
		boolean flag = false;
		String 	cmd;

		//FIRST THROW
		while(!flag)
		{
			printBanner(curr_player, curr_frame);
			System.out.println("Press 'm' for menu or enter current throw. ");
			System.out.println("Throw 1: ");
			cmd = scan.nextLine();
			
			if (cmd.equals("m"))
			{
				startMenu();
			}
			else
			{
			    try
			    {  
					throw1 = Integer.parseInt(cmd);
					if(throw1 > 10 || throw1 < 0)
					{
						 System.out.println("Please enter a valid throw."); 
					}
					else
					{
						playerManager.setThrow1m(curr_player, curr_frame, throw1);
						flag = true;
						if (throw1 == 10)
						{
							playerManager.getPlayer(curr_player).setThrow2(curr_frame, 0);
							System.out.println("STRIKE!");
						}
					}
			    }
			    catch(NumberFormatException e)
			    { 
			        System.out.println("Please enter a valid throw."); 
			    }
			    catch(NullPointerException e)
			    {
			    	System.out.println("Please enter a valid throw."); 
			    }
			}
		}
		calculateScore(curr_player);
		board.printBoard(playerManager.getPlayerList());
	
		//SECOND THROW
		if(throw1 < 10)
		{	
			flag = false;
			while(!flag)
			{
				printBanner(curr_player, curr_frame);
				System.out.println("Press 'm' for menu or enter current throw. ");
				System.out.println("Throw 2: ");
				cmd = scan.nextLine();
				
				if (cmd.equals("m"))
				{
					startMenu();
					printBanner(curr_player, curr_frame);
				}
				else
				{
				    try
				    {  
						throw2 = Integer.parseInt(cmd);
						if(throw2 > 10 || throw2 < 0 || throw1 + throw2 > 10)
						{
							 System.out.println("Please enter a valid throw."); 
						}
						else
						{
							playerManager.setThrow2m(curr_player, curr_frame, throw2);
							flag = true;
							if ((throw1 + throw2) == 10)
							{
								System.out.println("SPARE!");
							}
						}
				    }
				    catch(NumberFormatException e)
				    { 
				        System.out.println("Please enter a valid throw."); 
				    }
				    catch(NullPointerException e)
				    {
				    	System.out.println("Please enter a valid throw."); 
				    }
				}
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
		int 	throw1 = 0,
				throw2 = 0,
				extrathrow = 0;
		boolean flag = false;
		String 	cmd;

		//FIRST THROW
		while(!flag)
		{
			printBanner(curr_player, curr_frame);
			System.out.println("Press 'm' for menu or enter current throw. ");
			System.out.println("Throw 1: ");
			cmd = scan.nextLine();
			
			if (cmd.equals("m"))
			{
				startMenu();
				printBanner(curr_player, curr_frame);
			}
			else
			{
			    try
			    {  
					throw1 = Integer.parseInt(cmd);
					if(throw1 > 10 || throw1 < 0)
					{
						 System.out.println("Please enter a valid throw."); 
					}
					else
					{
						playerManager.setThrow1m(curr_player, curr_frame, throw1);
						flag = true;
						if (throw1 == 10)
						{
							playerManager.getPlayer(curr_player).setThrow2(curr_frame, 0);
							System.out.println("STRIKE!");
						}
					}
			    }
			    catch(NumberFormatException e)
			    { 
			        System.out.println("Please enter a valid throw."); 
			    }
			    catch(NullPointerException e)
			    {
			    	System.out.println("Please enter a valid throw."); 
			    }
			}
		}
		calculateScore(curr_player);
		board.printBoard(playerManager.getPlayerList());
	
		//SECOND THROW
		flag = false;
		while(!flag)
		{
			printBanner(curr_player, curr_frame);
			System.out.println("Press 'm' for menu or enter current throw. ");
			System.out.println("Throw 2: ");
			cmd = scan.nextLine();
			
			if (cmd.equals("m"))
			{
				startMenu();
				printBanner(curr_player, curr_frame);
			}
			else
			{
			    try
			    {  
					throw2 = Integer.parseInt(cmd);
					if(throw2 > 10 || throw2 < 0 || (throw1 + throw2 > 10 && throw1 != 10))
					{
						 System.out.println("Please enter a valid throw."); 
					}
					else
					{
						playerManager.setThrow2m(curr_player, curr_frame, throw2);
						flag = true;
						if(throw1 == 10 && throw2 == 10)
						{
							System.out.println("STRIKE!");
						}
						else if ((throw1 + throw2) == 10 && throw2 != 0)
						{
							System.out.println("SPARE!");
						}
					}
			    }
			    catch(NumberFormatException e)
			    { 
			        System.out.println("Please enter a valid throw."); 
			    }
			    catch(NullPointerException e)
			    {
			    	System.out.println("Please enter a valid throw."); 
			    }
			}
		}
		calculateScore(curr_player);
		board.printBoard(playerManager.getPlayerList());
		
		//EXTRA THROW
		if(throw1 == 10 || throw2 == 10 || throw1 + throw2 == 10)
		{
			flag = false;
			while(!flag)
			{
				printBanner(curr_player, curr_frame);
				System.out.println("Press 'm' for menu or enter current throw. ");
				System.out.println("Throw 3: ");
				cmd = scan.nextLine();
				
				if (cmd.equals("m"))
				{
					startMenu();
					printBanner(curr_player, curr_frame);
				}
				else
				{
				    try
				    {  
						extrathrow = Integer.parseInt(cmd);
						if(extrathrow > 10 || extrathrow < 0 || (throw2 + extrathrow > 10 && (throw2 != 10 && throw1 + throw2 != 10)))
						{
							 System.out.println("Please enter a valid throw."); 
						}
						else
						{
							playerManager.getPlayer(curr_player).setExtraThrow(extrathrow);
							flag = true;
							if(throw2 == 10 && extrathrow == 10)
							{
								System.out.println("STRIKE!");
							}
							else if (throw2 + extrathrow == 10 && extrathrow != 0)
							{
								System.out.println("SPARE!");
							}
						}
				    }
				    catch(NumberFormatException e)
				    { 
				        System.out.println("Please enter a valid throw."); 
				    }
				    catch(NullPointerException e)
				    {
				    	System.out.println("Please enter a valid throw."); 
				    }
				}
			}
			calculateScore(curr_player);
			board.printBoard(playerManager.getPlayerList());
		}
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
		displayMenu();
		String command = scan.nextLine();
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
				String newName = scan.nextLine();
				while (newName.length() > 10)
				{
					System.out.println("Name must be 10 or less characters.");
					System.out.println("Enter new player's name: ");
					newName = scan.nextLine();
				}
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
				System.out.println("Which player? (Enter a number 1-" + playerManager.playerCount() + ")");
				int player_to_remove = scan.nextInt();
				if(player_to_remove < 1 || player_to_remove > playerManager.playerCount())
				{
					System.out.println("Player " + player_to_remove + " doesn't exist");
				}
				else
				{
					playerManager.removePlayer(player_to_remove - 1);
					board.printBoard(playerManager.getPlayerList());
				}
			}
		}

		//EDIT THROW
		else if (cmd.equals("e"))
		{
			System.out.println("Which player? (Enter a number 1-" + playerManager.playerCount() + ")");
			int player = scan.nextInt();
			if(player < 1 || player> playerManager.playerCount())
			{
				System.out.println("Player " + player + " doesn't exist");
			}
			else
			{
				System.out.println("Which frame? (Enter a number 1-10)");
				eframe = scan.nextInt();
				if(eframe <= 10 || eframe >= 1)
				{
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
				}
				else
				{
					System.out.println("Invalid frame.");
				}
			}
			

		}

		//EDIT PLAYER NAME
		else if (cmd.equals("p"))
		{
			System.out.println("Which player? (Enter a number 1-" + playerManager.playerCount() + ")");
			String player = scan.nextLine();
			player_to_change = Integer.parseInt(player);
			if(player_to_change < 1 || player_to_change > playerManager.playerCount())
			{
				System.out.println("Player " + player + " doesn't exist");
			}
			else
			{
				System.out.println("What is the new name? ");
				String name = scan.nextLine();
				while (name.length() > 10)
				{
					System.out.println("Name must be 1 to 10 characters in length");
					System.out.println("What is the new name? ");
					name = scan.nextLine();
				}
				playerManager.editName(player_to_change - 1, name);
				board.printBoard(playerManager.getPlayerList());
			}
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
			playerManager.getPlayer(player).setScore(playerManager.getPlayer(player).getScore() + 10 + playerManager.getPlayer(player).getThrow2(9) + playerManager.getPlayer(player).getExtraThrow());
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
		scan = new Scanner(System.in);
		playerManager = new PlayerManager();
		board = new Board();
		//set up for new game

		//Main menu
		displayWelcomeMenu();
		
		//interpret commands
		runWelcomeMenuCommand(scan.nextLine());
	}

}
