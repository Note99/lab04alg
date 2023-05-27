import java.util.Arrays;

public class DynamicArray<T> {
    private Object[] array;
    private int size;

    public DynamicArray() {
        this.array = new Object[10];
        this.size = 0;
    }

    public DynamicArray(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative");
        }
        this.array = new Object[capacity];
        this.size = 0;
    }

    public DynamicArray(T[] elements) {
        this.array = Arrays.copyOf(elements, elements.length);
        this.size = elements.length;
    }

    public void fill(T value) {
        Arrays.fill(array, 0, size, value);
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        return (T) array[index];
    }

    public void set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        array[index] = value;
    }

    public void add(T element) {
        if (size == array.length) {
            increaseCapacity();
        }
        array[size++] = element;
    }

    @SuppressWarnings("unchecked")
    public T remove() {
        if (size == 0) {
            throw new IllegalStateException("Array is empty");
        }
        T element = (T) array[--size];
        array[size] = null; // To allow garbage collection
        return element;
    }

    public void insert(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (size == array.length) {
            increaseCapacity();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null; // To allow garbage collection
    }

    public void remove(T element) {
        int index = indexOf(element);
        if (index != -1) {
            remove(index);
        }
    }

    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (element.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public int indexOfMax() {
        if (size == 0) {
            throw new IllegalStateException("Array is empty");
        }
        Comparable<T> max = (Comparable<T>) array[0];
        int maxIndex = 0;
        for (int i = 1; i < size; i++) {
            Comparable<T> current = (Comparable<T>) array[i];
            if (current.compareTo((T) max) > 0) {
                max = current;
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public void sort() {
        quickSort(0, size - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    @SuppressWarnings("unchecked")
    private int partition(int low, int high) {
        T pivot = (T) array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            Comparable<T> current = (Comparable<T>) array[j];
            if (current.compareTo(pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    private void swap(int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void increaseCapacity() {
        int newCapacity = array.length * 2;
        array = Arrays.copyOf(array, newCapacity);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size - 1; i++) {
            sb.append(array[i]).append(", ");
        }
        sb.append(array[size - 1]).append(']');
        return sb.toString();
    }
}
