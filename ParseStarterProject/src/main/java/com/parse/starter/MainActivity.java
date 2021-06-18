/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

//import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.text_to_speech.v1.util.WaveUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;



public class MainActivity extends AppCompatActivity {

  Button Clogin, CsignIn,AdminSignin;
  private static final String API_KEY = "eFkY_P11tFNAhjPU-xjcQcV7R4qe67BM6gDOK8iwSYd0";
  private static final String URL = "https://api.eu-gb.text-to-speech.watson.cloud.ibm.com/instances/c08cb270-45ca-498c-b173-a2b7bd21cce1";
  private static final int BUFFER_SIZE = 1024;

  // customer log in function
  public void CusLoginClick (View view){

          String text = " Back Customer";
           WelcomeSound(text);
    Intent intent = new Intent(getApplicationContext(),CustomerLogIn.class);
    startActivity(intent);

  }

  // customer sign in function
  public void CusSignInClick(View view){

    String text = "New Customer";
    WelcomeSound(text);

    Intent intent = new Intent(getApplicationContext(),CustomerSignUp.class);
    startActivity(intent);
  }

  //admin log in functrion
  public  void adminButtonClick (View view)
  {

    String text = "Admin";
    WelcomeSound(text);
    Intent intent = new Intent(getApplicationContext(),AdminLogin.class);
    startActivity(intent);


  }




  public void WelcomeSound(final String x){

    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        //  String text = textEditText.getText().toString();
        String text = "Welcome " + x;
        if (text.length() > 0) {
          String voice = "en-GB_JamesV3Voice";
          try {
            createSoundFile(text, voice);
            playSoundFile(text + voice);
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }
    });

    thread.start();

  }



  public  void createSoundFile(String text, String voice) throws IOException {
    IamAuthenticator authenticator = new IamAuthenticator(API_KEY);
    TextToSpeech textToSpeech = new TextToSpeech(authenticator);
    textToSpeech.setServiceUrl(URL);

    SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder()
            .text(text)
            .accept("audio/mp3")
            .voice(voice)
            .build();

    InputStream inputStream = textToSpeech.synthesize(synthesizeOptions).execute().getResult();
    InputStream in = WaveUtils.reWriteWaveHeader(inputStream);

    String fileName = text + voice;
    FileOutputStream fos = getApplicationContext().openFileOutput(fileName, Context.MODE_PRIVATE);

    byte[] buffer = new byte[BUFFER_SIZE];
    int length;
    while ((length = in.read(buffer)) > 0) {
      fos.write(buffer, 0, length);
    }
    fos.close();

    in.close();
    inputStream.close();
  }

  public  void playSoundFile(String fileName) throws IOException {
    File file = new File(getApplicationContext().getFilesDir(), fileName);
    Uri fileUri = Uri.parse(file.getPath());
    MediaPlayer mediaPlayer = new MediaPlayer();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      mediaPlayer.setAudioAttributes(
              new AudioAttributes.Builder()
                      .setUsage(AudioAttributes.USAGE_MEDIA)
                      .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                      .setLegacyStreamType(AudioManager.STREAM_MUSIC)
                      .build()
      );
    } else {
      mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }
    mediaPlayer.setDataSource(getApplicationContext(), fileUri);
    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
      @Override
      public void onPrepared(MediaPlayer mp) {
        mp.start();
      }
    });
    mediaPlayer.prepareAsync();
  }





  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Clogin = (Button)findViewById(R.id.customerLogInButton);
    CsignIn = findViewById(R.id.customerSignINButton);
    AdminSignin = findViewById(R.id.AdminLoginButton);
    setTitle("Recycler App");

    
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}