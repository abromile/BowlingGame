import java.util.ArrayList;


public class Player 
{
	private String name;
	private ArrayList<Integer> firstThrows = new ArrayList<Integer>(Master.NUM_FRAMES);
	private ArrayList<Integer> secondThrows = new ArrayList<Integer>(Master.NUM_FRAMES);
	private int extraBall = 0;
	private int score;
	
	
	
	//************ CONSTRUCTORS *************************
	
	/**
	 * constructor with default name
	 */
	public Player()
	{
		name = "Player"; // should be "Player #"
		score = 0;
		for (int i = 0; i < firstThrows.size(); i++)
		{
			firstThrows.add(0);
			secondThrows.add(0);
		}
	}
	
	/**
	 * constructor with name
	 * @param n; name of player
	 */
	public Player(String n)
	{
		name = n;
		score = 0;
		for (int i = 0; i < firstThrows.size(); i++)
		{
			firstThrows.add(0);
			secondThrows.add(0);
		}
	}
	
	//**************** SETTERS **************************
	
	public void setName(String n)
	{
		name = n;
	}
	
	public void setScore(int s)
	{
		score = s;
	}
	
	public void setThrow1(int frame, int score)
	{
		firstThrows.set(frame, score);
	}
	
	public void setThrow2(int frame, int score)
	{
		secondThrows.set(frame, score);
	}
	
	public void addThrow1(int frame, int score)
	{
		firstThrows.add(score);
	}
	
	public void addThrow2(int frame, int score)
	{
		secondThrows.add(score);
	}
	
	public void setExtraBall(int score)
	{
		extraBall = score;
	}
	
	//**************** GETTERS **************************
	
	public String getName()
	{
		return name;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int getThrow1(int frame)
	{
		return firstThrows.get(frame);
	}
	
	public int getThrow2(int frame)
	{
		return secondThrows.get(frame);
	}
	
	public int getExtraBall()
	{
		return extraBall;
	}
	
	public ArrayList<Integer> getThrow1s()
	{
		return firstThrows;
	}
}
