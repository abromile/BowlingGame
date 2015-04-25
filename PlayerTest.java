import static org.junit.Assert.*;

import org.junit.Test;


public class PlayerTest
{
	
	Player testPlayer = new Player();
	
	@Test
	public void testEverything()
	{
		/*
		 *  set/getName
		 */
		testPlayer.setName("Bob");
		assertEquals("setName(\"Bob\"); should make name 'Bob'", testPlayer.getName(), "Bob");
	
		testPlayer.setName("Marigolds");
		assertEquals("setName(\"Marigolds\") should make name 'Marigolds'", testPlayer.getName(), "Marigolds");
		
		testPlayer.setName("");
		assertEquals("setName(\"\") should make name ''", testPlayer.getName(), "");
		
		testPlayer.setName("12 34 ABCD");
		assertEquals("setName(\"12 34 ABCD\") should make name '12 34 ABCD'", testPlayer.getName(), "12 34 ABCD");
		
		
		/*
		 * set/getThrow1
		 */
		testPlayer.setThrow1(0, 1);
		assertEquals("setThrow1(0, 1) should make the 1st frame's 1st throw 1", testPlayer.getThrow1(0), 1);
		
		testPlayer.setThrow1(9, 1);
		assertEquals("setThrow1(0, 1) should make the last frame's 1st throw 1", testPlayer.getThrow1(9), 1);
		
		testPlayer.setThrow1(0, -1);
		assertEquals("setThrow1(0, -1) should make the 1st frame's 1st throw -1", testPlayer.getThrow1(0), -1);
		
		testPlayer.setThrow1(0, 50);
		assertEquals("setThrow1(0, 50) should make the 1st frame's 1st throw 50", testPlayer.getThrow1(0), 50);
		
		testPlayer.setThrow1(0, 10);
		assertEquals("setThrow1(0, 10) should make the 1st frame's 1st throw 1", testPlayer.getThrow1(0), 10);
		
		testPlayer.setThrow1(9, 10);
		assertEquals("setThrow1(0, 1) should make the last frame's 1st throw 10", testPlayer.getThrow1(9), 10);
		
		
		/*
		 *  set/getThrow2
		 */
		testPlayer.setThrow2(0, 1);
		assertEquals("setThrow2(0, 1) should make the 1st frame's 2nd throw 1", testPlayer.getThrow2(0), 1);
		
		testPlayer.setThrow2(9, 1);
		assertEquals("setThrow2(0, 1) should make the last frame's 2nd throw 1", testPlayer.getThrow2(9), 1);
		
		testPlayer.setThrow2(0, -1);
		assertEquals("setThrow2(0, -1) should make the 1st frame's 2nd throw -1", testPlayer.getThrow2(0), -1);
		
		testPlayer.setThrow2(0, 50);
		assertEquals("setThrow2(0, 50) should make the 1st frame's 2nd throw 50", testPlayer.getThrow2(0), 50);
		
		testPlayer.setThrow2(0, 10);
		assertEquals("setThrow2(0, 10) should make the 1st frame's 2nd throw 1", testPlayer.getThrow2(0), 10);
		
		testPlayer.setThrow2(9, 10);
		assertEquals("setThrow2(0, 1) should make the last frame's 2nd throw 10", testPlayer.getThrow2(9), 10);
		
		
		/*
		 *  set/getExtraThrow
		 */
		testPlayer.setExtraThrow(1);
		assertEquals("setExtraThrow(1) should make the 1st frame's 2nd throw 1", testPlayer.getExtraThrow(), 1);

		testPlayer.setExtraThrow(-1);
		assertEquals("setExtraThrow(-1) should make the 1st frame's 2nd throw -1", testPlayer.getExtraThrow(), -1);
		
		testPlayer.setExtraThrow(50);
		assertEquals("setExtraThrow(50) should make the 1st frame's 2nd throw 50", testPlayer.getExtraThrow(), 50);
		
		testPlayer.setExtraThrow(10);
		assertEquals("setExtraThrow(10) should make the 1st frame's 2nd throw 1", testPlayer.getExtraThrow(), 10);
	}
}