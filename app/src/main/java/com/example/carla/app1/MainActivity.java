package com.example.carla.app1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView shoplist;
    private ImageView coffee;
    private ImageView appointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.shoplist=findViewById(R.id.shoplist);
        this.coffee=findViewById(R.id.coffee);
        this.appointment=findViewById(R.id.appointment);

        shoplist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherAct = new Intent(getApplicationContext(),ShopList.class);
                startActivity(otherAct);
               
            }
        });
        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherAct = new Intent(getApplicationContext(),Directory.class);
                startActivity(otherAct);

            }
        });

       appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent otherAct = new Intent(getApplicationContext(),Shop.class);
                startActivity(otherAct);

            }
        });
    }
}
