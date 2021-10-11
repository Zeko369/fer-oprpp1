package hr.fer.oprpp1.custom.collections;

public class WriteIfEvenHashProcessor extends Processor {
    @Override
    public void process(Object value) {
        if (value.hashCode() % 2 == 0) {
            System.out.println("Got it!");
        }
    }
}
