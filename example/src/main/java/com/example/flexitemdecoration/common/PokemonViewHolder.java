package com.example.flexitemdecoration.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.flexitemdecoration.R;


/**
 * Created by Guillaume 'DarzuL' Bourderye on 3/25/17.
 * <p>
 * Pokemon ViewHolder
 */

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    private final TextView mPokemonTv;

    public PokemonViewHolder(View itemView) {
        super(itemView);
        mPokemonTv = (TextView) itemView.findViewById(R.id.tv);
    }

    public void bindPokemon(String pokemon) {
        mPokemonTv.setText(pokemon);
    }
}
