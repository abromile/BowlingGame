import static org.junit.Assert.*;

import org.junit.Test;


public class PlayerManagerTest
{
	PlayerManager pm = new PlayerManager();
	
	@Test
	public void testEverything()
	{
		assertEquals("Player count should be 0 initially", pm.playerCount(), 0);
		
		pm.removePlayer(0);
		pm.removePlayer(99);
		assertEquals("Player count should still be 0", pm.playerCount(), 0);
		
		pm.addPlayer("Jojo");
		assertEquals("Player count should be 1 now", pm.playerCount(), 1);
		
		pm.addPlayer("Mango");
		assertEquals("Player count should be 2 now", pm.playerCount(), 2);
		
		assertEquals("Player 1 should be Jojo", pm.getPlayerName(0), "Jojo");
		
		assertEquals("Player 2 should be Jojo", pm.getPlayerName(1), "Mango");
		
		assertEquals("Player 3 shouldn't exist", pm.getPlayerName(2), null);
		
		assertEquals("Player 1's score should be 0", pm.getPlayer(0).getScore(), 0);
		
		pm.editScore(0, 300);
		pm.editScore(0, -1);
		pm.editScore(0, 301);
		assertEquals("Player 1's score should now be 300", pm.getPlayer(0).getScore(), 300);
		
		pm.setThrow1m(0, 4, 10);
		pm.setThrow2m(0, 0, 6);
		assertEquals("Player 1's 1st throw on frame 5 should be 10", pm.getThrow1m(0, 4), 10);
		assertEquals("Player 1's 2nd throw on frame 1 should be 6", pm.getThrow2m(0, 0), 6);
		
		pm.editName(0, "Brendan");
		assertEquals("Player 1's name should be Brendan", pm.getPlayerName(0), "Brendan");
		
		pm.editName(0, "Ma");
		assertEquals("Player 1's name should be Ma", pm.getPlayerName(0), "Ma");
		
		pm.removePlayer(1);
		assertEquals("Player count should now be 1", pm.playerCount(), 1);
		
		pm.removePlayer(0);
		assertEquals("Player count should still be 1", pm.playerCount(), 1);
	}
}
