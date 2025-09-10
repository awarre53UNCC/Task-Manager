package edu.ncsu.csc216.wolf_tasks.model.notebook;
import java.io.File;

import edu.ncsu.csc216.wolf_tasks.model.io.NotebookWriter;
import edu.ncsu.csc216.wolf_tasks.model.tasks.AbstractTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.ActiveTaskList;
import edu.ncsu.csc216.wolf_tasks.model.tasks.Task;
import edu.ncsu.csc216.wolf_tasks.model.tasks.TaskList;
import edu.ncsu.csc216.wolf_tasks.model.util.ISortedList;
import edu.ncsu.csc216.wolf_tasks.model.util.SortedList;

/**
 * The Notebook Class controls the creation of task lists and tasks. Methods include manipulation
 * of tasks and task lists for the note book. Used for the GUI class to update the UI.
 * IssueList has five fields for the notebook object, a string for the notebook name, 
 * a boolean if the notebook has been changed, and 3 lists for current list selected, 
 * list of active tasks, and a sorted list of taskLists. 
 * @author Austin Warren and Brynn Reed
 */
public class Notebook {
	
	/** A string field for the notebook's name. */	
	private String notebookName;
	
	/** A boolean field for the notebook's change status. */	
	private boolean isChanged;
	
	/** A task list field for the current list selected */
	private AbstractTaskList currentTaskList;
	
	/** An active task list field for the list of active tasks */
	private ActiveTaskList activeTaskList;
	
	/** A sorted list field for the list of task lists */
	private ISortedList<TaskList> taskLists;
	
	/**
	 * Constructs a Notebook with the given name. The taskLists field is constructed as a SortedList 
	 * and the activeTaskList is constructed and is set to the currentTaskList. isChanged is initialized to true. 
	 * @param name the string value for the notebook's name
	 * @throws IllegalArgumentException if the notebookName is null, empty, or matches ACTIVE_TASKS_NAME.
	 */
	public Notebook(String name) {
		setNotebookName(name);
		taskLists = new SortedList<TaskList>();
		activeTaskList = new ActiveTaskList();
		currentTaskList = activeTaskList;
		setChanged(true);
	}
	
	/** 
	 * Saves the current Notebook to the given file. isChanged is updated to false.
	 * @param file a file path for the notebook to be outputted to
	 */
	public void saveNotebook(File file) {
		NotebookWriter.writeNotebookFile(file, notebookName, taskLists);
		setChanged(false);
	}
	
	/**
	 * Standard getter method for the notebookName field, returning the name as a string
	 * @return a string for the note book name
	 */
	public String getNotebookName() {
		return notebookName;
	}
	
