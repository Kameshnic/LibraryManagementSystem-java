package org.example.miniproject1.Model;


import java.util.ArrayList;

public class LocationHelper {
        private double euclideanDistance(double[] point1, double[] point2) {
            return Math.sqrt(Math.pow(point1[0] - point2[0], 2) + Math.pow(point1[1] - point2[1], 2));
        }
        public Location findNearestNeighbor(ArrayList<Location> locations, double[] target) {
            if (locations == null || locations.isEmpty()) {
                return null;
            }
            Location nearestLocation = locations.get(0);
            double smallestDistance = euclideanDistance(nearestLocation.getCoordinates(), target);
            for (int i = 1; i < locations.size(); i++) {
                double currentDistance = euclideanDistance(locations.get(i).getCoordinates(), target);
                if (currentDistance < smallestDistance) {
                    nearestLocation = locations.get(i);
                    smallestDistance = currentDistance;
                }
            }
            return nearestLocation;
        }

}

