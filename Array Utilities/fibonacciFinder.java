class fibonacciFinder implements fibonnaciInterface{
	
	public int fibonacci(int index) {
		int fib = 0 , temp = 1;
		for (int i = 1; i <index ;i++) {
			fib += temp;
			temp = fib - temp;
		}
		return fib;
	}
}
