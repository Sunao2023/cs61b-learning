public class LinkedListDeque<Godness> {
    public class DequeNode {
        DequeNode front;
        Godness item;
        DequeNode back;

        public DequeNode(DequeNode f, Godness i, DequeNode b) {
            front = f;
            item = i;
            back = b;
        }
    }

    private DequeNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new DequeNode(null, null, null);
        sentinel.back = sentinel;
        sentinel.front = sentinel;
        size = 0;
    }

    public LinkedListDeque(Godness x) {
        sentinel = new DequeNode(null, null, null);
        sentinel.back = new DequeNode(sentinel, x, sentinel);
        sentinel.front = sentinel.back;
        size = 1;
    }

    /** Adds an item to the front of the deque. */
    public void addFirst(Godness x) {
        DequeNode Node = new DequeNode(sentinel, x, sentinel.back);
        sentinel.back = Node;
        Node.back.front = Node;
        size += 1;
    }

    /** Adds an item to the back of thr deque. */
    public void addLast(Godness x) {
        DequeNode Node = new DequeNode(sentinel.front, x, sentinel);
        sentinel.front = Node;
        Node.front.back = Node;
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
        DequeNode p = sentinel.back;
        while (p != sentinel) {
            System.out.print(p.item + " ");
            p = p.back;
        }
    }

    /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public Godness removeFirst() {
        if (sentinel.back == null) {
            return null;
        }
        Godness FirstNode = sentinel.back.item;
        sentinel.back = sentinel.back.back;
        sentinel.back.front.front = null;
        sentinel.back.front.back = null;
        sentinel.back.front = sentinel;
        size -= 1;
        return FirstNode;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public Godness removeLast() {
        if (sentinel.front == null) {
            return null;
        }
        Godness LastNode = sentinel.front.item;
        sentinel.front = sentinel.front.front;
        sentinel.front.back.back = null;
        sentinel.front.back.front = null;
        sentinel.front.back = sentinel;
        size -= 1;
        return LastNode;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
     *If no such item exists, returns null. Must not alter the deque. */
    public Godness get(int index) {
        if (index > size - 1) {
            return null;
        }
        DequeNode p = sentinel.back;
        while (index > 0) {
            p = p.back;
            index -= 1;
        }
        return p.item;
    }

    /** Same as get, but uses recursion.*/
    private Godness Helper(DequeNode x, int index){
        if (index == 0) {
            return x.item;
        }
        return Helper(x.back, index - 1);
    }

    public Godness getRecursive(int index){
        return Helper(sentinel.back, index);
    }
}