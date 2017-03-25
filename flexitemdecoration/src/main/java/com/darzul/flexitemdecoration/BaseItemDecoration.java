package com.darzul.flexitemdecoration;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by DarzuL on 20/02/2015.
 * <p>
 * Manage separation between two items in a list based on RecyclerView.ItemDecoration
 */

public abstract class BaseItemDecoration extends RecyclerView.ItemDecoration
        implements ItemDecorationUtils {

    final int mDecorationSize;
    final int mDecorationSizeHalf;
    final int mDecorationSizeThird;
    final int mDecorationSizeTwoThird;
    final Paint mPaint;
    final boolean mShowDecorationAtBeginning;
    final boolean mShowDecorationAtEnd;
    final int mPaddingTop;
    final int mPaddingBottom;
    final int mNumberItemPerLine;
    private final ItemDecorationUtils mItemDecorationUtils;

    private boolean consistencyChecked = false;

    BaseItemDecoration(
            int decorationSize,
            Paint paint,
            boolean showDecorationAtBeginning,
            boolean showDecorationAtEnd,
            int paddingTop,
            int paddingBottom,
            int numberItemPerLine,
            ItemDecorationUtils itemDecorationUtils
    ) {
        mDecorationSize = decorationSize;
        mDecorationSizeHalf = decorationSize / 2;
        mDecorationSizeThird = decorationSize / 3;
        mDecorationSizeTwoThird = mDecorationSizeThird * 2;
        mPaint = paint;
        mShowDecorationAtBeginning = showDecorationAtBeginning;
        mShowDecorationAtEnd = showDecorationAtEnd;
        mPaddingTop = paddingTop;
        mPaddingBottom = paddingBottom;
        mNumberItemPerLine = numberItemPerLine;
        mItemDecorationUtils = itemDecorationUtils;
    }

    protected abstract void checkConsistency(RecyclerView parent);

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getAdapter() == null) {
            return;
        }

        int itemCount = parent.getAdapter().getItemCount();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);

            c.save();
            onDrawOver(c, parent, position, view, itemCount);
            c.restore();
        }
    }

    protected abstract void onDrawOver(
            Canvas c,
            RecyclerView parent,
            int position,
            View view,
            int itemCount
    );

    @Override
    public void getItemOffsets(
            Rect outRect,
            View view,
            RecyclerView parent,
            RecyclerView.State state
    ) {
        if (!consistencyChecked) {
            checkConsistency(parent);
            consistencyChecked = true;
        }

        if (parent.getAdapter() == null) {
            return;
        }

        int position = parent.getChildAdapterPosition(view);
        getItemOffsets(outRect, position, parent.getAdapter().getItemCount());
    }

    protected abstract void getItemOffsets(Rect outRect, int position, int itemCount);

    /**
     * @return decoration size in pixel
     */
    public int getDecorationSize() {
        return mDecorationSize;
    }

    /**
     * @return whether the ItemDecoration add a decoration at the beginning of the list
     */
    public boolean isShowDecorationAtBeginning() {
        return mShowDecorationAtBeginning;
    }

    /**
     * @return whether the ItemDecoration add a decoration at the end of the list
     */
    public boolean isShowDecorationAtEnd() {
        return mShowDecorationAtEnd;
    }

    /**
     * @return padding at the top of the list in pixel
     */
    public int getPaddingTop() {
        return mPaddingTop;
    }

    /**
     * @return padding at the bottom of the list in pixel
     */
    public int getPaddingBottom() {
        return mPaddingBottom;
    }

    @Override
    public boolean isFirstRow(int position) {
        return mItemDecorationUtils.isFirstRow(position);
    }

    @Override
    public boolean isLastRow(int position, int itemCount) {
        return mItemDecorationUtils.isLastRow(position, itemCount);
    }

    @Override
    public boolean isFirstCol(int position) {
        return mItemDecorationUtils.isFirstCol(position);
    }

    @Override
    public boolean isLastCol(int position, int itemCount) {
        return mItemDecorationUtils.isLastCol(position, itemCount);
    }

    @Override
    public boolean hasItemAtNextLine(int position, int itemCount) {
        return mItemDecorationUtils.hasItemAtNextLine(position, itemCount);
    }

    @Override
    public boolean isBetweenTwoItems(int position, int itemCount) {
        return mItemDecorationUtils.isBetweenTwoItems(position, itemCount);
    }

    protected boolean isLastItem(int position, int itemCount) {
        return position == itemCount - 1;
    }

    protected boolean isLastLineFilled(int itemCount) {
        return itemCount / mNumberItemPerLine == 0;
    }

    /**
     * Builder pattern to create a ItemDecoration
     */
    public static class Builder {
        @DimenRes private int mDecorationSizeRes = R.dimen.space_0dp;
        @ColorRes private int mDecorationColorRes = android.R.color.transparent;
        private int mNumberItemPerLine = 1;
        private boolean mShowDecorationAtBeginning = false;
        private boolean mShowDecorationAtEnd = false;
        private boolean mIsHorizontal = false;
        @DimenRes private int mPaddingTopRes = R.dimen.space_0dp;
        @DimenRes private int mPaddingBottomRes = R.dimen.space_0dp;

        private Context mContext;

        public Builder(Context context) {
            mContext = context;
        }

        /**
         * Set the separation size, default is 0dp
         *
         * @param decorationSizeRes dimension resource
         * @return the Builder instance
         */
        public Builder setDecorationSize(@DimenRes int decorationSizeRes) {
            mDecorationSizeRes = decorationSizeRes;
            return this;
        }

        /**
         * Set the decoration color to use, default is transparent
         *
         * @param decorationColorRes color resource
         * @return the Builder instance
         */
        public Builder setDecorationColor(@ColorRes int decorationColorRes) {
            mDecorationColorRes = decorationColorRes;
            return this;
        }

        /**
         * Set the number of item per line
         *
         * @param numberItemPerLine number of item per line, default is 1
         * @return the Builder instance
         */
        public Builder setNumberItemPerLine(int numberItemPerLine) {
            if (numberItemPerLine <= 0) {
                throw new IllegalArgumentException("A list cannot have less than 1 item per line");
            }

            mNumberItemPerLine = numberItemPerLine;
            return this;
        }

        /**
         * @param showDecorationAtBeginning whether we add a decoration at the beginning of the list,
         *                                  default is false
         * @return the Builder instance
         */
        public Builder showDecorationAtBeginning(boolean showDecorationAtBeginning) {
            mShowDecorationAtBeginning = showDecorationAtBeginning;
            return this;
        }

        /**
         * @param showDecorationAtEnd whether we add a decoration at the end of the list,
         *                            default is false
         * @return the Builder instance
         */
        public Builder showDecorationAtEnd(boolean showDecorationAtEnd) {
            mShowDecorationAtEnd = showDecorationAtEnd;
            return this;
        }

        /**
         * @param isHorizontal whether the list is Horizontal or not, default is false
         * @return the Builder instance
         */
        public Builder setIsHorizontal(boolean isHorizontal) {
            mIsHorizontal = isHorizontal;
            return this;
        }

        /**
         * padding to add at the top of the list, it's like setClipPadding=false + paddingTop
         *
         * @param paddingTopRes dimension resource
         * @return the Builder instance
         */
        public Builder setPaddingTop(@DimenRes int paddingTopRes) {
            mPaddingTopRes = paddingTopRes;
            return this;
        }

        /**
         * padding to add at the bottom of the list
         *
         * @param paddingBottomRes dimension resource
         * @return the Builder instance
         */
        public Builder setPaddingBottom(@DimenRes int paddingBottomRes) {
            mPaddingBottomRes = paddingBottomRes;
            return this;
        }

        /**
         * Build the ItemDecoration
         *
         * @return ItemDecoration to attach to your list
         */
        public BaseItemDecoration build() {
            Resources res = mContext.getResources();

            Paint paint = new Paint();
            paint.setColor(ContextCompat.getColor(mContext, mDecorationColorRes));

            int decorationSize = res.getDimensionPixelSize(mDecorationSizeRes);
            decorationSize = resizeDecorationSize(decorationSize);
            paint.setStrokeWidth(decorationSize);

            int paddingTop = res.getDimensionPixelOffset(mPaddingTopRes);
            int paddingBottom = res.getDimensionPixelOffset(mPaddingBottomRes);
            if (mIsHorizontal) {
                return new HorizontalItemDecoration(decorationSize,
                        paint,
                        mNumberItemPerLine,
                        mShowDecorationAtBeginning,
                        mShowDecorationAtEnd,
                        paddingTop,
                        paddingBottom);
            }

            return new VerticalItemDecoration(decorationSize,
                    paint,
                    mNumberItemPerLine,
                    mShowDecorationAtBeginning,
                    mShowDecorationAtEnd,
                    paddingTop,
                    paddingBottom);
        }

        private int resizeDecorationSize(int decorationSize) {
            // Paint uses half of decoration size to draw the line.
            // So the decorationSize has to be pair
            if (decorationSize % 2 == 1) {
                decorationSize++;
            }

            return decorationSize;
        }
    }
}
