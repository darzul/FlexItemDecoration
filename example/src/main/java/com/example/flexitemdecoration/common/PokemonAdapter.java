package com.example.flexitemdecoration.common;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flexitemdecoration.R;


/**
 * Created by Guillaume 'DarzuL' Bourderye on 3/25/17.
 * <p>
 * Pokemon adapter
 */

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private final Type mType;
    private final Pokemons mPokemons;

    public PokemonAdapter(Type type) {
        mType = type;
        mPokemons = new Pokemons();
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(mType.viewHolderLayoutRes, parent, false);
        return new PokemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PokemonViewHolder holder, int position) {
        holder.bindPokemon(mPokemons.getPokemon(position));
    }

    @Override
    public int getItemCount() {
        return mPokemons.count();
    }

    public enum Type {
        VERTICAL(R.layout.viewholder_pokemon_vertical),
        HORIZONTAL(R.layout.viewholder_pokemon_horizontal),
        GRID(R.layout.viewholder_pokemon_grid);

        private final int viewHolderLayoutRes;

        Type(int viewHolderLayoutRes) {
            this.viewHolderLayoutRes = viewHolderLayoutRes;
        }
    }
}
