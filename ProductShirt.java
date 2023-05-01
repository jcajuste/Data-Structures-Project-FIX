// Represents a single Shirt. This class use the existing Product class as base class
import java.util.Date;
public class ProductShirt extends Product {

    public ProductShirt(String title, double price, Date date)
    {
        super(title, price, date, ProductCategory.Shirt);
    }


}