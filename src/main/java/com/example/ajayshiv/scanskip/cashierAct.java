package com.example.ajayshiv.scanskip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class cashierAct extends AppCompatActivity {


        private Button camPic;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        camPic = (Button) findViewById(R.id.cam);
        camPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iCam = new Intent(cashierAct.this, camera.class);
                startActivity(iCam);
            }
        });

    }
}
