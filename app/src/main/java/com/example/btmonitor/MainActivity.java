package com.example.btmonitor;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private MenuItem menuItem;
    private BluetoothAdapter btAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        //инициализация и доступ к блютуз адаптеру
        btAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    //создание менюшки справа в углу
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        //найти кнопочку в меню и присвоить переменной
        menuItem = menu.findItem(R.id.id_bt_button);
        return super.onCreateOptionsMenu(menu);
    }
}