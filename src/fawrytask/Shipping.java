package fawrytask;
/**
 *
 * @author mahalawy
 */

public class Shipping implements Shippable {
    private String name;
    private double weight;
    private double shippingFee;

    public Shipping(String name, double weight, double shippingFee) {
        this.name = name;
        this.weight = weight;
        this.shippingFee = shippingFee;
    }

    @Override
    public String getName() { return name; }
    @Override
    public double getWeight() { return weight; }
    public double getShippingFee() { return shippingFee; }
}