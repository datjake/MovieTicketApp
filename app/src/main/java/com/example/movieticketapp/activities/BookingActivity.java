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
    ImageButton btnBack; // Khai báo nút Back
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        // Ánh xạ View
        db = FirebaseFirestore.getInstance();
        edtSeat = findViewById(R.id.edtSeatNumber);
        btnConfirm = findViewById(R.id.btnConfirmBooking);
        btnBack = findViewById(R.id.btnBack);

        String movieId = getIntent().getStringExtra("movieId");
        String movieTitle = getIntent().getStringExtra("movieTitle"); // Nhận tên phim nếu có

        // Xử lý nút Back
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> finish());
        }

        // Xử lý xác nhận đặt vé
        btnConfirm.setOnClickListener(v -> {
            String seat = edtSeat.getText().toString().trim();
            String userId = FirebaseAuth.getInstance().getUid();

            if (seat.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập số ghế!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (userId == null) {
                Toast.makeText(this, "Bạn cần đăng nhập để đặt vé", Toast.LENGTH_SHORT).show();
                return;
            }

            Map<String, Object> ticket = new HashMap<>();
            ticket.put("userId", userId);
            ticket.put("movieId", movieId);
            ticket.put("movieTitle", movieTitle);
            ticket.put("seat", seat);
            ticket.put("timestamp", com.google.firebase.Timestamp.now());

            btnConfirm.setEnabled(false); // Tránh nhấn liên tục

            db.collection("tickets").add(ticket)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(this, "Đặt vé thành công cho ghế " + seat, Toast.LENGTH_LONG).show();
                        finish(); // Quay lại trang chi tiết phim
                    })
                    .addOnFailureListener(e -> {
                        btnConfirm.setEnabled(true);
                        Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        });
    }
}