package edu.ncsu.csc216.wolf_tasks.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;

/**
 * The NotebookWriter class handles the output side of the IO package in which it contains one method,
 * writeNotebookFile which will use a print stream to output a list of tasklists to output file.
 * @author Austin Warren and Brynn Reed
 */
public class NotebookWriter {
	
	/**
	 * Writes the notebook of tasks/tasklists to the desired output file 
	 * @param file a file path to output the notebook to
	 * @param name a string value for the name of the notebook
	 * @param list a sorted list of tasklists to output to file
	 * @throws IllegalArgumentException if output file is not found
	 */
	public static void writeNotebookFile(File file, String name, ISortedList<TaskList> list) {
		PrintStream fileWriter;
		try {
			fileWriter = new PrintStream(file);
			fileWriter.println("! " + name);
			for (int i = 0; i < list.size(); i++) {
				fileWriter.println("# " + list.get(i).getTaskListName() + "," 
						+ list.get(i).getCompletedCount());
				for (int j = 0; j < list.get(i).getTasks().size(); j++) {
					String taskString = list.get(i).getTask(j).toString();
					if (taskString.charAt(taskString.length() - 1) == '\n') {
						fileWriter.print(list.get(i).getTask(j).toString());
					}
					else {
						fileWriter.println(list.get(i).getTask(j).toString());
					}	
				}
			}
			fileWriter.close();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}
	}
	
}
