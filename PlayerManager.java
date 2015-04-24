import java.util.ArrayList;


public class PlayerManager {
	//Player players[] = new Player[Master.MAX_PLAYERS];
	private ArrayList<Player> players;
	
	

	/**
	 * Constructor for player manager instances
	 */
	public PlayerManager()
	{
		players = new ArrayList<Player>(Master.MAX_PLAYERS); // if 2 players in list, .size() = 2, .capacity() = 6
	}

	/**
	 * gets the size of the player list
	 * @return size of player list
	 */
	public int playerCount()
	{
		return players.size();
	}

	/**
	 * edits the players name at an index
	 * @param index; index of player
	 * @param name; new name to change to
	 */
	public void editName(int index, String name)
	{
		if(name.length() < 10)
		{
			players.get(index).setName(name);
		}
	}

	/**
	 * edits the players score at an index
	 * @param index; index of player
	 * @param score; new score to change to
	 */
	public void editScore(int index, int score)
	{
		players.get(index).setScore(score);
	}

	/**
	 * adds a new player with name name to end of list
	 * @param name; name of new player to add
	 */
	public void addPlayer(String name)
	{
		if(players.size() < 6)
		{
			players.add(new Player(name));
			for (int i = 0; i < Master.NUM_FRAMES; i++)
			{
				players.get(players.size()-1).addThrow1(i, 0);
				players.get(players.size()-1).addThrow2(i, 0);
			}
		}
	}

	/**
	 * removes player at an index
	 * @param index; index at which to remove player
	 */
	public void removePlayer(int index)
	{
		if(players.size() > 1)
		{
			players.remove(index);
		}
	}

	/**
	 * gets the name of the player at index index
	 * @param index; index of player
	 * @return name of player at index index
	 */
	public String getPlayerAt(int index)
	{
		return players.get(index).getName();
	}

	public Player getPlayer(int index)
	{
		return players.get(index);
	}
	
	public int getThrow1m(int player, int frame)
	{
		return players.get(player).getThrow1(frame);
	}
	
	public int getThrow2m(int player, int frame)
	{
		return players.get(player).getThrow2(frame);
	}
	
	public void setThrow1m(int player, int frame, int score)
	{
		players.get(player).setThrow1(frame, score);
	}
	
	public void setThrow2m(int player, int frame, int score)
	{
		players.get(player).setThrow2(frame, score);
	}
	
	
	public ArrayList<Player> getPlayerList()
	{
		return players;
	}

}
