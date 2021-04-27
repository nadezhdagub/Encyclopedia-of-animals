package com.example.kyrs;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class SearchPhotosActivity extends AppCompatActivity {


    public static  String EXTRA_MESSAGE = "";
    public Button imBtn, btn1, btn2, btn3, btn4, btn5;
    private ImageSwitcher imagesIs;

    private static final String SERVER_URL = "http://192.168.56.1:8080/animal/recognize";

    String result = null;

    //////////////////////////////////////////////////////////////////////////////////////
    class ServTask extends AsyncTask<Void, Void, String> {
        // Конец строки
        private String lineEnd = "\r\n";
        // Два тире
        private String twoHyphens = "--";
        // Разделитель
        private String boundary =  "----WebKitFormBoundary9xFB2hiUhzqbBQ4M";

        // Переменные для считывания файла в оперативную память
        private int bytesRead, bytesAvailable, bufferSize;
        private byte[] buffer;
        private int maxBufferSize = 1*1024*1024;

        // Путь к файлу в памяти устройства
        private String filePath;

        // Адрес метода api для загрузки файла на сервер
        public static final String API_FILES_UPLOADING_PATH = SERVER_URL;

        // Ключ, под которым файл передается на сервер
        public static final String FORM_FILE_NAME = "file";

        public ServTask(String filePath) {
            this.filePath = filePath;
        }

        public String doInBackground(Void... urls) {
            TextView text;

            try {
                // Создание ссылки для отправки файла
                URL uploadUrl = new URL(API_FILES_UPLOADING_PATH);

                // Создание соединения для отправки файла
                HttpURLConnection connection = (HttpURLConnection) uploadUrl.openConnection();

                // Разрешение ввода соединению
                connection.setDoInput(true);
                // Разрешение вывода соединению
                connection.setDoOutput(true);
                // Отключение кеширования
                connection.setUseCaches(false);

                // Задание запросу типа POST
                connection.setRequestMethod("POST");

                // Задание необходимых свойств запросу
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

                // Создание потока для записи в соединение
                DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());

                // Формирование multipart контента

                // Начало контента
                outputStream.writeBytes(twoHyphens + boundary + lineEnd);
                // Заголовок элемента формы
                outputStream.writeBytes("Content-Disposition: form-data; name=\"" +
                        FORM_FILE_NAME + "\"; filename=\"" + filePath + "\"" + lineEnd);
                // Тип данных элемента формы
                outputStream.writeBytes("Content-Type: image/jpeg" + lineEnd);
                // Конец заголовка
                outputStream.writeBytes(lineEnd);

                // Поток для считывания файла в оперативную память
                FileInputStream fileInputStream = new FileInputStream(new File(filePath));
                //Files.readAllBytes(Paths.get());

                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // Считывание файла в оперативную память и запись его в соединение
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {
                    outputStream.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }

                // Конец элемента формы
                outputStream.writeBytes(lineEnd);
                outputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Получение ответа от сервера
                int serverResponseCode = connection.getResponseCode();

                // Закрытие соединений и потоков
                fileInputStream.close();
                outputStream.flush();
                outputStream.close();
                System.out.println(serverResponseCode);

                // Считка ответа от сервера в зависимости от успеха
                if(serverResponseCode == 200) {
                    result = readStream(connection.getInputStream());
                    text = findViewById(R.id.textView3);
                    text.setText(result);
                    if (result.equalsIgnoreCase("Волк")) {
                        Information.i = 0;
                        Information_area.i = 0;
                        Information_downsizing.i = 0;
                        Information_number.i = 0;
                        Information_security.i = 0;
                    }else{
                        Information.i = 1;
                        Information_area.i = 1;
                        Information_downsizing.i = 1;
                        Information_number.i = 1;
                        Information_security.i = 1;
                    }

                    System.out.println(result);
                } else {
                    result = readStream(connection.getErrorStream());
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        // Считка потока в строку
        public String readStream(InputStream inputStream) throws IOException {
            StringBuffer buffer = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            return buffer.toString();
        }

    }
//////////////////////////////////////
    public void addOnButton(){
        imagesIs = findViewById(R.id.imageIs);
        imBtn = findViewById(R.id.buttonIm);
        btn1 = findViewById(R.id.buttonIm6);
        btn2 = findViewById(R.id.buttonIm5);
        btn3 = findViewById(R.id.buttonIm4);
        btn4 = findViewById(R.id.buttonIm3);
        btn5 = findViewById(R.id.buttonIm2);

        imagesIs.setFactory(new ViewSwitcher.ViewFactory(){
            @Override
            public View makeView(){
                ImageView imageView = new ImageView(getApplicationContext());
                return imageView;
            }
        });

        imBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
               imBtn();
            }
        });

        btn1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Information");
                        startActivity(intent);
                    }
                }
         );
        btn2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Information_downsizing");
                        startActivity(intent);
                    }
                }
        );
        btn3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Information_area");
                        startActivity(intent);
                    }
                }
        );
        btn4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Information_security");
                        startActivity(intent);
                    }
                }
        );
        btn5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(".Information_number");
                        startActivity(intent);
                    }
                }
        );

    }
    public void imBtn(){
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        final int ACTIVITY_SELECT_IMAGE = 1234;
        startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        ArrayList<Uri> imageUris = new ArrayList<>();
        switch (requestCode) {
            case 1234:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    System.out.println(selectedImage + "  45354362542");
                    imageUris.add(selectedImage);
                    imagesIs.setImageURI(imageUris.get(0));
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    cursor.close();

                    new ServTask(filePath).execute();
                }
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_photos);
        addOnButton();
    }
}
