package hr.fer.oprpp1.custom.collections;

public class LinkedListIndexedCollection extends Collection {
//    public static void main(String[] args) {
//        LinkedListIndexedCollection demo = new LinkedListIndexedCollection();
//
//        demo.add("Hello");
//        demo.add("World");
//        demo.add("12345");
//
//        demo.remove(2);
//
//        ListNode tmp = demo.first;
//        while (tmp != null) {
//            System.out.println(tmp);
//            tmp = tmp.next;
//        }
//    }

    private static class ListNode {
        private final Object value;

        private ListNode previous;
        private ListNode next;

        public ListNode(Object value, ListNode previous, ListNode next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }

        public ListNode(Object value, ListNode previous) {
            this(value, previous, null);
        }

        public ListNode(Object value) {
            this(value, null, null);
        }

        private String getHashCode(ListNode ln) {
            if (ln == null) {
                return "null";
            }

            return Integer.toHexString(ln.hashCode());
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("ListNode {");

            sb.append("value = ").append(value).append(", ");
            sb.append("prev = ").append(this.getHashCode(previous)).append(", ");
            sb.append("hash = ").append(this.getHashCode(this)).append(", ");
            sb.append("next = ").append(this.getHashCode(next));

            sb.append("}");
            return sb.toString();
        }
    }

    private int size = 0;
    private ListNode first = null;
    private ListNode last = null;

    public LinkedListIndexedCollection() {
    }

    public LinkedListIndexedCollection(Collection initCollection) {
        this.addAll(initCollection);
        this.size = initCollection.size();
    }

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

    Object get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        ListNode tmp = this.first;
        for (int i = 0; i < index; i++) {
            tmp = tmp.next;
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

        // todo: not really
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

    // same as get
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

        System.out.printf("Remove: %s\n", tmp);

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
        int i = 0;

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

    void forEach(Processor processor) {
        ListNode tmp = this.first;
        while (tmp != null) {
            processor.process(tmp.value);
            tmp = tmp.next;
        }
    }
}
