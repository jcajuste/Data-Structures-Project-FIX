import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;


/*
Class that handles all sorting
 */
public class Sorting {

    //Sorts the list by price using quicksort
    public static void quickSortByPrice(ArrayList<Product> list)
    {
        quickSort(list, 0, list.size() - 1, new ProductComparator("price"));
    }

    //sorts the list by date using quicksort
    public static void quickSortByDate(ArrayList<Product> list)
    {
        quickSort(list, 0, list.size() - 1, new ProductComparator("date"));
    }

    //quickSort to sort all of this list of items
    //Basically generic: comparator determines if we are sorting by price or date
    //Time complexity = O(n * log(n))
    //Space complexity of in-place quicksort = O(n)
    //start, end : start and end of the segment of the list to be sorted
    private static void quickSort(ArrayList<Product> list, int start, int end, ProductComparator comparator){
        if (start >= end) return;  //base case

        //define left pointer and right pointer
        int left = start;
        int right = end - 1;
        Product pivot = list.get(end);

        //loop until the left and right pointer cross each other
        while (left <= right)
        {
            //find an element on the left out of place
            while (left <= right && comparator.compare(list.get(left), pivot) <= 0) left++;

            //find an element on the right out of place
            while (left <= right && comparator.compare(list.get(right), pivot) >= 0) right--;

            //swap the out-of-place items
            if (left <= right){
                Product temp = list.get(left);
                list.set(left, list.get(right));
                list.set(right, temp);
                left++;
                right--;
            }
        }

        //move the pivot into the correct place
        Product temp = list.get(left);
        list.set(left, list.get(end));
        list.set(end, temp);  //sets the pivot to the left product

        //divide and conquer using the two halves of this interval
        quickSort(list, start, left - 1, comparator);
        quickSort(list, left + 1, end, comparator);
    }

    //Insertion sort just for comparing time complexity = O(n^2)
    public static void insertionSortPrices(ArrayList<Product> list)
    {
        for (int i = 0; i < list.size() - 1; i++)
        {
            for (int j = i; j > 0; j--){
                Product first = list.get(j);
                Product second = list.get(j - 1);
                if (first.getPrice() < second.getPrice()) break;
                list.set(j, second);
                list.set(j - 1, first);
            }
        }
    }


    //Comparator for either date or price
    public static class ProductComparator implements Comparator<Product>
    {
        private String type;
        public ProductComparator(String type)
        {
            //either date or price
            this.type = type;
        }
        @Override
        public int compare(Product o1, Product o2) {
            if (type.equals("price"))
            {
                double difference = o1.getPrice() - o2.getPrice();
                if (difference > 0) return 1;
                else if (difference < 0) return -1;
                else return 0;
            }
            else{
                return o1.getListingDate().compareTo(o2.getListingDate());
            }
        }
    }

    // The searchAllItems method searches and displays all items of a given category.
    public LinkedHashMap<String, Product> catigoryMap (Product.ProductCategory category) {
        // LinkedHashMap is a hash table implementation with predictable iteration order.
        // Here, it is used to store products of a specific category.
        Database db = new Database();
        LinkedHashMap<String, Product> productMap;

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
                //category = DecisionHandler.getCategory("Searching", scanner);
                return catigoryMap(category);
        }

        return productMap;

    }

}
