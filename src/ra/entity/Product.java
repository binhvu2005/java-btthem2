package ra.entity;

import java.time.LocalDate;
import java.util.Scanner;

public class Product implements IProduct {
    private static int counter = 1;
    private int productId;
    private String productName;
    private float price;
    private String category;
    private LocalDate createdDate;

    public Product() {
        this.productId = counter++;
        this.createdDate = LocalDate.now();
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public float getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    @Override
    public void inputData(Scanner scanner) {
        System.out.print("Enter product name: ");
        this.productName = scanner.nextLine();

        System.out.print("Enter price: ");
        this.price = Float.parseFloat(scanner.nextLine());

        System.out.print("Enter category: ");
        this.category = scanner.nextLine();
    }

    @Override
    public void displayData() {
        System.out.println("ID: " + productId + ", Name: " + productName + ", Price: " + price + ", Category: " + category + ", Created Date: " + createdDate);
    }
}