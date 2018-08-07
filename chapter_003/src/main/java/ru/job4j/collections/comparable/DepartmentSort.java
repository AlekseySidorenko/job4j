package ru.job4j.collections.comparable;

import java.util.Comparator;
import java.util.List;

public class DepartmentSort {

    public List<String> sort(List<String> departments) {
        departments.sort(new Comparator<String>() {

            public int compare(String left, String right) {
                int result = 0;

                for (int i = 0; (i < left.length()) && (i < right.length()); i++) {
                    result = Integer.compare(left.charAt(i), right.charAt(i));
                    if (left.charAt(i) != right.charAt(i)) {
                        break;
                    }
                }
                if ((result == 0) && (left.length() != right.length())) {
                    result = Integer.compare(left.length(), right.length());
                }
                return result;
            }
        });
        return departments;
    }
}
