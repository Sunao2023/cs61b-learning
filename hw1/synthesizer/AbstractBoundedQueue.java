package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    public int capacity() {
        return this.capacity;
    }
    public int fillCount() {
        return this.fillCount;
    }

    public boolean isEmpty() {
        return true;
    }
    public boolean isFull() {
        return true;
    }
    public abstract T peek();
    public abstract T dequeue();
    public abstract void enqueue(T x);
}
