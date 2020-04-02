package com.example.gitusers;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class Detail_information extends AppCompatActivity {

    TextView user_name;
    ImageView user_img;
    TextView user_id;
    //  TextView user_location;
    //  TextView user_email;
    String str_user_name;
    String url_base = "https://api.github.com/users/";
    private static final String TAG = "myLogs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_information);

        user_name = findViewById(R.id.user_name);
        user_img = findViewById(R.id.user_avatar);
        user_id = findViewById(R.id.user_id);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            str_user_name = arguments.getString("name_user");
            user_name.setText(str_user_name);
            url_base += str_user_name;
        } else {
            Toast.makeText(getApplicationContext(), "Нет логина пользователя", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Нет логина пользователя");
            this.finish();
        }
        //Output data
        new ProgressTask().execute(url_base);
        //Output image
        new DownloadImageTask().execute(url_base);

    }

    class ProgressTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... path) {
            String content;
            try {
                content = getContent(path[0]);
            } catch (IOException ex) {
                content = ex.getMessage();
            }
            return content;
        }

        @Override
        protected void onPostExecute(String id) {
            user_id.setText(id);
            Toast.makeText(getApplicationContext(), "Данные загружены", Toast.LENGTH_SHORT).show();
        }

        private String getContent(String path) throws IOException {
            String responss = "";
            try {
                URL url = new URL(path);
                Log.d(TAG, path);
                HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();
                //  myConnection.setRequestMethod("GET");
                //myConnection.setReadTimeout(10000);
                myConnection.connect();
                if (myConnection.getResponseCode() == 200) {
                    // Success
                    Log.d(TAG, Integer.toString(myConnection.getResponseCode()));
                    InputStream responseBody = myConnection.getInputStream();
                    InputStreamReader responseBodyReader =
                            new InputStreamReader(responseBody, "UTF-8");

                    JsonReader jsonReader = new JsonReader(responseBodyReader);

                    //jsonReader.beginArray();
                    jsonReader.beginObject(); // Start processing the JSON object
                    while (jsonReader.hasNext()) { // Loop through all keys
                        String key = jsonReader.nextName(); // Fetch the next key

                        if (key.equals("id")) {
                            try {
                                responss = responss + "ID: " + jsonReader.nextString() + "\n";
                            } catch (Throwable tr) {
                                jsonReader.skipValue();
                                continue;
                            }
                            Log.d(TAG, responss);
                            continue;
                        }
                        if (key.equals("name")) {
                            try {
                                responss = responss + "Name: " + jsonReader.nextString() + "\n";
                            } catch (Throwable tr) {
                                jsonReader.skipValue();
                                continue;
                            }
                            Log.d(TAG, responss);
                            continue;
                        }
                        if (key.equals("location")) {
                            try {
                                responss = responss + "Location: " + jsonReader.nextString() + "\n";
                            } catch (Throwable tr) {
                                jsonReader.skipValue();
                                continue;
                            }
                            Log.d(TAG, responss);
                            continue;
                        }
                        if (key.equals("email")) {
                            try {
                                responss = responss + "E-mail: " + jsonReader.nextString() + "\n";
                            } catch (Throwable tr) {
                                jsonReader.skipValue();
                                //continue;
                            }
                            Log.d(TAG, responss);
                            continue;
                        }
                        if (key.equals("followers")) {
                            try {
                                responss = responss + "Followers: " + jsonReader.nextString() + "\n";
                            } catch (Throwable tr) {
                                jsonReader.skipValue();
                            }
                            Log.d(TAG, responss);
                            continue;
                        }
                        if (key.equals("following")) {
                            try {
                                responss = responss + "Following: " + jsonReader.nextString() + "\n";
                            } catch (Throwable tr) {
                                jsonReader.skipValue();
                                continue;
                            }
                            Log.d(TAG, responss);
                            continue;
                        }
                        if (key.equals("public_repos")) {
                            try {
                                responss = responss + "Public repositories: " + jsonReader.nextString() + "\n";
                            } catch (Throwable tr) {
                                jsonReader.skipValue();
                                continue;
                            }
                            Log.d(TAG, responss);
                            continue;
                        }
                        if (key.equals("created_at")) {
                            try {
                                responss = responss + "Created data: " + jsonReader.nextString() + "\n";
                            } catch (Throwable tr) {
                                jsonReader.skipValue();
                                continue;
                            }
                            Log.d(TAG, responss);
                            continue;
                        }
                        if (key.equals("blog")) {
                            try {
                                responss = responss + "Blog: " + jsonReader.nextString() + "\n";
                            } catch (Throwable tr) {
                                jsonReader.skipValue();
                                continue;
                            }
                            Log.d(TAG, responss);
                            continue;
                        }
                        if (key.equals("company")) {
                            try {
                                responss = responss + "Company: " + jsonReader.nextString() + "\n";
                            } catch (Throwable tr) {
                                jsonReader.skipValue();
                                continue;
                            }
                            Log.d(TAG, responss);
                            continue;
                        }
                        jsonReader.skipValue();
                    }

                    jsonReader.endObject();
                    jsonReader.close();
                    myConnection.disconnect();
                }
                Log.d(TAG, responss);
                return (responss);
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "Не удалось устновить соединение");
            } finally {
           /* if (reader != null) {
                reader.close();
            }*/
            }
            return (responss);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.menu_exit:
                this.finish();
                break;
            case R.id.menu_update:
                user_id.setText("");
                Toast.makeText(getApplicationContext(), "Данные обновляются", Toast.LENGTH_SHORT).show();
                new ProgressTask().execute(url_base);
                new DownloadImageTask().execute(url_base);
                break;
            case R.id.menu_about:
                Intent intent = new Intent(this, About_Activiti.class);
                startActivity(intent);
                break;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
       // ImageView bmImage;

        //Получаю по урл (из дрг метода) картинку
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                String im_url = get_url_image(urldisplay); //урл картинки
                Log.d(TAG, "kk " + im_url);
                InputStream in = new java.net.URL(im_url).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Ошибка передачи image", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        //Получаю урл картинки
        private String get_url_image(String url_st) {
            String answ = "";
            try {
                URL url = new URL(url_st);
                Log.d(TAG, url_st);
                HttpsURLConnection myConnection = (HttpsURLConnection) url.openConnection();
                //  myConnection.setRequestMethod("GET");
                myConnection.setReadTimeout(10000);
                myConnection.connect();
                if (myConnection.getResponseCode() == 200) {
                    // Success
                    Log.d(TAG, Integer.toString(myConnection.getResponseCode()));
                    InputStream responseBody = myConnection.getInputStream();
                    InputStreamReader responseBodyReader =
                            new InputStreamReader(responseBody, "UTF-8");

                    JsonReader jsonReader = new JsonReader(responseBodyReader);

                    //jsonReader.beginArray();
                    jsonReader.beginObject(); // Start processing the JSON object
                    while (jsonReader.hasNext()) { // Loop through all keys
                        String key = jsonReader.nextName(); // Fetch the next key

                        if (key.equals("avatar_url")) {
                            try {
                                answ = jsonReader.nextString();
                            } catch (Throwable tr) {
                                jsonReader.skipValue();
                                break;
                            }
                            Log.d(TAG, "Adr image:" + answ);
                            break;
                        }
                        jsonReader.skipValue();
                    }
                    //jsonReader.endObject();
                    jsonReader.close();
                    myConnection.disconnect();
                }
                return (answ);
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "Не удалось устновить соединение");
            }

            return answ;
        }

        //Вывожу картинку
        protected void onPostExecute(Bitmap result)
        {
            user_img.setImageBitmap(result);
        }
    }
}