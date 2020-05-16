package com.project.bluejackkos;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity {

    EditText name_user;
    EditText password_user;
    Button logIn;
    TextView signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("Bluejack Kos");
        setContentView(R.layout.activity_main);

        addData();

        name_user = findViewById(R.id.name_user);
        password_user = findViewById(R.id.password_user);
        logIn = findViewById(R.id.login);
        signUp = findViewById(R.id.signup);

        logIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                if (TextUtils.isEmpty(name_user.getText())) {
//                    name_user.setError("Username Must Be Filled!");
//                }
//                if (TextUtils.isEmpty(password_user.getText())) {
//                    password_user.setError("Password Must Be Filled");
//                }
//                if((!TextUtils.isEmpty(name_user.getText())) && (!TextUtils.isEmpty(password_user.getText()))){
//                    if(matchedData()){
                        homePage();
//                    }
//                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpActivity();
            }
        });
    }

    void addData(){
        if (Helper.dataKos.size() != 0) return;
        String url = "https://bit.ly/2zd4uhX";

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0; i<response.length(); i++){
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);

                                String idKos = jsonObject.getString("id");
                                String kosName = jsonObject.getString("name");
                                String kosFacility = jsonObject.getString("facilities");
                                String price = jsonObject.getString("price");
                                String kosThumbnail = jsonObject.getString("image");
                                String kosAddress = jsonObject.getString("address");
                                String longitude = jsonObject.getString("LNG");
                                String latitude = jsonObject.getString("LAT");

                                int id = Integer.parseInt(idKos);
                                int kosPrice = Integer.parseInt(price);
                                float kosLongitude = Float.parseFloat(longitude);
                                float kosLatitude = Float.parseFloat(latitude);

                                Helper.dataKos.add(new Kos(id,kosName, kosFacility, kosPrice, kosThumbnail, kosAddress, kosLongitude, kosLatitude));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    public void homePage(){
//        for(User user : Helper.dataUser){
//            if (user.getUserName().equals(name_user.getText().toString())){
                Intent home = new Intent(this, HomePage.class);
//                home.putExtra("currentuserid",user.getUserId());
                startActivity(home);
//                return;
//            }
//        }
    }

    public void signUpActivity(){
        Intent sign = new Intent(this, SignForm.class);
        startActivity(sign);
    }

    public boolean matchedData(){
        String userNama = name_user.getText().toString();
        String userPw = password_user.getText().toString();
        for(int i=0; i<Helper.dataUser.size(); i++){
            if (Helper.dataUser.get(i).getUserName().compareTo(userNama) == 0 && Helper.dataUser.get(i).getUserPassword().compareTo(userPw) == 0) {
                return true;
            }
        }
        Toast noData = Toast.makeText(MainActivity.this, "User Unavailable", Toast.LENGTH_SHORT);
        noData.show();
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Helper.dataKos.clear();
        Helper.transaction.clear();
        Helper.dataUser.clear();
    }
}


