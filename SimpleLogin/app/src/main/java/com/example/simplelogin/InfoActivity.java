package com.example.simplelogin;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.simplelogin.bean.Student;

public class InfoActivity extends BaseActivity implements View.OnClickListener{
    private Intent intent;
    private TextView name;
    private TextView age;
    private TextView birth;
    private TextView gender;
    private TextView email;
    private TextView tel;
    private TextView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        intent = getIntent();
        Student student = (Student) intent.getSerializableExtra("info");
        name = findViewById(R.id.info_name);
        age = findViewById(R.id.info_age);
        birth = findViewById(R.id.info_birth);
        gender = findViewById(R.id.info_gender);
        email = findViewById(R.id.info_email);
        tel = findViewById(R.id.info_tel);
        logout = findViewById(R.id.info_logout);
        logout.setOnClickListener(this);
//        赋值
        name.setText(student.getName());
        age.setText(""+student.getAge());
        birth.setText(student.getBirth());
        if(student.getGender() == 1){
            gender.setText("男");
        }else if(student.getGender() == 0){
            gender.setText("女");
        }
        email.setText(student.getEmail());
        tel.setText(student.getTel());
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.info_logout){
            Intent intent = new Intent();
            intent.setAction("com.example.simplelogin.LOGOUT");
            intent.setComponent(new ComponentName(getPackageName(),"com.example.simplelogin.LogOutBroadcast"));
            sendBroadcast(intent);
        }
    }
}
