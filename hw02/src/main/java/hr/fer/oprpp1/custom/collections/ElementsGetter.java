package hr.fer.oprpp1.custom.collections;

public interface ElementsGetter {
    Object getNextElement();

    boolean hasNextElement();

    default void processRemaining(Processor p) {
        while (this.hasNextElement()) {
            p.process(this.getNextElement());
        }
    }
}
