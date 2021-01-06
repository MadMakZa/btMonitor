package com.example.btmonitor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.btmonitor.adapter.BtAdapter;
import com.example.btmonitor.adapter.ListItem;

import java.util.ArrayList;
import java.util.List;

public class BtListActivity extends AppCompatActivity {

    private ListView listView;
    private BtAdapter adapter;

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

        //временный список
        List<ListItem> list = new ArrayList<>();
        ListItem item = new ListItem();
        item.setBtName("BT 123456");
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        list.add(item);
        adapter = new BtAdapter(this, R.layout.bt_list_item, list);
        listView.setAdapter(adapter);
    }
}