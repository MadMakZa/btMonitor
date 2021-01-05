package com.example.btmonitor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private MenuItem menuItem;
    private BluetoothAdapter btAdapter;
    private final int ENABLE_REQUEST = 15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        init();
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
//        setBtIcon();
        return super.onCreateOptionsMenu(menu);
    }

    //слушатель нажатия кнопок в менюшке
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //если нажато, то включить блутуз
        if(item.getItemId() == R.id.id_bt_button){
            //если не включен
            if(!btAdapter.isEnabled()){
                //включить
                enableBt();
            }else {
                //выключить
                btAdapter.disable();
                menuItem.setIcon(R.drawable.ic_bt_enable);

            }
        //если нажато на кнопочку меню
        } else if(item.getItemId() == R.id.id_menu){
            //запуск нового активити
            Intent intent = new Intent(MainActivity.this, BtListActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    //проверка
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ENABLE_REQUEST){

            if(resultCode == RESULT_OK){

                setBtIcon();
            }
        }
    }

    //метод смены иконки блютуза
    private void setBtIcon(){
        //если включен
        if(btAdapter.isEnabled()){
            menuItem.setIcon(R.drawable.ic_bt_disable); //показать кнопку для выкл
        }else{
            menuItem.setIcon(R.drawable.ic_bt_enable); //показать кнопку для вкл
        }
    }

    //метод включения блутуза
    private void enableBt(){
        //запустить включение адаптера
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(intent, ENABLE_REQUEST);
    }
}