package com.example.charifymain;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class NotificationActivity extends AppCompatActivity {

    private ImageView backButton, bellIcon, charifyLogo;
    private ImageButton charifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notiftab); // Use the XML layout you provided

        // Initialize UI elements
        backButton = findViewById(R.id.back_button);
        bellIcon = findViewById(R.id.bell);
        charifyLogo = findViewById(R.id.charify_logo);


        // Set up back button functionality
        backButton.setOnClickListener(v -> {
            // Navigate back to the previous activity (e.g., MainActivity or MenuActivity)
            onBackPressed(); // This method takes care of going back to the previous activity in the stack
        });

        // Set up Charify button functionality (for example, navigating to another screen)
        charifyButton.setOnClickListener(v -> {
            // Example: Navigate to the main menu or dashboard activity
            startActivity(new Intent(NotificationActivity.this, MenuActivity.class));
        });
    }
}
