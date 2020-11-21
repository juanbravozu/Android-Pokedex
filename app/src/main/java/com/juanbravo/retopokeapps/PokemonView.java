package com.juanbravo.retopokeapps;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.juanbravo.retopokeapps.model.Pokemon;

public class PokemonView extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Pokemon pokemon;
    private ConstraintLayout root;
    private ImageView sprite;
    private TextView id;
    private TextView name;
    private OnPokemonClicked listener;

    public PokemonView(ConstraintLayout root) {
        super(root);
        this.root = root;
        sprite = root.findViewById(R.id.pokerow_sprite);
        id = root.findViewById(R.id.pokerow_id);
        name = root.findViewById(R.id.pokerow_name);
        root.setOnClickListener(this);
    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public TextView getId() {
        return id;
    }

    public TextView getName() {
        return name;
    }

    @Override
    public void onClick(View view) {
        if(listener != null) listener.onPokemonClicked(this.pokemon, this.root);
    }

    public void setOnPokemonClicked(OnPokemonClicked listener) {
        this.listener = listener;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public interface OnPokemonClicked {
        void onPokemonClicked(Pokemon pokemon, View v);
    }
}
