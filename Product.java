// Represent a single product. This is the base class for all the product types
import java.util.Date;
public class Product {
    public double price; // price of the product
    public String title; // title of the product
    public Date listingDate; // when the product first brought to the inventory
    public ProductCategory category; //the category of this product

    public enum ProductCategory {
        TShirt,
        Shirt,
        Shorts,
        Jeans,
        Hat,
        Shoes,
        Jacket;
    }

    public Product(String title, double price, Date listingDate, ProductCategory category)
    {
        this.title = title;
        this.price = price;
        this.listingDate = listingDate;
        this.category = category;
    }

    public String getTitle()
    {
        return title;
    }

    public double getPrice()
    {
        return price;
    }

    public Date getListingDate() {
        return listingDate;
    }

    public ProductCategory getProducts() {
        return category;
    }

    public String toString()
    {
        return "Title: " + title + ", Category: " + category.toString() + ", Price: $" + price + ", Date: " + listingDate;
    }
}