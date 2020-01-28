/*
 * Name: David Ward
 * Student Number: V00920409
 */
public class IntegerLinkedList implements IntegerList
{
	IntegerNode head;
	IntegerNode tail;
	int count;
	
	public IntegerLinkedList()
	{
		head = null;
		tail = null;
		count = 0;
	}

	/*
	 * PURPOSE:
	 *   Add the element x to the front of the list.
	 *
	 * PRECONDITIONS:
	 *   None.
	 *
	 * Examples:
	 *
	 * If l is {1,2,3} and l.addFront(9) returns, then l is {9,1,2,3}.
	 * If l is {} and l.addFront(3) returns, then l is {3}.
	 */
	public void addFront (int x)//works
	{
		IntegerNode n = new IntegerNode(x);
		if (head != null)
		{
			n.next = head;
			head.prev = n;
			head = n;
		}
		else
		{
			head = n;
			tail = n;
		}
		count++;
	}
	
	/*
	 * PURPOSE:
	 *   Add the element x to the back of the list.
	 *
	 * PRECONDITIONS:
	 *   None.
	 *
	 * Examples:
	 *
	 * If l is {1,2,3} and l.addBack(9) returns, then l is {1,2,3,9}.
	 * If l is {} and l.addBack(9) returns, then l is {9}.
	 */
	public void addBack (int x)//works
	{
		IntegerNode n = new IntegerNode(x);
		if (head != null)
		{
			tail.next = n;
			n.prev = tail;
			tail = n;
		}
		else
		{
			head = n; 
			tail = n;
		}
		count++;
	}

	/*
	 * PURPOSE:
	 *   Add the element x at position pos in the list.
	 *
	 * Note:
	 *   In a list with 3 elements, the valid positions for addAt are
	 *   0, 1, 2, 3.
	 *
	 * PRECONDITIONS:
	 *   pos >= 0 and pos <= l.size()
	 *
	 * Examples:
	 *
	 * If l is {} and l.addAt(9,0) returns, then l is {9}.
	 * If l is {1} and l.addAt(9,0) returns, then l is {9,1}.
	 * If l is {1,2} and l.addAt(9,1) returns, then l is {1,9,2}
	 * If l is {1,2} and l.addAt(9,2) returns, then l is {1,2,9}
	 */
	public void addAt (int x, int pos)//works
	{
		IntegerNode n = new IntegerNode(x);
		IntegerNode p = head;
		int i = 0;//index counter, skip first element. its covered by if(pos == 0)
		//check edge cases
		if(p == null)//list empty
		{
			head = n;
			tail = n;
		}
		else if(pos == 0)//add at front
		{
			n.next = head;
			head.prev = n;
			head = n;
		}
		else if(pos == size())//add at back
		{
			tail.next = n;
			n.prev = tail;
			tail = n;
		}
		else
		{
			for (int j = 0; j < pos; j++)
			{
				p = p.next;
			}
			n.next = p;	
			n.prev = p.prev;			
			p.prev.next = n;
			p.prev = n;	
		}
		count++;//increment count
	}

	/*
	 * PURPOSE:
	 *	Return the number of elements in the list
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {7,13,22} l.size() returns 3
	 *	If l is {} l.size() returns 0
	 */
	public int size()//works
	{
		return count;
	}

	/*
	 * PURPOSE:
	 *   Return the element at position pos in the list.
	 *
	 * PRECONDITIONS:
	 *	pos >= 0 and pos < l.size()
	 *
	 * Examples:
	 *	If l is {67,12,13} then l.get(0) returns 67
	 *	If l is	{67,12,13} then l.get(2) returns 13
	 *	If l is {92} then the result of l.get(2) is undefined.
	 *
	 */
	public int get (int pos)
	{
		IntegerNode p = head;
		/*
		int i = 0;
		while (p != null) // while on a non-null element 
		{
			if (i == pos)//if at specified position
			{
				return p.value;
			}
			//increment
			p = p.next;
			i++;
		}
		return -1;
		*/
		for (int i = 0; i < pos; i++)
		{
			p = p.next;
		}
		return p.value;
	}
	/*
	 * PURPOSE:
	 *   Remove all elements from the list.  After calling this
	 *   method on a list l, l.size() will return 0
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {67,12,13} then after l.clear(), l is {}
	 *	If l is {} then after l.clear(), l is {}
	 *
	 */
	public void clear()//works
	{
		count = 0;
		head = null;
		tail = null;
	}
	/*
	 * PURPOSE:
	 *   Remove all instances of value from the list.
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {67,12,13,12} then after l.remove(12), l is {67,13}
	 *	If l is {1,2,3} then after l.remove(2), l is {1,3}
	 *	If l is {1,2,3} then after l.remove(99), l is {1,2,3}
	 */
	public void remove (int value)//DOES NOT WORK
	{
		IntegerNode p = head;
		int i = 0;
		int oldCount = count;
		while (p != null) // while on a non-null element 
		{
			if (p.value == value)//if at specified position
			{
				if (i == 0)//first element
				{
					head = p.next;
					p.prev = null;
					
				}
				else if (i == oldCount-1)//last element
				{
					p = tail;
					tail = p.prev;
					tail.next = null;
					p.prev = null;
					
				}
				else//middle element
				{
					p.next.prev = p.prev;
					p.prev.next = p.next;
					//p.next = null;
					//p.prev = null;
				}
				count--;
			}
			//increment
			p = p.next;
			i++;
		}
	}
	/*
	 * PURPOSE:
	 *   Remove the element at position pos in the list.
	 *
	 * Note:
	 *   In a list with 3 elements, the valid positions for removeAt are
	 *   0, 1, 2.
	 *
	 * PRECONDITIONS:
	 *   pos >= 0 and pos < l.size()
	 *
	 * Examples:
	 *
	 * If l is {1} and l.removeAt(0) returns, then l is {}.
	 * If l is {1,2,3} and l.removeAt(1) returns, then l is {1,3}
	 * If l is {1,2,3} and l.removeAt(2) returns, then l is {1,2}
	 */
	public void removeAt (int pos)//works
	{
		int i = 0;
		IntegerNode p = head;
		if (pos == 0)
		{
			head = p.next;
			p.prev = null;
		}
		else if(pos == count-1)
		{
			p = tail;
			tail = p.prev;
			tail.next = null;
			p.prev = null;
		}
		else
		{
			for (int j = 0; j < pos; j++)
			{
				p = p.next;
			}
			p.next.prev = p.prev;
			p.prev.next = p.next;
			p.next = null;
			p.prev = null;
		}
		count--;
	}
	/*
	 * PURPOSE:
	 *	Return a string representation of the list
	 *
	 * PRECONDITIONS:
	 *	None.
	 *
	 * Examples:
	 *	If l is {1,2,3,4} then l.toString() returns "{1,2,3,4}"
	 *	If l is {} then l.toString() returns "{}"
	 *
	 */
	public String toString() //works
	{
		IntegerNode p = head;
		String s = "{";
		if (p != null)
		{
			while (p.next != null)
			{
				s += Integer.toString(p.value) + ",";
				p = p.next;
			}
			s += Integer.toString(p.value);
		}
		s += "}";
		return s;
	}
}