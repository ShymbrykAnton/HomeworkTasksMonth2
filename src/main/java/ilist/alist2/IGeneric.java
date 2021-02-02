package ilist.alist2;

public interface IGeneric<E> {
    void clear();

    int size();

    E get(int index);

    boolean add(E value);

    boolean add(int index, E value);

    E remove(E number);

    E removeByIndex(int index);

    boolean contains(E value);

    boolean set(int index, E value);

    void print();

    E[] toArray();

    boolean removeAll(E[] array);
}
