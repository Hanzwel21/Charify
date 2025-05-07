package com.example.charifymain;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class OrgRegistrationActivity extends AppCompatActivity {

    private EditText firstNameInput, lastNameInput, usernameInput, mobileInput, emailInput, passwordInput, confirmPasswordInput;
    private Button registerButton;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.org_registration); // Use the updated org_registration.xml layout file

        // Initialize Firebase Auth and Firestore
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Initialize UI elements
        firstNameInput = findViewById(R.id.first_name_input);
        lastNameInput = findViewById(R.id.last_name_input);
        usernameInput = findViewById(R.id.crt_usrn_input);
        mobileInput = findViewById(R.id.mbln_input);
        emailInput = findViewById(R.id.email_input);
        passwordInput = findViewById(R.id.psswrd_input);
        confirmPasswordInput = findViewById(R.id.cpsswrd_input);
        registerButton = findViewById(R.id.rgst_button);

        // Set up register button functionality
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerOrganization();
            }
        });

        // Set up back button functionality
        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void registerOrganization() {
        final String firstName = firstNameInput.getText().toString().trim();
        final String lastName = lastNameInput.getText().toString().trim();
        final String username = usernameInput.getText().toString().trim();
        final String mobile = mobileInput.getText().toString().trim();
        final String email = emailInput.getText().toString().trim();
        final String password = passwordInput.getText().toString().trim();
        final String confirmPassword = confirmPasswordInput.getText().toString().trim();

        // Validate inputs
        if (TextUtils.isEmpty(firstName)) {
            firstNameInput.setError("First name is required");
            return;
        }

        if (TextUtils.isEmpty(lastName)) {
            lastNameInput.setError("Last name is required");
            return;
        }

        if (TextUtils.isEmpty(username)) {
            usernameInput.setError("Username is required");
            return;
        }

        if (TextUtils.isEmpty(mobile)) {
            mobileInput.setError("Mobile number is required");
            return;
        }

        if (TextUtils.isEmpty(email)) {
            emailInput.setError("Email is required");
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailInput.setError("Please enter a valid email");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            passwordInput.setError("Password is required");
            return;
        }

        if (TextUtils.isEmpty(confirmPassword)) {
            confirmPasswordInput.setError("Confirm password is required");
            return;
        }

        if (!password.equals(confirmPassword)) {
            confirmPasswordInput.setError("Passwords do not match");
            return;
        }

        // Create a new Firebase user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                // Store organization data in Firestore
                                Map<String, Object> orgData = new HashMap<>();
                                orgData.put("firstName", firstName);
                                orgData.put("lastName", lastName);
                                orgData.put("username", username);
                                orgData.put("mobile", mobile);
                                orgData.put("email", email);
                                orgData.put("password", password);
                                orgData.put("userType", "organization"); // Mark this as an organization
                                orgData.put("approved", false); // Admin needs to approve

                                db.collection("organizations").document(user.getUid())
                                        .set(orgData)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(OrgRegistrationActivity.this, "Application submitted for approval", Toast.LENGTH_SHORT).show();
                                                    // Redirect to another activity (e.g., MenuActivity)
                                                    startActivity(new Intent(OrgRegistrationActivity.this, MenuActivity.class));
                                                    finish();
                                                } else {
                                                    Toast.makeText(OrgRegistrationActivity.this, "Failed to save organization data", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                        } else {
                            Toast.makeText(OrgRegistrationActivity.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Navigate back to the previous activity (e.g., ChooseAccountActivity)
        startActivity(new Intent(this, ChooseAccountActivity.class));
        finish();
    }
}
