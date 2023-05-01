import javax.xml.crypto.Data;
import java.util.LinkedHashMap;

public class Deleting{
    private Database db;

    public Deleting(Database db)
    {
        this.db = db;
    }
    public void delete(Product p) {
        LinkedHashMap<String, Product> map;
        String title = p.getTitle();
        switch (p.category) {
            case Jeans:
                map = db.jeansMap;
                break;
            case Shirt:
                map = db.shirtMap;
                break;
            case TShirt:
                map = db.tShirtMap;
                break;
            case Shorts:
                map = db.shortsMap;
                break;
            case Hat:
                map = db.hatMap;
                break;
            case Shoes:
                map = db.shoesMap;
                break;
            case Jacket:
                map = db.jacketMap;
                break;
            default:
                throw new IllegalArgumentException("The product category was not found; nothing was deleted");
        }

        if (!map.containsKey(title))
        {
            System.out.println("This product does not exist in the database.");
            System.out.println("It may be deleted already but still held as selected");
        }
        map.remove(title);
        db.toBeDeleted.add(p);

        System.out.println("Successfully deleted product " + title);
    }
}