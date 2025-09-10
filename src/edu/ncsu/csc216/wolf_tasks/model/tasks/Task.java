package edu.ncsu.csc216.wolf_tasks.model.tasks;

import edu.ncsu.csc216.wolf_tasks.model.util.ISwapList;
import edu.ncsu.csc216.wolf_tasks.model.util.SwapList;

/**
 * The class is used to manipulate task information with standard getters/setters for the fields
 * and is used to create task lists.
 * Fields are the information about each individual task as the fields including the taskName, 
 * taskDescription, if the task is recurring, and if the task is active. 
 * The Task class additionally contains an ISwapList of AbstractTaskLists called taskLists
 * Implements the Cloneable interface
 * @author Austin Warren and Brynn Reed
 */
public class Task implements Cloneable {

	/** A string field for the task's name. */	
	private String taskName;
	
	/** A string field for the task's description. */	
	private String taskDescription;
	
	/** A boolean field for the task's recurring value (True/False). */	
	private boolean recurring;
	
	/** A boolean field for the task's active value (True/False). */	
	private boolean active;
	
	/** A swap list field of task's*/
	private ISwapList<AbstractTaskList> taskLists;

	/**
	 * Constructs the Task with the given parameters. 
	 * The taskLists field is constructed to an empty SwapList of AbstractTaskLists.
	 * @param name a string value for the name of the task
	 * @param description a string value for the description of the task
	 * @param reccurVal a boolean value for the recurring status of the task
	 * @param activeVal a boolean value for the active status of the task
	 * @throws IllegalArgumentException if a task name is invalid or task description
	 */
	public Task(String name, String description, boolean reccurVal, boolean activeVal) {
		
		setTaskName(name);
		setTaskDescription(description);
		setRecurring(reccurVal);
		setActive(activeVal);
		taskLists = new SwapList<AbstractTaskList>();
	}
	
	/**
	 * Standard getter method for the taskName field, returning the name as a string
	 * @return a string for the task name
	 */
	public String getTaskName() {
		return taskName;
	}
	
	/**
	 * Public helper method that checks for a valid task name and 
	 * @param name a string value to set the taskName field
	 * @throws IllegalArgumentException if null or empty string. 
	 */
	public void setTaskName(String name) {
		if (name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		this.taskName = name;
	}
	
	/**
	 * Standard getter method for the taskDescription field, returning the description as a string
	 * @return a string for the task description
	 */
	public String getTaskDescription() {
		return taskDescription;
	}
	
	/**
	 * Public helper method that checks for a valid task description 
	 * @param description a string value to set the taskDescription field
	 * @throws IllegalArgumentException if null.
	 */
	public void setTaskDescription(String description) {
		if (description == null) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		this.taskDescription = description;
	}

	/**
	 * Standard getter method for the recurring field, returning the boolean
	 * @return a boolean for the recurring status (true/false)
	 */
	public boolean isRecurring() {
		return recurring;
	}
	
	/**
	 * Public setter method that sets the recurring field
	 * @param value a boolean value to set the recurring field
	 */
	public void setRecurring(boolean value) {
		this.recurring = value;
	}
	
	/**
	 * Standard getter method for the active field, returning the boolean
	 * @return a boolean for the active status (true/false)
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Public setter method that sets the active field
	 * @param value a boolean value to set the active field
	 */
	public void setActive(boolean value) {
		this.active = value;
	}
	
	/**
	 * Returns the name of the AbstractTaskList at index 0. 
	 * If the taskLists field is null or empty, an empty string is returned.
	 * There could be multiple AbstractTaskLists in the taskLists field; 
	 * if so, the name of the first AbstractTaskList is returned.
	 * @return the string value for the task list name
	 */
	public String getTaskListName() {
		if (taskLists == null || taskLists.size() == 0) {
			return "";
		}
		
		return taskLists.get(0).getTaskListName();
	}
	
	/**
	 * If the AbstractTaskList is NOT already registered with the Task 
	 * the AbstractTaskList is added to the end of the taskLists field. 
	 * @throws IllegalArgumentException if the parameter is null
	 * @param list the task list to be added
	 */
	public void addTaskList(AbstractTaskList list) {
		if (taskLists == null) {
			throw new IllegalArgumentException("Incomplete task information.");
		}
		
		boolean registered = false;
		for (int i = 0; i < taskLists.size(); i++) {
			if (taskLists.get(i).equals(list)) {
				registered = true;
				break;
			}
		}
		if (!registered) {
			taskLists.add(list);
		}
	}
	
	/**
	 * This method will complete the Task and notify the taskLists by sharing the current 
	 * Task instance via the TaskList.completeTask(Task) method. If the Task is recurring,
	 * the Task is cloned and the cloned Task is added to each registered AbstractTaskList.
	 */
	public void completeTask() {
		if (isRecurring()) {
			try {
				Task cloneTask = (Task) clone();
				
				for (int i = 0; i < taskLists.size(); i++) {
					taskLists.get(i).addTask(cloneTask);
				}
				
			} catch (CloneNotSupportedException e) {
				//TODO ??
			}
		}
		for (int i = 0; i < taskLists.size(); i++) {
			taskLists.get(i).completeTask(this);
		}
		
	}
	
	/**
	 * Returns a copy of the Task. A new Task is created with copies of all the fields. 
	 * @return an object, a clone of the task
	 * @throws CloneNotSupportedException if there are no AbstractTaskLists registered with the Task
	 */
	public Object clone() throws CloneNotSupportedException {

		Task cloneTask = new Task(getTaskName(), getTaskDescription(), isRecurring(), isActive());
		ISwapList<AbstractTaskList> cloneList = new SwapList<AbstractTaskList>();
		for (int i = 0; i < taskLists.size(); i++) {
			cloneList.add(taskLists.get(i));
		}
		taskLists = cloneList;
		
		if (taskLists == null || taskLists.size() == 0) {
			throw new CloneNotSupportedException("Cannot clone.");
		}
		return cloneTask;
	}
	
	/**
	 * Returns a string representation of the Task for printing to a file. 
	 * To support testing, “recurring” string before “active” is printed
	 * if both recurring and active are true.
	 * @return a string to be used for printing an output to a file
	 */
	public String toString() {
		String s = "";
		String recurringString = "";
		String activeString = "";
		
		if (isRecurring()) {
			recurringString = "recurring";
		}
		if (isActive()) {
			activeString = "active";
		}
		
		if (isRecurring() && isActive()) {
			s += "* " + taskName + "," + recurringString + "," + activeString + "\n";
			s += getTaskDescription().trim();
		}
		
		else if (!isRecurring() && isActive()) {
			s += "* " + taskName + "," + activeString + "\n";
			s += getTaskDescription().trim();
		}
		
		else if (isRecurring() && !isActive()) {
			s += "* " + taskName + "," + recurringString + "\n";
			s += getTaskDescription().trim();
		}
		else {
			s += "* " + taskName + "\n";
			s += getTaskDescription().trim();
		}
		
		return s;
	}
}
