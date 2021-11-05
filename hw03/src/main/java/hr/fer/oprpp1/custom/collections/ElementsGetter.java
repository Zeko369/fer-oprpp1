package hr.fer.oprpp1.custom.collections;

public interface ElementsGetter<T> {
    T getNextElement();

    boolean hasNextElement();

    default void processRemaining(Processor<T> p) {
        while (this.hasNextElement()) {
            p.process(this.getNextElement());
        }
    }
}
