
package com.example.charifymain;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class OrganizationDashboardActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private TextView orgNameText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.organization_profile); // You'll need to create this layout

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        orgNameText = findViewById(R.id.org_sub_text);

        // Check if organization is approved
        checkApprovalStatus();

        // Set up other UI elements and click listeners as needed
    }

    private void checkApprovalStatus() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            db.collection("organizations").document(user.getUid()).get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Boolean approved = document.getBoolean("approved");
                                String orgName = document.getString("orgName");

                                if (orgName != null) {
                                    orgNameText.setText(orgName);
                                }

                                if (approved == null || !approved) {
                                    Toast.makeText(this, "Your application is still under review", Toast.LENGTH_LONG).show();
                                    // Optionally restrict certain functionality until approved
                                }
                            }
                        }
                    });
        }
    }
}

