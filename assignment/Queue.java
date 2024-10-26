package assignment;

public interface Queue<E> {
	public int size();
	public boolean isEmpty();
	public E first();
	public void enqeue (E e);
	public E dequeue();
}
