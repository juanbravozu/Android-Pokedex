package com.juanbravo.retopokeapps.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.juanbravo.retopokeapps.R;
import com.juanbravo.retopokeapps.model.User;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private EditText nameEdt;
    private ImageButton loginBtn;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = FirebaseFirestore.getInstance();
        nameEdt = findViewById(R.id.main_name_edt);
        loginBtn = findViewById(R.id.main_login_btn);

        loginBtn.setOnClickListener(this::login);
    }

    private void login(View v) {
        String name = nameEdt.getText().toString();

        //VALIDACIONES
        if(name.trim().isEmpty()){
            Toast.makeText(this, "Escribe un nombre de entrenador", Toast.LENGTH_LONG).show();
            return;
        }

        Intent i = new Intent(this, Pokedex.class);

        Query query = db.collection("users").whereEqualTo("username", name);
        query.get().addOnCompleteListener(
                task -> {
                    if(task.getResult().getDocuments().size() > 0) {
                        for(DocumentSnapshot document : task.getResult().getDocuments()) {
                            User user = document.toObject(User.class);
                            Log.e(">>>", ""+user.getId());
                            i.putExtra("id", user.getId());
                            Toast.makeText(this, "Inicio sesión con usuario existente", Toast.LENGTH_SHORT).show();
                            startActivity(i);
                            finish();
                            return;
                        }
                    } else {
                        User user = new User(name, UUID.randomUUID().toString());
                        db.collection("users").document(user.getId()).set(user);
                        i.putExtra("id", user.getId());
                        Toast.makeText(this, "Usuario creado", Toast.LENGTH_SHORT).show();
                        startActivity(i);
                        finish();
                    }
                }
        );
    }
}