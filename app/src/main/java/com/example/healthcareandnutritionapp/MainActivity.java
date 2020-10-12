package com.example.healthcareandnutritionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView health_Tips, nutrition_Tips;
    ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        health_Tips = findViewById(R.id.health_tips);
        nutrition_Tips = findViewById(R.id.nutrition_tips);


        //Image Slider
        int[] images = {R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four,
                        R.drawable.five, R.drawable.six, R.drawable.seven, R.drawable.eight,
                        R.drawable.nine, R.drawable.ten, R.drawable.eleven};

        viewFlipper = findViewById(R.id.view_Flipper);

        //for loop
        /*for (int i = 0; i < images.length; i++){
            flipperImages(images[i]);
        }*/


        //Advanced Version of For Loop
        //foreach loop
        for (int image: images){
            flipperImages(image);
        }


        health_Tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HealthTips.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        nutrition_Tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NutritionTips.class);
                startActivity(intent);
                //overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }


    //onCreateOptionsMenu is used here to add menu options on the toolbar...
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }


    //Using method onOptionsItemSelected to log out user from an activity...

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //switch conditional structure is used when there're multiple options on menuOption...
        //Else no need to use switch()...
        if (item.getItemId() == R.id.menuOption) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(getApplicationContext(), "Logged out Successfully", Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }, 1500);
        }
        return true;
    }

    //ViewFlipper external method
    public void flipperImages(int image){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000); // 4 seconds
        viewFlipper.setAutoStart(true);


        //animation
//        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
//        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }
}