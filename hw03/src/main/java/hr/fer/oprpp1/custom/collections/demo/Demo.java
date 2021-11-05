package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.Collection;
import hr.fer.oprpp1.custom.collections.ElementsGetter;
import hr.fer.oprpp1.custom.collections.LinkedListIndexedCollection;

public class Demo {
    public static void main(String[] args) {
        Collection<String> col = new LinkedListIndexedCollection<>();
        col.add("Ivo");
        col.add("Ana");
        col.add("Jasna");

        ElementsGetter<String> getter = col.createElementsGetter();
        System.out.println("Jedan element: " + getter.getNextElement());

        getter.processRemaining(System.out::println);
    }
}
