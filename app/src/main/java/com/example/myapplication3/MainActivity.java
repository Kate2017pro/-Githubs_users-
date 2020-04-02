package com.example.myapplication3;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  implements OnClickListener {

    Button btok;
    Button btCan;
    TextView text1;

    CheckBox check_box;
    Switch sw;

    private static final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "найдем View-элементы");
        text1= findViewById((R.id.tvOut));
        btok= findViewById(R.id.btnOk);
        btCan=(Button) findViewById(R.id.btnCancel);

        check_box = findViewById(R.id.checkBox);
        sw=findViewById(R.id.switch1);


        //Обработчик
    /*    OnClickListener oclBtn = new OnClickListener(){
            @Override
            public void onClick(View v){
                // по id определеяем кнопку, вызвавшую этот обработчик
                switch (v.getId()) {
                    case R.id.btnOk:
                        // кнопка ОК
                        text1.setText("Нажата кнопка ОК");
                        break;
                    case R.id.btnCancel:
                        // кнопка Cancel
                        text1.setText("Нажата кнопка Cancel");
                        break;
                }
            }
        };
        btok.setOnClickListener(oclBtn);
        btCan.setOnClickListener(oclBtn);*/
        Log.d(TAG, "присваиваем обработчик кнопкам");
        btok.setOnClickListener(this);
        btCan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // по id определяем кнопку, вызвавшую этот обработчик
        Log.d(TAG, "по id определяем кнопку, вызвавшую этот обработчик");
        switch (v.getId()) {
            case R.id.btnOk:
                // кнопка ОК
                Log.d(TAG, "кнопка ОК");
                text1.setText("Нажата кнопка ОК");
                Toast.makeText(this, "Нажата кнопка ОК", Toast.LENGTH_LONG).show();
                break;
            case R.id.btnCancel:
                // кнопка Cancel
                Log.d(TAG, "кнопка Cancel");
                text1.setText("Нажата кнопка Cancel");
                Toast.makeText(this, "Нажата кнопка Cancel", Toast.LENGTH_LONG).show();
                break;
        }
    }

    // создание меню. вызыватся один раз
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub

     /*   menu.add(0, 1, 0, "add");
        menu.add(0, 2, 0, "edit");
        menu.add(0, 3, 3, "delete");
        menu.add(1, 4, 1, "copy");
        menu.add(1, 5, 2, "paste");
        menu.add(1, 6, 4, "exit");*/ //Все из xml файла будет

        getMenuInflater().inflate(R.menu.mymenu, menu);

//возвращет булеан - поазывть или нет
        return super.onCreateOptionsMenu(menu);
    }

    // обновление меню/ вызывается каждый раз перед отображением меню. Здесь мы вносим изменения в уже созданное меню, если это необходимо
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        // пункты меню с ID группы = 1 видны, если в CheckBox стоит галка
        menu.setGroupVisible(R.id.group1, sw.isChecked());
        return super.onPrepareOptionsMenu(menu);
    }


// Обработчик нажатия на пункт меню/ вызывается при нажатии пункта меню. Здесь мы определяем какой пункт меню был нажат
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        StringBuilder sb = new StringBuilder();

        // Выведем в TextView информацию о нажатом пункте меню
        sb.append("Item Menu");
        sb.append("\r\n groupId: " + String.valueOf(item.getGroupId()));
        sb.append("\r\n itemId: " + String.valueOf(item.getItemId()));
        sb.append("\r\n order: " + String.valueOf(item.getOrder()));
        sb.append("\r\n title: " + item.getTitle());
        text1.setText(sb.toString());

        return super.onOptionsItemSelected(item);
    }

}
