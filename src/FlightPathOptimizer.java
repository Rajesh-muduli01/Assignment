import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class FlightPathOptimizer {
    // Define a class to represent an airport
    static class Airport {
        double x, y;

        public Airport(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // Define a class to represent a flight path
    static class FlightPath {
        List<Airport> airports = new ArrayList<>();

        public void addAirport(Airport airport) {
            airports.add(airport);
        }

        public List<Airport> getAirports() {
            return airports;
        }
    }

    // Method to detect intersections between two line segments
    public static boolean intersects(Line2D line1, Line2D line2) {
        return line1.intersectsLine(line2);
    }

    // Method to adjust the flight path to avoid intersections
    public static void adjustFlightPath(FlightPath flightPath, List<FlightPath> allFlightPaths) {
        for (FlightPath otherFlightPath : allFlightPaths) {
            if (otherFlightPath != flightPath) {
                for (int i = 0; i < flightPath.getAirports().size() - 1; i++) {
                    Airport currentAirport = flightPath.getAirports().get(i);
                    Airport nextAirport = flightPath.getAirports().get(i + 1);
                    Line2D currentLine = new Line2D.Double(currentAirport.x, currentAirport.y, nextAirport.x, nextAirport.y);

                    for (int j = 0; j < otherFlightPath.getAirports().size() - 1; j++) {
                        Airport otherCurrentAirport = otherFlightPath.getAirports().get(j);
                        Airport otherNextAirport = otherFlightPath.getAirports().get(j + 1);
                        Line2D otherLine = new Line2D.Double(otherCurrentAirport.x, otherCurrentAirport.y, otherNextAirport.x, otherNextAirport.y);

                        if (intersects(currentLine, otherLine)) {
                            // Adjust the flight path to avoid the intersection
                            // This can be done by changing the coordinates of the next airport
                            nextAirport.x += 0.1; // adjust x-coordinate
                            nextAirport.y += 0.1; // adjust y-coordinate
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        // Create flight paths
        FlightPath flightPath1 = new FlightPath();
        flightPath1.addAirport(new Airport(1, 1));
        flightPath1.addAirport(new Airport(2, 2));
        flightPath1.addAirport(new Airport(3, 3));

        FlightPath flightPath2 = new FlightPath();
        flightPath2.addAirport(new Airport(1, 1));
        flightPath2.addAirport(new Airport(2, 4));
        flightPath2.addAirport(new Airport(3, 2));

        FlightPath flightPath3 = new FlightPath();
        flightPath3.addAirport(new Airport(1, 1));
        flightPath3.addAirport(new Airport(4, 2));
        flightPath3.addAirport(new Airport(3, 4));

        // Add flight paths to a list
        List<FlightPath> allFlightPaths = new ArrayList<>();
        allFlightPaths.add(flightPath1);
        allFlightPaths.add(flightPath2);
        allFlightPaths.add(flightPath3);

        // Adjust flight paths to avoid intersections
        for (FlightPath flightPath : allFlightPaths) {
            adjustFlightPath(flightPath, allFlightPaths);
        }

        // Print the adjusted flight paths
        for (FlightPath flightPath : allFlightPaths) {
            System.out.println("Flight Path:");
            for (Airport airport : flightPath.getAirports()) {
                System.out.println(airport.x + ", " + airport.y);
            }
        }
    }
}