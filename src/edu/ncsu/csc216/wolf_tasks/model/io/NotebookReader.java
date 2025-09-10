package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc216.wolf_tasks.model.notebook.Notebook;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;

/**
 * The NotebookReader class handles the input side of the IO package in which it
 * contains three methods, readNotebookFile, proccess TaskList, and process Task
 * which will parse through a file for tasks.
 * 
 * @author Austin Warren and Brynn Reed
 */
public class NotebookReader {

	/**
	 * Method that will create a scanner over a file and parse through each line of
	 * the file creating tasks/tasklists and adding them to an array.
	 * 
	 * @param file a value for the file path in which issues are to be read from
	 * @return a Notebook filled with tasks/tasklists that are read from file input
	 * @throws IllegalArgumentException if file cannot be processed or if file does
	 *                                  not start with !
	 */
	public static Notebook readNotebookFile(File file) {
		Notebook notebook = null;
		try {
			Scanner scnr = new Scanner(file);

			if (scnr.hasNextLine()) {
	            String line = scnr.nextLine();

	            if (line.startsWith("!")) {
	                notebook = new Notebook(line.substring(2).trim());
	            } else {
	            	scnr.close();
	                throw new IllegalArgumentException("Unable to load file.");
	            }
	        } else {
	        	scnr.close();
	            throw new IllegalArgumentException("Empty file.");
	        }
			
			String noteBookString = "";
			while(scnr.hasNext()) {
				noteBookString += scnr.nextLine();
				noteBookString += "\n";
			}
			Scanner in = new Scanner(noteBookString);
			in.useDelimiter("\\r?\\n?[#]");
			while(in.hasNext()) {
				TaskList list = processTaskList(in.next());
				if (list == null) {
					in.close();
					scnr.close();
					return notebook;
				}
				notebook.addTaskList(list);

			}

			in.close();
			scnr.close();
			notebook.setCurrentTaskList("Active Tasks");
			return notebook;

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
	}

	/**
	 * Helper method to process a task list from a notebook from file input
	 * @param tasklist a string value for a tasklist to process
	 * @return a TaskList from file input
	 */
	private static TaskList processTaskList(String tasklist) {
		Scanner scnr = new Scanner(tasklist);
		scnr.useDelimiter(",");
		try {

			String name = scnr.next();
			String taskListCount = scnr.next();
			int taskListCountIdx = taskListCount.indexOf("\n");
			int taskListCountInt;
			if (taskListCountIdx > 0) {
				taskListCountInt = Integer.valueOf(taskListCount.substring(0, taskListCountIdx));

			}
			else {
				taskListCountInt = Integer.valueOf(taskListCount);
			}
			name = name.trim();
			TaskList taskList = new TaskList(name, taskListCountInt);

			scnr.useDelimiter("\\r?\\n?[*]");
			if (scnr.hasNext()) {
				processTask(taskList, tasklist);
			}
				
			scnr.close();
			return taskList;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Helper method to process a task from a task list from file input
	 * 
	 * @param list the task list to which the task will be added
	 * @param tasklist the string value of the task to be processed
	 */
	private static void processTask(AbstractTaskList list, String tasklist) {
		String taskList = tasklist.substring(tasklist.indexOf("\n"));
		Scanner scnr = new Scanner(taskList);
		scnr.useDelimiter("\\r?\\n?[*]");
		while (scnr.hasNext()) {
			String taskLine = scnr.next();
			
			boolean recurring = false;
			boolean active = false;
			String taskName = "";
			String taskDescription = "";
			
			if (!taskLine.contains("\n")) {
				if (taskLine.contains(",")) {
					taskName = taskLine.substring(0, taskLine.indexOf(","));
					if (taskLine.contains("recurring")) {
						recurring = true;
					}
					if (taskLine.contains("active")) {
						active = true;
					}
					taskDescription = taskLine.substring(taskLine.indexOf("\n"));
				}
				else {
					taskName = taskLine;
					if (taskLine.contains("recurring")) {
						recurring = true;
					}
					if (taskLine.contains("active")) {
						active = true;
					}
					taskDescription = "";
				}
			}
			else {
				if (taskLine.contains(",")) {
					taskName = taskLine.substring(0, taskLine.indexOf(","));
					if (taskLine.contains("recurring")) {
						recurring = true;
					}
					if (taskLine.contains("active")) {
						active = true;
					}
					taskDescription = taskLine.substring(taskLine.indexOf("\n"));
				}
				else {
					taskName = taskLine.substring(0, taskLine.indexOf("\n"));
					if (taskLine.contains("recurring")) {
						recurring = true;
					}
					if (taskLine.contains("active")) {
						active = true;
					}
					taskDescription = taskLine.substring(taskLine.indexOf("\n"));
				}
			}
			
			while (taskName.contains("\n")) {
				taskName = taskName.substring(0, taskLine.indexOf("\n"));
			}
			taskName = taskName.trim();
			Task task = null;
			if (!taskName.isEmpty() || taskName == null) {
				task = new Task(taskName, taskDescription, recurring, active);
				list.addTask(task);

			}
			
		}
	}
	
}

