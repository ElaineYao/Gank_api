package com.elainedv.Gank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.elainedv.Classification.ClassActivity;
import com.elainedv.Register.Login_Activity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("------"+TAG+"-------",TAG);

        Button button=(Button)findViewById(R.id.intent_login);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ClassActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
