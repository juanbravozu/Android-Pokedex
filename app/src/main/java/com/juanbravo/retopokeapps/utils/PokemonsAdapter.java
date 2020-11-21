package com.juanbravo.retopokeapps.utils;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.juanbravo.retopokeapps.PokemonView;
import com.juanbravo.retopokeapps.R;
import com.juanbravo.retopokeapps.activities.PokemonDetails;
import com.juanbravo.retopokeapps.model.Pokemon;
import com.juanbravo.retopokeapps.model.Type;

import java.io.Serializable;
import java.util.ArrayList;

public class PokemonsAdapter extends RecyclerView.Adapter<PokemonView> implements PokemonView.OnPokemonClicked {

    private ArrayList<Pokemon> pokemons;
    private String uid;

    public PokemonsAdapter(String uid) {
        this.uid = uid;
        pokemons = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon) {
        pokemons.add(pokemon);
        this.notifyDataSetChanged();
    }

    public void addPokemonToStart(Pokemon pokemon) {
        pokemons.add(0, pokemon);
        this.notifyDataSetChanged();
    }

    public void clearList() {
        pokemons.clear();
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokemonView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.pokemon_row, parent, false);
        ConstraintLayout rowroot = (ConstraintLayout) row;
        PokemonView pokemonView = new PokemonView(rowroot);
        return pokemonView;
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonView holder, int position) {
        Pokemon currentPoke = pokemons.get(position);
        holder.setPokemon(currentPoke);
        holder.getId().setText("#"+currentPoke.getId());
        holder.getName().setText(currentPoke.getName().substring(0,1).toUpperCase() + currentPoke.getName().substring(1));

        String url = "";
        if(currentPoke.isShiny()) {
            url = currentPoke.getSprites().getFrontShiny();
        } else {
            url = currentPoke.getSprites().getFront();
        }

        Glide.with(holder.getRoot()).load(url).into(holder.getSprite());

        holder.setOnPokemonClicked(this);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }


    @Override
    public void onPokemonClicked(Pokemon pokemon, View v) {
        Intent i = new Intent(v.getContext(), PokemonDetails.class);
        i.putExtra("pokemon", (Serializable) pokemon);
        i.putExtra("id", uid);
        v.getContext().startActivity(i);
    }
}
