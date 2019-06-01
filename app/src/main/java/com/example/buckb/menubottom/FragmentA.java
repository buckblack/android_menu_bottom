package com.example.buckb.menubottom;

import android.app.Fragment;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class FragmentA extends Fragment {

    EditText txtA;
    Button btnA;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_a,container,false);
        txtA=view.findViewById(R.id.editTextA);
        btnA=view.findViewById(R.id.buttonA);

        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(),txtA.getText().toString(),Toast.LENGTH_SHORT).show();
                new doc_du_lieu().execute("http://192.168.56.1:81/QL_NhaHang/public/android/xembantrong/all");
                //xac_nhan_chinh_sua();
            }
        });






        return view;


    }

    public  void gannoidung(String noidung)
    {
        txtA.setText(noidung);
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
            Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();

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

    void xac_nhan_chinh_sua()
    {
        AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
        alert.setTitle("Thông báo!");
        alert.setIcon(R.mipmap.ic_launcher);
        alert.setMessage("Xác nhận chỉnh sửa?");
        alert.setPositiveButton("Có", new DialogInterface.OnClickListener() {//nhấn xác nhận chỉnh sửa
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Chọn có",Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("Không", new DialogInterface.OnClickListener() {//nhấn không xác nhận chỉnh sửa
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(),"Chọn không",Toast.LENGTH_SHORT).show();
            }
        });
        alert.show();
    }
}
