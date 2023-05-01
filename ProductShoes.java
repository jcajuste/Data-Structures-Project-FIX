// Represents a single pair of shoes. This class use the existing Product class as base class
import java.util.Date;
public class ProductShoes extends Product {
    public ProductShoes(String title, double price, Date date)
    {
        super(title, price, date, ProductCategory.Shoes);
    }


}