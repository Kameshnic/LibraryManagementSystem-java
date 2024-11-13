package org.example.miniproject1.Service;

import org.example.miniproject1.Model.Location;
import org.example.miniproject1.Model.LocationHelper;
import org.example.miniproject1.Util.DBConnection;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LocationService {
    public double[] getLocation(String address) {
        double lat=0.0;
        double long1=0.0;
        try {
            String urlString = "https://nominatim.openstreetmap.org/search?q=" + address.replace(" ", "+") + "&format=json&limit=1";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            connection.disconnect();
            JSONArray jsonArray = new JSONArray(content.toString());
            if (jsonArray.length() > 0) {
                JSONObject jsonObject = jsonArray.getJSONObject(0);
                lat= Double.parseDouble(jsonObject.getString("lat"));
                long1= Double.parseDouble(jsonObject.getString("lon"));
            } else {
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new double[]{lat,long1};
    }
    public ArrayList<Location> getAllStudentsLocation(String exclude) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT username,location FROM students;";
        ArrayList<Location> studentsLocation = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("username");
                String loc = rs.getString("location");
                if (!name.equals(exclude)) {
                    double[] latLong = getLocation(loc);
                    studentsLocation.add(new Location(name, latLong[0], latLong[1],loc));
                }
            }
        }
        return studentsLocation;
    }
    public ArrayList<Location> getAllAuthorLocation() throws SQLException {
        Connection connection = DBConnection.getConnection();
        String sql = "SELECT username,location FROM author;";
        ArrayList<Location> authorLocation = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("username");
                String loc = rs.getString("location");
                double[] latLong = getLocation(loc);
                authorLocation.add(new Location(name, latLong[0], latLong[1],loc));
            }
        }
        return authorLocation;
    }
    public Location findClosestStudent(double latitude, double longitude,String exclude) throws SQLException {
        ArrayList<Location> studentsLocation = getAllStudentsLocation(exclude);
        LocationHelper locationHelper=new LocationHelper();
        double[] target = {latitude, longitude};
        return locationHelper.findNearestNeighbor(studentsLocation,target);
    }

    public Location findClosestAuthor(double lat, double long1) throws SQLException {
        ArrayList<Location> authorLocation = getAllAuthorLocation();
        LocationHelper locationHelper=new LocationHelper();
        double[] target = {lat, long1};
        return locationHelper.findNearestNeighbor(authorLocation,target);
    }
}
