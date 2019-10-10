class linkedListNode{
	private double value;
	private linkedListNode Next ;
	
	public linkedListNode(double newValue) {
		value = newValue;
	}
	
	public void setValue(double newValue) {
		value = newValue;
	}
	
	public void setNext(linkedListNode next) {
		Next = next;
	}
	
	public double getValue() {
		return value;
	}
	
	public linkedListNode getNext() {
		return Next;
	}
	
}