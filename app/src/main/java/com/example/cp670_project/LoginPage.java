package com.example.cp670_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.VideoView;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.util.ArrayList;

public class LoginPage extends AppCompatActivity {
    ArrayList<Account> userAccountList;
    EditText inputtedUsername, inputtedPassword;

    //variables usedd by video background for login
    VideoView videoView;
    int mCurrentVideoPosition;
    MediaPlayer mMediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // Initializes class variables
        userAccountList = new ArrayList<>();
        inputtedUsername = findViewById(R.id.username_input);
        inputtedPassword = findViewById(R.id.password_input);

        // Creates dummy account (username & password 'admin')
        initAdminAccount();

        byte[] b = "admin".getBytes();
        Log.i("TEST123", String.valueOf(b.hashCode()));
        Log.i("TEST123", String.valueOf(b.hashCode()));



        //Code to initialize variables and run video in a look. Should later on be
        //should later on be converted to a method
        videoView = findViewById(R.id.loginVid);
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.wovid4);
        videoView.start();


        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mMediaPlayer = mediaPlayer;
                // We want our video to play over and over so we set looping to true.
                mMediaPlayer.setLooping(true);
                // We then seek to the current posistion if it has been set and play the video.
                if (mCurrentVideoPosition != 0) {
                    mMediaPlayer.seekTo(mCurrentVideoPosition);
                    mMediaPlayer.start();
                }
            }
        }); // of video login


    }

    // Creates dummy account (username & password 'admin')
    public void initAdminAccount() {
        String dummyUsername = "admin";
        String dummyPassword = "admin";
        String dummySalt = "admin";
        String dummyHashedSaltedPassword = getHashedSaltedPassword(dummyPassword, dummySalt);

        // Adds dummy account to userAccountList
        Account dummyAccount = new Account(dummyUsername, dummySalt, dummyHashedSaltedPassword);
        userAccountList.add(dummyAccount);
    }

    // Constructs hashed salted password using MD5 hash
    public String getHashedSaltedPassword(String password, String salt) {
        return salt;
        // TODO: Implement this function using a cryptography library


//        String hashedSaltedPassword = String.valueOf((password + salt).getBytes().hashCode());
    }

    // Called when user clicks "Log In" button
    public void loginButtonClicked(View view) {
        // Extracts username & password from input
        String username = inputtedUsername.getText().toString();
        String password = inputtedPassword.getText().toString();

        // Clears username & password input
        inputtedUsername.setText("");
        inputtedPassword.setText("");

        // Attempts to find
        Account account = findAccountByUsername(username);
        if (account == null) {
            Log.i("TEST123", "1");

            return;
        }
        Log.i("TEST123", "2");
        Log.i("TEST123", account.getSalt());

        // If account is found, tries to login
        String hashedSaltedPassword = getHashedSaltedPassword(password, account.getSalt());
        Log.i("TEST123", hashedSaltedPassword);
        Log.i("TEST123", account.getHashedSaltedPassword());

        if (account.getHashedSaltedPassword().equals(hashedSaltedPassword)) {
            login();
        }
    }

    public Account findAccountByUsername(String username) {
        for (Account account : userAccountList) {
            if (account.getUsername().equals(username)) {
                return account;
            }
        }

        return null;
    }

    public void login() {
        Meal[] meals = new Meal[0];
        Exercise[] exercises = new Exercise[0];
        CustomExercise[] customExercises = new CustomExercise[0];
        Account newAcc = new Account("", 25, 1.75, 150, "mikesadowski@gmail.com", "123", "123", meals, exercises, customExercises);

        Intent intent = new Intent(LoginPage.this, MainHub.class);
        intent.putExtra("Account", newAcc);
        startActivity(intent);
    }


    @Override
    protected void onPause() {
        super.onPause();
        mCurrentVideoPosition = mMediaPlayer.getCurrentPosition();
        videoView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Restart the video when resuming the Activity
        videoView.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // When the Activity is destroyed, release our MediaPlayer and set it to null.
        mMediaPlayer.release();
        mMediaPlayer = null;

    }



}
