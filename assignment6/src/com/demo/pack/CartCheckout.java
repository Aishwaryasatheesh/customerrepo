package com.demo.pack;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CartCheckout {


public String billGenerator(Map<String, BigDecimal> cart, Double taxPercent) { 
	if (cart == null || cart.isEmpty()) {
return "The cart Map is empty";
}

if (taxPercent == null) {
return "The taxPercent cannot be null";
}

if (taxPercent < 0) {
return "The taxPercent cannot be negative";
}

for (Map.Entry<String, BigDecimal> entry : cart.entrySet()) { 
	BigDecimal value = entry.getValue();
if (value == null || value.compareTo(BigDecimal.ZERO) <= 0) { 
	return "The cart Map contains null or non-positive values";
}
}


BigDecimal totalCost = cart.values().stream()
.filter(value -> value != null && value.compareTo(BigDecimal.ZERO) > 0)
.reduce(BigDecimal.ZERO, BigDecimal::add);

 

BigDecimal taxAmount = totalCost.multiply(BigDecimal.valueOf(taxPercent / 
		100));
		BigDecimal totalWithTax = totalCost.add(taxAmount);
return totalWithTax.toString();

}
public static void main(String[] args) {
	CartCheckout checkout = new CartCheckout(); Scanner scanner = new Scanner(System.in);
System.out.print("Enter cart items in the format {item1=price1, item2=price2,...}: ");
String cartInput = scanner.nextLine().trim();


Map<String, BigDecimal> cart = parseCartInput(cartInput); if (cart == null) {
System.out.println("The cart Map is empty"); return;
}


Double taxPercent = null;
System.out.print("Enter tax percentage in decimal format (e.g., 10.5): "); String taxPercentInput = scanner.nextLine().trim();
 

if (!taxPercentInput.isEmpty()) { try {
taxPercent = Double.parseDouble(taxPercentInput);
} catch (NumberFormatException e) { System.out.println("The taxPercent cannot be null"); return;
}
}


scanner.close();


String billResult = checkout.billGenerator(cart, taxPercent); System.out.println(billResult);
}

private static Map<String, BigDecimal> parseCartInput(String cartInput) {
 Map<String, BigDecimal> cart = new HashMap<>();
cartInput = cartInput.substring(1, cartInput.length() - 1).trim();
String[] items = cartInput.split(",\\s*");
for (String item : items) {

String[] parts = item.split("="); if (parts.length == 2) {
String itemName = parts[0].trim(); 
try {
BigDecimal itemPrice = new BigDecimal(parts[1].trim()); cart.put(itemName, itemPrice);
} catch (NumberFormatException e) { return null;
}
} 
else {
return null;
}
}
return cart;
}
}
