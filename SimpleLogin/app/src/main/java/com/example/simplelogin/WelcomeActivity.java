package com.example.simplelogin;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.simplelogin.bean.Student;

public class WelcomeActivity extends BaseActivity implements View.OnClickListener {
    private TextView info;
    private TextView logout;
    private Student student;
    private IntentFilter intentFilter;
    private LogOutBroadcast logOutBroadcast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Intent intent = getIntent();
        student = (Student) intent.getSerializableExtra("student");
        String name = student.getName();
        System.out.println(name);
        TextView welcome_name = findViewById(R.id.welcome_name);
        info = findViewById(R.id.welcome_info);
        info.setOnClickListener(this);
        logout = findViewById(R.id.welcome_logout);
        logout.setOnClickListener(this);
        welcome_name.setText(name);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.welcome_info){
            Intent intent = new Intent(WelcomeActivity.this,InfoActivity.class);
            intent.putExtra("info",student);
            startActivity(intent);
        }
        if(v.getId() == R.id.welcome_logout){
            Intent intent = new Intent();
            intent.setAction("com.example.simplelogin.LOGOUT");
            intent.setComponent(new ComponentName(getPackageName(),"com.example.simplelogin.LogOutBroadcast"));
            sendBroadcast(intent);
        }
    }
}
