package ru.job4j.collections.comparable;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;

public class DepartmentSortTest {

    /**
     * Test compare.
     */
    @Test
    public void whenStringsAreEqualThenZero() {
        DepartmentSort compare = new DepartmentSort();
        List<String> departments = new ArrayList<>();
        departments.add("K1\\SK2");
        departments.add("K1\\SK1");
        departments.add("K2\\SK1\\SSK1");
        departments.add("K2");

        List<String> result = new ArrayList<>();
        departments.add("K1\\SK1");
        departments.add("K1\\SK2");
        departments.add("K2");
        departments.add("K2\\SK1\\SSK1");

        assertThat(result, is(compare.sort(departments)));
    }
}
