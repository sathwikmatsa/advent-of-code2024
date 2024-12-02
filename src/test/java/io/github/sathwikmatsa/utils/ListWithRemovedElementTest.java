package io.github.sathwikmatsa.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ListWithRemovedElementTest {

    @Test
    void testConstructor_invalidIndex() {
        List<Integer> originalList = List.of(1, 2, 3, 4, 5);

        // Test: index to remove is negative
        assertThrows(IndexOutOfBoundsException.class, () -> {
            new ListWithRemovedElement<>(originalList, -1);
        });

        // Test: index to remove is greater than the highest index
        assertThrows(IndexOutOfBoundsException.class, () -> {
            new ListWithRemovedElement<>(originalList, 5);
        });
    }

    @Test
    void testGet() {
        List<Integer> originalList = List.of(1, 2, 3, 4, 5);
        ListWithRemovedElement<Integer> listWithRemovedElement = new ListWithRemovedElement<>(originalList, 2);

        // Test: elements before the removed element
        assertEquals(1, listWithRemovedElement.get(0));
        assertEquals(2, listWithRemovedElement.get(1));

        // Test: elements after the removed element (index 2 was removed)
        assertEquals(4, listWithRemovedElement.get(2));
        assertEquals(5, listWithRemovedElement.get(3));
    }

    @Test
    void testSize() {
        List<Integer> originalList = List.of(1, 2, 3, 4, 5);
        ListWithRemovedElement<Integer> listWithRemovedElement = new ListWithRemovedElement<>(originalList, 2);

        // Test size: the list size should be one less because one element is "removed"
        assertEquals(4, listWithRemovedElement.size());
    }

    @Test
    void testGetOutOfBounds() {
        List<Integer> originalList = List.of(1, 2, 3, 4, 5);
        ListWithRemovedElement<Integer> listWithRemovedElement = new ListWithRemovedElement<>(originalList, 2);

        // Test: Accessing index out of bounds (after removal, the size is 4)
        assertThrows(IndexOutOfBoundsException.class, () -> {
            listWithRemovedElement.get(4); // This should throw since the size is 4
        });
    }
}