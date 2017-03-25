package com.example.flexitemdecoration.grid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.darzul.flexitemdecoration.BaseItemDecoration;
import com.example.flexitemdecoration.R;
import com.example.flexitemdecoration.common.ItemDecorationProvider;

/**
 * Created by Guillaume 'DarzuL' Bourderye on 3/25/17.
 * <p>
 * Provide ItemDecoration for a grid list
 */

public class GridItemDecorationProvider implements ItemDecorationProvider {

    @Override
    public RecyclerView.ItemDecoration provide(Context context, int itemPerLine) {
        return new BaseItemDecoration.Builder(context)
                .setNumberItemPerLine(itemPerLine)
                .setDecorationSize(R.dimen.pokemons_item_space)
                .setPaddingTop(R.dimen.pokemons_list_paddingTop)
                .setPaddingBottom(R.dimen.pokemons_list_paddingBottom)
                .showDecorationAtBeginning(true)
                .showDecorationAtEnd(true)
                .setDecorationColor(R.color.colorAccent)
                .build();
    }
}
