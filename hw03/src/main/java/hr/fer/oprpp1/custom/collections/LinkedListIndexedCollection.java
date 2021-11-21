package hr.fer.oprpp1.custom.collections;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Collection implementation built like a linked list
 *
 * @param <T> the type parameter
 * @author franzekan
 * @version 1.0
 */
public class LinkedListIndexedCollection<T> implements List<T> {
    private long modificationCount = 0;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        LinkedListIndexedCollection<Integer> l = new LinkedListIndexedCollection<>();
        l.add(1);
        l.add(2);
        l.add(3);
        l.add(4);
        l.add(5);

        l.forEach(o -> System.out.printf("%d, ", o));
        System.out.println();

        l.insert(9, 5);

        l.forEach(o -> System.out.printf("%d, ", o));
        System.out.println();
    }

    private static class LinkedListIndexedCollectionElementsGetter<K> implements ElementsGetter<K> {
        private ListNode<K> node;
        private final LinkedListIndexedCollection<K> collection;
        private final long savedModificationCount;

        /**
         * Instantiates a new Linked list indexed collection elements getter.
         *
         * @param collection the collection
         */
        public LinkedListIndexedCollectionElementsGetter(LinkedListIndexedCollection<K> collection) {
            this.collection = collection;
            this.node = this.collection.first;
            this.savedModificationCount = this.collection.modificationCount;
        }

        @Override
        public K getNextElement() {
            if (!this.hasNextElement()) {
                throw new NoSuchElementException();
            }

            K tmp = this.node.value;
            this.node = this.node.next;
            return tmp;
        }

        @Override
        public boolean hasNextElement() {
            if (this.savedModificationCount != this.collection.modificationCount) {
                throw new ConcurrentModificationException();
            }

            return this.node != null;
        }
    }

    public ElementsGetter<T> createElementsGetter() {
        return new LinkedListIndexedCollectionElementsGetter<>(this);
    }

    /**
     * Internal class used to represent a node in a linked list
     */
    private static class ListNode<K> {
        /**
         * Node value
         */
        private final K value;

        /**
         * Reference to previous node
         */
        private ListNode<K> previous;

        /**
         * Reference to next node
         */
        private ListNode<K> next;

        /**
         * Constructor with all properties as arguments
         *
         * @param value    the value
         * @param previous the previous
         * @param next     the next
         */
        public ListNode(K value, ListNode<K> previous, ListNode<K> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        /**
         * Constructor with value and previous node
         *
         * @param value    the value
         * @param previous the previous
         */
        public ListNode(K value, ListNode<K> previous) {
            this(value, previous, null);
        }

        /**
         * Constructor with only value
         *
         * @param value the value
         */
        public ListNode(K value) {
            this(value, null, null);
        }

        /**
         * Helper for easier debugging and representation
         *
         * @param node node to get hashCode for
         * @return hashCode as hex
         */
        private String getHashCode(ListNode<K> node) {
            if (node == null) {
                return "null";
            }

            return Integer.toHexString(node.hashCode());
        }

        @Override
        public String toString() {
            return "ListNode {" +
                    "value = " + value + ", " +
                    "prev = " + this.getHashCode(previous) + ", " +
                    "hash = " + this.getHashCode(this) + ", " +
                    "next = " + this.getHashCode(next) +
                    "}";
        }
    }

    /**
     * Size of list
     */
    private int size = 0;

    /**
     * Reference to the first element in the list
     */
    private ListNode<T> first = null;

    /**
     * Reference to the last element in the list
     */
    private ListNode<T> last = null;

    public boolean isEmpty() {
        return this.first == null;
    }

    /**
     * Empty constructor
     */
    public LinkedListIndexedCollection() {
    }

    /**
     * Constructor that copies an existing list into <code>this</code>
     *
     * @param initCollection the init collection
     */
    public LinkedListIndexedCollection(Collection<T> initCollection) {
        this.addAll(initCollection);
        this.size = initCollection.size();
    }

    /**
     * Adds the value to the list
     *
     * @param value value to be added
     * @throws NullPointerException if value is null
     */
    @Override
    public void add(T value) {
        if (value == null) {
            throw new NullPointerException();
        }

        this.size++;
        this.modificationCount++;

        if (this.last == null) {
            this.last = new ListNode<>(value);
            this.first = this.last;

            return;
        }

        this.last.next = new ListNode<>(value, this.last);
        this.last = this.last.next;
    }

    /**
     * Get element by index
     *
     * @param index the index
     * @return element on index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        boolean fromStart = this.size / 2 + 1 > index;
        int for_max = fromStart ? index : this.size - index - 1;

        ListNode<T> tmp = fromStart ? this.first : this.last;
        for (int i = 0; i < for_max; i++) {
            tmp = fromStart ? tmp.next : tmp.previous;
        }

        return tmp.value;
    }

    @Override
    public void insert(T value, int index) {
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            this.first.previous = new ListNode<>(value, null, this.first);
            this.first = this.first.previous;
            return;
        }

        ListNode<T> ln = this.first;
        for (int i = 0; i < index; i++) {
            if (i == index - 1) {
                ln.next = new ListNode<>(value, ln);
                return;
            }

            ln = ln.next;
        }

        ListNode<T> tmp = new ListNode<>(value, ln.previous, ln);
        ln.previous.next = tmp;
        ln.previous = tmp;
    }

    @Override
    public int indexOf(Object value) {
        int i = 0;

        ListNode<T> tmp = this.first;
        if (tmp == null) {
            throw new NullPointerException();
        }

        while (tmp.next != null) {
            if (tmp.value.equals(value)) {
                return i;
            }

            i++;
            tmp = tmp.next;
        }

        return -1;
    }


    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        this.size = 0;
        this.modificationCount++;

        this.first = null;
        this.last = null;
    }

    @Override
    public boolean contains(Object value) {
        ListNode<T> tmp = this.first;

        while (tmp != null) {
            if (tmp.value.equals(value)) {
                return true;
            }

            tmp = tmp.next;
        }

        return false;
    }

    /**
     * Remove element by index
     *
     * @param index the index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public void remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        this.modificationCount++;

        if (index == 0) {
            this.first = this.first.next;
            this.first.previous = null;
            this.size--;
            return;
        }

        ListNode<T> tmp = this.first;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }

        if (tmp.previous != null) {
            tmp.previous.next = tmp.next;
        }
        this.size--;
    }

    @Override
    public boolean remove(T value) {
        ListNode<T> tmp = this.first;

        while (tmp != null) {
            if (tmp.value.equals(value)) {
                if (this.first == tmp) {
                    this.first = this.first.next;
                    this.first.previous = null;
                } else {
                    tmp.previous.next = tmp.next;
                }

                this.size--;
                this.modificationCount++;
                return true;
            }

            tmp = tmp.next;
        }

        return false;
    }

    @Override
    public T[] toArray() {
        class GetProcessor implements Processor<T> {
            private int i = 0;
            private final T[] arr;

            @SuppressWarnings("unchecked")
            public GetProcessor(int size) {
                this.arr = (T[]) new Object[size];
            }

            public T[] getArr() {
                return arr;
            }

            @Override
            public void process(T value) {
                this.arr[i++] = value;
            }
        }

        GetProcessor processor = new GetProcessor(this.size);
        this.forEach(processor);

        return processor.getArr();
    }

    @Override
    public void forEach(Processor<T> processor) {
        ListNode<T> tmp = this.first;
        while (tmp != null) {
            processor.process(tmp.value);
            tmp = tmp.next;
        }
    }
}
