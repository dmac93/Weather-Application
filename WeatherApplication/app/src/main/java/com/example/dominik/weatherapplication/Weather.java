package com.example.dominik.weatherapplication;

import java.io.Serializable;

public class Weather {

    public Location location = new Location();
    public CurrentCondition currentCondition = new CurrentCondition();
    public Temperature temperature = new Temperature();
    public Wind wind = new Wind();

    public byte[] iconData;

    public class Location implements Serializable {

        private String country;
        private String city;

        public String getCountry() {
            return country;
        }
        public void setCountry(String country) {
            this.country = country;
        }
        public String getCity() {
            return city;
        }
        public void setCity(String city) {
            this.city = city;
        }

    }

    public  class CurrentCondition {

        private String icon;
        private float humidity;

        public String getIcon() {
            return icon;
        }
        public void setIcon(String icon) {
            this.icon = icon;
        }
        public float getHumidity() {
            return humidity;
        }
        public void setHumidity(float humidity) {
            this.humidity = humidity;
        }
    }

    public  class Temperature {
        private float temp;
        public float getTemp() {
            return temp;
        }
        public void setTemp(float temp) {
            this.temp = temp;
        }
    }

    public  class Wind {
        private float speed;
        public float getSpeed() {
            return speed;
        }
        public void setSpeed(float speed) {
            this.speed = speed;
        }

    }


}