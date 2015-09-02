package com.example.dominik.weatherapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;


public class CityViewActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_view);
        Intent intent = getIntent();
        String city = intent.getExtras().getString("city")+"PL";

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {
        ProgressDialog dialog ;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            String tx = getString(R.string.dialogList);
            dialog = ProgressDialog.show(CityViewActivity.this, null, tx);
        }

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ( (new Connect()).getWeatherData(params[0]));

            try {
                weather = JSONparser.getWeather(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;
        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);


            TextView cityname = (TextView) findViewById(R.id.CityName);
            TextView temp = (TextView) findViewById(R.id.Temp);
            TextView humidity = (TextView) findViewById(R.id.humidity);
            TextView windspeed = (TextView) findViewById(R.id.windspeed);

            cityname.setText(weather.location.getCity() + " " + weather.location.getCountry());
            temp.setText(Math.round((weather.temperature.getTemp()-273.15))+"°C");
            humidity.setText(getString(R.string.humidity)+" "+weather.currentCondition.getHumidity()+"%");
            windspeed.setText(getString(R.string.windspeed)+" "+weather.wind.getSpeed()+"m/s");
            dialog.dismiss();


        }
    }



}
