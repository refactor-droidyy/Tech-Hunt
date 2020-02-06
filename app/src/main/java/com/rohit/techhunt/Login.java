package com.rohit.techhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {

    private ProgressDialog loading;
    private FirebaseAuth mauth;
    private Button signin;
    private EditText uuser, ppaswrd;
    TextView register ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin = findViewById(R.id.signin_btn);
        uuser = findViewById(R.id.email_input_log);
        ppaswrd = findViewById(R.id.pass_input_log);
        mauth = FirebaseAuth.getInstance();

        register = findViewById(R.id.need_account);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Register.class));
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }
        });


    }

    private void signin() {
        String Email = uuser.getText().toString();
        String pass = ppaswrd.getText().toString();

        loading = new ProgressDialog(this);
        loading.setTitle("Logging In...");
        loading.setMessage("Please Wait .....");


        if (TextUtils.isEmpty(Email) || TextUtils.isEmpty(pass)) {
            Toast.makeText(Login.this, "All Fields Are Required...", Toast.LENGTH_SHORT).show();
        } else {
            loading.show();

            mauth.signInWithEmailAndPassword(Email, pass).
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).isEmailVerified()) {
                                    Intent intent = new Intent(Login.this, MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    loading.dismiss();
                                    finish();
                                } else {
                                    loading.dismiss();
                                    Toast.makeText(Login.this, "Please Verify Your email first", Toast.LENGTH_SHORT).show();

                                }
                            } else {

                                Toast.makeText(Login.this, "" + Objects.requireNonNull(task.getException()).toString(), Toast.LENGTH_SHORT).show();
                                loading.dismiss();
                            }

                        }
                    });
        }
    }
}
