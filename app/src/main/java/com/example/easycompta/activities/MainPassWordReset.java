package com.example.easycompta.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.easycompta.R;

public class MainPassWordReset extends AppCompatActivity {
Button btn_validerpwdreset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_pass_word_reset);

        btn_validerpwdreset = findViewById(R.id.btn_validerpwdreset);

        btn_validerpwdreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "Nous vous avons envoye un mail pour completer l'etape de reinitialisation";
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }
}
