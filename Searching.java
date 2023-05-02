import java.util.LinkedHashMap;
import java.util.Scanner;

public class Searching{
  // The Searching class provides methods for searching and displaying products from the inventory database.

  // The database object used to store the inventory data.
  private Database db;
  private Scanner scanner;

    // The constructor method that initializes the database object.
    public Searching(Database db) {
        this.db = db;
    }

    public Product searchByTitle(Product.ProductCategory category, String title)
    {
        LinkedHashMap<String, Product> map = db.getProductMap(category);

        if (map.containsKey(title))
            return map.get(title);

        System.out.println("There is no product with the title: " + title);
        return null;
    }


    // The searchAllItems method searches and displays all items of a given category.
    public String searchAllItems(Product.ProductCategory category) {
        // LinkedHashMap is a hash table implementation with predictable iteration order.
        // Here, it is used to store products of a specific category.
        LinkedHashMap<String, ? extends Product> productMap;

        // Switch statement to determine which category of products to search.
        switch (category) {
            case Jeans:
                productMap = db.jeansMap;
                break;
            case Shirt:
                productMap = db.shirtMap;
                break;
            case TShirt:
                productMap = db.tShirtMap;
                break;
            case Shorts:
                productMap = db.shortsMap;
                break;
            case Hat:
                productMap = db.hatMap;
                break;
            case Shoes:
                productMap = db.shoesMap;
                break;
            case Jacket:
                productMap = db.jacketMap;
                break;
            default:
                // Use the DecisionHandler class to get a valid category input.
                category = DecisionHandler.getCategory("Searching", scanner);
                return searchAllItems(category);
        }

        // Iterate through the productMap and display the product details.
        String productTitle = "";
        for (String key : productMap.keySet()) {
            Product product = productMap.get(key);
            System.out.println(product.getTitle() + " - " + product.getPrice() + " - " + product.getListingDate());
            productTitle = product.getTitle();
        }

        // Return the title of the last product in the map.
        return productTitle;
    }


    // The searchPriceRange method searches and displays products within a given price range
    // for a specific category.
    public void searchPriceRange (Product.ProductCategory category, Integer maxPrice, Integer minPrice){

        System.out.println("the value of the min price" + minPrice);
        System.out.println("the value of the max price" + maxPrice);

        
        LinkedHashMap<String, Product> productMap;

        switch (category) {
            case Jeans:
                productMap = db.jeansMap;
                break;
            case Shirt:
                productMap = db.shirtMap;
                break;
            case TShirt:
                productMap = db.tShirtMap;
                break;
            case Shorts:
                productMap = db.shortsMap;
                break;
            case Hat:
                productMap = db.hatMap;
                break;
            case Shoes:
                productMap = db.shoesMap;
                break;
            case Jacket:
                productMap = db.jacketMap;
                break;
            default:
                // Use the DecisionHandler class to get a valid category input.
                category = DecisionHandler.getCategory("Searching", scanner);
                searchPriceRange(category, maxPrice, minPrice);
                return;
        }

        // Iterate through the productMap and display the product details if it falls
        // within the given price range
        int counter = 0;
        for (String key : productMap.keySet()) {
            Product product = productMap.get(key);
            if ((minPrice == null || product.getPrice() >= minPrice) && (maxPrice == null || product.getPrice() <= maxPrice)) {
                System.out.println(product); // product toString method called implicitly
                counter++;
            }
        }
        if (counter == 0) {
            System.out.println("No products found within the given price range.");
        }
    } 
}


