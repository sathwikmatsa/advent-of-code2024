package io.github.sathwikmatsa.utils;

import java.util.AbstractList;
import java.util.List;

public class ListWithRemovedElement<T> extends AbstractList<T> {
    private final List<T> list;
    private final int indexToRemove;

    public ListWithRemovedElement(List<T> list, int indexToRemove) {
        if (indexToRemove < 0 || indexToRemove >= list.size()) {
            throw new IndexOutOfBoundsException("indexToRemove is out of bounds: " + indexToRemove);
        }
        this.list = list;
        this.indexToRemove = indexToRemove;
    }

    @Override
    public T get(int index) {
        return list.get(index >= indexToRemove ? index + 1 : index);
    }

    @Override
    public int size() {
        if (indexToRemove < list.size()) {
            return list.size() - 1;
        }
        return list.size();
    }
}