	/**
	 * Private setter method for the notebookName field, setting the notebook name
	 * @param name the string value for the notebook's name
	 * @throws IllegalArgumentException if the notebookName is null, empty, or matches ACTIVE_TASKS_NAME.
	 */
	private void setNotebookName(String name) {
		if (name == null || name.isEmpty() || "Active Tasks".equals(name)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		this.notebookName = name;
	}

	/**
	 * Standard getter method for the isChanged field, returning the boolean value
	 * @return a boolean for the change status of the notebook
	 */
	public boolean isChanged() {
		return isChanged;
	}
	
	/**
	 * Public setter method for the isChanged field, setting the boolean value
	 * @param value the boolean value for the notebook's change status
	 */
	public void setChanged(boolean value) {
		this.isChanged = value;
	}
	
	/**
	 * The TaskList is added to the list of task lists, the current task list is 
	 * updated to the new task list, and isChanged is updated to true.
	 * @param taskList a task list to be added to the list of task lists.
	 * @throws IllegalArgumentException if the new TaskList’s name is ACTIVE_TASKS_NAME 
	 * or a duplicate of an existing TaskList
	 */
	public void addTaskList(TaskList taskList) {
		if (taskList.getTaskListName().equals("Active Tasks")) {
			throw new IllegalArgumentException("Invalid name.");
		}
		boolean isDuplicate = false;
		for (int i = 0; i < taskLists.size(); i++) {
			if (taskLists.get(i).getTaskListName().equals(taskList.getTaskListName())) {
				isDuplicate = true;
				break;
			}
		}
		if (isDuplicate) {
			throw new IllegalArgumentException("Invalid name.");
		}
		taskLists.add(taskList);
		currentTaskList = taskList;
		setChanged(true);
		getActiveTaskList();
	}
	
	/**
	 * Returns a list of task list names. The “Active Tasks” list is always listed first.
	 * @return a string array for the names of the task lists
	 */
	public String[] getTaskListsNames() {
		String[] taskListNames = new String[taskLists.size() + 1];
		taskListNames[0] = activeTaskList.getTaskListName();
		for (int i = 0; i < taskLists.size(); i++) {
			taskListNames[i + 1] = taskLists.get(i).getTaskListName();

		}
		return taskListNames;
		
	}
	
	/**
	 * A private helper method that is useful for working with the ActiveTaskList. 
	 * The order that Tasks are stored in the ActiveTask list is related to the order of the 
	 * TaskLists and then the order of the active Tasks in those TaskLists.
	 */
	private void getActiveTaskList() {
		activeTaskList.clearTasks();
	
		for (int i = 0; i < taskLists.size(); i++) {
			for (int j = 0; j < taskLists.get(i).getTasks().size(); j++) {
				Task task = taskLists.get(i).getTasks().get(j);
	            if (task.isActive()) {
	                activeTaskList.addTask(task);
	            }
			}
		}
	}
	
	/**
	 * Sets the currentTaskList to the AbstractTaskList with the given name. If a TaskList 
	 * with that name is not found, then the currentTaskList is set to the activeTaskList.
	 * @param name the name of the task list to be selected as the currentTaskList field
	 */
	public void setCurrentTaskList(String name) {
		boolean taskListFound = false;
		for (int i = 0; i < taskLists.size(); i++) { 
			if (taskLists.get(i).getTaskListName().equals(name)) {
				currentTaskList = taskLists.get(i);
				taskListFound = true;
				break;
			}
		}
		if (!taskListFound) {
			getActiveTaskList();
			currentTaskList = activeTaskList;
		}
		
	}
	
	/**
	 * Standard getter method for the currentTaskList field, returning the list
	 * @return a AbstractTaskList for the current list to be edited
	 */
	public AbstractTaskList getCurrentTaskList() {
		return currentTaskList;
	}
	
	/**
	 * Method to edit the currentTaskList, by removing the list, editing it, 
	 * then adding it back to the taskLists field. isChanged is updated to true.
	 * @param name the task list to be edited.
	 * @throws IllegalArgumentException if the currentTaskList is an ActiveTaskList, if the new name 
	 * matches “Active Tasks” (case insensitive), or is a duplicate of the name of another TaskList 
	 */
	public void editTaskList(String name) {
		
		if (currentTaskList == activeTaskList) {
			throw new IllegalArgumentException("The Active Tasks list may not be edited.");
		}
		
		if (name.equals(activeTaskList.getTaskListName())) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		boolean isDuplicate = false;
		for (int i = 0; i < taskLists.size(); i++) { 
			if (taskLists.get(i).getTaskListName().equalsIgnoreCase(name)) {
				isDuplicate = true;
				break;
			}
		}
		if (isDuplicate) {
			throw new IllegalArgumentException("Invalid name.");
		}
		
		AbstractTaskList list = currentTaskList;
		removeTaskList();
	    list.setTaskListName(name);
		taskLists.add((TaskList) list);
		
		int idx = 0;
		for (int i = 0; i < taskLists.size(); i++) { 
			if (taskLists.get(i).getTaskListName().equals(name)) {
				idx = i;
				break;
			}
		}
		currentTaskList = taskLists.get(idx);		
		setChanged(true);
	   
	}
	
	/**
	 * The currentTaskList is removed and then set to the activeTaskList. isChanged is updated to true.
	 * @throws IllegalArgumentException if the currentTaskList is an ActiveTaskList 
	 */
	public void removeTaskList() {
		
		if (currentTaskList == activeTaskList) {
			throw new IllegalArgumentException("The Active Tasks list may not be deleted.");
		}
		
		int currentIdx = 0;
		for (int i = 0; i < taskLists.size(); i++) { 
			if (taskLists.get(i) == currentTaskList) {
				currentTaskList = taskLists.get(i);
				currentIdx = i;
				break;
			}
		}
		taskLists.remove(currentIdx);
		currentTaskList = activeTaskList;
		isChanged = true;
		
	}
	
	/**
	 * If the currentTaskList is not a TaskList do nothing with the Task. 
	 * If the currentTaskList is a TaskList, then add the task and check if the Task is active. 
	 * If so, then you can update the activeTaskList. isChanged is updated to true.
	 * @param task the task to be added to the currentTaskList
	 */
	public void addTask(Task task) {
		
		if (currentTaskList instanceof TaskList) {
			currentTaskList.addTask(task);
			if (task.isActive()) {
				activeTaskList.addTask(task);
				getActiveTaskList();

			}
			setChanged(true);
		}
	}
	
	/**
	 * A Task can only be edited if the currentTaskList is a TaskList; otherwise, do nothing.
	 * If the Task can be edited, update the fields of the Task at the specified index. Check if the 
	 * Task is active. If so, then you can update the activeTaskList. isChanged is updated to true.
	 * @param idx an integer value for the index of the task to be edited
	 * @param name a string value for the name of the task to be edited
	 * @param description a string value for the description of the task to be edited
	 * @param recurring a boolean value for the recurring status to be edited
	 * @param active a boolean value for the active status to be edited
	 */
	public void editTask(int idx, String name, String description, boolean recurring, boolean active) {
		if (currentTaskList instanceof TaskList) {
			 Task task = currentTaskList.getTask(idx);
		     task.setTaskName(name);
		     task.setTaskDescription(description);
		     task.setRecurring(recurring);
		     
		     if (!task.isActive() && active) {
			     task.setActive(active);
		    	 activeTaskList.addTask(task);
		    	 getActiveTaskList();
		     }
		     else if (task.isActive() && !active) {
			     task.setActive(active);
			     
			     int index = 0;
			     for (int i = 0; i < activeTaskList.getTasks().size(); i++) {
			    	 if (activeTaskList.getTask(i) == task) {
			    		 index = i;
			    		 break;
			    	 }
			     }
		    	 activeTaskList.removeTask(index);
				 getActiveTaskList();
		     }
		     else {
		    	 task.setActive(active);
		     }
		     setChanged(true);
		}
	}

}
