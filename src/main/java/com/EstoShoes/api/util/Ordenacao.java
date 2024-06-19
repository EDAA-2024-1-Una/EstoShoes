package com.EstoShoes.api.util;

import java.util.Comparator;
import java.util.List;

public class Ordenacao {
    private static <T> void swap(List<T> array, int index1, int index2) {
        T temp = array.get(index1);
        array.set(index1, array.get(index2));
        array.set(index2, temp);
    }

    public static <T> void selectionSort(List<T> array, Comparator<T> comparador, String ordem) {
        for (int i = 0; i < array.size() - 1; i += 1) {
            int minIndex = i;
            for (int j = i + 1; j < array.size(); j += 1) {
                if (ordem.equals("crescente") && comparador.compare(array.get(j), array.get(minIndex)) < 0) {
                    minIndex = j;
                }
                if (ordem.equals("decrescente") && comparador.compare(array.get(j), array.get(minIndex)) > 0) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }
}
