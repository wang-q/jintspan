/*
 * THE SOFTWARE IS PROVIDED "AS IS" WITHOUT A WARRANTY OF ANY KIND. ALL EXPRESS OR IMPLIED
 * REPRESENTATIONS AND WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY DISCLAIMED.
 */

package org.egateam;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class TestIndex {

    @SuppressWarnings("CanBeFinal")
    private static class TestDataAt {
        String runlist;
        int index;
        int expected;

        TestDataAt(String runlist, int index, int expected) {
            this.runlist = runlist;
            this.index = index;
            this.expected = expected;
        }
    }

    private static final TestDataAt[] testsAt =
        {
            new TestData("", "-", new int[]{}),
            new TestData("-", "-", new int[]{}),
            new TestData("     ", "-", new int[]{}),
            new TestData("0", "0", new int[]{0}),
            new TestData("1", "1", new int[]{1}),
            new TestData("1-1", "1", new int[]{1}),
            new TestData("-1", "-1", new int[]{-1}),
            new TestData("1-2", "1-2", new int[]{1, 2}),
            new TestData("-2--1", "-2--1", new int[]{-2, -1}),
            new TestData("-2 -     -1  ", "-2--1", new int[]{-2, -1}),
            new TestData("-2-1", "-2-1", new int[]{-2, -1, 0, 1}),
            new TestData("1,2-4", "1-4", new int[]{1, 2, 3, 4}),
            new TestData("1-3,4,5-7", "1-7", new int[]{1, 2, 3, 4, 5, 6, 7}),
            new TestData("1-3,4", "1-4", new int[]{1, 2, 3, 4}),
            new TestData("1,2,3,4,5,6,7", "1-7", new int[]{1, 2, 3, 4, 5, 6, 7}),
        };

    @Test
    public void prompt() {
        System.out.println("TestIndex");
    }

    @Test
    public void testCreationRunlist() {

        for ( TestData t : tests ) {
            ArrayList<Integer> array = new ArrayList<Integer>();
            for ( int i : t.elements ) {
                array.add(i);
            }
            String message = "Test " + t.input;

            IntSpan set = new IntSpan(t.input);
            Assert.assertEquals(set.cardinality(), t.elements.length, message);
            Assert.assertEquals(set.asString(), t.runlist, message);
            Assert.assertEquals(set.asArray(), array, message);

            // aliases
            IntSpan set1 = new IntSpan(set.copy());
            Assert.assertEquals(set.size(), t.elements.length, message);
            Assert.assertEquals(set.count(), t.elements.length, message);
            Assert.assertEquals(set1.runlist(), t.runlist, message);
            Assert.assertEquals(set1.elements(), array, message);
        }
    }

    @Test
    public void testCreationInt() {
        {
            String message = "Test int";

            IntSpan set = new IntSpan(1);

            String expectedString = "1";
            ArrayList<Integer> expectedArray = new ArrayList<Integer>();
            expectedArray.add(1);

            Assert.assertEquals(set.cardinality(), 1, message);
            Assert.assertEquals(set.asString(), expectedString, message);
            Assert.assertEquals(set.asArray(), expectedArray, message);
        }

    }
}