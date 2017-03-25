package com.example.flexitemdecoration.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Guillaume 'DarzuL' Bourderye on 3/25/17.
 * <p>
 * Provide an ItemDecoration
 */

public interface ItemDecorationProvider {
    RecyclerView.ItemDecoration provide(Context context, int itemPerLine);
}
