package fawrytask;
/**
 *
 * @author mahalawy
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
        import java.util.Calendar;

public class FawryTask {

    public static void main(String[] args) {
        
///*********expirey selector******//
Calendar cal = Calendar.getInstance();
cal.set(2021, Calendar.APRIL, 20);
Date expiredDate = cal.getTime();
cal.set(2026, Calendar.APRIL, 20);
Date notExpiredDate = cal.getTime();
///////////////////////////////////
        Product cheese;
        Product gums;
        Product tv;
        Product clock;
        Product gold;
        cheese = new Product(1, "cheese", 50.0, 100, expiredDate, new Shipping("Cheese", 0.5, 10.0));
         gums = new Product(2, "gums", 30.0, 200, notExpiredDate, null);
         tv = new Product(3, "TV", 500.0, 10, null, new Shipping("TV", 10.0, 50.0));
         clock = new Product(4, "clock", 10.0, 500, null, null);
 gold = new Product(5, "gold", 50.0, 500, null, new Shipping("gold", 1.0, 2.0));
        Customer ali = new Customer(1, "Ali", 50.0); // boor customer
        Customer abdo = new Customer(2, "Abdo", 20000.0); //has enough money

        Cart aliCart = new Cart();
        Cart aliCart2 = new Cart();
        Cart abdoCart = new Cart();
        Cart abdoCart2 = new Cart();
        Cart abdoCart3 = new Cart();
        Cart abdoCart4 = new Cart();
        Cart abdoCart5 = new Cart();
////////////////////////////////test case1 (no money)///////////////////////////////////////////////////
        aliCart.add(tv, 1);    
/////////////////////////////////////test case2 (1 item isn't available in quantity////////////
abdoCart.add(tv, 11);  
 /////////////////////////////test case 3 (1 item is expired)////////////////////////////////////////////////////
        abdoCart2.add(tv, 2);     
        abdoCart2.add(gums, 3);  
        abdoCart2.add(clock, 1);     
        abdoCart2.add(cheese, 1); 
/////////////////////////////test case 4 (all item is good)////////////////////////////////////////////////////
        abdoCart3.add(tv, 2);     
        abdoCart3.add(gums, 3);  
        abdoCart3.add(clock, 1);
        abdoCart3.add(gold, 1); 

/////////////////////////////test case 5 (buy broduct with exact amount of balance)////////////////////////////////////////////////////
        aliCart2.add(gold, 1); 

//////////////////////////////////////////////////////////////////////////////
        checkout(abdo, abdoCart); 
        checkout(abdo, abdoCart2);
        checkout(abdo, abdoCart3);
        checkout(ali, aliCart);
        checkout(ali, aliCart2);
    }

public static void checkout(Customer customer, Cart cart) {
    if (cart.isEmpty()) {
        System.out.println("Error: Cart is empty.");
        return;
    }

    double subtotal = 0;
    double totalShippingFee = 0;
    double totalWeight = 0;
    List<String> shippableItems = new ArrayList<>();

    for (CartItem cartItem : cart.getItems()) {
        Product product = cartItem.getProduct();

        if (product.isExpired()) {
            System.out.println("Error: Product " + product.getName() + " is expired.");
            return;
        }
        subtotal += product.getPrice() * cartItem.getQuantity();
       if (product.getShipping().isPresent()) {
       Shipping shipping = product.getShipping().get();
        double productWeight = shipping.getWeight() * cartItem.getQuantity();
       totalShippingFee += shipping.getShippingFee() * cartItem.getQuantity();  
       totalWeight += productWeight;
      shippableItems.add(cartItem.getQuantity() + "x " + shipping.getName() + " " + productWeight + "g");
     }
    }
    double totalAmount = subtotal + totalShippingFee;
    if (customer.getBalance() < totalAmount) {
        System.out.println("Error: Insufficient balance for " + customer.getName());
        return;
    }
    customer.deductBalance(totalAmount);
    if (!shippableItems.isEmpty()) {
        System.out.println("** Shipment notice **");
        shippableItems.forEach(System.out::println);
        System.out.println("Total package weight " + totalWeight + "g");
    }
    
    for (CartItem cartItem : cart.getItems()) {
        System.out.println(cartItem.getQuantity() + "x " + cartItem.getProduct().getName() +
                " " + (cartItem.getQuantity() * cartItem.getProduct().getPrice()));
    }
    System.out.println("----------------------");
    System.out.println("Subtotal: " + subtotal);
    System.out.println("Shipping: " + totalShippingFee);
    System.out.println("Amount: " + totalAmount);
    System.out.println("Remaining Balance: " + customer.getBalance());
        System.out.println("--------------------------------------------------------------");

}
}