/*
 * Written by Nick Lauer
 */
public class DynamicArray<T> {
    private Object[] array;
    private int size = 0;
    private int capacity = 10;

    public DynamicArray() {
        array = new Object[capacity];
    }

    // Adds an item to the array; if array is full, capacity is increased
    public void add(T item) {
        if (size == capacity) {
            increaseCapacity();
        }
        array[size++] = item;
    }

    public T get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (T) array[index];
    }

    public void set(int index, T item) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        array[index] = item;
    }

    // Removes the last item from the array, reducing its size by one
    public void removeLast() {
        if (size > 0) {
            array[size - 1] = null;
            size--;
        }
    }

    public int size() {
        return size;
    }

    private void increaseCapacity() {
        capacity = capacity * 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
}