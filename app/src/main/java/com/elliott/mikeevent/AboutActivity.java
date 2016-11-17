package com.elliott.mikeevent;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            InputStream imgStream = getAssets().open("mommo.jpg");
            Drawable draw = Drawable.createFromStream(imgStream, null);

            ImageView iView = (ImageView) findViewById(R.id.imageView2);
            iView.setImageDrawable(draw);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement

        switch (id){
            case R.id.action_attendees:
                Intent intent = new Intent(AboutActivity.this, AttendeeActivity.class);
                startActivity(intent);
                break;
            case R.id.action_about:

                Intent i = new Intent(AboutActivity.this, AboutActivity.class);
                startActivity(i);

                break;
            case R.id.action_main:
                Intent intent2 = new Intent(AboutActivity.this, MainActivity.class);
                startActivity(intent2);
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
