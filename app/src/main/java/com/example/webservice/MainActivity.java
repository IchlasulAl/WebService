package com.example.webservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listAkademik;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listAkademik= (RecyclerView) findViewById (R.id.listAkademik);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        listAkademik.setLayoutManager(linearLayoutManager);
        new GetAkademikAsyncTask().execute();
    }

    private class GetAkademikAsynTask extends AsyncTask<String,String,String>{
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params){
            String respon="";
            try {
                String url = "https://sab.if-unpas.org/pertemuan_07/akademik.php?action=get_akademik";
                respon = CustomeHttp.executeHttpGet(url);
            }catch (Exception e){
                respon = e.toString();
            }
            return respon;
        }

        @Override
        protected void onPostExecute(String result){
            super.onPostExecute(result);
            try {
                Log.e("masuk","Respon result->"+result);
                JSONObject object = new JSONObject(result);
                ArrayList<HashMap<String,String>> arr = new ArrayList<>();
                if (object.getString("success").equalsIgnoreCase("1")){
                    JSONArray array = object.getJSONArray("data");
                    HashMap<String,String> map;
                    for (int i=0;i<array.length();i++){
                        JSONObject jsonObject = array.getJSONObject(i);
                        map = new HashMap<String,String>();
                        map.put("img_url",jsonObject.getString("img_url"));
                        map.put("singkatan",jsonObject.getString("singkatan"));
                        map.put("nama",jsonObject.getString("nama"));
                        map.put("url",jsonObject.getString("url"));
                        arr.add(map);
                    }
                }
                listAkademik.setAdapter(new AkademikAdapter(arr,MainActivity.this));
            }catch (Exception e){
                Log.e("Masuk","->"+e.getMessage());
            }
        }
    }
}
