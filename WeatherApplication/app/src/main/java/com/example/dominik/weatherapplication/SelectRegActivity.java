package com.example.dominik.weatherapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;


public class SelectRegActivity extends Activity {

    private ArrayAdapter<String> listAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_reg);
        new getCountryList().execute("http://test4apps.keep.pl/weather/weather2.txt");

        final ListView lv = (ListView) findViewById(R.id.CityList);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) lv.getItemAtPosition(i);
                Intent intent = new Intent(getApplicationContext(), CityViewActivity.class);
                intent.putExtra("city", item);
                startActivity(intent);
            }
        });

    }

    public class getCountryList  extends AsyncTask<String, Void, String> {

        ProgressDialog dialog ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            String tx = getString(R.string.dialogList);
            dialog = ProgressDialog.show(SelectRegActivity.this, null, tx);
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpGet httpGet = new HttpGet(params[0]);
                HttpResponse response = httpClient.execute(httpGet);
                HttpEntity entity = response.getEntity();

                BufferedHttpEntity buf = new BufferedHttpEntity(entity);

                InputStream is = buf.getContent();

                BufferedReader r = new BufferedReader(new InputStreamReader(is));

                StringBuilder total = new StringBuilder();
                String line;

                listAdapter = new ArrayAdapter<>(getApplication(), R.layout.row);
                while ((line = r.readLine()) != null) {
                    listAdapter.add(line.substring(0, line.length() - 3));
                }

                String result = total.toString();
                Log.i("Get URL", "Downloaded string: " + result);
                return result;
            } catch (Exception e) {
                Log.e("Get Url", "Error in downloading: " + e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            ListView lv = (ListView) findViewById(R.id.CityList);
            super.onPostExecute(result);
            lv.setAdapter(listAdapter);
            dialog.dismiss();
        }
    }

}
