package com.example.movieticketapp.activities;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.movieticketapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class BookingActivity extends AppCompatActivity {
    EditText edtSeat;
    Button btnConfirm;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        db = FirebaseFirestore.getInstance();
        edtSeat = findViewById(R.id.edtSeatNumber);
        btnConfirm = findViewById(R.id.btnConfirmBooking);

        String movieId = getIntent().getStringExtra("movieId");

        btnConfirm.setOnClickListener(v -> {
            String seat = edtSeat.getText().toString();
            String userId = FirebaseAuth.getInstance().getUid();

            Map<String, Object> ticket = new HashMap<>();
            ticket.put("userId", userId);
            ticket.put("movieId", movieId);
            ticket.put("seat", seat);

            db.collection("tickets").add(ticket).addOnSuccessListener(documentReference -> {
                Toast.makeText(this, "Đặt vé thành công!", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }
}