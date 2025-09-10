package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Class to test the Task class methods of task functionality
 * @author Austin Warren and Brynn Reed
 */
public class TaskTest {

	/**
	 * Tests Task's to string method
	 */
	@Test
	public void testTaskToString() {
		Task t1 = new Task("Exercise", "Swim Weekdays\nRun Weekends", true, true);
		assertEquals("* Exercise,recurring,active\nSwim Weekdays\nRun Weekends", 
				t1.toString());
		
		Task t2 = new Task("Exercise", "Swim Weekdays\nRun Weekends", false, true);
		assertEquals("* Exercise,active\nSwim Weekdays\nRun Weekends", 
				t2.toString());
		
		Task t3 = new Task("Exercise", "Swim Weekdays\nRun Weekends", true, false);
		assertEquals("* Exercise,recurring\nSwim Weekdays\nRun Weekends", 
				t3.toString());
		
		Task t4 = new Task("Exercise", "Swim Weekdays\nRun Weekends", false, false);
		assertEquals("* Exercise\nSwim Weekdays\nRun Weekends", 
				t4.toString());
	}

	/**
	 * Tests adding a task list to a a task
	 */
	@Test
	public void testAddTaskListToTask() {
		Task t1 = new Task("Exercise", "Swim Weekdays\nRun Weekends", true, true);
		assertThrows(NullPointerException.class, () -> t1.addTaskList(null));
		
		AbstractTaskList taskList = new TaskList("List 1", 0);
		t1.addTaskList(taskList);
		assertEquals("List 1", t1.getTaskListName());
		
	}
	
	/**
	 * Tests completting a task
	 */
	@Test
	public void testCompleteTask() {
		Task t1 = new Task("Exercise", "Swim Weekdays\nRun Weekends", true, true);
		Task t2 = new Task("Homework", "Discrete Hw6 Due Thursday", false, false);
		Task t3 = new Task("Test Prep", "Prepare for Test on Wendsday", false, false);
		
		AbstractTaskList taskList = new TaskList("My List", 1);
		
		taskList.addTask(t1);
		taskList.addTask(t2);
		taskList.addTask(t3);
		
		t1.completeTask();
		t2.completeTask();
		t3.completeTask();
		
		assertEquals(4, taskList.getCompletedCount());
	}
	
	
}
