package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * Class for a sorted list using linked nodes
 * Contains methods to add, remove, a task list, and methods to check if the element is in the list
 * and to get that specific list. Methods used to manipulate task lists.
 * @author Austin Warren and Brynn Reed
 * @param <E> type for the SortedList
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	
	/** A LinkedNode field for the front of the list */
	private LinkedNode front;
	/** A integer field for the size of the list */
	private int size;
	
	/**
	 * Private class to construct LinkedNodes for the SortedList class,
	 * Contains a constructor to set the data and next fields. 
	 */
	private class LinkedNode {
		
		/** Data field for the element of the current LinkedNode */ 
		public E data;
		/** LinkedNode object represnting the next node in the list */
		public LinkedNode next;
		
		/**
		 * Constructor to create node by setting the data and next fields
		 * @param value integer to store
		 * @param next reference to next node in the list
		 */
		public LinkedNode(E value, LinkedNode next) {
			this.data = value;
			this.next = next;
		}		
	}
	
	/**
	 * A method to add a element to the list
	 * @param e the element to be added to the sorted list
	 */
	public void add(E e) {
		if (e == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		
		if (contains(e)) {
			throw new IllegalArgumentException("Cannot add duplicate element.");
		}
		if (front == null || front.data.compareTo(e) > 0) {
			LinkedNode newFront = new LinkedNode(e, front);
		    front = newFront;		
		} 
		else {
			 LinkedNode current = front;
		     while (current.next != null && current.next.data.compareTo(e) <= 0) {
		    	 current = current.next;
		     }
		     current.next = new LinkedNode(e, current.next);
		}
		
		size++;
	}
	
	/**
	 * A method to remove an element from the sorted list, returning the element removed
	 * @param idx and integer for the index of which to be removed
	 * @return the element removed from the list
	 * @throws IndexOutOfBoundsException if the index is invalid for the list
	 */
	public E remove(int idx) {
	    checkIndex(idx);
	    
	    if (idx == 0) {
	        E temp = front.data;
	        front = front.next;
	        size--;
	        return temp;
	    } 
	    else {
	        LinkedNode current = front;
	        
	        for (int i = 0; i < idx - 1; i++) {
	            current = current.next;
	        }
	        
	        E temp = current.next.data;
	        current.next = current.next.next;
	        size--;
	        return temp;
	    }
	}
	
	/**
	 * Helper method to check the index's validity, throws exception if invalid
	 * @param idx the index to be checked
	 * @throws IndexOutOfBoundsException if the index is invalid for the list
	 */
	private void checkIndex(int idx) {
		if (idx < 0 || idx >= size) {
	        throw new IndexOutOfBoundsException("Invalid index.");
	    }
	}

	/**
	 * Method to check if the sorted list contains a specific element
	 * @param e an element to be checked if the list contains it
	 * @return a boolean whether or not the list contains the element
	 */
	public boolean contains(E e) {
		LinkedNode current = front;

		if (current == null) {
			return false;
		}
		else if (current.data == e) {
			return true;
		}
        while (current.next != null) 
        {
            current = current.next;
            if (current.data.equals(e)) {
            	return true;
            }
        }
		return false;
	}
	
	/**
	 * Method to get the element at a specific index
	 * @param idx an integer for the index of the element to be returned 
	 * @return the element at the index in the parameter
	 * @throws IndexOutOfBoundsException if the index is invalid for the list
	 */
	public E get(int idx) {
		checkIndex(idx);
		LinkedNode current = front;
		for (int i = 0; i < idx; i++) {
			current = current.next;
		}
		return current.data;
	}
	
	/**
	 * A method to return the size of the sorted list
	 * @return an integer for the size of the list
	 */
	public int size() {
		return size;
    }
	
}
