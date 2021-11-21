package hr.fer.oprpp1.custom.collections;

/**
 * Object stack modeled with ArrayIndexedCollection as the store backend
 *
 * @param <T> the type parameter
 * @author franzekan
 * @version 1.0
 */
public class ObjectStack<T> {
    /**
     * Internal store
     */
    private final ArrayIndexedCollection<T> arr;

    /**
     * Instantiates a new Object stack.
     */
    public ObjectStack() {
        this.arr = new ArrayIndexedCollection<>();
    }

    /**
     * Returns the size of the stack
     *
     * @return the int size
     */
    public int size() {
        return this.arr.size();
    }

    /**
     * Checks if there are any items on the stack
     *
     * @return the boolean if there are any items
     */
    public boolean isEmpty() {
        return this.arr.isEmpty();
    }

    /**
     * Helper to get the index of the last element
     *
     * @return index of last element
     * @throws EmptyStackException if stack is empty
     */
    private int getLastIndex() {
        int index = this.size() - 1;
        if (index < 0) {
            throw new EmptyStackException();
        }

        return index;
    }

    /**
     * Adds a value to the stack
     *
     * @param value the value
     */
    public void push(T value) {
        this.arr.add(value);
    }

    /**
     * Removes the top value on the stack and returns it
     *
     * @return the object
     */
    public T pop() {
        T tmp = this.peek();
        this.arr.remove(this.getLastIndex());

        return tmp;
    }

    /**
     * Gets the value on the top
     *
     * @return the object
     */
    public T peek() {
        return this.arr.get(this.getLastIndex());
    }


    /**
     * Empties the stack
     */
    public void clear() {
        this.arr.clear();
    }
}

