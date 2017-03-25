package com.darzul.flexitemdecoration;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Guillaume 'DarzuL' Bourderye on 12/09/16.
 * <p>
 * Manage decoration for horizontal list
 * WARNING: This class doesn't manage several line.
 * If you need it take a look at {@link VerticalItemDecoration}
 */

class HorizontalItemDecoration extends BaseItemDecoration {

    HorizontalItemDecoration(
            int decorationSize, Paint paint,
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
                new HorizontalItemDecorationUtils(numberItemPerLine));

        if (numberItemPerLine > 1) {
            throw new IllegalArgumentException(
                    "HorizontalItemDecoration doesn't manage several item per line. " +
                            "Use VerticalItemDecoration instead");
        }
    }

    @Override
    protected void checkConsistency(RecyclerView parent) {
        if (!parent.getLayoutManager().canScrollHorizontally()) {
            throw new IllegalStateException(
                    "HorizontalItemDecoration shouldn't be use with a LayoutManager which " +
                            "cannot scroll horizontally");
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, int position, View view, int itemCount) {
        if (mShowDecorationAtBeginning || !isFirstCol(position)) {
            int x = view.getLeft() - mDecorationSizeHalf;
            drawDecoration(c, view, x);
        }

        if (mShowDecorationAtEnd && isLastCol(position, itemCount)) {
            int x = view.getRight() + mDecorationSizeHalf;
            drawDecoration(c, view, x);
        }
    }

    private void drawDecoration(Canvas c, View view, int x) {
        c.drawLine(x,
                view.getTop(),
                x,
                view.getBottom(),
                mPaint);
    }

    @Override
    protected void getItemOffsets(Rect outRect, int position, int itemCount) {
        if (mShowDecorationAtBeginning || !isFirstCol(position)) {
            outRect.left += mDecorationSize;
        }

        if (mShowDecorationAtEnd && isLastCol(position, itemCount)) {
            outRect.right += mDecorationSize;
        }
    }
}
