package com.example.masters.scanfinger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.masters.scanfinger.R.id.IDcard;
import static com.example.masters.scanfinger.R.id.start;


public class IDkeyActivity extends AppCompatActivity {

    Button ok;
    EditText ID;
    TextView show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idkey);

        ID = (EditText) findViewById(IDcard);
//        show.setText(ID.getText().toString());
        String show = ID.getText().toString();
        ok = (Button) findViewById(R.id.okbotton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Key.idKey = ID.getText().toString(); ///// save idcard
                int maxLength = 13;
                if (ID.getText().toString().length() == maxLength) {
//                    Key.idKey = ID.getText().toString();
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(IDkeyActivity.this,"Please enter your IDCard",Toast.LENGTH_SHORT).show();
                    ID.setText("");

                }
            }
        });

//    public void RegisOnclick(View v){
//        Thread nt = new Thread() {
//            EditText ID = (EditText) findViewById(R.id.IDcard);
//            String show = ID.getText().toString();
//            @Override
//            public void run(){
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
//                        i.putExtra("ID",show);
//                        startActivity(i);
//                    }
//
//                });
//            }
//        };
//        nt.start();
//    }
    }
}