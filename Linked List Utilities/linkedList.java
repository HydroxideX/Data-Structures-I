class linkedList {
	
	public linkedListNode head;
	private int size;
	
	public void insertNode(double value) {
		linkedListNode node = new linkedListNode(value);
		node.setNext(head);
		head = node;
		size++;
	}

	public int getSize() {
		return size;
	}

	public void removeHead() {
		head = head.getNext();
		size--;
	}

	public void ListDisplay() {
		for (linkedListNode currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
			System.out.println(currentNode.getValue());
		}
	}

	/**
	* Function which find the sum, average, median, maximum and minimum
	* of the values in the list
	* @return
	* Returns the sum, average, median, maximum and minimum
	* of the values in the list in a 1-D array
	*/
	public double[] summary() {
		double[] summaryArray = new double[5];
		int ct;
		boolean evenSize = true;
		if (size % 2 == 1) {
			ct = (int) size / 2;
			evenSize = false;
		}  else {
			ct = (int) size / 2 - 1;
		}
		double sum = 0;
		double temp;
		double min = head.getValue();
		double max = head.getValue();
		int i = 0;
		for (linkedListNode currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
			sum += currentNode.getValue();
			temp = currentNode.getValue();
			if (temp > max)
				max = temp;
			temp = currentNode.getValue();
			if (temp < min)
				min = temp;
			if (i == ct ) {
				if (evenSize) {
					summaryArray[2] = (currentNode.getValue() + currentNode.getNext().getValue()) / 2.0;
				} else {
					summaryArray[2] = currentNode.getValue();
				}
			}
		}
		summaryArray[0] = sum;
		summaryArray[1] = sum / (double) size;
		summaryArray[3] = max;
		summaryArray[4] = min;
		return summaryArray;
	}

		/**
		* Function that reverses a linkedList
		* @return
		* Returns the new head of the reversed list
		*/
	public linkedListNode reverse(linkedListNode head) {
		if (size <= 1)
			return head;
		linkedListNode current = head.getNext();
		linkedListNode previous = head;
		head.setNext(null);
		while (current != null) {
			previous = current;
			current = current.getNext();
			previous.setNext(head);
			head = previous;
		}
		return head;
	}

	public   linkedListNode evenIndexedElements() {
		linkedList evenElements = new linkedList();
		evenElements.head.setValue(head.getValue());
		for (linkedListNode currentNode= head; currentNode!= null; ) {
			if (currentNode.getNext() == null) {
				break;
			} else {
				if(currentNode.getNext().getNext() == null) {
					break;
				} else {
					evenElements.insertNode(currentNode.getNext().getNext().getValue());
					currentNode = currentNode.getNext().getNext();
				}
			}
		}
		if(evenElements.getSize() > 0 ) {
			evenElements.head = evenElements.reverse(evenElements.head);
		}
		return evenElements.head;
	}

	public   linkedListNode insertionSort() {
		linkedListNode  previous = head, current = null, after = null;
		if (head != null) {
			current = head.getNext();
			if (head.getNext() != null) {
				after = head.getNext().getNext();
			}
		}
		while (current != null) {
			if (current.getValue() < head.getValue()) {
				previous.setNext(after);
				current.setNext(head);
				head = current;
				current = after;
				if (after != null) {
					after = after.getNext();
				}
				continue;
			}
			if(current.getValue() > previous.getValue()) {
				linkedListNode temp = head;
				while (temp != null && temp.getNext() != current) {
					temp = temp.getNext();
				}
				current.setNext(temp.getNext());
				temp.setNext(current);
				previous.setNext(after);
				current = after;
				if (after != null) {
					after = after.getNext();
				}
			}
		}
		return head;
	}

	public linkedListNode mergeSort(linkedListNode head,int size) {
		if(size<2) return head;
		linkedListNode left = head;
		linkedListNode right;
		int j = 0;
		while(j!=size/2) {
			left = left.getNext();
			j++;
		}
		right = left;
		left = head;
		left = mergeSort(left,j);
		right = mergeSort(right,size-j);
		linkedList tempList;
		tempList = merge(left,right,j,size-j);
		head = tempList.head;
		return head;
	}
	public linkedList merge(linkedListNode left,linkedListNode right,int sizeLeft,int sizeRight) {
		linkedList List = new linkedList();
		linkedListNode ctRight = right, ctLeft = left;
		int i1 = 0, i2= 0;
		while(i1<sizeLeft && i2<sizeRight) {
			if(ctRight.getValue()<ctLeft.getValue()) {
				List.insertNode(ctRight.getValue());
				ctRight = ctRight.getNext();
				i2++;
			}
			else {
				List.insertNode(ctLeft.getValue());
				ctLeft = ctLeft.getNext();
				i1++;
			}
		}
		while(i1<sizeLeft) {
			List.insertNode(ctLeft.getValue());
			ctLeft = ctLeft.getNext();
			i1++;
		}
		while(i2<sizeRight) {
			List.insertNode(ctRight.getValue());
			ctRight = ctRight.getNext();
			i2++;
		}
		List.head = List.reverse(List.head);
		return List;
	}


	public linkedListNode removeCentralNode(linkedListNode head) {
		int ct, i= 0;
		if (size % 2 == 1)
			ct = size / 2;
		else
			ct = size / 2 - 1;
		linkedListNode previous = head;
		for (linkedListNode current = head; current != null; current = current.getNext()) {
			if (i == ct) {
				previous.setNext(current.getNext());
				break;
			}
			i++;
			previous = current;
		}
		return head;
	}

	public boolean palindrome(linkedListNode head) {
		linkedListNode currentNode= null;
		for (linkedListNode leftIterator = head; leftIterator != null; leftIterator = leftIterator.getNext()) {
			for (linkedListNode rightIterator = head; rightIterator != null; rightIterator = rightIterator.getNext()) {
				if (rightIterator.getNext() == currentNode) {
					if (leftIterator.getValue() != rightIterator.getValue())
						return false;
					currentNode = rightIterator;
				}
			}
		}
		return true;
	}
}
