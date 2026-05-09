package com.example.favorite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button moviebtn, commentarybtn, btn1;
    TextView name, email, tv;
    EditText edt1, edt2;
    View dialogView, toastView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("사용자 정보 입력");
        moviebtn=findViewById(R.id.moviebtn);
        commentarybtn=findViewById(R.id.commentarybtn);
        btn1=findViewById(R.id.btn1);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = (View) View.inflate(MainActivity.this,R.layout.dialog,null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("사용자 정보 입력");
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                edt1 = (EditText) dialogView.findViewById(R.id.edt1);
                                edt2 = (EditText) dialogView.findViewById(R.id.edt2);

                                name.setText(edt1.getText().toString());
                                email.setText(edt2.getText().toString());
                            }
                        });
                dlg.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast toast = new Toast(MainActivity.this);
                                toastView = (View) View.inflate(MainActivity.this,
                                        R.layout.toast, null);
                                tv = (TextView) toastView.findViewById(R.id.tv);
                                tv.setText("취소했습니다");
                                toast.setView(toastView);
                                toast.show();
                            }
                        });
                dlg.show();
            }
        });

        moviebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MovieActivity.class);
                startActivity(intent);
            }
        });
        commentarybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CommentaryActivity.class);
                startActivity(intent);
            }
        });



    }
}
