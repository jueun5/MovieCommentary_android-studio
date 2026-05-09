package com.example.favorite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class CommentaryActivity extends AppCompatActivity {

    Button main, writebtn, backbtn;
    DatePicker dp;
    EditText edtDiary;
    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commentary);
        setTitle("영화 감상문");

        main = findViewById(R.id.main);
        writebtn = findViewById(R.id.writebtn);
        dp = findViewById(R.id.dp);
        edtDiary = findViewById(R.id.edtDiary);
        backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Calendar cal = Calendar.getInstance();
        int cyear = cal.get(Calendar.YEAR); ///2026
        int cmonth = cal.get(Calendar.MONTH); //월
        int cday = cal.get(Calendar.DAY_OF_MONTH); //일

        dp.init(cyear, cmonth, cday, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                filename = Integer.toString(year)+"-"+Integer.toString(month+1)+"-"+Integer.toString(day)+"txt";
                String str = readDiary(filename);
                edtDiary.setText(str);
                writebtn.setEnabled(true);
            }
        });
        writebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileOutputStream outf = openFileOutput(filename, Context.MODE_PRIVATE);
                    String str = edtDiary.getText().toString();
                    outf.write(str.getBytes());
                    outf.close();
                    Toast.makeText(getApplicationContext(), filename+"이 저장됨", Toast.LENGTH_SHORT).show();
                }catch (IOException e){
                    Toast.makeText(getApplicationContext(), filename+"이 오류", Toast.LENGTH_SHORT).show();
                }

            }
        });

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
    String readDiary(String fname) {
        String diaryStr = null;
        FileInputStream inf;
        try {
            inf = openFileInput(fname);
            byte [] txt = new byte[inf.available()];
            inf.read(txt);
            inf.close();
            diaryStr = (new String(txt)).trim();
            writebtn.setText("수정하기");
        }catch (IOException e){
            edtDiary.setHint("일기 없음");
            writebtn.setText("새로 저장");
        }
        return diaryStr;
    }
}
