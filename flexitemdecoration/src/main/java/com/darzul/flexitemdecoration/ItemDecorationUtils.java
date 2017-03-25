package com.darzul.flexitemdecoration;

/**
 * Created by Guillaume 'DarzuL' Bourderye on 3/25/17.
 * <p>
 * Utils class to abstract computation to search first/last row/column
 */

interface ItemDecorationUtils {

    /**
     * @param position item position obtained by RecyclerView.getChildAdapterPosition(View)
     * @return whether the position correspond to an item in the first row
     */
    boolean isFirstRow(int position);

    /**
     * @param position  item position obtained by RecyclerView.getChildAdapterPosition(View)
     * @param itemCount total item in list
     * @return whether the position correspond to an item in the last row
     */
    boolean isLastRow(int position, int itemCount);

    /**
     * @param position item position obtained by RecyclerView.getChildAdapterPosition(View)
     * @return whether the position correspond to an item in the first column
     */
    boolean isFirstCol(int position);

    /**
     * @param position  item position obtained by RecyclerView.getChildAdapterPosition(View)
     * @param itemCount total item in list
     * @return whether the position correspond to an item in the first column
     */
    boolean isLastCol(int position, int itemCount);

    /**
     * @param position  item position obtained by RecyclerView.getChildAdapterPosition(View)
     * @param itemCount total item in list
     * @return whether the is a item at the next line
     */
    boolean hasItemAtNextLine(int position, int itemCount);

    /**
     * @param position  item position obtained by RecyclerView.getChildAdapterPosition(View)
     * @param itemCount total item in list
     * @return whether the item is between two items
     */
    boolean isBetweenTwoItems(int position, int itemCount);
}
