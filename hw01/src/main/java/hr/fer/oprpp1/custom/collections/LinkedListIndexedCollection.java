package hr.fer.oprpp1.custom.collections;

/**
 * Collection implementation built like a linked list
 *
 * @author franzekan
 * @version 1.0
 */
public class LinkedListIndexedCollection extends Collection {
    /**
     * Internal class used to represent a node in a linked list
     */
    private static class ListNode {
        /**
         * Node value
         */
        private final Object value;

        /**
         * Reference to previous node
         */
        private ListNode previous;

        /**
         * Reference to next node
         */
        private ListNode next;

        /**
         * Constructor with all properties as arguments
         *
         * @param value    the value
         * @param previous the previous
         * @param next     the next
         */
        public ListNode(Object value, ListNode previous, ListNode next) {
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
        public ListNode(Object value, ListNode previous) {
            this(value, previous, null);
        }

        /**
         * Constructor with only value
         *
         * @param value the value
         */
        public ListNode(Object value) {
            this(value, null, null);
        }

        /**
         * Helper for easier debugging and representation
         *
         * @param node node to get hashCode for
         * @return hashCode as hex
         */
        private String getHashCode(ListNode node) {
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
    private ListNode first = null;

    /**
     * Reference to the last element in the list
     */
    private ListNode last = null;

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
    public LinkedListIndexedCollection(Collection initCollection) {
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
    void add(Object value) {
        if (value == null) {
            throw new NullPointerException();
        }

        this.size++;

        if (this.last == null) {
            this.last = new ListNode(value);
            this.first = this.last;

            return;
        }

        this.last.next = new ListNode(value, this.last);
        this.last = this.last.next;
    }

    /**
     * Get element by index
     *
     * @param index the index
     * @return element on index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    Object get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        boolean fromStart = this.size / 2 + 1 > index;
        int for_max = fromStart ? index : this.size - index - 1;

        ListNode tmp = fromStart ? this.first : this.last;
        for (int i = 0; i < for_max; i++) {
            tmp = fromStart ? tmp.next : tmp.previous;
        }

        return tmp.value;
    }


    @Override
    int size() {
        return this.size;
    }

    @Override
    void clear() {
        this.size = 0;

        ListNode tmp = this.last;
        while (tmp.previous != null) {
            tmp = tmp.previous;
            tmp.next = null;
        }

        this.first = null;
        this.last = null;
    }

    @Override
    boolean contains(Object value) {
        ListNode tmp = this.first;

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
     * @return the boolean
     * @throws IndexOutOfBoundsException if index is out of range
     */
    boolean remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            this.first = this.first.next;
            this.first.previous = null;
            this.size--;

            return true;
        }

        ListNode tmp = this.first;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
        }

        tmp.previous.next = tmp.next;
        this.size--;

        return true;
    }

    @Override
    boolean remove(Object value) {
        ListNode tmp = this.first;

        while (tmp != null) {
            if (tmp.value.equals(value)) {
                if (this.first == tmp) {
                    this.first = this.first.next;
                    this.first.previous = null;
                } else {
                    tmp.previous.next = tmp.next;
                }

                this.size--;
                return true;
            }

            tmp = tmp.next;
        }

        return false;
    }

    @Override
    Object[] toArray() {
        class GetProcessor extends Processor {
            private int i = 0;
            private final Object[] arr;

            public GetProcessor(int size) {
                this.arr = new Object[size];
            }

            public Object[] getArr() {
                return arr;
            }

            @Override
            public void process(Object value) {
                this.arr[i++] = value;
            }
        }

        GetProcessor processor = new GetProcessor(this.size);
        this.forEach(processor);

        return processor.getArr();
    }

    @Override
    void forEach(Processor processor) {
        ListNode tmp = this.first;
        while (tmp != null) {
            processor.process(tmp.value);
            tmp = tmp.next;
        }
    }
}
