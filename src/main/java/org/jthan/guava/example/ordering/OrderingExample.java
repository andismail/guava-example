package org.jthan.guava.example.ordering;

import static org.junit.Assert.assertEquals;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;
import java.util.Collections;
import java.util.List;
import org.junit.Test;


/**
 */
public class OrderingExample {

    @Test
    //MIN VALUE
    public void order_list_of_strings_alphabetically_case_insensitive() {

        List<String> TOP_RATED_CENTERS = Lists.newArrayList(
                "Dawson", "Gatski", "Langer", "Hein",
                "Frankie Baggadonuts", "Turner", "Trafton",
                "Stephenson", "Ringo", "Otto", "Webster");

        String topNameAlphabetically = Ordering
                .from(String.CASE_INSENSITIVE_ORDER)//order from a exist Comparator strategy
                .min(TOP_RATED_CENTERS);

        assertEquals("Dawson", topNameAlphabetically);
    }

    @Test
    //MAX VALUE
    public void find_max_value_from_list_of_integers_guava() {

        List<Integer> top10CentersNumbers = Lists.newArrayList(
                63, 52, 62, 0, 66, 0, 57, 51, 60
        );

        Integer maxJerseyNumber = Ordering
                .natural()//order use natural
                .max(top10CentersNumbers);

        assertEquals(new Integer(66), maxJerseyNumber);
    }

    @Test
    public void order_elements_based_on_length() {

        //definite a order strategy
        Ordering<String> byLength = new Ordering<String>() {
            @Override
            public int compare(String s1, String s2) {
                return Ints.compare(s1.length(), s2.length());
            }
        };

        List<String> famousBridges = Lists.newArrayList(
                "Great Belt Bridge",
                "Chapel Bridge",
                "Chengyang Bridge",
                "null",
                "Brooklyn Bridge",
                "Ponte Vecchio"
        );

        //WAY 1:
        Collections.sort(famousBridges, byLength.nullsFirst());

        //WAY 2:
        famousBridges.sort(byLength);

        //TODO not correct
        Ordering.from(byLength).sortedCopy(famousBridges);

        /*Comparator<String> cp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Ints.compare(o1.length(), o2.length());
            }
        };
        famousBridges.sort(cp);*/

        assertEquals("Great Belt Bridge", famousBridges.get(5));
    }
}
