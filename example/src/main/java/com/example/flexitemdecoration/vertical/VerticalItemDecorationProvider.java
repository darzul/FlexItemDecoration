package com.example.flexitemdecoration.vertical;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.darzul.flexitemdecoration.BaseItemDecoration;
import com.example.flexitemdecoration.R;
import com.example.flexitemdecoration.common.ItemDecorationProvider;

/**
 * Created by Guillaume 'DarzuL' Bourderye on 3/25/17.
 * <p>
 * Provide ItemDecoration for vertical list
 */

public class VerticalItemDecorationProvider implements ItemDecorationProvider {

    @Override
    public RecyclerView.ItemDecoration provide(Context context, int itemPerLine) {
        return new BaseItemDecoration.Builder(context)
                .setDecorationSize(R.dimen.pokemons_item_space)
                .setDecorationColor(R.color.colorAccent)
                .build();
    }
}
