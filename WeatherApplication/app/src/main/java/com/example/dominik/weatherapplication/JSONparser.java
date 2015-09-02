package com.example.dominik.weatherapplication;


import org.json.JSONException;
import org.json.JSONObject;

public class JSONparser {

    public static Weather getWeather(String data) throws JSONException  {
        Weather weather = new Weather();

        JSONObject jObj = new JSONObject(data);

        JSONObject sysObj = getObject("sys", jObj);
        weather.location.setCountry(getString("country", sysObj));
        weather.location.setCity(getString("name", jObj));

        JSONObject mainObj = getObject("main", jObj);
        weather.temperature.setTemp(getFloat("temp", mainObj));
        weather.currentCondition.setHumidity(getInt("humidity", mainObj));
        JSONObject wObj = getObject("wind", jObj);
        weather.wind.setSpeed(getFloat("speed", wObj));

        return weather;
    }


    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        return jObj.getJSONObject(tagName);
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

}
