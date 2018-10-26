package com.example.clwury.communication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login extends AppCompatActivity implements View.OnClickListener{
    Button regiester;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        regiester=(Button) findViewById(R.id.register);
        login=(Button) findViewById(R.id.btn_login);
        login.getBackground().setAlpha(80);
        regiester.getBackground().setAlpha(80);
        regiester.setOnClickListener(this);
        login.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_login:
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.register:
                Intent intent1=new Intent(this,registe.class);
                startActivity(intent1);
                break;
        }
    }
}
