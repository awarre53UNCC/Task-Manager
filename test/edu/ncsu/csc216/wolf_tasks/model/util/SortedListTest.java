package edu.ncsu.csc216.wolf_tasks.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Class to test the SortedList class methods of sorting task lists
 * @author Austin Warren and Brynn Reed
 */
public class SortedListTest {

	/**
	 * Tests that the elements are added into a sorted order
	 */
	@Test
	public void testAddOrdering() {
		SortedList<String> list = new SortedList<>();
        list.add("3");
        list.add("2");
        list.add("1");
        
        assertEquals(3, list.size());
        
        assertEquals("1", list.get(0));
        assertEquals("2", list.get(1));
        assertEquals("3", list.get(2));

        assertTrue(list.contains("3"));
        assertTrue(list.contains("2"));
        assertTrue(list.contains("1"));
        
		SortedList<String> list2 = new SortedList<>();
		list2.add("7");
        list2.add("5");
        list2.add("8");
        
        assertEquals(3, list2.size());
        
        assertEquals("5", list2.get(0));
        assertEquals("7", list2.get(1));
        assertEquals("8", list2.get(2));
	}
	
	/**
	 * Tests that the element is removed from the list, and the size is decreased
	 */
	@Test
	public void testRemoveElement() {
		SortedList<String> list = new SortedList<>();
        list.add("1");
        assertEquals(1, list.size());
        assertEquals("1", list.get(0));
        list.add("2");
        assertEquals("2", list.get(1));
        list.add("3");
        assertEquals("3", list.get(2));
       
        assertEquals("3", list.remove(2));
        assertEquals(2, list.size());
	}
	
	/**
	 * Tests that the correct exception is thrown for a invalid sorted list operation
	 * This includes the checkIndex method
	 */
	@Test
	public void testSortedListExceptions() {
		SortedList<String> list = new SortedList<>();
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
			
		assertThrows(NullPointerException.class, () -> list.add(null));

		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(10));
		
		list.add("1");
		assertThrows(IllegalArgumentException.class, () -> list.add("1"));
	}
}
