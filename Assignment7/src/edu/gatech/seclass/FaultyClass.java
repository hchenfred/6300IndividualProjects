package edu.gatech.seclass;

public class FaultyClass {
	public int method1(int a, int b) {
		int x = 0;
		if (a > 0) {
			x++;
		}
		if (b > 0) {
			x++;
		} else {
			x--; 
		}
		return 100/x;
		
	}
	
	
	public int method2(int x, int a, int b) {
		if (a > 0) {
			x++;
		}
		if (b > 0) {
			x++;
		} else {
			x--; 
		}
		return 100/x;
	}
	
	public int method3(int j) {
		int i = 0;
		if (i > 0) {
			return 100/i;   //Dead
		}
		return j;
	}
	
	public void method4() {
		//branch coverage subsumes statement coverage. 
	}

}
