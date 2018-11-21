package com.example.alias.arenaofvalor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Info extends AppCompatActivity {
    Hero hero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.herodetail);

        Intent intent = getIntent();
        hero = (Hero)intent.getSerializableExtra("person");






    }
}
