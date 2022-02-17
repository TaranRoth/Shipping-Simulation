package src;

public class PostageCalculator {
    public static double getShippingPrice(Package p) {
        double cost = 3.75;
        cost += getWeightCost(p.getWeight());
        String originZip = p.getOrigin().getZipCode();
        String destinationZip = p.getDestination().getZipCode();
        cost += getZipCost(originZip, destinationZip);
        cost += getSizeCost(p.getLength(), p.getWidth(), p.getHeight());
        return Math.floor(cost * 100) / 100.0;
    }

    public static double getShippingPrice(String zipOne, String zipTwo, double weight, int length, int width, int height) {
        double cost = 3.75;
        cost += getWeightCost(weight);
        cost += getZipCost(zipOne, zipTwo);
        cost += getSizeCost(length, width, height);
        return cost;
    }

    public static double getShippingPrice(Address aOne, Address aTwo, double weight, int length, int width, int height) {
        double cost = 3.75;
        cost += getWeightCost(weight);
        cost += getZipCost(aOne.getZipCode(), aTwo.getZipCode());
        cost += getSizeCost(length, width, height);
        return cost;
    }

    private static double getZipCost(String zipOne, String zipTwo) {
        return Math.abs(Integer.parseInt(zipOne) / 100 - Integer.parseInt(zipTwo) / 100) / 100.0;
    }

    private static double getWeightCost(double weight) {
        double weightCost = Math.floor(weight * 10) * .05;
        if (weight > 40) weightCost += Math.floor((weight - 40) * 10) * .05;
        return weightCost;
    }

    private static double getSizeCost(int length, int width, int height) {
        int totalInches = length + width + height;
        if (totalInches > 36) return totalInches * 0.1;
        else return 0;
    }
}
