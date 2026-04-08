package com.example.movieticketapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.movieticketapp.R;

public class MovieDetailActivity extends AppCompatActivity {
    ImageView imgDetail;
    TextView tvTitle, tvDesc;
    Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        imgDetail = findViewById(R.id.imgDetailPoster);
        tvTitle = findViewById(R.id.txtDetailTitle);
        btnBook = findViewById(R.id.btnBookNow);

        String title = getIntent().getStringExtra("title");
        String poster = getIntent().getStringExtra("poster");
        String movieId = getIntent().getStringExtra("movieId");

        tvTitle.setText(title);
        Glide.with(this).load(poster).into(imgDetail);

        btnBook.setOnClickListener(v -> {
            Intent i = new Intent(this, BookingActivity.class);
            i.putExtra("movieId", movieId);
            startActivity(i);
        });
    }
}