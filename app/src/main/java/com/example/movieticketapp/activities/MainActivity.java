package com.example.movieticketapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieticketapp.R;
import com.example.movieticketapp.adapters.MovieAdapter;
import com.example.movieticketapp.models.Movie;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvMovies;
    MovieAdapter adapter;
    List<Movie> movieList;
    FirebaseFirestore db;
    ImageButton btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // --- XỬ LÝ LOGOUT ---
        btnLogout = findViewById(R.id.btnLogout);
        if (btnLogout != null) {
            btnLogout.setOnClickListener(v -> {
                FirebaseAuth.getInstance().signOut();
                // Quay về LoginActivity
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
                Toast.makeText(this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
            });
        }

        // --- THIẾT LẬP RECYCLERVIEW ---
        rvMovies = findViewById(R.id.rvMovies);
        rvMovies.setLayoutManager(new GridLayoutManager(this, 2));

        movieList = new ArrayList<>();
        adapter = new MovieAdapter(this, movieList);
        rvMovies.setAdapter(adapter);

        // --- LẤY DỮ LIỆU TỪ FIRESTORE ---
        db = FirebaseFirestore.getInstance();
        db.collection("movies").addSnapshotListener((value, error) -> {
            if (error != null) {
                Toast.makeText(this, "Lỗi tải phim: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                return;
            }
            if (value != null) {
                movieList.clear();
                for (DocumentSnapshot doc : value) {
                    Movie m = doc.toObject(Movie.class);
                    if (m != null) {
                        m.setMovieId(doc.getId());
                        movieList.add(m);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
}