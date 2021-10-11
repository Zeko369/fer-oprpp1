package hr.fer.oprpp1.custom.collections;

public class Collection {
    int size() {
        return 0;
    }

    boolean isEmpty() {
        return this.size() == 0;
    }

    void clear() {
    }

    void add(Object value) {
    }

    boolean contains(Object value) {
        return false;
    }

    boolean remove(Object value) {
        return false;
    }

    Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    void forEach(Processor processor) {
    }

    void addAll(Collection other) {
        class AddItemsProcessor extends Processor {
            @Override
            public void process(Object value) {
                Collection.this.add(value);
            }
        }

        other.forEach(new AddItemsProcessor());
    }
}
