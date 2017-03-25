package com.darzul.flexitemdecoration;

/**
 * Created by Guillaume 'DarzuL' Bourderye on 3/25/17.
 * <p>
 * Impl for horizontal list. It's the same logic as {@link VerticalItemDecorationUtils}
 * with column and row reversed
 */

class HorizontalItemDecorationUtils implements ItemDecorationUtils {

    private final VerticalItemDecorationUtils mVerticalItemDecorationUtils;

    HorizontalItemDecorationUtils(int numberItemPerLine) {
        mVerticalItemDecorationUtils = new VerticalItemDecorationUtils(numberItemPerLine);
    }

    @Override
    public boolean isFirstRow(int position) {
        return mVerticalItemDecorationUtils.isFirstCol(position);
    }

    @Override
    public boolean isLastRow(int position, int itemCount) {
        return mVerticalItemDecorationUtils.isLastCol(position, itemCount);
    }

    @Override
    public boolean isFirstCol(int position) {
        return mVerticalItemDecorationUtils.isFirstRow(position);
    }

    @Override
    public boolean isLastCol(int position, int itemCount) {
        return mVerticalItemDecorationUtils.isLastRow(position, itemCount);
    }

    @Override
    public boolean hasItemAtNextLine(int position, int itemCount) {
        return mVerticalItemDecorationUtils.hasItemAtNextLine(position, itemCount);
    }

    @Override
    public boolean isBetweenTwoItems(int position, int itemCount) {
        return !isFirstRow(position) && !isLastRow(position, itemCount);
    }
}
