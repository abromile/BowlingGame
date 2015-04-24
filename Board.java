import java.util.ArrayList;


public class Board 
{	

	public Board()
	{

	}

	private void printScores(Player player)
	{

		String n = player.getName();
		int numSpaces = 10 - n.length();

		//print correct number of spaces before name
		for (int i = 0; i < numSpaces; i++)
		{
			System.out.print(" ");
		}

		//print name
		System.out.print(n);

		//for each frame, print ball1 and ball 2
		for (int frame = 0; frame < 9; frame++)
		{
			if (player.getThrow1(frame) == 10)
			{
				System.out.print(" |   X");
			}
			else if ((player.getThrow1(frame) + player.getThrow2(frame)) == 10)
			{
				System.out.print(" | ");
				System.out.print(player.getThrow1(frame));
				System.out.print(" /");
			}
			else
			{
				System.out.print(" | ");
				System.out.print(player.getThrow1(frame));
				System.out.print(" ");
				System.out.print(player.getThrow2(frame));
			}
		}

		//print last frame
		
		System.out.print(" | ");
		
		//first throw
		if(player.getThrow1(9) == 10)
		{
			System.out.print("X ");
		}
		else
		{
			System.out.print(player.getThrow1(9));
			System.out.print(" ");
		}
		
		//second throw
		if(player.getThrow1(9) == 10 && player.getThrow2(9) == 10)
		{
			System.out.print("X ");
		}
		else if(player.getThrow1(9) < 10 && player.getThrow1(9) + player.getThrow2(9) == 10)
		{
			System.out.print("/ ");
		}
		else
		{
			System.out.print(player.getThrow2(9));
			System.out.print(" ");
		}
		
		//third throw
		if((player.getExtraBall() != 0) && (player.getThrow1(9) == 10 || player.getThrow2(9) == 10 || player.getThrow1(9) + player.getThrow2(9) == 10))
		{
			if((player.getThrow2(9) == 10 || (player.getThrow1(9) < 10 && player.getThrow1(9) + player.getThrow2(9) == 10)) && player.getExtraBall() == 10)
			{
				System.out.print("X");
			}
			else if((player.getThrow2(9) + player.getExtraBall() == 10) && (player.getThrow1(9) == 10 || (player.getThrow1(9) + player.getThrow2(9) == 10 && player.getThrow2(9) == 0)))
			{
				System.out.print("/");
			}
			else
			{
				System.out.print(player.getExtraBall());
			}
		}
		else
		{
			System.out.print(player.getExtraBall());
		}
		
		System.out.print(" | ");
		
		//print total score
		System.out.println(player.getScore());
	}

	public void printBoard(ArrayList<Player> pl)
	{
		int size = pl.size();

		System.out.println("     FRAME |  1  |  2  |  3  |  4  |  5  |  6  |  7  |  8  |  9  |  10   | SCORE ");
		for (int i = 0; i < size; i++)
		{
			printScores(pl.get(i));
		}
		System.out.println("      Team Score: ");
	}

}
