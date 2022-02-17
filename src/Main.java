package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.print("Would you like to automatically simulate packages (1) or enter specifications (2)? "); 
        int input = s.nextInt();
        if (input == 1) {
            System.out.print("How many packages do you want to simulate? ");
            input = s.nextInt();
            ArrayList<Package> simulation = Simulation.simulate(input);
            int c = 1;
            for (Package p: simulation) {
                System.out.println("Package " + c);
                System.out.println("Origin: " + p.getOrigin());
                System.out.println("Destination: " + p.getDestination());
                System.out.println("Size: " + p.getLength() + "x" + p.getWidth() + "x" + p.getHeight());
                System.out.println("Shipping Cost: $" + PostageCalculator.getShippingPrice(p));
                System.out.println();
                c++;
            }
        } else if (input == 2) {
            System.out.print("Package Length (in): "); 
            int length = s.nextInt();
            System.out.print("Package Width (in): "); 
            int width = s.nextInt();
            System.out.print("Package Depth (in): "); 
            int height = s.nextInt();
            System.out.print("Package Weight (lbs): "); 
            int weight = s.nextInt();
            System.out.print("Origin Zip Code: ");
            int originZip = s.nextInt();
            System.out.print("Destination Zip Code: ");
            int destinationZip = s.nextInt();
            double cost = PostageCalculator.getShippingPrice(Integer.toString(originZip), Integer.toString(destinationZip), weight, length, width, height);
            System.out.println("Shipping cost: $" + cost);
        } else {
            System.out.println("Sorry, that wasn't an option."); 
        }
        s.close();
    }
}
