package com.example.buckb.menubottom;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class FragmentB extends Fragment {

    ArrayList<ban_class> arrayList;
    ban_Adapter adapter;
    ListView lv_ds;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_b,container,false);
        arrayList=new ArrayList<>();
        adapter=new ban_Adapter(getActivity(),arrayList);
        lv_ds=view.findViewById(R.id.lv_ds);
        new doc_du_lieu().execute("http://192.168.56.1:81/QL_NhaHang/public/android/xembantrong/all");

        lv_ds.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ban_class b=arrayList.get(position);
                Toast.makeText(getActivity(),b.getId()+"",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    class doc_du_lieu extends AsyncTask<String,Integer,String>
    {
        @Override
        protected String doInBackground(String... strings) {
            String nd=docNoiDung_Tu_URL(strings[0]);
            return nd;
        }
        @Override
        protected void onPostExecute(String s) {
            try {
                arrayList.clear();
                JSONArray mang=new JSONArray(s);
                for(int i=0;i<mang.length();i++)
                {
                    JSONObject son=mang.getJSONObject(i);
                    arrayList.add(new ban_class(son.getInt("id"),son.getString("trang_thai")));
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
            lv_ds.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }
    private String docNoiDung_Tu_URL(String theUrl){
        StringBuilder content = new StringBuilder();
        try    {
            URL url = new URL(theUrl);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null){
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)    {
            e.printStackTrace();
        }
        return content.toString();
    }
}
