package edu.ncsu.csc216.wolf_tasks.model.util;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

/**
 * Test class to test the SwapList classess methods of creating a list of elements
 * @author Austin Warren and Brynn Reed
 */
public class SwapListTest {

	/**
	 * Tests the add and remove methods of swap list
	 */
	@Test
    public void testAddAndRemove() {
        SwapList<String> list = new SwapList<>();

        //Adding elements 
        list.add("1st");
        list.add("2nd");
        list.add("3rd");
        assertEquals(3, list.size());
        assertEquals("1st", list.get(0));
        assertEquals("2nd", list.get(1));
        assertEquals("3rd", list.get(2));
        
        // Removing an element
        list.remove(0);
        assertEquals(2, list.size());
        assertEquals("2nd", list.get(0));
        assertEquals("3rd", list.get(1));
         
	}
	
	/**
	 * Tests the add and remove methods of swap list, using different functionality
	 */
	@Test
    public void testAddAndRemove2() {
        SwapList<String> list = new SwapList<>();

        //Adding elements 
        list.add("1st");
        list.add("2nd");
        list.add("3rd");
        assertEquals(3, list.size());
        assertEquals("1st", list.get(0));
        assertEquals("2nd", list.get(1));
        assertEquals("3rd", list.get(2));
        
        // Removing an element
        list.remove(1);
        assertEquals(2, list.size());
        assertEquals("1st", list.get(0));
        assertEquals("3rd", list.get(1));
         
	}
	
	/**
	 * Tests the move up method
	 */
	@Test
    public void testMoveUp() {
		 SwapList<String> list = new SwapList<>();
		 //Adding elements 
	     list.add("1");
	     list.add("2");
	     list.add("3");
	     list.add("4");
	     list.add("5");
	     
	     list.moveUp(2);
	     
	     assertEquals("3", list.get(1));
	     assertEquals("2", list.get(2));

	}
	

	/**
	 * Tests the move down method
	 */
	@Test
    public void testMoveDown() {
		 SwapList<String> list = new SwapList<>();
		 //Adding elements 
	     list.add("1");
	     list.add("2");
	     list.add("3");
	     list.add("4");
	     list.add("5");
	     
	     list.moveDown(2);
	     
	     assertEquals("4", list.get(2));
	     assertEquals("3", list.get(3));

	}
	
	/**
	 * Tests the move to front method
	 */
	@Test
    public void testMoveFront() {
		 SwapList<String> list = new SwapList<>();
		 //Adding elements 
	     list.add("Apple");
	     list.add("Pear");
	     list.add("Bannana");
	     list.add("Cherry");
	     list.add("Blueberries");
	     
	     list.moveToFront(2);
	 
	     assertEquals("Bannana", list.get(0));
	     assertEquals("Apple", list.get(1));
	     assertEquals("Pear", list.get(2));
	     assertEquals("Cherry", list.get(3));
	     assertEquals("Blueberries", list.get(4));

	     list.moveToFront(1);

	     assertEquals("Apple", list.get(0));
	     assertEquals("Bannana", list.get(1));
	     assertEquals("Pear", list.get(2));
	     assertEquals("Cherry", list.get(3));
	     assertEquals("Blueberries", list.get(4));
	     
	     list.moveToFront(4);
	     
	     assertEquals("Blueberries", list.get(0));
	     assertEquals("Apple", list.get(1));
	     assertEquals("Bannana", list.get(2));
	     assertEquals("Pear", list.get(3));
	     assertEquals("Cherry", list.get(4));


	}
	
	/**
	 * Tests the move to back method
	 */
	@Test
    public void testMoveBack() {
		SwapList<String> list = new SwapList<>();
		 //Adding elements 
	     list.add("Apple");
	     list.add("Pear");
	     list.add("Bannana");
	     list.add("Cherry");
	     list.add("Blueberries");
	     
	     list.moveToBack(2);
	 
	     assertEquals("Apple", list.get(0));
	     assertEquals("Pear", list.get(1));
	     assertEquals("Cherry", list.get(2));
	     assertEquals("Blueberries", list.get(3));
	     assertEquals("Bannana", list.get(4));

	     list.moveToBack(1);

	     assertEquals("Apple", list.get(0));
	     assertEquals("Cherry", list.get(1));
	     assertEquals("Blueberries", list.get(2));
	     assertEquals("Bannana", list.get(3));
	     assertEquals("Pear", list.get(4));
	     
	     list.moveToBack(4);
	     
	     assertEquals("Apple", list.get(0));
	     assertEquals("Cherry", list.get(1));
	     assertEquals("Blueberries", list.get(2));
	     assertEquals("Bannana", list.get(3));
	     assertEquals("Pear", list.get(4));
	     
	     list.moveToBack(0);
	     assertEquals("Cherry", list.get(0));
	     assertEquals("Blueberries", list.get(1));
	     assertEquals("Bannana", list.get(2));
	     assertEquals("Pear", list.get(3));
	     assertEquals("Apple", list.get(4));


	}
	
	/**
	 * Tests the checking of the capacity and the increase of the array's size
	 */
	@Test
    public void testCapacity() {
		 SwapList<String> list = new SwapList<>();
		 //Adding elements 
	     list.add("1");
	     list.add("2");
	     list.add("3");
	     list.add("4");
	     list.add("5");
	     list.add("6");
	     list.add("7");
	     list.add("8");
	     list.add("9");
	     list.add("10");
	     list.add("11");

	     
	     assertEquals(11, list.size());
	     assertEquals("11", list.get(10));
	}
	
}
