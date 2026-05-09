package com.example.favorite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MovieActivity extends AppCompatActivity {

    Button commentarybtn, main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie);
        setTitle("영화 선택");
        Gallery gallery = findViewById(R.id.gallery);
        commentarybtn = findViewById(R.id.commentarybtn);
        main = findViewById(R.id.main);
        commentarybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CommentaryActivity.class);
                startActivity(intent);
            }
        });
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        MyGallery myGallery = new MyGallery(this);
        gallery.setAdapter(myGallery);

        String posterName[] = {"슬램덩크", "스파이더맨:노 웨이 홈", "왕과 사는 남자", "베테랑", "주토피아",
                "군함도", "인사이드아웃", "슈퍼배드", "극한직업", "비긴 어게인"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, posterName);


    }

    class MyGallery extends BaseAdapter {
        Context context;
        MyGallery(Context c) {
            context = c;
        }

        Integer [] posterID={R.drawable.m1,R.drawable.m2,R.drawable.m3,R.drawable.m4,R.drawable.m5,
                R.drawable.m6,R.drawable.m7,R.drawable.m8,R.drawable.m9,R.drawable.m10};

        @Override
        public int getCount() {
            return posterID.length;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            ImageView imageView = new ImageView(context);
            imageView.setLayoutParams(new Gallery.LayoutParams(250, 350));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5, 5, 5, 5);
            imageView.setImageResource(posterID[position]);

            final int pos = position;
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    ImageView ivposter=findViewById(R.id.ivposter);
                    ivposter.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivposter.setImageResource(posterID[pos]);
                    return false;
                }
            });


            return imageView;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }
    }
}
