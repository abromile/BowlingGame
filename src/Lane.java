/* Brendan Reis & Ashley Bromiley
 * CS 347
 * I pledge my honor that I have abided by the Stevens Honor System
 */

import java.util.Scanner;

public class Lane 
{
	private static PlayerManager playerManager;
	private static Scanner scan;
	private static Board board;
	
	private static boolean debugFlag;
	private static String debugTarget;
	private static String[] debugParams;
	
	private static String[] commands;
	private static int currentCommand;

	public static String getCommand()
	{
		if(debugFlag && currentCommand < commands.length)
		{
			if(currentCommand == commands.length - 1)
			{
				checkOutput();
				return null;
			}
			else
			{
				currentCommand++;
				return commands[currentCommand];
			}
		}
		else
		{
			return scan.nextLine();
		}
	}
	
	/**
	 * just displays the startup menu
	 */
	public static void displayWelcomeMenu()
	{
		System.out.println("Welcome to the Stevens Bowling Alley!");
		System.out.println();
		System.out.println("s: Start a new game");
		System.out.println("q: Quit");
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
			System.out.print("Enter Player 1 name: ");
			String newName = getCommand();
			while (newName.length() > 10)
			{
				System.out.println("Name must be 10 or less characters.");
				System.out.print("Enter Player 1 name: ");
				newName = getCommand();
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
			String command = getCommand();
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
			System.out.print("Throw 1: ");
			cmd = getCommand();
			
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
				System.out.print("Throw 2: ");
				cmd = getCommand();
				
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
			System.out.print("Throw 1: ");
			cmd = getCommand();
			
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
			System.out.print("Throw 2: ");
			cmd = getCommand();
			
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
				System.out.print("Throw 3: ");
				cmd = getCommand();
				
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
		String command = getCommand();
		runCommand(command);
	}

	/**
	 * just displays the menu
	 */
	public static void displayMenu()
	{
		System.out.println("a: Add player");
		System.out.println("r: Remove player");
		System.out.println("e: Edit throw");
		System.out.println("p: Edit player name");
		System.out.println("b: Back to game");
	}

	/**
	 * determines what to do and does current command
	 * @param cmd; command to be done
	 */
	/**
	 * determines what to do and does current command
	 * @param cmd; command to be done
	 */
	public static void runCommand(String cmd)
	{
		//ADD PLAYER
		if (cmd.equals("a"))
		{
			if (playerManager.playerCount() == 6)
			{
				System.out.println("You can not add any more players.");
			}

			else
			{
				System.out.print("Enter new player's name: ");
				String newName = getCommand();
				/*
				while (newName.length() > 10)
				{
					System.out.println("Name must be 10 or less characters.");
					System.out.println("Enter new player's name: ");
					newName = getCommand();
				}
				*/
				if(newName.length() > 10)
				{
					System.out.println("Name must be 10 or less characters.");
				}
				else
				{
					playerManager.addPlayer(newName);
					board.printBoard(playerManager.getPlayerList());
				}
			}
		}

		//REMOVE PLAYER
		else if (cmd.equals("r"))
		{
			if (playerManager.playerCount() == 1)
			{
				System.out.println("Need at least one player in the game!");
			}
			else
			{
				System.out.println("Which player? (Enter a number 1-" + playerManager.playerCount() + ")");
				try
				{
					int ePlayer = Integer.parseInt(getCommand());
					if(ePlayer < 1 || ePlayer > playerManager.playerCount())
					{
						System.out.println("Player " + ePlayer + " doesn't exist!");
					}
					else
					{
						playerManager.removePlayer(ePlayer - 1);
						board.printBoard(playerManager.getPlayerList());
					}
				}
				catch(NumberFormatException e)
				{
					System.out.println("Invalid value!");
				}
				catch(NullPointerException e)
				{
					System.out.println("Invalid value!");
				}
			}
		}

		//EDIT THROW
		else if (cmd.equals("e"))
		{
			System.out.println("Which player? (Enter a number 1-" + playerManager.playerCount() + ")");
			try
			{
				int ePlayer = Integer.parseInt(getCommand());
				if(ePlayer < 1 || ePlayer > playerManager.playerCount())
				{
					System.out.println("Player " + ePlayer + " doesn't exist");
				}
				else
				{
					System.out.println("Which frame? (Enter a number 1-10)");
					int eFrame = Integer.parseInt(getCommand());
					if(eFrame <= 10 || eFrame >= 1)
					{
						if (eFrame == 10)
						{
							System.out.println("Which throw? (Enter 1, 2, or 3)");
						}
						else 
						{
							System.out.println("Which throw? (Enter 1 or 2)");
						}
						int eThrow = Integer.parseInt(getCommand());
						if((eFrame == 10 && eThrow == 3) || (eThrow == 1 || eThrow == 2))
						{
							System.out.println("What should the throw be now? (Enter a number 1-10)");
							int newScore = Integer.parseInt(getCommand());
							if(newScore >= 0 && newScore <= 10)
							{
								if(eFrame == 10 && eThrow == 3)
								{
									playerManager.getPlayer(ePlayer - 1).setExtraThrow(newScore);
								}
								else if(eThrow == 1)
								{
									playerManager.setThrow1m(ePlayer - 1, eFrame - 1, newScore);
								}
								else
								{
									playerManager.setThrow2m(ePlayer - 1, eFrame - 1, newScore);
								}
							}
							else
							{
								System.out.println("Invalid score!");
							}
						}
						else
						{
							System.out.println("Invalid throw!");
						}
					}
					else
					{
						System.out.println("Invalid frame!");
					}
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid value!");
			}
			catch(NullPointerException e)
			{
				System.out.println("Invalid value!");
			}
		}

		//EDIT PLAYER NAME
		else if (cmd.equals("p"))
		{
			System.out.println("Which player? (Enter a number 1-" + playerManager.playerCount() + ")");
			try
			{
				String player = getCommand();
				int ePlayer = Integer.parseInt(player);

				if(ePlayer < 1 || ePlayer > playerManager.playerCount())
				{
					System.out.println("Player " + ePlayer + " doesn't exist");
				}
				else
				{
					System.out.print("What is the new name?: ");
					String name = getCommand();
					while (name.length() > 10)
					{
						System.out.println("Name must be 1 to 10 characters in length");
						System.out.print("What is the new name?: ");
						name = getCommand();
					}
					playerManager.editName(ePlayer - 1, name);
					board.printBoard(playerManager.getPlayerList());
				}
			}
			catch(NumberFormatException e)
			{
				System.out.println("Invalid value!");
			}
			catch(NullPointerException e)
			{
				System.out.println("Invalid value!");
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
	
	private static void setupDebugParams(String[] values)
	{
		int valIndex = 2;
		
		// Setup debug parameters	
		int size;
		for(size = valIndex; !(values[size].equals("/")); size++)
		{
			;
		}
		debugParams = new String[size - valIndex];
		for(int paramIndex = 0; paramIndex < debugParams.length; paramIndex++, valIndex++)
		{
			debugParams[paramIndex] = values[valIndex];
		}
		
		// Setup program input
		for(size = valIndex; size < values.length; size++)
		{
			;
		}
		commands = new String[size - valIndex];
		for(int cmdIndex = 0; cmdIndex < commands.length; cmdIndex++, valIndex++)
		{
			commands[cmdIndex] = values[valIndex];
		}
	}
	
	private static void checkOutput()
	{
		if(debugTarget.equals("edit_name"))
		{
			int player = Integer.parseInt(debugParams[0]);
			String name = debugParams[1];
			if(
					((player >= 1 && player <= playerManager.playerCount() && name.length() <= 10)
							&& name.equals(playerManager.getPlayerName(player-1)))
					||
					(((player < 1 || player > playerManager.playerCount()) || name.length() > 10)
							&& !(name.equals(playerManager.getPlayerName(player-1))))
			)
				System.out.println("pass");
			else
				System.out.println("fail");
		}
		else if(debugTarget.equals("add_player"))
		{
			String name = debugParams[0];
			int oldPlayerCount = Integer.parseInt(debugParams[1]);
			if(
					((name.length() <= 10 && oldPlayerCount < 6)
							&& (name.equals(playerManager.getPlayerName(oldPlayerCount))))
					||
					((name.length() > 10 || oldPlayerCount >= 6)
							&& !(name.equals(playerManager.getPlayerName(oldPlayerCount))))
			)
				System.out.println("pass");
			else
				System.out.println("fail");
			if(
					((name.length() <= 10 && oldPlayerCount < 6)
							&& (name.equals(playerManager.getPlayerName(oldPlayerCount)) && playerManager.playerCount() == oldPlayerCount + 1))
					||
					((name.length() > 10 || oldPlayerCount >= 6)
							&& !(name.equals(playerManager.getPlayerName(oldPlayerCount)) && playerManager.playerCount() == oldPlayerCount))
			)
				System.out.println("pass");
			else
				System.out.println("fail");
		}
		else if(debugTarget.equals("remove_player"))
		{
			int player = Integer.parseInt(debugParams[0]);
			int oldPlayerCount = Integer.parseInt(debugParams[1]);
			if(
					(player <= oldPlayerCount && player >= 1 && playerManager.playerCount() == oldPlayerCount - 1)
					||
					((player > oldPlayerCount || player < 1 || oldPlayerCount == 1) && playerManager.playerCount() == oldPlayerCount)
			)
				System.out.println("pass");
			else
			{
				System.out.println("fail");
			}		
		}
		else if(debugTarget.equals("edit_throw"))
		{
			int player = Integer.parseInt(debugParams[0]);
			int eFrame = Integer.parseInt(debugParams[1]);
			int eThrow = Integer.parseInt(debugParams[2]);
			int newScore = Integer.parseInt(debugParams[3]);
			if(eThrow == 1 || eThrow == 2)
			{
				if(
					(((player <= playerManager.playerCount() && player >= 1)
						&& (eFrame <= 10 && eFrame >= 1)
						&& (eThrow == 1 || eThrow == 2 || (eThrow == 3 && eFrame == 10))
						&& (newScore <= 10 && newScore >= 0))
					&& ((eThrow == 1 ? playerManager.getThrow1m(player - 1, eFrame - 1) : playerManager.getThrow2m(player - 1, eFrame - 1)) == newScore))
					||
					!(((player <= playerManager.playerCount() && player >= 1)
							&& (eFrame <= 10 && eFrame >= 1)
							&& (eThrow == 1 || eThrow == 2 || (eThrow == 3 && eFrame == 10))
							&& (newScore <= 10 && newScore >= 0)))
				)
					System.out.println("pass");
				else
					System.out.println("fail");
			}
			else
			{			
				if(
						(((player <= playerManager.playerCount() && player >= 1)
							&& (eFrame <= 10 && eFrame >= 1)
							&& (eThrow == 1 || eThrow == 2 || (eThrow == 3 && eFrame == 10))
							&& (newScore <= 10 && newScore >= 0))
						&& (playerManager.getPlayer(player - 1).getExtraThrow() == newScore))
						||
						!(((player <= playerManager.playerCount() && player >= 1)
								&& (eFrame <= 10 && eFrame >= 1)
								&& (eThrow == 1 || eThrow == 2 || (eThrow == 3 && eFrame == 10))
								&& (newScore <= 10 && newScore >= 0)))
					)
						System.out.println("pass");
					else
						System.out.println("fail");
			}
		}
		else
		{
			System.out.println("Error: Unrecognized debug target!");
		}
		System.exit(0);
	}

	public static void main(String[] args)
	{
		// Check if debugging and setup input
		if(args.length > 1 && args[0].equals("debug"))
		{
			System.out.println("***DEBUG MODE***");
			debugFlag = true;
			debugTarget = args[1];
			setupDebugParams(args);		
			currentCommand = 0;
		}
		else
		{
			debugFlag = false;
			
		}
		scan = new Scanner(System.in);		
		
		// Initialize player manager and board
		playerManager = new PlayerManager();
		board = new Board();

		// Main menu
		displayWelcomeMenu();
		
		// Interpret commands
		runWelcomeMenuCommand(getCommand());
	}

}
