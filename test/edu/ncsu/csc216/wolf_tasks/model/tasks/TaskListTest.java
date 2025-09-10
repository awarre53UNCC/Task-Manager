package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Class to test the TaskList class methods of task list functionality
 * @author Austin Warren and Brynn Reed
 */
public class TaskListTest {
	
	/**
	 * Tests TaskList construction and adding of a task to the task list
	 */
	@Test
	public void testTaskListAddTask() {
		Task t1 = new Task("Exercise", "Swim Weekdays\nRun Weekends", true, true);
		
		TaskList taskList = new TaskList("List 1", 0);
		t1.addTaskList(taskList);
		assertEquals("List 1", t1.getTaskListName());
		
		taskList.addTask(t1);
		assertEquals(t1, taskList.getTask(0));
		assertEquals("Exercise", taskList.getTask(0).getTaskName());

		
	}
	
	/**
	 * Tests TaskList's exceptions for construction
	 */
	@Test
	public void testTaskListInvalidConstructor() {		
		
		assertThrows(IllegalArgumentException.class, () -> new TaskList("List 1", -1));
		assertThrows(IllegalArgumentException.class, () -> new TaskList("", -1));
		assertThrows(IllegalArgumentException.class, () -> new TaskList(null, -1));

	}
	
	/**
	 * Tests TaskList compare to method
	 */
	@Test
	public void testTaskListCompareTo() {	
		
		TaskList taskList = new TaskList("B List", 0);
		TaskList taskList2 = new TaskList("A List", 0);
		assertTrue(taskList.compareTo(taskList2) > 0);


	}
	
	/**
	 * Tests TaskList array method of getTasksAsArray
	 */
	@Test
	public void testTaskListGetTasksAsArray() {	
		
		Task t1 = new Task("Exercise", "Swim Weekdays\nRun Weekends", true, true);
		Task t2 = new Task("Homework", "Discrete Hw6 Due Thursday", false, false);
		Task t3 = new Task("Test Prep", "Prepare for Test on Wendsday", false, false);

		TaskList taskList = new TaskList("A List of Tasks", 0);

		taskList.addTask(t1);
		taskList.addTask(t2);
		taskList.addTask(t3);
		
		String[][] array = new String[3][2];
		array[0][0] = "1";
		array[0][1] = "Exercise";
		array[1][0] = "2";
		array[1][1] = "Homework";
		array[2][0] = "3";
		array[2][1] = "Test Prep";
		
		String[][] arrayToTest = taskList.getTasksAsArray();
		assertEquals(array[0][0], arrayToTest[0][0]);
		assertEquals(array[0][1], arrayToTest[0][1]);
		assertEquals(array[1][0], arrayToTest[1][0]);
		assertEquals(array[1][1], arrayToTest[1][1]);
		assertEquals(array[2][0], arrayToTest[2][0]);
		assertEquals(array[2][1], arrayToTest[2][1]);

	}

}
