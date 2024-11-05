package evoLog.evoLog_TP3_Maven;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import Repository.ProductRepository;
import entities.Product;
import entities.User;

public class App {
		private static ProductRepository productRepository;
	    private static User currentUser;
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        // Create a user
	        System.out.println("=== User Registration ===");
	        System.out.print("Enter ID: ");
	        int userId = Integer.parseInt(scanner.nextLine());
	        System.out.print("Enter Name: ");
	        String userName = scanner.nextLine();
	        System.out.print("Enter Age: ");
	        int userAge = Integer.parseInt(scanner.nextLine());
	        System.out.print("Enter Email: ");
	        String userEmail = scanner.nextLine();
	        System.out.print("Enter Password: ");
	        String userPassword = scanner.nextLine();

	        currentUser = new User(userId, userName, userAge, userEmail, userPassword);
	        System.out.println("User created successfully!\n");

	        // Initialize ProductRepository
	        productRepository = new ProductRepository();

	        // Display menu
	        int choice;
	        do {
	            System.out.println("=== Product Management Menu ===");
	            System.out.println("1. Display all products");
	            System.out.println("2. Fetch product by ID");
	            System.out.println("3. Add new product");
	            System.out.println("4. Delete product by ID");
	            System.out.println("5. Update product info");
	            System.out.println("6. Exit");
	            System.out.print("Enter your choice: ");
	            choice = Integer.parseInt(scanner.nextLine());
	            try {
	                switch (choice) {
	                    case 1:
	                        productRepository.displayAllProducts();
	                        break;
	                    case 2:
	                        fetchProductById(scanner);
	                        break;
	                    case 3:
	                        addNewProduct(scanner);
	                        break;
	                    case 4:
	                        deleteProductById(scanner);
	                        break;
	                    case 5:
	                        updateProductInfo(scanner);
	                        break;
	                    case 6:
	                        System.out.println("Exiting application. Goodbye!");
	                        break;
	                    default:
	                        System.out.println("Invalid choice. Please try again.");
	                }
	            } catch (Exception ex) {
	                System.out.println("Error: " + ex.getMessage());
	            }
	            System.out.println();
	        } while (choice != 6);

	        scanner.close();
	    }

	    private static void fetchProductById(Scanner scanner) throws Exception {
	        System.out.print("Enter Product ID: ");
	        int productId = Integer.parseInt(scanner.nextLine());
	        Product product = productRepository.getProduct(productId);
	        System.out.println(product.toString());
	    }

	    private static void addNewProduct(Scanner scanner) throws Exception {
	        System.out.print("Enter Product ID: ");
	        int productId = Integer.parseInt(scanner.nextLine());
	        System.out.print("Enter Product Name: ");
	        String productName = scanner.nextLine();
	        System.out.print("Enter Price: ");
	        double price = Double.parseDouble(scanner.nextLine());
	        System.out.print("Enter Expiration Date (YYYY-MM-DD): ");
	        String dateStr = scanner.nextLine();
	        LocalDate expirationDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);

	        Product product = new Product(productId, productName, price, expirationDate);
	        productRepository.addProduct(product);
	        System.out.println("Product added successfully!");
	    }

	    private static void deleteProductById(Scanner scanner) throws Exception {
	        System.out.print("Enter Product ID to delete: ");
	        int productId = Integer.parseInt(scanner.nextLine());
	        productRepository.deleteProduct(productId);
	        System.out.println("Product deleted successfully!");
	    }

	    private static void updateProductInfo(Scanner scanner) throws Exception {
	        System.out.print("Enter Product ID to update: ");
	        int productId = Integer.parseInt(scanner.nextLine());
	        System.out.print("Enter New Product Name: ");
	        String productName = scanner.nextLine();
	        System.out.print("Enter New Price: ");
	        double price = Double.parseDouble(scanner.nextLine());
	        System.out.print("Enter New Expiration Date (YYYY-MM-DD): ");
	        String dateStr = scanner.nextLine();
	        LocalDate expirationDate = LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);

	        Product product = new Product(productId, productName, price, expirationDate);
	        productRepository.updateProduct(product);
	        System.out.println("Product updated successfully!");
	    }
	}

