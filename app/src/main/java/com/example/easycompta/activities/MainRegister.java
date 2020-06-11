package com.example.easycompta.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.easycompta.R;
import com.example.easycompta.entities.User;
import com.example.easycompta.manager.UserManager;
import com.example.easycompta.services.BusinessBookDatabaseHelper;
import com.example.easycompta.services.ConnexionBd;

public class MainRegister extends AppCompatActivity {
    TextView return_login;
    TextView passwordforgot;

    EditText edt_firstname;
    EditText edt_lastname;
    EditText edt_email;
    EditText edt_pwd;
    EditText edt_phone;

    Button btn_register;
    Context ctx;

    BusinessBookDatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_main_register);
      //  db = new BusinessBookDatabaseHelper(this, "first", null, 1);

        ConnexionBd.importDatabase(ctx);
        edt_firstname = findViewById(R.id.edt_firstname);
        edt_lastname = findViewById(R.id.edt_lastname);
        edt_email = findViewById(R.id.edt_email);
        edt_pwd = findViewById(R.id.edt_pwd);
        edt_phone = findViewById(R.id.edt_phone);

        btn_register = findViewById(R.id.btn_register);
        return_login = findViewById(R.id.return_login);
        passwordforgot = findViewById(R.id.passwordforgot);


        return_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnlogin = new Intent(ctx, LoginActivity.class);
                startActivity(returnlogin);
            }
        });
        passwordforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentresetpwd = new Intent(ctx, MainPassWordReset.class);
                startActivity(intentresetpwd);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String firstName = edt_firstname.getText().toString().trim();
                String lastName = edt_lastname.getText().toString().trim();
                String email = edt_email.getText().toString().trim();
                String password = edt_pwd.getText().toString().trim();
                String phone = edt_phone.getText().toString().trim();
                if (firstName.equals("") || lastName.equals("") || email.equals("") || password.equals("") || phone.equals("")) {
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_LONG).show();
                } else {
                    Boolean checkemail = UserManager.checkemail(email, ctx);

                    if (checkemail == true) {
                        User newUser = new User();
                        newUser.setFirstname(firstName);
                        newUser.setLastname(lastName);
                        newUser.setEmail(email);
                        newUser.setPassword(password);
                        newUser.setPhone(phone);
                        Boolean insertion = UserManager.add(newUser, ctx);
                        if (insertion == true) {
                            Toast.makeText(getApplicationContext(), "Register succesfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"Probleme d'insertion a la bd",Toast.LENGTH_LONG).show();}
                    } else {
                        Toast.makeText(getApplicationContext(), "Email Already exists", Toast.LENGTH_LONG).show();
                    }
                }


            }
        });
    }
}
