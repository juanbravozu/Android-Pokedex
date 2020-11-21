package com.juanbravo.retopokeapps.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.gson.Gson;
import com.juanbravo.retopokeapps.utils.Constants;
import com.juanbravo.retopokeapps.utils.HTTPSWebUtilDomi;
import com.juanbravo.retopokeapps.utils.PokemonsAdapter;
import com.juanbravo.retopokeapps.R;
import com.juanbravo.retopokeapps.model.Pokemon;

import java.io.IOException;
import java.util.UUID;

public class Pokedex extends AppCompatActivity {

    private FirebaseFirestore db;
    private EditText pokemonNameEdt;
    private EditText searchEdt;
    private String uid;
    private ImageButton catchBtn;
    private ImageButton searchBtn;
    private RecyclerView pokeViewList;
    private LinearLayoutManager layoutManager;
    private PokemonsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        db = FirebaseFirestore.getInstance();
        uid = getIntent().getExtras().getString("id");

        searchEdt = findViewById(R.id.poke_search_edt);
        pokemonNameEdt = findViewById(R.id.poke_catch_edt);
        catchBtn = findViewById(R.id.poke_catch_btn);
        searchBtn = findViewById(R.id.poke_search_btn);
        pokeViewList = findViewById(R.id.poke_list_rv);

        pokeViewList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        adapter = new PokemonsAdapter(uid);

        pokeViewList.setLayoutManager(layoutManager);
        pokeViewList.setAdapter(adapter);

        catchBtn.setOnClickListener(this::catchPokemon);
        searchBtn.setOnClickListener(this::filterPokemons);

    }

    private void catchPokemon(View v) {
        new Thread(
                () -> {
                    HTTPSWebUtilDomi https = new HTTPSWebUtilDomi();
                    String response = null;
                    String pokemonName = pokemonNameEdt.getText().toString().trim().toLowerCase();
                    try {
                        response = https.GETrequest(Constants.BASE_URL + pokemonName);
                    } catch (IOException e) {
                        runOnUiThread(
                                () -> {
                                    Toast.makeText(this, "Ese pokemon no existe :(", Toast.LENGTH_SHORT).show();
                                }
                        );
                        e.printStackTrace();
                        return;
                    }



                    if(!pokemonName.isEmpty()) {
                        Pokemon pokemon = new Gson().fromJson(response, Pokemon.class);

                        if(Math.random() < 0.1) pokemon.setShiny(true);

                        pokemon.setTime(System.currentTimeMillis());
                        runOnUiThread(
                                () -> {
                                    adapter.addPokemonToStart(pokemon);
                                    Toast.makeText(this, pokemon.getName().substring(0,1).toUpperCase() + pokemon.getName().substring(1)+" atrapado", Toast.LENGTH_SHORT).show();
                                }
                        );
                        String id = UUID.randomUUID().toString();
                        pokemon.setDbId(id);
                        db.collection("users").document(uid).collection("pokemons").document(id).set(pokemon);
                    } else {
                        runOnUiThread(
                                () -> {
                                    Toast.makeText(this, "Escribe el nombre de un Pokemon", Toast.LENGTH_SHORT).show();
                                }
                        );
                    }

                }
        ).start();
    }

    private void updatePokemons() {
        adapter.clearList();
        db.collection("users").document(uid).collection("pokemons").orderBy("time", Query.Direction.DESCENDING).get().addOnCompleteListener(
                task -> {
                    for (DocumentSnapshot document : task.getResult().getDocuments()) {
                        adapter.addPokemon(document.toObject(Pokemon.class));
                    }
                }
        );
    }

    private void filterPokemons(View v) {
        adapter.clearList();
        String name = searchEdt.getText().toString().trim().toLowerCase();

        if(!name.isEmpty()) {
            db.collection("users").document(uid).collection("pokemons").whereEqualTo("name", name).get().addOnCompleteListener(
                    task -> {
                        for (DocumentSnapshot document : task.getResult().getDocuments()) {
                            adapter.addPokemon(document.toObject(Pokemon.class));
                        }
                    }
            );
        } else {
            updatePokemons();
        }
    }

    @Override
    protected void onResume() {
        updatePokemons();
        super.onResume();
    }
}