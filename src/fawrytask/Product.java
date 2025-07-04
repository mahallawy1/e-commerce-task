package fawrytask;
/**
 *
 * @author mahalawy
 */

import java.util.Optional;
import java.util.Date;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private Optional<Date> expiryDate;
    private Optional<Shipping> shipping;

    public Product(int id, String name, double price, int quantity, Date expiryDate, Shipping shipping) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expiryDate = Optional.ofNullable(expiryDate);
        this.shipping = Optional.ofNullable(shipping);
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public Optional<Date> getExpiryDate() { return expiryDate; }
    public Optional<Shipping> getShipping() { return shipping; }

    public boolean isExpired() {
        return expiryDate.map(date -> date.before(new Date())).orElse(false);
    }
}