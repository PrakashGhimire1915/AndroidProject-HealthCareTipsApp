package com.example.healthcareandnutritionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class NutritionTipsForWomen extends AppCompatActivity {
    Button buttonBackWomenTips;

    RecyclerView womenNutritionRecyclerView;

    // Creating object of RecyclerViewAdapter
    AdapterRecyclerView adapterRecyclerView;


    ProgressBar progressBar_WomenNutritionTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_for_women);

        buttonBackWomenTips = findViewById(R.id.backArrow);
        buttonBackWomenTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NutritionTipsForWomen.this, NutritionTips.class));
            }
        });


        progressBar_WomenNutritionTips = findViewById(R.id.progressBar_WomenNutritionTips);

        womenNutritionRecyclerView = findViewById(R.id.womenNutritionRecyclerView);
        womenNutritionRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Nutrition Tips").child("Nutrition Tips focused for Women"), Model.class)
                        .build();

        adapterRecyclerView = new AdapterRecyclerView(options);

        womenNutritionRecyclerView.setAdapter(adapterRecyclerView);


        progressBar_WomenNutritionTips.setVisibility(View.VISIBLE);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar_WomenNutritionTips.setVisibility(View.GONE);
                onStart();
            }
        }, 2000);




        FloatingActionButton nutritionTipsWomen_fab = findViewById(R.id.nutritionTipsWomen_fab);

        //OnClick fab button, the screen or activity switches to its respective data insert activity
        nutritionTipsWomen_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), InsertDataForWomenNutritionTips.class));
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();

        adapterRecyclerView.startListening();
    }
}
