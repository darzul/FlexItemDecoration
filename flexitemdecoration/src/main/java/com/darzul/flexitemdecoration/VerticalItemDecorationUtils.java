package com.darzul.flexitemdecoration;

/**
 * Created by Guillaume 'DarzuL' Bourderye on 3/25/17.
 * <p>
 * Impl for vertical list
 */

class VerticalItemDecorationUtils implements ItemDecorationUtils {

    private final int mNumberItemPerLine;

    VerticalItemDecorationUtils(int numberItemPerLine) {
        mNumberItemPerLine = numberItemPerLine;
    }

    @Override
    public boolean isFirstRow(int position) {
        return position < mNumberItemPerLine;
    }

    @Override
    public boolean isLastRow(int position, int itemCount) {
        return position / mNumberItemPerLine == (itemCount - 1) / mNumberItemPerLine;
    }

    @Override
    public boolean isFirstCol(int position) {
        return mNumberItemPerLine == 1 || position % mNumberItemPerLine == 0;
    }

    @Override
    public boolean isLastCol(int position, int itemCount) {
        return mNumberItemPerLine == 1 || position % mNumberItemPerLine == mNumberItemPerLine - 1;
    }

    @Override
    public boolean hasItemAtNextLine(int position, int itemCount) {
        return itemCount - position > mNumberItemPerLine;
    }

    @Override
    public boolean isBetweenTwoItems(int position, int itemCount) {
        return !isFirstCol(position) && !isLastCol(position, itemCount);
    }
}
