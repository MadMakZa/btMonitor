package com.example.btmonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.btmonitor.adapter.BtAdapter;
import com.example.btmonitor.adapter.ListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BtListActivity extends AppCompatActivity {

    private ListView listView;
    private BtAdapter adapter;
    private BluetoothAdapter btAdapter;
    private List<ListItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bt_list);
        init();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //стандартная кнопка стрелочка назад на экшн баре сверху слева
        if (item.getItemId() == android.R.id.home){
            //закрыть активити
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void init(){
        ActionBar ab = getSupportActionBar();
        if (ab == null) return;
        ab.setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.listView);
        btAdapter = BluetoothAdapter.getDefaultAdapter();
        list = new ArrayList<>();

        adapter = new BtAdapter(this, R.layout.bt_list_item, list);
        listView.setAdapter(adapter);
    }

    //найденные девайсы и передача его в список
    private void getPairedDevices(){
        Set<BluetoothDevice> pairedDevices = btAdapter.getBondedDevices();

        if (pairedDevices.size() > 0){
            list.clear(); //очистить
            for (BluetoothDevice device : pairedDevices){
                ListItem item = new ListItem();
                item.setBtName(device.getName());
                item.setBtMac(device.getAddress()); //mac address
                list.add(item);
            }
            adapter.notifyDataSetChanged(); //сказать адаптеру, что данные надо обновить
        }
    }
}