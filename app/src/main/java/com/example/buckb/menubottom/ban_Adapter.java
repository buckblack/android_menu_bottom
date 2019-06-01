package com.example.buckb.menubottom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ban_Adapter extends ArrayAdapter {
    Context context;
    ArrayList<ban_class> arrayList;
    public ban_Adapter(Context context,ArrayList<ban_class> lst)
    {
        super(context,R.layout.dong_ban,lst);
        this.context=context;
        arrayList=lst;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null)
            convertView= LayoutInflater.from(context).inflate(R.layout.dong_ban,null);
        TextView txt_id=convertView.findViewById(R.id.txt_id);
        TextView txt_status=convertView.findViewById(R.id.txt_status);
        ban_class b=arrayList.get(position);
        txt_id.setText(String.valueOf(b.getId()));
        txt_status.setText(b.getStatus());
        return convertView;
    }
}
