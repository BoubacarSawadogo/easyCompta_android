package com.example.easycompta.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.easycompta.R;
import com.example.easycompta.manager.UserManager;
import com.example.easycompta.services.BusinessBookDatabaseHelper;
import com.example.easycompta.services.ConnexionBd;

public class LoginActivity extends Activity {
    TextView register;
    TextView resetpassword;
    EditText edt_email;
    EditText edt_pwd;
    Button btn_login;
    BusinessBookDatabaseHelper db;
    Context ctx;
    TextView emptyEmail;
    TextView emptypwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        // ConnexionBd.importDatabase(ctx);
        // ArrayList<String> test = UserManager.checkMailUser(this);
        setContentView(R.layout.activity_login);
        edt_email = findViewById(R.id.edt_email);
        edt_pwd = findViewById(R.id.edt_pwd);
        btn_login = findViewById(R.id.btn_login);
        db = new BusinessBookDatabaseHelper(this, "businessdatabase", null, 1);
        register = findViewById(R.id.register);
        resetpassword = findViewById(R.id.resetpassword);
        emptyEmail = findViewById(R.id.emptyemail);
        emptypwd = findViewById(R.id.emptypwd);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, MainRegister.class);
                startActivity(intent);
            }
        });
        resetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentresetpwd = new Intent(ctx, MainPassWordReset.class);
                startActivity(intentresetpwd);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edt_email.getText().toString().trim();
                String password = edt_pwd.getText().toString().trim();
                Boolean checkmailpass = UserManager.checkemailpassword(email, password, ctx);
                if (email.isEmpty() && password.isEmpty()) {
                    emptyEmail.setText("* Entrez votre email svp");
                    emptypwd.setText("* Entrez votre pwd svp");
                    // emptyEmail.setTextColor(getResources().getColor(R.color.redColor));
                } else if (email.isEmpty()) {
                    emptyEmail.setText("* Entrez votre email svp");
                    emptypwd.setText("");
                } else if (password.isEmpty()) {
                    emptyEmail.setText("");
                    emptypwd.setText("* Entrez votre pwd svp");
                } else {
                    emptyEmail.setText("");
                    emptypwd.setText("");
                }
                if (checkmailpass == true) {
                    setContentView(R.layout.activity_splas_screen);
                    Intent intent = new Intent(ctx, SplasScreenActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ctx, "Wrong password or email", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
