package com.example.movieticketapp.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.movieticketapp.R;
import com.example.movieticketapp.adapters.MovieAdapter;
import com.example.movieticketapp.models.Movie;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvMovies;
    MovieAdapter adapter;
    List<Movie> movieList;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvMovies = findViewById(R.id.rvMovies);
        rvMovies.setLayoutManager(new GridLayoutManager(this, 2));

        movieList = new ArrayList<>();
        adapter = new MovieAdapter(this, movieList);
        rvMovies.setAdapter(adapter);

        db = FirebaseFirestore.getInstance();
        db.collection("movies").addSnapshotListener((value, error) -> {
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