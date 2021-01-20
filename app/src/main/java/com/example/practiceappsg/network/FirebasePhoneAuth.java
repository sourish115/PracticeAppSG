package com.example.practiceappsg.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.practiceappsg.Login;
import com.example.practiceappsg.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;

public class FirebasePhoneAuth {
    private Context context;
    private FirebaseAuth mAuth;
    public FirebasePhoneAuth(Context context) {
        this.context=context;
        mAuth = FirebaseAuth.getInstance();
    }

    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("AUTH", "signInWithCredential:success");
                            Intent intent = new Intent(context,MainActivity.class);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                        else {
                            // Sign in failed, display a message and update the UI
                            Log.w("AUTH", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(context, "Incorrect OTP!", Toast.LENGTH_LONG).show();
                            }

                        }
                    }
                });
    }
}
