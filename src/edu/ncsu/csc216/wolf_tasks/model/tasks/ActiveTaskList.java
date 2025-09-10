package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;

/**
 * Class represents an active task list, extending AbstractTaskList for tasks 
 * with an active boolean value set to true. 
 * TaskList methods provide functionality for managing tasks in an active list.
 * @author Austin Warren and Brynn Reed
 */
public class ActiveTaskList extends AbstractTaskList {

	/** A string constant for the task list's name. */	
	public static final String ACTIVE_TASKS_NAME = "Active Tasks";
	
	/**
	 * Constructs the ActiveTaskList with the expected name an no completed tasks.
	 */
	public ActiveTaskList() {
		super(ACTIVE_TASKS_NAME, 0);
	}

	
	/**
	 * Overrides the method to check that the Task is active before adding to the end of the ISwapList. 
	 * @param task a task to be added to the active task list
	 * @throws IllegalArgumentException if the Task is not active
	 */
	@Override
	public void addTask(Task task) {
		if (task.isActive()) {
			super.addTask(task);
		}
		else {
			throw new IllegalArgumentException("Cannot add task to Active Tasks.");
		}
	}
	
	/**
	 * Overrides the method to ensure that the paramter value matches the expected name. 
	 * If so, the name is set. If not, throws IAE.
	 * @param name a string value for the name to be checked
	 * @throws IllegalArgumentException if the name parameter does not match "Active Tasks"
	 * if the parameter does not match the expected name
	 */
	@Override
	public void setTaskListName(String name) {
		if (!name.equals(ACTIVE_TASKS_NAME)) {
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		}
		else {
			super.setTaskListName(name);
		}
		
	}
	
	/**
	 * Returns a 2D String array where the first column is the name of the TaskList that the 
	 * Task belongs to (or at least the TaskList at index 0) and the name of the Task.
	 * @return a 2D string array for the task list information
	 */
	public String[][] getTasksAsArray() {
		ISwapList<Task> tasks = getTasks();
		String[][] taskArray = new String[tasks.size()][2];
		for (int i = 0; i < tasks.size(); i++) {
			taskArray[i][0] = tasks.get(i).getTaskListName();
			taskArray[i][1] = tasks.get(i).getTaskName();
		}
		return taskArray;
	}
	
	/**
	 * Clears the ActiveTaskList of all Tasks.
	 */
	public void clearTasks() {
		ISwapList<Task> tasks = getTasks();
		for (int i = tasks.size() - 1; i >= 0; i--) {
			tasks.remove(i);
		}
	}
	
}
