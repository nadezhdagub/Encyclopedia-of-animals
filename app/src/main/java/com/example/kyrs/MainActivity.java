package com.example.kyrs;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public Button registration, mainMenu;
    public TextView login, password;

    public static String resul = "noo";

    public static String testUrl;
    public static  String SERVER_URL = "http://localhost:8080/user/login";

    public void setUrl(String url) {
        testUrl = url;
    }


    public class ServTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
                System.out.println("0");
            String log = login.getText().toString();
            String passwrd = password.getText().toString();
            ///////////////////////////////
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();
            RequestBody body =
                    RequestBody.create(MediaType.parse("application/json"), "");
            System.out.println(urls[0]);
             System.out.println("1");
            Request request = new Request.Builder()
                    .url(urls[0])
                    .addHeader("Authorization", Credentials.basic(log, passwrd))
                    .post(body)
                    .build();
             System.out.println("2");
            try {
                Response response = client.newCall(request).execute();
                 System.out.println("3");
                try {
                     System.out.println("4");
                    resul = (Objects.requireNonNull(response.body()).string());
                     System.out.println("5");
                    if (!resul.equalsIgnoreCase("logged")) {
                             System.out.println("6");
                        resul = "no";
                    }
                    System.out.println(resul+"           9999");
                    return resul;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            return "";
        }
    }

    public void addOnButton(){
        registration = (Button)findViewById(R.id.registration_but);
        mainMenu = (Button)findViewById(R.id.entrance_but);


        mainMenu.setOnClickListener(
                new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(MainActivity.this);

                        if(!testUrl.equals("")) {
                                System.out.println(testUrl + "   1");
                            new ServTask().execute(testUrl);
                        }else{    System.out.println(SERVER_URL + "    1");
                            new ServTask().execute(SERVER_URL);
                        }
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        System.out.println(resul.equalsIgnoreCase("logged"));
                        if (resul.equalsIgnoreCase("logged")) {

///////////////////////////////////
                            a_builder.setMessage("Вход успешен!")
                                    .setCancelable(false)
                                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                            Intent intent = new Intent(".MainMenuActivity");
                                            startActivity(intent);
                                        }
                                    });
                            AlertDialog alert = a_builder.create();
                            alert.show();
                        } else {

                        }
                        System.out.println(resul);
                    }
                }
        );

        registration.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".registrationActivity");
                        startActivity(intent);
                    }
                }
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        testUrl = "";
        addOnButton();
    }

}
