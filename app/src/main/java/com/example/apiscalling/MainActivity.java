package com.example.apiscalling;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<UserModel> userList;

    String api="https://jsonplaceholder.typicode.com/photos";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userList=new ArrayList<>();
        getData();


    }

    private void getData() {
        RequestQueue queue = Volley.newRequestQueue(this);

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("api","onResponse" + response.toString());
                        try {
                            JSONArray array=new JSONArray(response);
                            for (int i=0;i < array.length();i++){
                                JSONObject singleObject=array.getJSONObject(i);
                                UserModel singleModel= new UserModel(singleObject.getInt("albumId"),
                                    singleObject.getInt("id"),
                                    singleObject.getString("title"),
                                    singleObject.getString("url"),
                                    singleObject.getString("thumbnailUrl")
                                );
                                userList.add(singleModel);
                            }
                            recyclerView.setAdapter(new userAdapter(MainActivity.this,userList));

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e("api","onResponse"+e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("api","onErrorResponse" + error.getLocalizedMessage());

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}