package com.example.kyrs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Information_downsizing extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "";
    public static int i;
    public TextView text;
    public static String SERVER_URLB = "http://192.168.56.1:8080/animal/infb_downsizing";
    public static String SERVER_URLV = "http://192.168.56.1:8080/animal/infv_downsizing";

    public static String  result = "";

    public void addOnButton(){

        class ServTask extends AsyncTask<String, Void, String> {
            private TextView text;
            @Override
            protected String doInBackground(String... urls) {
                OkHttpClient client = new OkHttpClient.Builder()
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .build();
                RequestBody body =
                        RequestBody.create(MediaType.parse("application/json"), "");
                System.out.println(urls[0]);
                Request request = new Request.Builder()
                        .url(urls[0])
                        .post(body)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    try {
                        result = (Objects.requireNonNull(response.body()).string());
                        System.out.println(result+"           9999");
                        return result;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                return result;
            }
        }
        TestUrl testUrl = new TestUrl();
        new ServTask().execute();
        if(!testUrl.testUrl.equals("")) {
            new ServTask().execute(testUrl.testUrl);
        }else{  if (i == 0) {
            new ServTask().execute(SERVER_URLV);
        }else{
            new ServTask().execute(SERVER_URLB);
        }
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        text.setText(result);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information);
        text = findViewById(R.id.textInf);
        addOnButton();
        if (i == 0) {
            System.out.println("волчар");
        }else{
            System.out.println("медведь");
        }
    }
}
