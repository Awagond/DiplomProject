package com.example.cabelpc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
   private Button CreateAccountButton;
    private EditText InputName,InputPhoneNumber,InputPassword, InputEmail;
    private ProgressDialog LoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        CreateAccountButton =(Button) findViewById(R.id.register_btn);
        InputName =(EditText) findViewById(R.id.register_username_input);
        InputPhoneNumber =(EditText) findViewById(R.id.register_phone_number_input);
        InputPassword =(EditText) findViewById(R.id.register_password_input);
        InputEmail =(EditText) findViewById(R.id.register_email_input);
        LoadingBar= new ProgressDialog (this);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateAccount();
            }
        });
    }

    private void CreateAccount() {
        String name =InputName.getText().toString();
        String phone =InputPhoneNumber.getText().toString();
        String password =InputPassword.getText().toString();
        String email = InputEmail.getText().toString();
        if (TextUtils .isEmpty(name))
        {
            Toast .makeText(this,"Пожалуйста укажите логин",Toast.LENGTH_LONG);
        }
        else if (TextUtils .isEmpty(email))
        {
            Toast .makeText(this,"Пожалуйста укажите почту",Toast.LENGTH_LONG);
        }
      else if (TextUtils .isEmpty(phone))
        {
            Toast .makeText(this,"Пожалуйста укажите номер телефона",Toast.LENGTH_LONG);
        }
        else if (TextUtils .isEmpty(password))
        {
            Toast .makeText(this,"Пожалуйста укажите пароль",Toast.LENGTH_LONG);
        }
        else {
            LoadingBar.setTitle("Создание аккаунта");
            LoadingBar.setMessage("Пожалуйста подождите");
            LoadingBar.setCanceledOnTouchOutside(false);
            LoadingBar.show();
            ValidatephoneNumber(name,phone,password,email);
        }
    }

    private void ValidatephoneNumber(final String name, final String phone, final String password, final String email)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!(dataSnapshot.child("Users").child(phone).exists()))
                {
                    HashMap<String,Object>userdataMap=new HashMap<>();
                    userdataMap.put("phone",phone);
                    userdataMap.put("email",email);
                    userdataMap.put("password",password);
                    userdataMap.put("name",name);
                    RootRef.child("Users").child(phone).updateChildren(userdataMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(RegisterActivity.this, "Аккаунт создан", Toast.LENGTH_SHORT).show();
                                        LoadingBar.dismiss();
                                        Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        LoadingBar.dismiss();
                                        Toast.makeText(RegisterActivity.this, "Ошибка сервера", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });


                }
                else
                {
                    Toast.makeText(RegisterActivity.this,"Этот" +phone+ " уже зарегистрирован",Toast.LENGTH_SHORT).show();
                    LoadingBar.dismiss();
                    Toast.makeText(RegisterActivity.this,"Пожалуйста, введите другой номер телефона",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
