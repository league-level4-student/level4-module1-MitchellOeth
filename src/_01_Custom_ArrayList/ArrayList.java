package _01_Custom_ArrayList;


@SuppressWarnings("unchecked")

public class ArrayList <T>{
	 T[] t;
	public ArrayList() {
		this.t = (T[]) new Object[0];
	}
	
	public T get(int loc) throws IndexOutOfBoundsException {
		
		return t[loc];
	}
	
	public void add(T val) {
	T[] a = (T []) new Object[t.length+1];
	a[a.length-1] = val;
	for (int i = 0; i < t.length; i++) {
		a[i] = t[i];
	}
	t = a;
	}
	
	public void insert(int loc, T val) throws IndexOutOfBoundsException {
		int b = 0;
		T[] a = (T []) new Object[t.length + 1];
		for (int i = 0; i < t.length+1; i++) {
			if (b == 0) {
			a[i] = t[i];
			} else {
				a[i] = t[i-1];
			}
			if (i == loc) {
				a[i] = val;
				b = 1;
			}
		}
	}
	
	public void set(int loc, T val) throws IndexOutOfBoundsException {
		t[loc] = val;
	}
	
	public void remove(int loc) throws IndexOutOfBoundsException {
		int b = 0;
		T[] a = (T []) new Object[t.length - 1];
		for (int i = 0; i < t.length-1; i++) {
			if (b == 0) {
				a[i] = t[i];
			} else {
				a[i] = t[i+1];
			}
			if (i == loc) {
				a[i] = t[i+1];
				b = 1;
			}
		}
		t = a;
	}
	
	public boolean contains(T val) {
		
		return false;
	}
}