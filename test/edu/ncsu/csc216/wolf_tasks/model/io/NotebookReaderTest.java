package edu.ncsu.csc216.wolf_tasks.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;


import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;

/**
 * Class to test the NotebookReader class methods of reading input files
 * @author Austin Warren and Brynn Reed
 */
public class NotebookReaderTest {
	
	/**
	 * Tests reading a valid file filled with task lists/tasks
	 */
	@Test
	public void testReadValidNotebook() {
		File file = new File("test-files/notebook2.txt");
		Notebook notebook = NotebookReader.readNotebookFile(file);
		assertEquals("School", notebook.getNotebookName());
		
		String[] taskNames = new String[4];
		taskNames[0] = "Active Tasks";
		taskNames[1] = "CSC 216";
		taskNames[2] = "CSC 226";
		taskNames[3] = "Habits";
		for (int i = 0; i < notebook.getTaskListsNames().length; i++) {
			assertEquals(taskNames[i], notebook.getTaskListsNames()[i]);
		}
		
		notebook.setCurrentTaskList("CSC 226");
		AbstractTaskList list = notebook.getCurrentTaskList();
		ISwapList<Task> list2 = list.getTasks();
		assertEquals(5, list2.size());
		
		String[][] tasksFromImportedTaskList = new String[5][2];
		tasksFromImportedTaskList[0][0] = "1";
		tasksFromImportedTaskList[0][1] = "Homework 7";
		tasksFromImportedTaskList[1][0] = "2";
		tasksFromImportedTaskList[1][1] = "Homework 8";
		tasksFromImportedTaskList[2][0] = "3";
		tasksFromImportedTaskList[2][1] = "Homework 9";
		tasksFromImportedTaskList[3][0] = "4";
		tasksFromImportedTaskList[3][1] = "Homework 10";
		tasksFromImportedTaskList[4][0] = "5";
		tasksFromImportedTaskList[4][1] = "Watch lectures";

		assertEquals(5, list.getTasksAsArray().length);
		for (int i = 0; i < tasksFromImportedTaskList.length; i++) {
			assertEquals(tasksFromImportedTaskList[i][0], list.getTasksAsArray()[i][0]);
			assertEquals(tasksFromImportedTaskList[i][1], list.getTasksAsArray()[i][1]);
		}
		notebook.setCurrentTaskList("Active Tasks");
		assertEquals(3, notebook.getCurrentTaskList().getTasks().size());
		
	}
	
	/**
	 * Tests reading a valid file filled with task lists/tasks
	 * (Replicated TS test)
	 */
	@Test
	public void testReadValidNotebook2() {
		File file = new File("test-files/notebook1.txt");
		Notebook notebook = NotebookReader.readNotebookFile(file);
		assertEquals("School", notebook.getNotebookName());
		notebook.setCurrentTaskList("Active Tasks");
		assertEquals(5, notebook.getCurrentTaskList().getTasks().size());
		
		notebook.setCurrentTaskList("CSC 216");
		AbstractTaskList list = notebook.getCurrentTaskList();
		ISwapList<Task> list2 = list.getTasks();
		assertEquals(9, list2.size());
		
		String[][] tasksFromImportedTaskList = new String[9][2];
		tasksFromImportedTaskList[0][0] = "1";
		tasksFromImportedTaskList[0][1] = "Read Project 2 Requirements";
		tasksFromImportedTaskList[1][0] = "2";
		tasksFromImportedTaskList[1][1] = "Create CRC Cards";
		tasksFromImportedTaskList[2][0] = "3";
		tasksFromImportedTaskList[2][1] = "Transfer CRC Cards to UMLetino";
		tasksFromImportedTaskList[3][0] = "4";
		tasksFromImportedTaskList[3][1] = "Download design proposal and rational template";
		tasksFromImportedTaskList[4][0] = "5";
		tasksFromImportedTaskList[4][1] = "Write design proposal and rationale";
		tasksFromImportedTaskList[5][0] = "6";
		tasksFromImportedTaskList[5][1] = "Identify 5 system tests";
		tasksFromImportedTaskList[6][0] = "7";
		tasksFromImportedTaskList[6][1] = "Watch lecture";
		tasksFromImportedTaskList[7][0] = "8";
		tasksFromImportedTaskList[7][1] = "Complete exercises";
		tasksFromImportedTaskList[8][0] = "9";
		tasksFromImportedTaskList[8][1] = "Complete quizzes";

		
		assertEquals(tasksFromImportedTaskList[5][1], list.getTasksAsArray()[5][1]);
		
		
		assertEquals(9, list.getTasksAsArray().length);
		for (int i = 0; i < tasksFromImportedTaskList.length; i++) {
			assertEquals(tasksFromImportedTaskList[i][0], list.getTasksAsArray()[i][0]);
			assertEquals(tasksFromImportedTaskList[i][1], list.getTasksAsArray()[i][1]);
		}
	}
	
	/**
	 * Tests reading a invalid file filled with task lists/tasks
	 * (Replicated TS test)
	 */
	@Test
	public void testReadInvalidNotebook() {
		File file = new File("test-files/notebook4.txt");
		Notebook notebook = NotebookReader.readNotebookFile(file);
		assertEquals("Personal", notebook.getNotebookName());
		notebook.setCurrentTaskList("Active Tasks");
		assertEquals(0, notebook.getCurrentTaskList().getTasks().size());
		assertEquals(1, notebook.getTaskListsNames().length);

	}
	
	/**
	 * Tests reading a invalid file filled with task lists/tasks
	 * (Replicated TS test)
	 */
	@Test
	public void testReadInvalidNotebook2() {
		File file = new File("test-files/notebook7.txt");
		Notebook notebook = NotebookReader.readNotebookFile(file);
		assertEquals("Personal", notebook.getNotebookName());
		assertEquals(2, notebook.getTaskListsNames().length);

	}

}
