package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * Class represents an abstract task list, methods used to manipulate a task list information
 * Contains three fields a string for taskListName, an int for completedCount, and a swap list of tasks
 * TaskList methods provide functionality for managing tasks in an active list.
 * @author Austin Warren and Brynn Reed
 */
public abstract class AbstractTaskList {

	/** A string field for the task list's name. */	
	private String taskListName;
	
	/** An integer field for the task's completed count. */	
	private int completedCount;
	
	/** A swap list field of task's*/
	private ISwapList<Task> tasks;
	
	/**
	 * Sets the fields from the parameters and constructs a SwapList for the Tasks. 
	 * @param name a string value representing the list name
	 * @param count an integer value representing the count of completed tasks
	 * @throws IllegalArgumentException if the taskListName is null, empty string, or if the 
	 * completedCount is less than zero.
	 */
	public AbstractTaskList(String name, int count) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Invalid name.");
		}
		if (count < 0) {
			throw new IllegalArgumentException("Invalid completed count.");
		}
		
		setTaskListName(name);
		this.completedCount = count;
		tasks = new SwapList<Task>();

	}
	
	/**
	 * Standard getter method for the taskListName field, returning the name as a string
	 * @return a string for the task list name
	 */
	public String getTaskListName() {
		return this.taskListName;
	}
	
	/**
	 * Public setter method that sets the taskListName field
	 * @param name a boolean value to set the recurring field
	 */
	public void setTaskListName(String name) {
		this.taskListName = name;
	}
	
	/**
	 * Standard getter method for the tasks field, returning the tasks in the list
	 * @return a swap list of tasks for the task list
	 */
	public ISwapList<Task> getTasks() {
		return tasks;
	}
	
	/**
	 * Standard getter method for the completedCount field, returning the count of the list
	 * @return an integer for the count of completed tasks in the list
	 */
	public int getCompletedCount() {
		return completedCount;
	}
	
	/**
	 * Adds the Task to the end of the list. 
	 * The current instance of the TaskList adds itself to the Task.
	 * @param task the task to be added to the list
	 */
	public void addTask(Task task) {
		tasks.add(task);
		task.addTaskList(this);
	}
	
	/**
	 * Removes the Task from the list of tasks and returns the removed task.
	 * @param idx the integer value for the index of the task to be removed
	 * @return the task removed from the list
	 */
	public Task removeTask(int idx) {
		Task taskRemoved = tasks.remove(idx);
		return taskRemoved;
	}
	
	/**
	 * Returns the Task at the given index.
	 * @param idx the integer value for the index of the task to be returned.
	 * @return the Task at the given index.
	 */
	public Task getTask(int idx) {
		Task taskGotten = tasks.get(idx);
		return taskGotten;
	}
	
	/**
	 * Finds the given Task in the list and removes it. The completedCount is incremented. 
	 * @param task the task to be completed and removed from the list
	 */
	public void completeTask(Task task) {
		for (int i = 0; i < tasks.size(); i++) {
			if (task == tasks.get(i)) {
				tasks.remove(i);
			}
		}
		this.completedCount += 1;

	}
	
	/**
	 * An abstract method that returns a 2D String array. 
	 * The contents of the array are left for the child classes to define.
	 * @return a 2D array for the task list
	 */
	public abstract String[][] getTasksAsArray();
		
}
