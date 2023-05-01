// Represents a single Jacket. This class use the existing Product class as base class
import java.util.Date;
public class ProductJacket extends Product {
    public ProductJacket(String title, double price, Date date)
    {
        super(title, price, date, ProductCategory.Jacket);
    }

}