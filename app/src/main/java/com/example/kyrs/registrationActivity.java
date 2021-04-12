package com.example.kyrs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;

public class registrationActivity extends AppCompatActivity {

    public Button registrationNew;
    public TextView login, password;

    public static String testUrl;
    private static final String SERVER_URL = "http://192.168.56.1:8080/user/register";

    public String result = "";

    public void setUrl(String url) {
        testUrl = url;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        login = findViewById(R.id.new_login);
        password = findViewById(R.id.new_password);
        testUrl = "";
        addOnButton();
    }

    class ServTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String respon = null;
            String log = login.getText().toString();
            String passwrd = password.getText().toString();
            System.out.println("1");

            ///////////////////////////////
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.accumulate("login", log);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                jsonObject.accumulate("password", passwrd);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            boolean requestResult = false;
            InputStream inputStream = null;

            try {

                HttpClient httpclient = new DefaultHttpClient();

                HttpPost httpPost = new HttpPost(urls[0]);


                String json = "";

                json = jsonObject.toString();


                StringEntity se = new StringEntity(json);

                httpPost.setEntity(se);

                httpPost.setHeader("Accept", "application/json");
                httpPost.setHeader("Content-type", "application/json");

                HttpResponse httpResponse = httpclient.execute(httpPost);
                System.out.println("2");

                inputStream = httpResponse.getEntity().getContent();

                if (inputStream != null) {
                    result = (String) httpclient.execute(httpPost, new BasicResponseHandler());
                }
            } catch (Exception e) {
                Log.d("InputStream", e.getLocalizedMessage());
                requestResult = false;
            }
            return respon;
        }
    }

    public void addOnButton() {
        registrationNew = (Button) findViewById(R.id.registNew_but);

        registrationNew.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!testUrl.equals("")) {
                            new ServTask().execute(testUrl);
                        }else{
                            new ServTask().execute(SERVER_URL);
                        }
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(registrationActivity.this);
                        //new ServTask().execute();

                        a_builder.setMessage("Регистрация успешно прошла!")
                                .setCancelable(true);
                        AlertDialog alert = a_builder.create();
                        //alert.setTitle("fcsdfgvs");
                        alert.show();
                    }
                }
        );
    }
}
