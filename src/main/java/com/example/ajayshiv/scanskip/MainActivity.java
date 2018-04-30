package com.example.ajayshiv.scanskip;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button userActivity;
    private Button cashierActivity;
    private String setStore = "ExampleStore";
    private Database database = new Database(setStore);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userActivity = findViewById(R.id.button2);
        cashierActivity = findViewById(R.id.button1);
        userActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, userActivity.class );
                startActivity(i);
            }
        });

        cashierActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MainActivity.this, cashierAct.class);
                startActivity(i2);
            }
        });

    }
}
