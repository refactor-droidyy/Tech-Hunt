package com.rohit.techhunt;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class Register extends AppCompatActivity {

    DatabaseReference root_reference;
    private Button register;
    private EditText passwordd, emaill, username, member1, member2, member3, member4, member5;
    private FirebaseAuth auth;
    private ProgressDialog dialog;
    TextView nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register = findViewById(R.id.register_btn);
        emaill = findViewById(R.id.email_input_reg);
        passwordd = findViewById(R.id.pass_input_reg);
        username = findViewById(R.id.name_input_reg);


        member1 = findViewById(R.id.member1);
        member2 = findViewById(R.id.member2);
        member3 = findViewById(R.id.member3);
        member4 = findViewById(R.id.member4);
        member5 = findViewById(R.id.member5);

        dialog = new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        root_reference = FirebaseDatabase.getInstance().getReference();

        nav = findViewById(R.id.navigateto);
                nav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createnewacount();
            }
        });


    }

    private void createnewacount() {
        final String Eemail = emaill.getText().toString();
        String ppass = passwordd.getText().toString();
        final String usernaaam = username.getText().toString();

        final String M1 = member1.getText().toString();
        final String M2 = member2.getText().toString();
        final String M3 = member3.getText().toString();
        final String M4 = member4.getText().toString();
        final String M5 = member5.getText().toString();

        if (Eemail.contains("iiitl.ac.in")) {

            if (TextUtils.isEmpty(Eemail) || TextUtils.isEmpty(ppass) || TextUtils.isEmpty(usernaaam) || TextUtils.isEmpty(M1)
                    || TextUtils.isEmpty(M2) || TextUtils.isEmpty(M3) || TextUtils.isEmpty(M4) || TextUtils.isEmpty(M5)) {
                Toast.makeText(Register.this, "All Fields Are Necessary", Toast.LENGTH_SHORT).show();
            } else {

                dialog.setTitle("Creating New Account");
                dialog.setMessage("Please Wait While We Process Your Request");
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();


                auth.createUserWithEmailAndPassword(Eemail, ppass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    String userid = Objects.requireNonNull(auth.getCurrentUser()).getUid();

                                    root_reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                                    HashMap<String, String> hashMap = new HashMap<>();

                                    hashMap.put("id", userid);
                                    hashMap.put("teamname", usernaaam);
                                    hashMap.put("email", Eemail);
                                    hashMap.put("member1", M1);
                                    hashMap.put("member2", M2);
                                    hashMap.put("member3", M3);
                                    hashMap.put("member4", M4);
                                    hashMap.put("member5", M5);
                                    hashMap.put("question1","1");
                                    hashMap.put("question2","");
                                    hashMap.put("question3","");
                                    hashMap.put("question4","");
                                    hashMap.put("question5","");
                                    hashMap.put("question6","");
                                    hashMap.put("question7","");
                                    hashMap.put("question8","");
                                    hashMap.put("question9","");
                                    hashMap.put("question10","");


                                    root_reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Intent intent = new Intent(Register.this, Login.class);
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        startActivity(intent);
                                                        Toast.makeText(Register.this, "Please check your email for verification .....", Toast.LENGTH_SHORT).show();
                                                        dialog.dismiss();
                                                    } else {
                                                        Toast.makeText(Register.this, "" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                                                        dialog.dismiss();
                                                    }
                                                }
                                            });
                                        }
                                    });

                                } else {
                                    Toast.makeText(Register.this, "" + Objects.requireNonNull(task.getException()).toString(), Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                }

                            }
                        });
            }
        }else{
            Toast.makeText(this,"Enter Your College Email Id",Toast.LENGTH_SHORT).show();
        }
    }

}
