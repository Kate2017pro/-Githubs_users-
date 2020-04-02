package com.example.gitusers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static final String TAG = "myLogs";

    Button btn_det;
    TextView inf_user;
    // Handler handler;
    List<String> users1 = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "найдем View-элементы");

     //   btn_det = findViewById(R.id.detail1);
     //   inf_user = findViewById(R.id.inf_users1);
     //   btn_det.setOnClickListener(this);

        recyclerView=findViewById(R.id.recycle_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new GetListUsers().execute("https://api.github.com/users");

      /*  handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                String text = (String) msg.obj;
                inf_user.setText(text);
            }
        };*/
    }

    class GetListUsers extends AsyncTask<String, Void, Void> {
        List<String> users = new ArrayList<>();
        List<UsersClass> userclasslist = new ArrayList<>();

        protected Void doInBackground(String... urls) {
            String url_adr = urls[0];
            URL githubEndpoint = null;
            try {

                githubEndpoint = new URL(url_adr);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Log.d(TAG, "Не удалось поделючиться к сайту");
            }
            // Create connection
            HttpsURLConnection myConnection = null;
            try {
                myConnection = (HttpsURLConnection) githubEndpoint.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "Не удалось устновить соединение");
            }
            myConnection.setRequestProperty("Accept",
                    "application/vnd.github.v3+json");
                       /* myConnection.setRequestProperty("Contact-Me",
                                "hathibelagal@example.com");*/
            try {
                if (myConnection.getResponseCode() == 200) {
                    // Success
                    // Further processing here
                    InputStream responseBody = myConnection.getInputStream();
                    InputStreamReader responseBodyReader =
                            new InputStreamReader(responseBody, "UTF-8");

                    JsonReader jsonReader = new JsonReader(responseBodyReader);
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        users.add(readMessage(jsonReader));
                    }
                    jsonReader.endArray();
                  /*  Message msg = new Message();
                    // в users хранятся все имена юзеров
                    msg.obj = users.get(1);
                    handler.sendMessage(msg);*/
                    Log.d(TAG, "Data download");
                    jsonReader.close();

                } else {
                    // Error handling code goes here
                    Log.d(TAG, "Не удалось получить ответ не равный 200. ошибка сервера");
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.d(TAG, "Не удалось получить ответ");
            }
            myConnection.disconnect();
            return null;
        }

        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);

            //TextView inf_user;
            // inf_user = findViewById(R.id.inf_users1);
        //    inf_user.setText(users.get(0));
          //  inf_user.setText(userclasslist.get(1).getId());
            users1 = users;

            final DataAdapter dataAdapter = new DataAdapter(userclasslist, MainActivity.this);
            recyclerView.setAdapter(dataAdapter);



        }

        private String readMessage(JsonReader reader) throws IOException {

            String text = null;
         //   UsersClass user;
            String id = null;

            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();

                if (name.equals("login")) {
                    text = reader.nextString();
                    continue;
                }
                if (name.equals("id")) {
                    id = reader.nextString();
                    continue;
                }
                    reader.skipValue();
            }
            reader.endObject();
      /*  Message mg=new Message();
        mg.obj=text;
        return mg;*/
      userclasslist.add(new UsersClass(text,id));
            return text;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        //возвращет булеан - поазывть или нет
        return super.onCreateOptionsMenu(menu);
    }

    // Обработчик нажатия на пункт меню/ вызывается при нажатии пункта меню. Здесь мы определяем какой пункт меню был нажат
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.menu_exit:
                this.finish();
                break;
            case R.id.menu_about:
                Log.d(TAG, "Перключение экрана");
                Intent intent = new Intent(this, About_Activiti.class);
                startActivity(intent);
                break;
            case R.id.menu_update:
                Toast.makeText(getApplicationContext(), "Данные обновляются", Toast.LENGTH_SHORT).show();
                new GetListUsers().execute("https://api.github.com/users");
                break;
        }
        //  inf_user.setText(users.get(4));
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        /*   case R.id.detail1:

                // inf_user.setText(users.get(3));
                Intent intent = new Intent(this, Detail_information.class);
                intent.putExtra("name_user", users1.get(1));
                startActivity(intent);
                break;*/
        }
    }
}
