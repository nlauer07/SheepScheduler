/*
 * Written by Nick Lauer
 */
public class Sheep implements Comparable<Sheep> {
    private String name;
    private int shearingTime;
    private int arrivalTime;

    public Sheep(String name, int shearingTime, int arrivalTime) {
        this.name = name;
        this.shearingTime = shearingTime;
        this.arrivalTime = arrivalTime;
    }

    public String getName() {
        return name;
    }

    public int getShearingTime() {
        return shearingTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int compareTo(Sheep other) {
        if (this.shearingTime != other.shearingTime) {
            return this.shearingTime - other.shearingTime;
        }
        return this.name.compareTo(other.name);
    }

    // Returns a string of the sheep with its details
    public String toString() {
        return "Name: " + name + ", Sheer Time: " + shearingTime + ", Arrival Time: " + arrivalTime;
    }
}