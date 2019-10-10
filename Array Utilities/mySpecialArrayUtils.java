public class mySpecialArrayUtils{
	/**
	* Function that reverses the Array
	* @param ar
	* 1-D Array that needed to be reversed
	* @return
	* The reverse of the array
	*/
	public void reverse(int[] ar)
	{
		for(int i = 0;i<ar.length/2;i++)
		{
			ar[i] = ar[ar.length-1-i] ^ ar[i];
			ar[ar.length-1-i] = ar[ar.length-1-i] ^ ar[i];
			ar[i] = ar[ar.length-1-i] ^ ar[i];
		}
	}
	
	/**
	* Function calculates the sum of even numbers and sum of odd numbers in an array
	* @param array
	* Array to have the sum of even numbers and odd numbers calculated
	* @return
	* 1-D array with the sum of even numbers on zero index and odd numbers on the first
	*/

	public int[] sumEvenOdd(int [] ar)
	{
		
		int sumOfEvenAndOddElements[] = {0,0};
		for(int i = 0;i<ar.length;i++)
		{
			if( (ar[i]&1) != 0)
			{
				 sumOfEvenAndOddElements[1] += ar[i];
			}
			else {
				sumOfEvenAndOddElements[0] += ar[i];
			}
		}
		return sumOfEvenAndOddElements;
	}
	
	/**
	* Function calculates the average of array elements
	* @param ar
	* Array to calculate average of it's elements
	* @return
	* The average of array elements
	*/

	public double average(int[] ar)
	{
		double average = 0;
		for(int i = 0;i<ar.length;i++)
			average += (double)ar[i]/ar.length;
		return average;
	}
	
	/**
	* Function moves a certain value to the end of array
	* @param ar
	* Array to search for the value and move it to the end
	* @param value
	* Value to move to end of array.
	* @return
	* Modified array with the numbers at end
	*/

	public void moveValue(int[] ar, int val)
	{
		int j = 0;
		for(int i = 0;i<ar.length;i++)
		{
			while(i<ar.length && ar[i] != val)
				i++;
			while(j<ar.length && ar[j]==val)
				j++;
			if(j!= ar.length)
			{
				ar[i] = ar[j];
				ar[j] = val;
			}
		}
	}
	
	/**
	* Function that transposes an Array
	* @param ar
	* Array to be transposed
	* @return
	* Transposed array
	*/

	public int[][] transpose(int[][] ar)
	{
		if(ar.length == 0)
			return null;
		int[][] transposeArr = new int [ar[0].length][ar.length];
		for(int i = 0;i<ar.length;i++)
		{
			for(int j = 0;j<ar[i].length;j++)
			{
				transposeArr[j][i] = ar[i][j];
			}
		}
		return transposeArr;
	}	
}
