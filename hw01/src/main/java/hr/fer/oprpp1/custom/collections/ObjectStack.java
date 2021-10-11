package hr.fer.oprpp1.custom.collections;

public class ObjectStack {
//    public static void main(String[] args) {
//        ObjectStack obj = new ObjectStack();
//        obj.push("8");
//        obj.push("2");
//        obj.push("/");
//
//        System.out.println(obj.size());
//        System.out.println(obj.peek());
//        System.out.println(obj.pop());
//        System.out.println(obj.peek());
//        obj.clear();
//
//        System.out.println(obj.peek());
//        System.out.println(obj.size());
//    }

    private final ArrayIndexedCollection arr;

    public ObjectStack() {
        this.arr = new ArrayIndexedCollection();
    }

    public int size() {
        return this.arr.size();
    }

    public boolean isEmpty() {
        return this.arr.isEmpty();
    }

    private int getLastIndex() {
        int index = this.size() - 1;
        if (index < 0) {
            throw new EmptyStackException();
        }

        return index;
    }

    public void push(Object value) {
        this.arr.add(value);
    }

    public Object pop() {
        Object tmp = this.peek();
        this.arr.remove(this.getLastIndex());

        return tmp;
    }

    public Object peek() {
        return this.arr.get(this.getLastIndex());
    }


    public void clear() {
        this.arr.clear();
    }
}
