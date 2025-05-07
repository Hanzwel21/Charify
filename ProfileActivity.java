package com.example.charifymain;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private ImageView notificationIcon, icHexagon, backButton, profileAvatar, profileImage;
    private TextView userName, emailValue, contactValue, addressValue, organizationValue;
    private LinearLayout userInfoLayout;
    private ImageButton charifyButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_layout); // Use profile_layout.xml

        // Initialize UI elements
        notificationIcon = findViewById(R.id.notificationIcon);
        icHexagon = findViewById(R.id.ic_hexagon);
        backButton = findViewById(R.id.back_button);
        profileAvatar = findViewById(R.id.profileAvatar);
        profileImage = findViewById(R.id.profile);
        userName = findViewById(R.id.userName);
        emailValue = findViewById(R.id.emailValue);
        contactValue = findViewById(R.id.contactValue);
        addressValue = findViewById(R.id.addressValue);
        organizationValue = findViewById(R.id.organizationValue);
        userInfoLayout = findViewById(R.id.userInfoLayout);
        charifyButton = findViewById(R.id.charify_bttn);

        // Set up back button functionality
        backButton.setOnClickListener(v -> {
            // Navigate to the previous screen
            finish(); // Close the current activity
        });

        // Set up Charify button functionality
        charifyButton.setOnClickListener(v -> {
            // Handle Charify button click
            // Navigate to another activity or show some action
            // Example: startActivity(new Intent(ProfileActivity.this, SomeOtherActivity.class));
        });

        // Set up user information display
        displayUserInformation();
    }

    private void displayUserInformation() {
        // In a real application, you would load this information from Firebase or another database
        userName.setText("PIO JERICHO AMODO"); // Example user name
        emailValue.setText("piojerico_amodo@gmail.com");
        contactValue.setText("0969-424-7890");
        addressValue.setText("Laoag City, Ilocos Norte");
        organizationValue.setText("None");

        // You could set the profile image or avatar dynamically as well
        // For example:
        // profileAvatar.setImageResource(R.drawable.profile_avatar_image);
        // profileImage.setImageResource(R.drawable.profile_image);
    }
}