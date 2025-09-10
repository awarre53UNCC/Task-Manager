package edu.ncsu.csc216.wolf_tasks.model.util;

/**
 * Class for a custom array based list called SwapList
 * Contains methods to add, remove, or prioritize tasks by moving up/down the list
 * SwapList can contain duplicates
 * @author Austin Warren and Brynn Reed
 * @param <E> type for the ISwapList
 */
public class SwapList<E> implements ISwapList<E> {
	
	/** A constant integer for the initial size of list field */
	private static final int INIT_SIZE = 10;
	
	/** A field for an array of E, used to store elements */
	private E[] list;
	
	/** A integer field to hold the size of the array */
	private int size;	
	
	/**
	 * Constructor to inialize size and create the initial list for the field
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		size = 0;
		list = (E[]) new Object[INIT_SIZE];
	}
	
	/**
	 * Adds the element to the end of the list.
	 * @param element the element to be added to the list
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added 
	 */
	public void add(E element) {
		checkCapacity(size);
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		try {
			size++;
            list[size - 1] = element;
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot add null element.");
        }
		
	}
	
	/**
	 * Helper method to check the capacity
	 * @param s the int of the capacity to be checked
	 */
	private void checkCapacity(int s) {
		if (s >= list.length) {
	        int capacity = list.length * 2;
	        
	        @SuppressWarnings("unchecked")
			E[] newList = (E[]) new Object[capacity];
	        
	        for (int i = 0; i < list.length; i++) {
	        	newList[i] = list[i];
	        }
	        list = newList;
	    }
	}
	
	/**
	 * Returns the element from the given index. The element is removed from the list.
	 * @param idx index to remove element from
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	public E remove(int idx) {
		checkIndex(idx);
		
		E temp;
		temp = list[idx];
		list[idx] = null;
		
		for (int i = idx; i < size; i++) {
			list[i] = list[i + 1];
			
		}
		list[size - 1] = null; 
		size--;
		return temp;
	}
	
	/**
	 * Helper method to check the index validity, throws exception if invalid
	 * @param idx the index to be checked
	 * @throws IndexOutOfBoundsException if the index is invalid for the array
	 */
	private void checkIndex(int idx) {
		if (idx < 0 || idx >= size) {
	        throw new IndexOutOfBoundsException("Invalid index.");
	    }
	}
	
	/**
	 * Moves the element at the given index to index-1.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move up
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	public void moveUp(int idx) {
		checkIndex(idx);
		if (idx != 0) {
			E val1 = list[idx];
			E val2 = list[idx - 1];

			list[idx] = val2;
			list[idx - 1] = val1;
		}
	}
	
	/**
	 * Moves the element at the given index to index+1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move down
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	public void moveDown(int idx) {
		
		checkIndex(idx);
		if (idx != size() - 1) {
			E val1 = list[idx];
			E val2 = list[idx + 1];

			list[idx] = val2;
			list[idx + 1] = val1;
		}
	}
	
	/**
	 * Moves the element at the given index to index 0.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move to the front
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	public void moveToFront(int idx) {
		checkIndex(idx);
		if (idx != 0) {	
			moveUp(idx);
			
			for (int i = 1; i < idx; i++) {
					moveUp(idx - i);
			}
	
		}
	}
	
	/**
	 * Moves the element at the given index to size-1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move to the back
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	public void moveToBack(int idx) {
		checkIndex(idx);
		if (idx != size - 1) {
			moveDown(idx);
			for (int i = idx; i < size - 2; i++) {
					moveDown(i + 1);
			}
		}
	}
	
	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	public E get(int idx) {
		checkIndex(idx);
		return list[idx];
		
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	public int size() {
		return this.size;
	}

}
