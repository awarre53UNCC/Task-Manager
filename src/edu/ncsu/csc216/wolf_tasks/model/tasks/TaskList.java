package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;

/**
 * Represents a task list, extending AbstractTaskList and implementing the Comparable interface.
 * TaskList methods provide functionality for managing tasks in a list.
 * @author Austin Warren and Brynn Reed
 */
public class TaskList extends AbstractTaskList implements Comparable<TaskList> {

	/**
	 * Sets the fields from the parameters and constructs a SwapList for the Tasks. 
	 * @param name a string value representing the list name
	 * @param count an integer value representing the count of completed tasks
	 * @throws IllegalArgumentException if the taskListName is null, empty string, or if the 
	 * completedCount is less than zero.
	 */
	public TaskList(String name, int count) {
		super(name, count);
	}
	
	/**
	 * Returns a 2D String array where the first column is the priority of the Task, 
	 * starting at 1, and the name of the Task.
	 * @return a 2D array for the task list
	 */
	public String[][] getTasksAsArray() {
		ISwapList<Task> tasks = getTasks();
		String[][] taskArray = new String[tasks.size()][2];
		for (int i = 0; i < tasks.size(); i++) {
			String number = String.valueOf(i + 1);
			taskArray[i][0] = number;
			taskArray[i][1] = tasks.get(i).getTaskName();
		}
		return taskArray;
	}
	
	/**
	 * Compares the names of the TaskLists.
	 * @param list the list to be compared
	 * @return an integer value for the lists to be compared
	 */
	public int compareTo(TaskList list) {
		String taskName = getTaskListName();
		return taskName.compareTo(list.getTaskListName());
	}
	
}
