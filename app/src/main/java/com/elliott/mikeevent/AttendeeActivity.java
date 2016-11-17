package com.elliott.mikeevent;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AttendeeActivity extends AppCompatActivity {

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendee);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final ArrayList<Attending> attendList = new ArrayList<>();
        final ArrayList<FoodInfo> foodInfos = new ArrayList<>();

        final RequestQueue queue = Volley.newRequestQueue(this);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest
                (Request.Method.GET, "http://www.matapi.se/foodstuff", null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for (int i = 0; i < response.length(); i++) {

                                counter++;
                                JSONObject j = response.getJSONObject(i);

                                foodInfos.add(new FoodInfo(j.getString("name"),(j.getString("number"))));

                            }

                            ArrayAdapter<FoodInfo> arrayAdapter = new ArrayAdapter<>(AttendeeActivity.this,
                                    android.R.layout.simple_list_item_1, android.R.id.text1, foodInfos);

                            ListView listView = (ListView) findViewById(R.id.listView);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                    Toast.makeText(AttendeeActivity.this,"Antal produkter funna: "+ counter , Toast.LENGTH_SHORT).show();

                                    Intent i = new Intent(AttendeeActivity.this, SpecifiedDetails.class);
                                    i.putExtra("details", foodInfos.get(position).toString());
                                    i.putExtra("row", position+"");
                                    startActivity(i);

                                }
                            });

                            listView.setAdapter(arrayAdapter);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        queue.add(jsonArrayRequest);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent sendMail = new Intent(Intent.ACTION_SEND);
                sendMail.setType("text/mail");
                sendMail.putExtra(Intent.EXTRA_EMAIL, new String[]{"elliott.latt@gmail.com"});
                sendMail.putExtra(Intent.EXTRA_SUBJECT, "Names of the attendants");
                sendMail.putExtra(Intent.EXTRA_TEXT, attendList.toString());

                startActivity(Intent.createChooser(sendMail, "Välj epostprogram:"));
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        switch (id){
            case R.id.action_attendees:

                Intent intent = new Intent(AttendeeActivity.this, AttendeeActivity.class);
                startActivity(intent);
                break;
            case R.id.action_about:

                Intent i = new Intent(AttendeeActivity.this, AboutActivity.class);
                startActivity(i);

                break;
            case R.id.action_main:

                Intent intent2 = new Intent(AttendeeActivity.this, MainActivity.class);
                startActivity(intent2);
                break;

        }

        return super.onOptionsItemSelected(item);
    }


}
