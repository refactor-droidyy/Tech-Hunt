package com.rohit.techhunt.fragments;


import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rohit.techhunt.R;
import com.rohit.techhunt.Users;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionListFragment extends Fragment implements View.OnClickListener {

    private TextView level1, level2, level3, level4, level5, level6, level7, level8, level9, level10,team_name;
    private ImageView imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10;

    public QuestionListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.fragment_question_list, null);

        level1 = v.findViewById(R.id.lv1);
        level2 = v.findViewById(R.id.lv2);
        level3 = v.findViewById(R.id.lv3);
        level4 = v.findViewById(R.id.lv4);
        level5 = v.findViewById(R.id.lv5);
        level6 = v.findViewById(R.id.lv6);
        level7 = v.findViewById(R.id.lv7);
        level8 = v.findViewById(R.id.lv8);
        level9 = v.findViewById(R.id.lv9);
        level10 = v.findViewById(R.id.lv10);
        team_name = v.findViewById(R.id.team_name);

        imageView1 = v.findViewById(R.id.img_lv1);
        imageView2 = v.findViewById(R.id.img_lv2);
        imageView3 = v.findViewById(R.id.img_lv3);
        imageView4 = v.findViewById(R.id.img_lv4);
        imageView5 = v.findViewById(R.id.img_lv5);
        imageView6 = v.findViewById(R.id.img_lv6);
        imageView7 = v.findViewById(R.id.img_lv7);
        imageView8 = v.findViewById(R.id.img_lv8);
        imageView9 = v.findViewById(R.id.img_lv9);
        imageView10 = v.findViewById(R.id.img_lv10);

//        checklockstatus(imageView1);
//        checklockstatus(imageView2);
//        checklockstatus(imageView3);
//        checklockstatus(imageView4);
//        checklockstatus(imageView5);
//        checklockstatus(imageView6);
//        checklockstatus(imageView7);
//        checklockstatus(imageView8);
//        checklockstatus(imageView9);
//        checklockstatus(imageView10);


//
//        level1.setOnClickListener(this);
//        level2.setOnClickListener(this);
//        level3.setOnClickListener(this);
//        level4.setOnClickListener(this);
//        level5.setOnClickListener(this);
//        level6.setOnClickListener(this);
//        level7.setOnClickListener(this);
//        level8.setOnClickListener(this);
//        level9.setOnClickListener(this);
//        level10.setOnClickListener(this);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot mdataSnapshot : dataSnapshot.getChildren()) {
                        Users user = mdataSnapshot.getValue(Users.class);
                        assert user != null;
                        String team_nae = user.getTeamname();
                        team_name.setText(team_nae);

                        String level1 = user.getQuestion1();
                        if(level1.isEmpty()){
                            imageView1.setImageResource(R.drawable.ic_lock_black_24dp);
                        }else{
                            imageView1.setImageResource(R.drawable.ic_lock_open_black_24dp);
                        }

                        String level2 = user.getQuestion2();
                        if(level2.isEmpty()){
                            imageView2.setImageResource(R.drawable.ic_lock_black_24dp);
                        }else{
                            imageView2.setImageResource(R.drawable.ic_lock_open_black_24dp);
                        }

                        String level3 = user.getQuestion3();
                        if(level3.isEmpty()){
                            imageView3.setImageResource(R.drawable.ic_lock_black_24dp);
                        }else{
                            imageView3.setImageResource(R.drawable.ic_lock_open_black_24dp);
                        }

                        String level4 = user.getQuestion4();
                        if(level4.isEmpty()){
                            imageView4.setImageResource(R.drawable.ic_lock_black_24dp);
                        }else{
                            imageView4.setImageResource(R.drawable.ic_lock_open_black_24dp);
                        }

                        String level5 = user.getQuestion5();
                        if(level5.isEmpty()){
                            imageView5.setImageResource(R.drawable.ic_lock_black_24dp);
                        }else{
                            imageView5.setImageResource(R.drawable.ic_lock_open_black_24dp);
                        }

                        String level6 = user.getQuestion6();
                        if(level6.isEmpty()){
                            imageView6.setImageResource(R.drawable.ic_lock_black_24dp);
                        }else{
                            imageView6.setImageResource(R.drawable.ic_lock_open_black_24dp);
                        }

                        String level7 = user.getQuestion7();
                        if(level7.isEmpty()){
                            imageView7.setImageResource(R.drawable.ic_lock_black_24dp);
                        }else{
                            imageView7.setImageResource(R.drawable.ic_lock_open_black_24dp);
                        }

                        String level8 = user.getQuestion8();
                        if(level8.isEmpty()){
                            imageView8.setImageResource(R.drawable.ic_lock_black_24dp);
                        }else{
                            imageView8.setImageResource(R.drawable.ic_lock_open_black_24dp);
                        }

                        String level9 = user.getQuestion9();
                        if(level9.isEmpty()){
                            imageView9.setImageResource(R.drawable.ic_lock_black_24dp);
                        }else{
                            imageView9.setImageResource(R.drawable.ic_lock_open_black_24dp);
                        }

                        String level10 = user.getQuestion10();
                        if(level10.isEmpty()){
                            imageView10.setImageResource(R.drawable.ic_lock_black_24dp);
                        }else{
                            imageView10.setImageResource(R.drawable.ic_lock_open_black_24dp);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        return v;
    }

    @Override
    public void onClick(View view) {
        if(level1 == view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, new Q1Fragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else if(level2 == view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, new Q2Fragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else if(level3 == view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, new Q3Fragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if(level4 == view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, new Q4Fragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if(level5 == view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, new Q5Fragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }else if(level6 == view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, new Q6Fragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else if(level7 == view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, new Q7Fragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if(level8 == view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, new Q8Fragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
        else if(level9 == view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, new Q9Fragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if(level10 == view) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_layout, new Q10Fragment());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }



}
