package com.juanbravo.retopokeapps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.FirebaseFirestore;
import com.juanbravo.retopokeapps.R;
import com.juanbravo.retopokeapps.model.Pokemon;

import java.util.ArrayList;

public class PokemonDetails extends AppCompatActivity {

    private Pokemon pokemon;
    private ArrayList<String> types;

    private TextView id;
    private TextView name;
    private TextView height;
    private TextView weight;
    private TextView hp;
    private TextView atk;
    private TextView def;
    private TextView satk;
    private TextView sdef;
    private TextView speed;
    private ImageView sprite;
    private ImageView type1;
    private ImageView type2;
    private ImageButton releaseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        pokemon = (Pokemon) getIntent().getExtras().getSerializable("pokemon");

        id = findViewById(R.id.details_id_tv);
        name = findViewById(R.id.details_name_tv);
        height = findViewById(R.id.details_height_tv);
        weight = findViewById(R.id.details_weight_tv);
        hp = findViewById(R.id.details_hp_tv);
        atk = findViewById(R.id.details_atk_tv);
        def = findViewById(R.id.details_def_tv);
        satk = findViewById(R.id.details_satk_tv);
        sdef = findViewById(R.id.details_sdef_tv);
        speed = findViewById(R.id.details_speed_tv);
        sprite = findViewById(R.id.details_sprite_img);
        type1 = findViewById(R.id.details_type1_img);
        type2 = findViewById(R.id.details_type2_img);
        releaseBtn = findViewById(R.id.details_release_btn);

        id.setText("#"+pokemon.getId());
        name.setText(pokemon.getName().substring(0,1).toUpperCase() + pokemon.getName().substring(1));
        height.setText((pokemon.getHeight()/10.0)+"m");
        weight.setText((pokemon.getWeight()/10.0)+"kg");
        hp.setText(""+pokemon.getStats().get(0).getBaseStat());
        atk.setText(""+pokemon.getStats().get(1).getBaseStat());
        def.setText(""+pokemon.getStats().get(2).getBaseStat());
        satk.setText(""+pokemon.getStats().get(3).getBaseStat());
        sdef.setText(""+pokemon.getStats().get(4).getBaseStat());
        speed.setText(""+pokemon.getStats().get(5).getBaseStat());

        if(pokemon.isShiny()) {
            Glide.with(this).load(pokemon.getSprites().getFrontShiny()).into(sprite);
        } else {
            Glide.with(this).load(pokemon.getSprites().getFront()).into(sprite);
        }

        setTypeImage(type1, pokemon.getTypes().get(0).getType().getName());

        if(pokemon.getTypes().size() > 1) {
            setTypeImage(type2, pokemon.getTypes().get(1).getType().getName());
        }

        releaseBtn.setOnClickListener(this::removePokemon);
    }

    private void removePokemon(View v) {
        String uid = getIntent().getExtras().getString("id");
        FirebaseFirestore.getInstance().collection("users").document(uid).collection("pokemons").document(pokemon.getDbId()).delete();
        finish();
    }

    private void setTypeImage(ImageView typeImg, String name) {
        switch(name) {
            case "fire":
                typeImg.setImageResource(R.drawable.type_fire);
                break;

            case "water":
                typeImg.setImageResource(R.drawable.type_water);
                break;

            case "grass":
                typeImg.setImageResource(R.drawable.type_grass);
                break;

            case "ground":
                typeImg.setImageResource(R.drawable.type_ground);
                break;

            case "rock":
                typeImg.setImageResource(R.drawable.type_rock);
                break;

            case "normal":
                typeImg.setImageResource(R.drawable.type_normal);
                break;

            case "dark":
                typeImg.setImageResource(R.drawable.type_dark);
                break;

            case "steel":
                typeImg.setImageResource(R.drawable.type_steel);
                break;

            case "ice":
                typeImg.setImageResource(R.drawable.type_ice);
                break;

            case "poison":
                typeImg.setImageResource(R.drawable.type_poison);
                break;

            case "bug":
                typeImg.setImageResource(R.drawable.type_bug);
                break;

            case "ghost":
                typeImg.setImageResource(R.drawable.type_ghost);
                break;

            case "dragon":
                typeImg.setImageResource(R.drawable.type_dragon);
                break;

            case "electric":
                typeImg.setImageResource(R.drawable.type_electric);
                break;

            case "flying":
                typeImg.setImageResource(R.drawable.type_flying);
                break;

            case "fairy":
                typeImg.setImageResource(R.drawable.type_fairy);
                break;

            case "fighting":
                typeImg.setImageResource(R.drawable.type_fighting);
                break;

            case "psychic":
                typeImg.setImageResource(R.drawable.type_psychic);
                break;

        }
    }
}