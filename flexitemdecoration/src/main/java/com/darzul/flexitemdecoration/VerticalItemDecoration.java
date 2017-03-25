package com.darzul.flexitemdecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Guillaume 'DarzuL' Bourderye on 12/09/16.
 * <p>
 * Manage decoration for vertical list.
 */

class VerticalItemDecoration extends BaseItemDecoration {

    VerticalItemDecoration(
            int decorationSize,
            Paint paint,
            int numberItemPerLine,
            boolean showDecorationAtBeginning,
            boolean showDecorationAtEnd,
            int paddingTop,
            int paddingBottom
    ) {
        super(decorationSize,
                paint,
                showDecorationAtBeginning,
                showDecorationAtEnd,
                paddingTop,
                paddingBottom,
                numberItemPerLine,
                new VerticalItemDecorationUtils(numberItemPerLine));
    }

    @Override
    protected void checkConsistency(RecyclerView parent) {
        if (!parent.getLayoutManager().canScrollVertically()) {
            throw new IllegalStateException(
                    "VerticalItemDecoration shouldn't be use with a LayoutManager which " +
                            "cannot scroll vertically");
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, int position, View view, int itemCount) {
        boolean drewTop = false;
        boolean drewBottom = false;
        if (shouldAddDecorationBeginning(position)) {
            drewTop = true;
            int y = view.getTop() - mDecorationSizeHalf;
            drawDecoration(c, view, y);
        }

        if (shouldAddDecorationEnd(position, itemCount)) {
            drewBottom = true;
            int y = view.getBottom() + mDecorationSizeHalf;
            drawDecoration(c, view, y);
        }

        int startY = drewTop ? view.getTop() - mDecorationSize : view.getTop();
        int stopY = drewBottom ? view.getBottom() + mDecorationSize : view.getBottom();
        if (!isFirstCol(position)) {
            int x = view.getLeft() - mDecorationSizeHalf;
            c.drawLine(x, startY, x, stopY, mPaint);
        }
    }

    private void drawDecoration(Canvas c, View view, int y) {
        c.drawLine(view.getLeft(), y, view.getRight(), y, mPaint);
    }

    @Override
    protected void getItemOffsets(Rect outRect, int position, int itemCount) {
        outRect.left = 0;
        outRect.top = 0;
        outRect.right = 0;
        outRect.bottom = 0;

        if (isFirstRow(position)) {
            outRect.top += mPaddingTop;
        }

        if (isLastRow(position, itemCount)) {
            outRect.bottom += mPaddingBottom;
        }

        if (shouldAddDecorationBeginning(position)) {
            outRect.top += mDecorationSize;
        }

        if (shouldAddDecorationEnd(position, itemCount)) {
            outRect.bottom += mDecorationSize;
        }

        computeGridOffset(outRect, position, mNumberItemPerLine, itemCount);
    }

    private boolean shouldAddDecorationBeginning(int position) {
        return mShowDecorationAtBeginning && isFirstRow(position);
    }

    private boolean shouldAddDecorationEnd(int position, int itemCount) {
        return mShowDecorationAtEnd || !isLastRow(position, itemCount);
    }

    private void computeGridOffset(Rect rect, int position, int numberItemPerLine, int itemCount) {
        if (numberItemPerLine < 2) {
            return;
        }

        if (numberItemPerLine == 2) {
            if (isFirstCol(position)) {
                rect.right += mDecorationSizeHalf;
                return;
            }
            rect.left += mDecorationSizeHalf;
            return;
        }

        if (isFirstCol(position)) {
            rect.right += mDecorationSizeTwoThird;
            return;
        }

        if (isBetweenTwoItems(position, itemCount)) {
            rect.left += mDecorationSizeThird;
            rect.right += mDecorationSizeThird;
            return;
        }

        rect.left += mDecorationSizeTwoThird;
    }
}
