public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private int capicity = 8;

    /**
     * Creates an empty list.
     */
    public ArrayDeque() {
        items = (T[]) new Object[capicity];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }
    /** Resizing the underlying array to the target capacity*/
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, nextLast);
        System.arraycopy(items, nextLast + 1, a,
                capacity/2 + nextLast, capacity/2 - nextLast - 1);
    }

    /** Adds an item to the front of the deque.*/
    public void addFirst(T x) {
        if (nextFirst % capicity == nextLast) {
            capicity *= 2;
            resize(capicity);
            nextFirst = capicity / 2 + nextLast - 1;
        }
        items[nextFirst] = x;
        nextFirst = (nextFirst - 1 + capicity) % capicity;
        size += 1;
    }

    /** Adds an item to the back of thr deque. */
    public void addLast(T x) {
        if (nextFirst % capicity == nextLast) {
            capicity *= 2;
            resize(capicity);
        }
        items[nextLast] = x;
        nextLast = (nextLast + 1) % capicity;
        size += 1;
    }

    /** Judge whether the deque is empty. */
    public boolean isEmpty() {
        return (this.size == 0);
    }

    /** Return the size of the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space. */
    public void printDeque() {
        int index = nextFirst;
        while (index % capicity != nextLast - 1) {
            System.out.print(items[nextFirst + 1] + " ");
            index += 1;
        }
    }

    /** Removes and returns the item at the front of the deque.
     * If no such item exists, returns null. */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = items[(nextFirst + 1) % capicity];
        nextFirst += 1;
        return item;
    }

    /** Removes and returns the item at the back of the deque.
     * If no such item exists, returns null. */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = items[(nextLast - 1 + capicity) % capicity];
        nextLast = (nextLast - 1 + capicity) % capicity;
        return item;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *If no such item exists, returns null. Must not alter the deque. */
    public T get(int index) {
        return items[index];
    }
}
