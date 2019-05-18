package com.example.simplelogin;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class RegistActivity extends BaseActivity implements View.OnClickListener {
    private DataBase dataBase;
    private SQLiteDatabase db;
    private Button regist_button;
    private EditText regist_name;
    private EditText regist_password;
    private EditText regist_age;
    private EditText regist_birth;
    private String gender;
    private EditText regist_email;
    private EditText regist_tel;
    private RadioButton man;
    private RadioButton woman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        dataBase = new DataBase(this,"student.db",null,1);
        db = dataBase.getWritableDatabase();
        man = findViewById(R.id.regist_r1);
        woman = findViewById(R.id.regist_r2);
        regist_button = findViewById(R.id.regist_button);
        regist_name = findViewById(R.id.regist_name);
        regist_password = findViewById(R.id.regist_password);
        regist_age = findViewById(R.id.regist_age);
        regist_birth = findViewById(R.id.regist_birth);
        regist_email = findViewById(R.id.regist_email);
        regist_tel = findViewById(R.id.regist_tel);

        regist_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.regist_button){
            registUser();
        }
    }

//    注册用户
    private void registUser() {
        String name =regist_name.getText().toString();
        String password = regist_password.getText().toString();
        String age = regist_age.getText().toString();
        String birth = regist_birth.getText().toString();
        String email = regist_email.getText().toString();
        String tel = regist_tel.getText().toString();
        if(man.isChecked()){
            gender = "1";
//            System.out.println("男");
        }else if (woman.isChecked()){
            gender = "0";
//            System.out.println("女");
        }
        db.execSQL("insert into student(name,password,age,birth,gender,email,tel) values(?,?,?,?,?,?,?)",new String[]{name,password,age,birth,gender,email,tel});
        Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(RegistActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
