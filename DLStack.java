/*
 * Class DLStack extended stack ADT implemented using a doubly linked list
 * @Rubaisha  CS1027 Assignment 3
 */

public class DLStack<T> implements DLStackADT<T> {

	private DoubleLinkedNode<T> top; // reference to the top of the stack
	private int numItems; // number of data items stored in stack
	

	public DLStack() { // create empty stack
		top = null;
		numItems = 0;
	}
	
	/*
	 * add the dataItems to the top of stack
	 */
	public void push(T dataItem) {

		DoubleLinkedNode<T> list = new DoubleLinkedNode<T>(dataItem);
		if (top == null) {
			top = list; }
		else if (top != null) {
			list.setPrevious(top);
	        top.setNext(list);
	        top = list;
	        
		}
		numItems++;
		
	}
	/*
	 * throw empty stack exception if empty 
	 * else remove and return the top of stack by going through the numItems
	 */
	public T pop() throws EmptyStackException{
		
		T result;
		if (isEmpty()) {
			throw new EmptyStackException("Empty Stack");
		} else {
		result = top.getElement();
		
		if(numItems != 1) {
			top = top.getPrevious();
			top.setNext(null);
			
		}
		numItems--;

		return result;
	}}
	
	/*
	 * throw invalid item exception if the int k is less than or equal to 0 or its greater than numItems
	 * loop through k int and remove the kth item from top of stack
	 */
	public T pop(int k) throws InvalidItemException{
		if (k > numItems) {
			throw new InvalidItemException("Invalid Item");
		}
		else if (k <= 0){
			throw new InvalidItemException("Invalid Item");
		} else {
		T kDataItem = null;
		DoubleLinkedNode<T> data = new DoubleLinkedNode<T>();
		
		 if (k == 1) {
			 kDataItem = pop(); // return the top 
		 } else if (k > 1) {
				data = top;
				
				for (int i = 1; i < k ; i++) { // loop through k length and remove top and go to the kth item
					data = data.getPrevious();
				}
				data.getNext().setPrevious(data.getPrevious());
				data.getPrevious().setNext(data.getNext());
				kDataItem = data.getElement();

		 }
		 numItems--;
		return kDataItem;}
		
	}
	
	/*
	 * get top without removing 
	 * throw empty stack exception if it is empty
	 */
	public T peek() throws EmptyStackException{
		if (!(isEmpty())) {
			return top.getElement();
		} else {
			throw new EmptyStackException("Empty Stack");
		} 
	}
	
	/*
	 * return if true if stack is empty else return false
	 */
	public boolean isEmpty() {
		return (numItems == 0);
	}
	
	/*
	 * return data items number
	 */
	public int size() {
		return numItems; 
	}
	
	/*
	 * return the top of stack
	 */
	public DoubleLinkedNode<T> getTop(){
		return top;
	}
	
	/*
	 * return data in string form 
	 */
	public String toString() {
		DoubleLinkedNode<T> topNode = new DoubleLinkedNode<T>();
		String dataString = "";
		topNode = top;
		
		if  (topNode != null) {
			for (int i = 1; i < numItems ;i++) {
				dataString = dataString + topNode.getElement(); // add top in the string
			}
				topNode.getPrevious(); //go to the previous node
		}
		dataString = "[" + dataString + "]";
		return dataString;
	}

}

	
