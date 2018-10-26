package com.example.clwury.communication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class registe extends AppCompatActivity implements View.OnClickListener {
    EditText ed1;
    EditText ed2;
    EditText ed3;
    Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);
        ed1=(EditText) findViewById(R.id.name1);
        ed2=(EditText) findViewById(R.id.pswd);
        ed3=(EditText) findViewById(R.id.spswd);
        bt1=(Button) findViewById(R.id.surereg);
        bt1.getBackground().setAlpha(100);
    }
    public void onClick(View v){

    }
}
