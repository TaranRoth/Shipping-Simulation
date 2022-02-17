package src;

public class Package {
    private Address origin;
    private Address destination;
    private double weight;
    private int length;
    private int width;
    private int height;

    public Package(Address o, Address d, double w, int l, int wi, int h) {
        origin = o;
        destination = d;
        weight = w;
        length = l;
        width = wi;
        height = h;
    }

    public Address getOrigin() {
        return origin;
    }

    public Address getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
