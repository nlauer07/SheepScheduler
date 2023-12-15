/*
 * Written by Nick Lauer
 */
public class MinHeap<T extends Comparable<T>> {
    private DynamicArray<T> items;

    public MinHeap() {
        items = new DynamicArray<>();
    }

    // Adds an item to the heap and sifts it up to maintain heap 
    public void add(T item) {
        items.add(item);
        siftUp();
    }
    
    // Removes and returns the top item from the heap
    public T remove() {
        if (items.size() == 0) {
            throw new IllegalStateException("MinHeap is empty");
        }

        T hold = items.get(0); 

        if (items.size() == 1) {
            items.removeLast();
            return hold; 
        }

        items.set(0, items.get(items.size() - 1));
        items.removeLast();
        siftDown();
        return hold;
    }

    public T peek() {
        if (items.size() == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return items.get(0);
    }

    public boolean isEmpty() {
        return items.size() == 0;
    }

    public int size() {
        return items.size();
    }

    private void siftUp() {
        int k = items.size() - 1;
        while (k > 0) {
            int p = (k - 1) / 2;
            T item = items.get(k);
            T parent = items.get(p);
            if (item.compareTo(parent) < 0) {
                items.set(k, parent);
                items.set(p, item);
                k = p;
            } else {
                break;
            }
        }
    }

    private void siftDown() {
        int k = 0;
        int l = 2 * k + 1;
        while (l < items.size()) {
            int max = l, r = l + 1;
            if (r < items.size() && items.get(r).compareTo(items.get(l)) < 0) {
                max = r;
            }
            if (items.get(k).compareTo(items.get(max)) > 0) {
                T temp = items.get(k);
                items.set(k, items.get(max));
                items.set(max, temp);
                k = max;
                l = 2 * k + 1;
            } else {
                break;
            }
        }
    }
}