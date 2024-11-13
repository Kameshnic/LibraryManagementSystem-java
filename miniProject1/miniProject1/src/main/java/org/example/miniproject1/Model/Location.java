package org.example.miniproject1.Model;

public class Location {
    String name;
    double latitude;
    double longitude;
    String location;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Location(String name, double latitude,double longitude,String location) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.location=location;
    }
    public double[] getCoordinates() {
        return new double[]{latitude, longitude};
    }
}
