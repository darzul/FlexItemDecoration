package com.darzul.flexitemdecoration;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class VerticalItemDecorationUtilsTest {

    private static final int MULTIPLE_COLUMN_COUNT = 5;
    private static final int ITEM_COUNT = 22;

    private VerticalItemDecorationUtils SUT;

    @Test
    public void shouldKnowIfItemIsInFirstColumn() {
        givenSingleColumnSUT();
        assertTrue(SUT.isFirstCol(0));
        assertTrue(SUT.isFirstCol(17));

        givenMultipleColumnSUT();
        assertTrue(SUT.isFirstCol(0));
        assertFalse(SUT.isFirstCol(MULTIPLE_COLUMN_COUNT - 1));
        assertTrue(SUT.isFirstCol(MULTIPLE_COLUMN_COUNT));
    }

    @Test
    public void shouldKnowIfItemIsInFirstRow() {
        givenSingleColumnSUT();
        assertTrue(SUT.isFirstRow(0));
        assertFalse(SUT.isFirstRow(4));
        assertFalse(SUT.isFirstRow(7));

        givenMultipleColumnSUT();
        assertTrue(SUT.isFirstRow(0));
        assertTrue(SUT.isFirstRow(1));
        assertTrue(SUT.isFirstRow(MULTIPLE_COLUMN_COUNT - 1));
        assertFalse(SUT.isFirstRow(MULTIPLE_COLUMN_COUNT));
    }

    @Test
    public void shouldKnowIfItemIsInLastColumn() {
        givenSingleColumnSUT();
        assertTrue(SUT.isLastCol(0, ITEM_COUNT));
        assertTrue(SUT.isLastCol(17, ITEM_COUNT));

        givenMultipleColumnSUT();
        assertFalse(SUT.isLastCol(0, ITEM_COUNT));
        assertTrue(SUT.isLastCol(MULTIPLE_COLUMN_COUNT - 1, ITEM_COUNT));
        assertFalse(SUT.isLastCol(MULTIPLE_COLUMN_COUNT, ITEM_COUNT));
    }

    @Test
    public void shouldKnowIfItemIsInLastRow() {
        givenSingleColumnSUT();
        assertFalse(SUT.isLastRow(0, ITEM_COUNT));
        assertFalse(SUT.isLastRow(4, ITEM_COUNT));
        assertFalse(SUT.isLastRow(ITEM_COUNT - 2, ITEM_COUNT));
        assertTrue(SUT.isLastRow(ITEM_COUNT - 1, ITEM_COUNT));

        givenMultipleColumnSUT();
        assertFalse(SUT.isLastRow(0, ITEM_COUNT));
        assertFalse(SUT.isLastRow(4, ITEM_COUNT));
        assertFalse(SUT.isLastRow(ITEM_COUNT - 3, ITEM_COUNT));
        assertTrue(SUT.isLastRow(ITEM_COUNT - 2, ITEM_COUNT));
        assertTrue(SUT.isLastRow(ITEM_COUNT - 1, ITEM_COUNT));
    }

    @Test
    public void shouldKnowIfItemHasItemNextLine() {
        givenSingleColumnSUT();
        assertTrue(SUT.hasItemAtNextLine(0, ITEM_COUNT));
        assertTrue(SUT.hasItemAtNextLine(ITEM_COUNT - 2, ITEM_COUNT));
        assertFalse(SUT.hasItemAtNextLine(ITEM_COUNT - 1, ITEM_COUNT));

        givenMultipleColumnSUT();
        assertTrue(SUT.hasItemAtNextLine(0, ITEM_COUNT));
        assertTrue(SUT.hasItemAtNextLine(ITEM_COUNT - MULTIPLE_COLUMN_COUNT - 1, ITEM_COUNT));
        assertFalse(SUT.hasItemAtNextLine(ITEM_COUNT - MULTIPLE_COLUMN_COUNT, ITEM_COUNT));
        assertFalse(SUT.hasItemAtNextLine(ITEM_COUNT - 1, ITEM_COUNT));
    }

    @Test
    public void shouldKnowIfItemIsBetweenTwoItems() {
        givenSingleColumnSUT();
        assertFalse(SUT.isBetweenTwoItems(0, ITEM_COUNT));
        assertFalse(SUT.isBetweenTwoItems(4, ITEM_COUNT));
        assertFalse(SUT.isBetweenTwoItems(ITEM_COUNT - 1, ITEM_COUNT));

        givenMultipleColumnSUT();
        assertFalse(SUT.isBetweenTwoItems(0, ITEM_COUNT));
        assertFalse(SUT.isBetweenTwoItems(MULTIPLE_COLUMN_COUNT - 1, ITEM_COUNT));
        assertFalse(SUT.isBetweenTwoItems(MULTIPLE_COLUMN_COUNT, ITEM_COUNT));
        assertTrue(SUT.isBetweenTwoItems(MULTIPLE_COLUMN_COUNT - 2, ITEM_COUNT));
        assertTrue(SUT.isBetweenTwoItems(2, ITEM_COUNT));
    }

    private void givenSingleColumnSUT() {
        SUT = new VerticalItemDecorationUtils(1);
    }

    private void givenMultipleColumnSUT() {
        SUT = new VerticalItemDecorationUtils(MULTIPLE_COLUMN_COUNT);
    }
}
