package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Class to test the ActiveTaskList class methods of task list functionality for active tasks
 * @author Austin Warren and Brynn Reed
 */
public class ActiveTaskListTest {

	/**
	 * Tests ActivetTaskList adding of a task to the task list
	 */
	@Test
	public void testActiveTaskListAddTask() {		
		ActiveTaskList activeList = new ActiveTaskList();
		assertEquals("Active Tasks", activeList.getTaskListName());
		
		Task t1 = new Task("Exercise", "Swim Weekdays\nRun Weekends", true, true);
		activeList.addTask(t1);
		assertEquals(t1, activeList.getTask(0));
		assertEquals("Exercise", activeList.getTask(0).getTaskName());
		
		Task t2 = new Task("Homework", "Discrete Hw6 Due Thursday", false, false);
		assertThrows(IllegalArgumentException.class, () -> activeList.addTask(t2));
		
	}
	
	/**
	 * Tests ActiveTaskList removing of a task to the task list
	 */
	@Test
	public void testActiveTaskListRemoveTask() {
		Task t1 = new Task("Exercise", "Swim Weekdays\nRun Weekends", true, true);
		Task t2 = new Task("Homework", "Discrete Hw6 Due Thursday", true, true);
		Task t3 = new Task("Test Prep", "Prepare for Test on Wendsday", true, true);
		
		ActiveTaskList activeList = new ActiveTaskList();
		
		activeList.addTask(t1);
		activeList.addTask(t2);
		activeList.addTask(t3);

		activeList.clearTasks();
		
		assertEquals(0, activeList.getTasks().size());
	}
	
	/**
	 * Tests ActiveTaskList removing of a task to the task list
	 */
	@Test
	public void testActiveTaskListArray() {
		Task t1 = new Task("Exercise", "Swim Weekdays\nRun Weekends", true, true);
		Task t2 = new Task("Homework", "Discrete Hw6 Due Thursday", false, true);
		Task t3 = new Task("Test Prep", "Prepare for Test on Wendsday", false, true);

		ActiveTaskList activeList = new ActiveTaskList();
		TaskList taskList = new TaskList("A List of Tasks", 0);

		t1.addTaskList(taskList);
		t2.addTaskList(taskList);
		t3.addTaskList(taskList);
		
		activeList.addTask(t1);
		activeList.addTask(t2);
		activeList.addTask(t3);
		
		String[][] array = new String[3][2];
		array[0][0] = "A List of Tasks";
		array[0][1] = "Exercise";
		array[1][0] = "A List of Tasks";
		array[1][1] = "Homework";
		array[2][0] = "A List of Tasks";
		array[2][1] = "Test Prep";
		
		String[][] arrayToTest = activeList.getTasksAsArray();
		assertEquals(array[0][0], arrayToTest[0][0]);
		assertEquals(array[0][1], arrayToTest[0][1]);
		assertEquals(array[1][0], arrayToTest[1][0]);
		assertEquals(array[1][1], arrayToTest[1][1]);
		assertEquals(array[2][0], arrayToTest[2][0]);
		assertEquals(array[2][1], arrayToTest[2][1]);
	}
	
	/**
	 * Replicating Teacher test
	 */
	@Test
	public void testActiveTaskListAdd() {
		Task t1 = new Task("Task 3", "Task 3 Description", true, true);

		ActiveTaskList activeList = new ActiveTaskList();
		
		activeList.addTask(t1);
		
		assertEquals("Active Tasks", t1.getTaskListName());
	}
		

}