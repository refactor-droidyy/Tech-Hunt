 package com.rohit.techhunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.rohit.techhunt.fragments.Q10Fragment;
import com.rohit.techhunt.fragments.Q1Fragment;
import com.rohit.techhunt.fragments.Q2Fragment;
import com.rohit.techhunt.fragments.Q8Fragment;
import com.rohit.techhunt.fragments.Q9Fragment;
import com.rohit.techhunt.fragments.QuestionListFragment;


 public class MainActivity extends AppCompatActivity {

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         defaultFragment();

     }

     private void defaultFragment() {
         getSupportFragmentManager().
                 beginTransaction().replace(R.id.frame_layout, new Q1Fragment()).commit();
     }

     @Override
     public boolean onCreateOptionsMenu(Menu menu) {
         MenuInflater inflater = getMenuInflater();
         inflater.inflate(R.menu.menu, menu);
         return true;

     }

     @Override
     public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         switch (item.getItemId()) {
             case R.id.menu_progress:
                 getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new QuestionListFragment()).commit();
                 break;
             case R.id.menu_about:
                 startActivity(new Intent(MainActivity.this,About.class));
                 break;
         }
         return super.onOptionsItemSelected(item);
     }

     @Override
     protected void onStart() {
         super.onStart();
         if (FirebaseAuth.getInstance().getCurrentUser() == null) {
             startActivity(new Intent(MainActivity.this, Login.class));
         }

     }
 }
