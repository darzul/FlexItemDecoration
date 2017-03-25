package com.example.flexitemdecoration.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.flexitemdecoration.R;
import com.example.flexitemdecoration.grid.GridItemDecorationProvider;
import com.example.flexitemdecoration.horizontal.HorizontalItemDecorationProvider;
import com.example.flexitemdecoration.vertical.VerticalItemDecorationProvider;

/**
 * Created by Guillaume 'DarzuL' Bourderye on 3/25/17.
 * <p>
 * Main activity
 */

public class MainActivity extends AppCompatActivity {

    private final VerticalItemDecorationProvider mVerticalItemDecorationProvider = new VerticalItemDecorationProvider();
    private final HorizontalItemDecorationProvider mHorizontalItemDecorationProvider = new HorizontalItemDecorationProvider();
    private final GridItemDecorationProvider mGridItemDecorationProvider = new GridItemDecorationProvider();

    private RecyclerView mRecyclerView;
    private RecyclerView.ItemDecoration mItemDecoration;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        PokemonAdapter.Type type;
        RecyclerView.LayoutManager layoutManager;
        RecyclerView.ItemDecoration itemDecoration;

        switch (item.getItemId()) {
            case R.id.menu_vertical:
                type = PokemonAdapter.Type.VERTICAL;
                layoutManager = new LinearLayoutManager(this);
                itemDecoration = mVerticalItemDecorationProvider.provide(this, 1);
                break;

            case R.id.menu_horizontal:
                type = PokemonAdapter.Type.HORIZONTAL;
                layoutManager = new LinearLayoutManager(this,
                        LinearLayoutManager.HORIZONTAL,
                        false);
                itemDecoration = mHorizontalItemDecorationProvider.provide(this, 1);
                break;

            case R.id.menu_grid_x2:
                type = PokemonAdapter.Type.GRID;
                layoutManager = new GridLayoutManager(this, 2);
                itemDecoration = mGridItemDecorationProvider.provide(this, 2);
                break;

            case R.id.menu_grid_x3:
                type = PokemonAdapter.Type.GRID;
                layoutManager = new GridLayoutManager(this, 3);
                itemDecoration = mGridItemDecorationProvider.provide(this, 3);
                break;

            case R.id.menu_grid_x4:
                type = PokemonAdapter.Type.GRID;
                layoutManager = new GridLayoutManager(this, 4);
                itemDecoration = mGridItemDecorationProvider.provide(this, 4);
                break;

            case R.id.menu_grid_x5:
                type = PokemonAdapter.Type.GRID;
                layoutManager = new GridLayoutManager(this, 5);
                itemDecoration = mGridItemDecorationProvider.provide(this, 5);
                break;

            default:
                throw new IllegalArgumentException("unknown itemId: " + item.getItemId());
        }

        mRecyclerView.setAdapter(new PokemonAdapter(type));
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.removeItemDecoration(mItemDecoration);
        mItemDecoration = itemDecoration;
        mRecyclerView.addItemDecoration(mItemDecoration);

        return true;
    }
}
