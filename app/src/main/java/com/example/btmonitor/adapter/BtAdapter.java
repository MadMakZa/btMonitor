package com.example.btmonitor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.btmonitor.R;

import java.util.List;

/**
 * Адаптер для заполнения листа найденных устройств
 */
public class BtAdapter extends ArrayAdapter<ListItem> {

    private List<ListItem> mainList;

    //constructor
    public BtAdapter(@NonNull Context context, int resource, List<ListItem> btList) {
        super(context, resource, btList);

        mainList = btList;
    }

    //заполнение списка устройствами
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null){
            viewHolder = new ViewHolder();
            //надуть элемент вью
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bt_list_item, null, false);
            viewHolder.tvBtName = convertView.findViewById(R.id.tvBtName);
            viewHolder.chBtSelected = convertView.findViewById(R.id.checkBox);
            convertView.setTag(viewHolder);

        } else {
           viewHolder = (ViewHolder) convertView.getTag();

        }

        viewHolder.tvBtName.setText(mainList.get(position).getBtName());
        viewHolder.chBtSelected.setChecked(true);

        return convertView;
    }
    //будет хранить инициилизированные (найденные устройства)
    static class ViewHolder{

        TextView tvBtName;
        CheckBox chBtSelected;
    }
}
