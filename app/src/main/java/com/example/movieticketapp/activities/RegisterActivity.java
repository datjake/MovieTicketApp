package com.example.movieticketapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.movieticketapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword, edtFullName;
    Button btnRegister;
    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtFullName = findViewById(R.id.edtFullName);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String pass = edtPassword.getText().toString().trim();
            String name = edtFullName.getText().toString().trim();

            if (email.isEmpty() || pass.isEmpty() || name.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    String uid = mAuth.getCurrentUser().getUid();
                    Map<String, Object> user = new HashMap<>();
                    user.put("uid", uid);
                    user.put("email", email);
                    user.put("fullName", name);

                    db.collection("users").document(uid).set(user).addOnSuccessListener(aVoid -> {
                        Toast.makeText(this, "Đăng ký thành công! Mời bạn đăng nhập.", Toast.LENGTH_SHORT).show();

                        // SỬA TẠI ĐÂY: Chuyển về LoginActivity thay vì MainActivity
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        // Xóa các màn hình trước đó để quay về Login sạch sẽ
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);

                        finish(); // Đóng màn hình đăng ký
                    }).addOnFailureListener(e -> {
                        Toast.makeText(this, "Lỗi lưu thông tin: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
                } else {
                    Toast.makeText(this, "Lỗi đăng ký: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}