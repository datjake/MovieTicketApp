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
    ImageButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // Ánh xạ View
        imgDetail = findViewById(R.id.imgDetailPoster);
        tvTitle = findViewById(R.id.txtDetailTitle);
        tvDesc = findViewById(R.id.txtDetailDesc); // Ánh xạ thêm mô tả
        btnBook = findViewById(R.id.btnBookNow);
        btnBack = findViewById(R.id.btnBack);

        // Nhận dữ liệu
        String title = getIntent().getStringExtra("title");
        String poster = getIntent().getStringExtra("poster");
        String movieId = getIntent().getStringExtra("movieId");
        String desc = getIntent().getStringExtra("description"); // Giả sử bạn gửi thêm mô tả từ Adapter

        // Hiển thị dữ liệu
        tvTitle.setText(title);
        if (desc != null) tvDesc.setText(desc);
        Glide.with(this).load(poster).into(imgDetail);

        // Xử lý nút Back
        btnBack.setOnClickListener(v -> finish());

        // Xử lý nút Đặt vé
        btnBook.setOnClickListener(v -> {
            Intent i = new Intent(this, BookingActivity.class);
            i.putExtra("movieId", movieId);
            i.putExtra("movieTitle", title); // Gửi thêm tên phim sang trang đặt vé
            startActivity(i);
        });
    }
}