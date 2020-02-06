package com.rohit.techhunt.fragments;


import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rohit.techhunt.R;
import com.rohit.techhunt.Users;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class Q3Fragment extends Fragment {


    private DatabaseReference reference;
    private Button next;
    private EditText flagg;
    private ImageView img;

    public Q3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_q3, container, false);

        img = v.findViewById(R.id.lock);
        next = v.findViewById(R.id.next_level);
        TextView link = v.findViewById(R.id.linkk);
        checklockstatus();
        reference = FirebaseDatabase.getInstance().getReference("level1flag");
        flagg = v.findViewById(R.id.lv_1_flag);

        Button test = v.findViewById(R.id.submit_lv1);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference.child("lv_3flag").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (Objects.equals(dataSnapshot.getValue(), flagg.getText().toString())) {
                            Toast.makeText(getContext(), "Flag Matched ", Toast.LENGTH_SHORT).show();
                            DatabaseReference referencee = FirebaseDatabase.getInstance().getReference("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            referencee.child("question3").setValue(flagg.getText().toString());
                        } else {
                            Toast.makeText(getContext(), "Wrong Flag \n", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

//        link.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                openCrometab();
//            }
//        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new Q4Fragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return v;
    }

//    private void openCrometab() {
//        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
//        builder.setToolbarColor(Color.parseColor("#003366"));
//        CustomTabsIntent intent = builder.build();
//        intent.launchUrl(Objects.requireNonNull(getContext()), Uri.parse("https://www.google.com"));
//    }

    private void checklockstatus() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot mdataSnapshot : dataSnapshot.getChildren()) {

                    Users user = mdataSnapshot.getValue(Users.class);
                    assert user != null;
                    String level1 = user.getQuestion3();
                    if (level1.isEmpty()) {
                        img.setImageResource(R.drawable.ic_lock_black_24dp);
                        next.setVisibility(View.INVISIBLE);
                    } else {
                        img.setImageResource(R.drawable.ic_lock_open_black_24dp);
                        next.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}