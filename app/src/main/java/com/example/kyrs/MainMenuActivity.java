package com.example.kyrs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {
    public Button activPht, exit;

    public void addOnButton(){
        activPht = (Button)findViewById(R.id.button);
        exit = (Button)findViewById(R.id.button3);

        activPht.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".SearchPhotosActivity");
                        startActivity(intent);
                    }
                }
        );

        exit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                }
        );
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        addOnButton();

    }
}
