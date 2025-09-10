package edu.ncsu.csc216.wolf_tasks.model.notebook;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * Class to test the Notebook class methods of task/task list manipulation
 * @author Austin Warren and Brynn Reed
 */
public class NotebookTest {

	/**
	 * Tests Notebook construction
	 */
	@Test
	public void testNotebookConstruction() {
		Notebook notebook = new Notebook("School Notebook");
		
		assertThrows(IllegalArgumentException.class, () -> new Notebook(""));
		assertThrows(IllegalArgumentException.class, () -> new Notebook(null));
		assertThrows(IllegalArgumentException.class, () -> new Notebook("Active Tasks"));
		
		assertEquals(notebook.getNotebookName(), "School Notebook");
		assertTrue(notebook.isChanged());


	}
	
	/**
	 * Tests Notebook's addTaskList method
	 */
	@Test
	public void testNotebookAddTaskList() {
		Notebook notebook = new Notebook("School Notebook");
		
		TaskList taskList = new TaskList("Math Work", 0);
		TaskList invalidTaskList = new TaskList("Active Tasks", 0);
		TaskList duplicateTaskList = new TaskList("Math Work", 0);

		notebook.addTaskList(taskList);
		assertEquals(notebook.getCurrentTaskList(), taskList);
		assertThrows(IllegalArgumentException.class, () -> 
			notebook.addTaskList(invalidTaskList));
		assertThrows(IllegalArgumentException.class, () -> 
		notebook.addTaskList(duplicateTaskList));
	

	}
	
	/**
	 * Tests Notebook's addTask method
	 */
	@Test
	public void testNotebookAddTask() {
		Notebook notebook = new Notebook("School Notebook");
		
		TaskList taskList = new TaskList("Math Work", 0);
		
		Task t1 = new Task("Study for Math Test", "Test #2 on Monday", false, false);


		notebook.addTaskList(taskList);
		notebook.addTask(t1);
		assertEquals(1, notebook.getCurrentTaskList().getTasks().size());

	}
	
	/**
	 * Tests Notebook's removeTaskList method
	 */
	@Test
	public void testNotebookRemoveTaskList() {
		Notebook notebook = new Notebook("School Notebook");
		
		TaskList taskList = new TaskList("Math Work", 0);
		
		Task t1 = new Task("Study for Math Test", "Test #2 on Monday", false, false);


		notebook.addTaskList(taskList);
		notebook.addTask(t1);
		notebook.setCurrentTaskList("Math Work");
		notebook.removeTaskList();
		
		assertEquals("Active Tasks", notebook.getCurrentTaskList().getTaskListName());
		assertThrows(IllegalArgumentException.class, () -> 
			notebook.removeTaskList());

	}
	
	/**
	 * Tests Notebook's editTaskList method
	 */
	@Test
	public void testNotebookEditTaskList() {
		Notebook notebook = new Notebook("School Notebook");
		
		TaskList taskList = new TaskList("Math Work", 0);
		
		Task t1 = new Task("Study for Math Test", "Test #2 on Monday", false, false);


		notebook.addTaskList(taskList);
		notebook.addTask(t1);
		notebook.setCurrentTaskList("Math Work");
		notebook.editTaskList("Math Work for April");
		
		String[] taskListNames = new String[2];
		taskListNames[0] = "Active Tasks";
		taskListNames[1] = "Math Work for April";

		
		assertEquals(taskListNames[0], notebook.getTaskListsNames()[0]);
		assertEquals(taskListNames[1], notebook.getTaskListsNames()[1]);


	}
	
	/**
	 * Tests Notebook's editTask method
	 */
	@Test
	public void testNotebookEditTask() {
		Notebook notebook = new Notebook("School Notebook");
		
		TaskList taskList = new TaskList("Math Work", 0);
		
		Task t1 = new Task("Study for Math Test", "Test #2 on Monday", false, false);

		notebook.addTaskList(taskList);
		notebook.addTask(t1);
		notebook.setCurrentTaskList("Math Work");
		notebook.editTask(0, "Study for Math Test", "Test #2 on Tuesday", true, true);
		Task t2 = new Task("Study for Math Test", "Test #2 on Tuesday", true, true);

		
		assertEquals(t2.toString(), notebook.getCurrentTaskList().getTask(0).toString());


	}
	
	/**
	 * Tests Notebook's editTask method
	 */
	@Test
	public void testNotebookEditTask2() {
		Notebook notebook = new Notebook("School Notebook");
		
		TaskList taskList = new TaskList("Math Work", 0);
		
		Task t1 = new Task("Study for Math Test", "Test #2 on Monday", false, false);

		notebook.addTaskList(taskList);
		notebook.addTask(t1);
		notebook.setCurrentTaskList("Math Work");
		notebook.editTask(0, "Study for Math Test", "Test #2 on Tuesday", true, true);
		Task t2 = new Task("Study for Math Test", "Test #2 on Tuesday", true, true);

		
		assertEquals(t2.toString(), notebook.getCurrentTaskList().getTask(0).toString());


	}

}
