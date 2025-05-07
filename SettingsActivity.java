package com.example.charifymain;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private ImageView backButton, charifyLogo, profileIcon, notificationIcon, securityIcon, helpIcon;
    private TextView userName, emailValue, contactValue, addressValue, organizationValue;
    private RelativeLayout editProfileItem, notificationItem, securityItem, helpItem;
    private ImageButton charifyButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings); // Use the settings.xml layout file

        // Initialize UI elements
        backButton = findViewById(R.id.back_button);
        charifyLogo = findViewById(R.id.charify_logo);
        profileIcon = findViewById(R.id.profile_icon);
        notificationIcon = findViewById(R.id.notification_icon);
        securityIcon = findViewById(R.id.security_icon);
        helpIcon = findViewById(R.id.help_icon);
        userName = findViewById(R.id.userName);
        emailValue = findViewById(R.id.emailValue);
        contactValue = findViewById(R.id.contactValue);
        addressValue = findViewById(R.id.addressValue);
        organizationValue = findViewById(R.id.organizationValue);
        editProfileItem = findViewById(R.id.edit_profile_item);
        notificationItem = findViewById(R.id.notification_item);
        securityItem = findViewById(R.id.security_item);
        helpItem = findViewById(R.id.help_item);
        charifyButton = findViewById(R.id.charify_bttn);

        // Set up back button functionality
        backButton.setOnClickListener(v -> {
            // Navigate to the previous screen (finish the current activity)
            onBackPressed();
        });

        // Set up charify button functionality
        charifyButton.setOnClickListener(v -> {
            // Example: Navigate to the main menu or dashboard activity
            startActivity(new Intent(SettingsActivity.this, MenuActivity.class));
        });

        // Set up item click functionality
        editProfileItem.setOnClickListener(v -> {
            // Navigate to edit profile activity
            startActivity(new Intent(SettingsActivity.this, ProfileActivity.class));
        });

        notificationItem.setOnClickListener(v -> {
            // Navigate to notification settings activity
            startActivity(new Intent(SettingsActivity.this, NotificationActivity.class));
        });

      /*  securityItem.setOnClickListener(v -> {
            // Navigate to security settings activity
            startActivity(new Intent(SettingsActivity.this, SecuritySettingsActivity.class));
        });

        helpItem.setOnClickListener(v -> {
            // Navigate to help activity
            startActivity(new Intent(SettingsActivity.this, HelpActivity.class));
        }); */
    }
}

