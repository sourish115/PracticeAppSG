package com.example.practiceappsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practiceappsg.network.FirebasePhoneAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class Login extends AppCompatActivity {
    private EditText etPhoneNumber, etPass;
    private TextView tvTimer;
    private String verificationCode;
    private CountDownTimer cTimer;
    private FirebasePhoneAuth fAuth;
    public String phoneNumber, otp;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = findViewById(R.id.verify);
        Button btnReqOTP = findViewById(R.id.generate_otp);
        etPhoneNumber = findViewById(R.id.outlined_exposed_dropdown_editable);
        etPass = findViewById(R.id.et_otp);
        tvTimer = findViewById(R.id.counter);
        mAuth = FirebaseAuth.getInstance();
        fAuth = new FirebasePhoneAuth(Login.this);

        btnReqOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumber = etPhoneNumber.getText().toString();

                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber(phoneNumber)       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                .setActivity(Login.this)                 // Activity (for callback binding)
                                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        Toast.makeText(getApplicationContext(),"Verified",Toast.LENGTH_SHORT).show();
                                        fAuth.signInWithPhoneAuthCredential(phoneAuthCredential);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        Toast.makeText(getApplicationContext(),"FAILED",Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(s, forceResendingToken);
                                        verificationCode = s;
                                        countDown(tvTimer);
                                        Toast.makeText(getApplicationContext(),"Code Sent!",Toast.LENGTH_SHORT).show();
                                    }
                                })          // OnVerificationStateChangedCallbacks
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otp = etPass.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationCode,otp);
                fAuth.signInWithPhoneAuthCredential(credential);
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(cTimer!=null){
            cTimer.cancel();
        }
    }

    private void countDown(TextView textView){
        cTimer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("00:"+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                textView.setText("Resend?");
            }
        };cTimer.start();

    }
}