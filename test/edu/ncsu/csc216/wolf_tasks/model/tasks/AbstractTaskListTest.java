package edu.ncsu.csc216.wolf_tasks.model.tasks;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Class to test the AbstractTaskList class methods of task list functionality
 * @author Austin Warren and Brynn Reed
 */
public class AbstractTaskListTest {

	/**
	 * Tests AbstractTaskList adding of a task to the task list
	 */
	@Test
	public void testAbstractTaskListAddTask() {
		Task t1 = new Task("Exercise", "Swim Weekdays\nRun Weekends", true, true);
		
		AbstractTaskList taskList = new TaskList("List 1", 1);
		t1.addTaskList(taskList);
		assertEquals("List 1", t1.getTaskListName());
		
		taskList.addTask(t1);
		assertEquals(t1, taskList.getTask(0));
		assertEquals("Exercise", taskList.getTask(0).getTaskName());

		
	}
	
	/**
	 * Tests AbstractTaskList removing of a task to the task list
	 */
	@Test
	public void testAbstractTaskListRemoveTask() {
		Task t1 = new Task("Exercise", "Swim Weekdays\nRun Weekends", true, true);
		Task t2 = new Task("Homework", "Discrete Hw6 Due Thursday", false, false);
		Task t3 = new Task("Test Prep", "Prepare for Test on Wendsday", false, false);
		
		AbstractTaskList taskList = new TaskList("My List", 1);
		
		taskList.addTask(t1);
		taskList.addTask(t2);
		taskList.addTask(t3);

		assertEquals(t3, taskList.removeTask(2));
		assertEquals(t2, taskList.removeTask(1));
		assertEquals(t1, taskList.removeTask(0));
		
	}
	
	/**
	 * Tests completing of a task in the task list
	 */
	@Test
	public void testAbstractTaskListCompleteTask() {
		Task t1 = new Task("Exercise", "Swim Weekdays\nRun Weekends", true, true);
		Task t2 = new Task("Homework", "Discrete Hw6 Due Thursday", false, false);
		Task t3 = new Task("Test Prep", "Prepare for Test on Wendsday", false, false);
		
		AbstractTaskList taskList = new TaskList("My List", 0);
		
		taskList.addTask(t1);
		taskList.addTask(t2);
		taskList.addTask(t3);

		taskList.completeTask(t3);
		assertEquals(1, taskList.getCompletedCount());

		assertEquals(2, taskList.getTasks().size());
		
	}
	
	

}
