package edu.ncsu.csc216.wolf_tasks.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tasks.model.util.SortedList;

/**
 * Class to test the NotebookWriter class methods of outputting task information
 * to file
 * 
 * @author Austin Warren and Brynn Reed
 */
public class NotebookWriterTest {

	/** taskList to write to file */
	private ISortedList<TaskList> taskList;

	/**
	 * Tests valid test case for NoteBookWriter class.
	 */
	@Test
	public void testNoteBookWriter() {
        
		File file = new File("test-files/notebook2.txt");
		Notebook notebook = NotebookReader.readNotebookFile(file);
		assertTrue(file.exists());

		File outputFile = new File("test-files/testNotebookWriter.txt");

		notebook.saveNotebook(outputFile);


		checkFiles("test-files/testNotebookWriter.txt", "test-files/testWriterExpected.txt");
    }
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(new File(actFile));) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}
	
	/**
	 * Tests NotebookWriter
	 */
	@Test
	public void testNotebookWriterinValid() {

		taskList = new SortedList<TaskList>();

		TaskList x = new TaskList("task list", 1);

		taskList.add(x);
		File file = new File("");
		try {
			NotebookWriter.writeNotebookFile(file, "Notebook", taskList);
		} catch (IllegalArgumentException e) {
			assertEquals("Unable to save file.", e.getMessage());
		}

		assertFalse(file.exists());
	}

	/**
	 * Tests NotebookWriter
	 */
	@Test
	public void testNotebookWriterEmpty() {

		taskList = new SortedList<TaskList>();

		File file = new File("testNotebookWriter.txt");
		try {
			NotebookWriter.writeNotebookFile(file, "Notebook", taskList);
			assertTrue(file.exists());
		} catch (IllegalArgumentException e) {
			assertEquals("Empty file.", e.getMessage());
		}

	}

}