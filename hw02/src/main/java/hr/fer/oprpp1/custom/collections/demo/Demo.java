package hr.fer.oprpp1.custom.collections.demo;

import hr.fer.oprpp1.custom.collections.Collection;
import hr.fer.oprpp1.custom.collections.ElementsGetter;
import hr.fer.oprpp1.custom.collections.LinkedListIndexedCollection;

/**
 * The type Demo.
 *
 * @author franzekan
 */
public class Demo {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Collection col = new LinkedListIndexedCollection();
        col.add("Ivo");
        col.add("Ana");
        col.add("Jasna");
        ElementsGetter getter = col.createElementsGetter();
        System.out.println("Jedan element: " + getter.getNextElement());

        getter.processRemaining(System.out::println);
    }
}
