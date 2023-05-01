
public class Main {
    public static void main(String[] args) {
        /* 
        double s = System.nanoTime();
        Database products = new Database();
        System.out.println("Initializing the Database: " + (System.nanoTime() - s));

        //Testing using both a hashmap and a linked list-------------------------------------------
        //Time complexity for hashmap is O(1) because consant time lookups
        //Time complexity for linked lists is O(n) because of need to traverse up to element
        //For some reason, the first search of Jeans takes longer
        double startTime = System.nanoTime();
        System.out.println("Search by name O(1): " + products.searchJeansHashMap("Jeans500"));
        System.out.println(System.nanoTime() - startTime);

        startTime = System.nanoTime();
        System.out.println("Search by index O(n): " + products.getJeansLL(500));
        System.out.println(System.nanoTime() - startTime);


        startTime = System.nanoTime();
        System.out.println("Search by name O(1): " + products.searchJeansHashMap("Jeans5000"));
        System.out.println(System.nanoTime() - startTime);

        startTime = System.nanoTime();
        System.out.println("Search by index O(n): " + products.getJeansLL(5000));
        System.out.println(System.nanoTime() - startTime);


        startTime = System.nanoTime();
        System.out.println("Search by name O(1): " + products.searchJeansHashMap("Jeans9999"));
        System.out.println(System.nanoTime() - startTime);

        startTime = System.nanoTime();
        System.out.println("Search by index O(1): " + products.getJeansLL(9999));
        System.out.println(System.nanoTime() - startTime);

        //Testing using the Linked HashMap-------------------------------------------
        //Time complexity for searching in LinkedHashMap is same as HashMap = O(1)
        //Time complexity of iterating is O(n) due to need to iterate still
        System.out.println("USING LINKED HASHMAP");

        startTime = System.nanoTime();
        System.out.println("Search by name O(1): " + products.searchJeans("Jeans9999"));
        System.out.println(System.nanoTime() - startTime);

        //Search by index takes O(n) time becasue all items must be taken from value list
        startTime = System.nanoTime();
        System.out.println("Search by index O(n): " + products.getJeans(9999));
        System.out.println(System.nanoTime() - startTime);

        //Sorting by quicksort expected run-time is  O(n * log(n)) due to divide and conquer
        //Space complexity is O(n) due to implementation of in-place quicksort
        System.out.println("Sorting by Price----------------");
        startTime = System.nanoTime();
        products.sortPrices("jeans");
        double endTime = System.nanoTime();
//        products.printAll(products.jeansPrices);
        System.out.println("Time for QuickSort O(n*log(n)) : " + (endTime - startTime));

        System.out.print("\nSorting by price using insertion sort O(n^2): ");
        startTime = System.nanoTime();
       Sorting.insertionSortPrices(products.jeansPrices);
        endTime = System.nanoTime();
        System.out.println(endTime - startTime);
        */

        Database db = new Database();
        UI ui = new UI(db);
        ui.runDatabase();
    }

}
