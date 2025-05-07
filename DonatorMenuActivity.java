package com.example.charifymain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class DonatorMenuActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private TextView userGreeting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donator_menu);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        userGreeting = findViewById(R.id.userGreeting);

        // Set up click listeners for all categories
        setupCategoryClickListeners();

        // Set up header icons
        ImageView profileIcon = findViewById(R.id.ic_profile);
        ImageView notificationIcon = findViewById(R.id.ic_notification);
        ImageView settingsIcon = findViewById(R.id.ic_hexagon);

        profileIcon.setOnClickListener(v -> {
            startActivity(new Intent(DonatorMenuActivity.this, ProfileActivity.class));
        });

        notificationIcon.setOnClickListener(v -> {
            startActivity(new Intent(DonatorMenuActivity.this, NotificationActivity.class));
        });

        settingsIcon.setOnClickListener(v -> {
            startActivity(new Intent(DonatorMenuActivity.this, SettingsActivity.class));
        });

        // Load user data
        loadUserData();
    }

    private void loadUserData() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            db.collection("users").document(user.getUid()).get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                String firstName = document.getString("firstName");
                                if (firstName != null) {
                                    userGreeting.setText("HELLO, " + firstName.toUpperCase());
                                }
                            }
                        }
                    });
        }
    }

    private void setupCategoryClickListeners() {
        FrameLayout healthCategory = findViewById(R.id.healthCategory);
        FrameLayout educationCategory = findViewById(R.id.educationCategory);
        FrameLayout natureCategory = findViewById(R.id.natureCategory);
        FrameLayout animalCategory = findViewById(R.id.animalCategory);
        FrameLayout memorialCategory = findViewById(R.id.memorialCategory);
        FrameLayout nonprofitCategory = findViewById(R.id.nonprofitCategory);
        FrameLayout moreCategory = findViewById(R.id.moreCategory);

       /*  healthCategory.setOnClickListener(v -> openCategory("HEALTH"));
        educationCategory.setOnClickListener(v -> openCategory("EDUCATION"));
        natureCategory.setOnClickListener(v -> openCategory("NATURE"));
        animalCategory.setOnClickListener(v -> openCategory("ANIMAL"));
        memorialCategory.setOnClickListener(v -> openCategory("MEMORIAL"));
        nonprofitCategory.setOnClickListener(v -> openCategory("NONPROFIT"));
        moreCategory.setOnClickListener(v -> openCategory("MORE"));  */
    }

/*   private void openCategory(String category) {
        Intent intent = new Intent(DonatorMenuActivity.this, CategoryActivity.class);
        intent.putExtra("category", category);
        startActivity(intent);
    } */
}