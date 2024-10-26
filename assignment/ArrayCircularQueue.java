package assignment;


public class ArrayCircularQueue<E> implements Queue<E>{
	private static final int CAPACITY = 1000;
	private int front= -1;
	private int rear = -1;
	private int sz = 0;
	private E[] arr;
	
	public ArrayCircularQueue() {
		this(CAPACITY);
	}
	
	public ArrayCircularQueue(int capacity) {
		arr = (E[]) new Object[capacity];
	}
	
	public int size() {
		
		return sz;
	}

	public boolean isEmpty() {
		
		return sz==0;
	}

	public E first() {
		if (isEmpty()) return null;
		return arr[front];
	}

	public void enqeue(E e) {
		if (sz==arr.length) System.out.println("Queue is full");
		else {
			if(front==-1) front=0;
			rear =(rear+1)%arr.length;
			arr[rear]=e;
			sz++;
		}
		
	}

	public E dequeue() {
		if(isEmpty()){
			System.out.println("Array is empty");
			return null;
			}
		E value = arr[front];
		arr[front] = null;
		front=(front+1)%arr.length;
		sz--;
		return value;
	}

	public Depositors get(int index) {
		if (index < 0 || index >= sz) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + sz);
        }
        int actualIndex = (front + index) % arr.length;
        return (Depositors) arr[actualIndex];
		
	}
}