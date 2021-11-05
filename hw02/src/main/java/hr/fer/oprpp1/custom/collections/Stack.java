package hr.fer.oprpp1.custom.collections;

// Create a stack class that uses ArrayIndexedCollection as a storage.
public class Stack {
    private ArrayIndexedCollection stack;

    // Constructor
    public Stack() {
        stack = new ArrayIndexedCollection();
    }

    // push method
    void push(Object o) {
        stack.add(o);
    }

    // peek at last element
    Object peek() {
        return stack.get(stack.size() - 1);
    }

    // method pop, first get last lemenet, remove it and return it
    public Object pop() {
        Object o = peek();
        stack.remove(stack.size() - 1);
        return o;
    }

    // clear stack
    public void clear() {
        stack.clear();
    }

}
