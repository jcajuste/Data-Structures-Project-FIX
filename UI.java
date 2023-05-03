import java.util.Date;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.LinkedHashMap;


public class UI
{
  private Database db;
  private Scanner scanner;

  public UI(Database database)
  {
    db = database;
    scanner = new Scanner(System.in);
  }

  public void runDatabase()
  {
    boolean running = true;

    while (running)
      {
        running = master();
      }
  }

  public void Introduction(){
    //i do not have java 15 on my device 
    //so the 3 quoted comments do not work for me 
    //i simply do a quick change to test
    System.out.println //("THE CODE CODE STARTS HERE");
            ("-----------------------------------------\n" +
                    "|Welcome to the Designer Clothing Shop!!|\n" +
                    "|----------- Shop Created By -----------|\n" +
                    "|---------- Jefferson Cajuste ----------|\n" +
                    "|------------ Michael Doyle ------------|\n" +
                    "|------------ Ryder Raymond ------------|\n" +
                    "-----------------------------------------");

    System.out.println("Within this clothing shop you will be able to complete several actions.");
  }

  public boolean master()
  {
    Introduction();
//    Scanner scanner = new Scanner(System.in);

    String[] options =
            {"Search", "Insert", "Delete", "Sort", "Print", "Exit"};

    Product currentSelection = null;
    String choice;

    while (true){
      System.out.print("Possible actions are: ");
      for(String option : options) System.out.print(option + ", ");
      System.out.println();
      System.out.print("Current product selected: ");

      if (currentSelection == null)
        System.out.println("[No product selected]");
      else
        System.out.println(currentSelection);

      System.out.print("What would you like to do?: ");

      choice = scanner.nextLine();

      choice = DecisionHandler.handleDecisions(choice, options, scanner);

      switch(choice.toLowerCase())
      {
        case "search":
          currentSelection = search();
          break;
        case "insert":
          currentSelection = insert();
          break;
        case "delete":
          currentSelection = delete(currentSelection);
          break;
        case "sort":
          currentSelection = sort();
          break;
        case "print":
          currentSelection = print();
          break;
        case "exit":
          return false;
      }
    }
  }


/*---START OF SEARCHING--- */
  public Product search()
  {
    System.out.println("----");
    System.out.println("*You are now searching.*");
    String choice = "";

    HashMap<String, String> commands = new HashMap<String, String>();
    commands.put("Exit", "returns to the selection screen");
    commands.put("Title", "searches for a product with a specific title");
    commands.put("Range", "searches the product category at a specific price range of products");
    commands.put("Suggestion", "searches for a product with a specific title with word auto-suggestion");
    commands.put("Help", "prints these options again");

    System.out.println("----------------------------------------------------------");
    System.out.println("Possible commands are: ");
    for (String command : commands.keySet())
      {
        System.out.println(command + ": " + commands.get(command));
      }

      System.out.println("----------------------------------------------------------");
    //    Scanner keyboard = new Scanner(System.in);
      System.out.print("Your Command: ");

      choice = scanner.nextLine();

//      choice = keyboard.nextLine();
      choice = DecisionHandler.handleDecisions(choice, commands.keySet(), scanner);

      switch(choice.toLowerCase()) {
        case "exit":
          return null;
        case "title":
          return searchByTitle();
        case "range":
          return searchByRange();
        case "suggestion":
          return searchTitleWithSuggestion();
        case "help":
          return search();
      }

    return null;  //choice somehow was not an option
  }

  public Product searchByTitle()
  {
    Searching searching = new Searching(db); // Passing the database object to the constructor.
    Product.ProductCategory catigory = DecisionHandler.getCategory("searching", scanner);
//    Scanner scanner = new Scanner(System.in);
    System.out.println("\nEnter the title of the product from the category you wish to search for: ");
    String title = scanner.nextLine();
    Product result = searching.searchByTitle(catigory,title);

    if (result != null)
      System.out.println("Product " + result.getTitle() + " was found");
    else
      System.out.println("No product with title " + title + " was found");

    return result;
  }

  public Product searchTitleWithSuggestion()
  {
    System.out.println("You are now searching with word suggestion turned on");
    Searching searching = new Searching(db);
    WordSuggester suggester = new WordSuggester(2);

    Product.ProductCategory category = DecisionHandler.getCategoryWithSuggestion(suggester, scanner);

    System.out.println("\nEnter the title of the product from the category you wish to search for: ");
    String title = scanner.nextLine();
    title = suggester.suggest(title, scanner);

    Product result = searching.searchByTitle(category,title);

    if (result != null)
      System.out.println("Product " + result.getTitle() + " was found");
    else
      System.out.println("No product with title " + title + " was found");

    return result;
  }

  public Product searchByRange(){
    Database db = new Database(); // Creating a new database object.
    Searching searching = new Searching(db); // Passing the database object to the constructor.
//    DecisionHandler decisionHandler = new DecisionHandler();
//    Scanner scanner = new Scanner(System.in);
    Product.ProductCategory catigory = DecisionHandler.getCategory("Searching", scanner);
    System.out.print("Enter the Max price range, EX $100: ");
    Integer maxR = scanner.nextInt();
    //maxR = Integer.parseInt(maxR);
    System.out.print("Enter the Min price range, EX $50: ");
    Integer minR = scanner.nextInt();
    //minR = Integer.parseInt(minR);

    searching.searchPriceRange(catigory,maxR,minR);
    return null;
  }
/*---END OF SEARCHING--- */


/*---START OF INSERTING--- */
  public Product insert(){
    System.out.println("----");
    System.out.println("*You are now Inserting.*");
    String choice;

    HashMap<String, String> commands = new HashMap<String, String>();
    commands.put("Enter", "enters a new product into the database");
    commands.put("Exit", "returns to the selection screen");
    commands.put("Help", "prints these options again");

    System.out.println("----------------------------------------------------------");
    System.out.println("Possible commands:");
    for (String command : commands.keySet())
    {
      System.out.println(command + ": " + commands.get(command));
    }
    System.out.println("----------------------------------------------------------");

    System.out.print("\nYour command: ");
    choice = scanner.nextLine();

    choice = DecisionHandler.handleDecisions(choice, commands.keySet(), scanner);

    switch(choice.toLowerCase())
    {
      case ("enter"):
        return createNewProduct();
      case ("exit"):
        return null;
      case ("help"):
        return insert();

    }
    return null; ///just for now
  }

  private Product createNewProduct()
  {
    Product.ProductCategory category;
    String title;
    double price;

    System.out.print("Enter the category of the new product: ");
    category = DecisionHandler.getCategory("Inserting", scanner);

    System.out.print("Enter the tile of the new product: " );
    title = scanner.nextLine();

    //they need to enter a product with a title at least 1 character long
    while (title.length() < 1)
    {
      System.out.println("The title must have a length greater than 0");
      System.out.print("New title: " );
      title = scanner.nextLine();
    }

    System.out.println("\nEnter the price of the new product: " );

    price = scanner.nextDouble();
//    scanner.next();

    Product p;

    switch(category)
    {
      case Jeans:
         p = new ProductJeans(title, price, new Date());
         break;
      case Shirt:
        p = new ProductShirt(title, price, new Date());
        break;
      case TShirt:
        p = new ProductTShirt(title, price, new Date());
        break;
      case Hat:
        p = new ProductHat(title, price, new Date());
        break;
      case Jacket:
        p = new ProductJacket(title, price, new Date());
        break;
      case Shoes:
        p = new ProductShoes(title, price, new Date());
        break;
      case Shorts:
        p = new ProductShorts(title, price, new Date());
        break;
      default:
        System.out.println("Incorrect category selection");
        return null;
    }

    //return the product if insertion was successful, null otherwise
    return Inserting.insert(p, db) ? p : null;

  }
/*---END OF INSERTING--- */


/*---START OF SORTING--- */
  public Product sort() {
    //synchronize the hashmaps with the arraylists because that is how we sort by price or date
    db.sync();
    System.out.println("----");
    System.out.println("*You are now Sorting.*");
    String choice;
//    Scanner scanner = new Scanner(System.in);

    HashMap<String, String> commands = new HashMap<String, String>();
    commands.put("Exit", "returns to the selection screen");
    commands.put("Price", "sorts products by Price");
    commands.put("Date", "sorts product by Date");
    commands.put("Help", "prints these options again");

    System.out.println("----------------------------------------------------------");
    System.out.println("Possible commands:");
    for (String command : commands.keySet())
    {
      System.out.println(command + ": " + commands.get(command));
    }
    System.out.println("----------------------------------------------------------");

    System.out.print("\nYour command: ");
    choice = scanner.nextLine();

    choice = DecisionHandler.handleDecisions(choice, commands.keySet(), scanner);

    switch(choice.toLowerCase())
    {
      case ("price"):
        return priceSort();
      case ("exit"):
        return null;
      case ("help"):
        return sort();
      case ("date"):
        return dateSort();
    }
    return null;
  }

  //need to work more on both priceSord and dateSort
  //public ArrayList<Product> priceSort(){
  public Product priceSort(){
    String category;
    String order;

    System.out.println("\nYou are now Sorting by Price.");
    System.out.println("Enter the category you want sort:");
    category = DecisionHandler.getCategory("sorting", scanner).toString();
    System.out.println();
    
    db.sortPrices(category);

    System.out.println("Would you like to sort high to low or low to high?");
    System.out.println("Commands: high to low | low to high");
    order = scanner.nextLine();
    order = DecisionHandler.handleDecisions(order, new String[] {"high to low", "low to high"}, scanner);

    if (order.equals("low to high"))
      db.printAll(db.getPricesList(category));
    else
      db.printAllReverse(db.getPricesList(category));

    return null;
  }

  public Product dateSort(){
    String category;
    String order;

    System.out.println("\nYou are now Sorting by Date.");
    System.out.println("Enter the category you want sort:");
    category = DecisionHandler.getCategory("sorting", scanner).toString();
    System.out.println();

    System.out.println("Would you like to sort newest or oldest first?");
    System.out.println("Commands: newest | oldest");
    order = scanner.nextLine();
    order = DecisionHandler.handleDecisions(order, new String[] {"newest", "oldest"}, scanner);

    db.sortDates(category);

    if (order.equals("oldest"))
      db.printAll(db.getDatesList(category));
    else
      db.printAllReverse(db.getDatesList(category));

    return null;
  }
/*---END OF SORTING--- */

/*---START OF DELETING--- */
  public Product delete(Product selected)
  {
    System.out.println("----");
    System.out.println("*You are now deleting*");

    String choice;

    HashMap<String, String> commands = new HashMap<>();
    commands.put("Title", "deletes the product with the matching title");
    commands.put("Selected", "deletes the currently selected product, if one is selected");
    commands.put("Help", "prints the options again");
    commands.put("Exit", "returns to the selection menu");

    System.out.println("----------------------------------------------------------");
    System.out.println("Possible commands:");

    for (String command : commands.keySet()){
      System.out.println(command + ": " + commands.get(command));
    }
    System.out.println("----------------------------------------------------------");

    System.out.print("Your command: ");
    choice = scanner.nextLine();
    choice = DecisionHandler.handleDecisions(choice, commands.keySet(), scanner);

    switch (choice.toLowerCase())
    {
      case "title":
        return deleteByTitle();
      case "selected":
        return deleteBySelected(selected);
      case "help":
        return delete(selected);
      case "exit":
        return null;
      default:
        System.out.println("That was not an option");
        return null;
    }
  }

  public Product deleteByTitle()
  {
    Product.ProductCategory category;
    String title;

    category = DecisionHandler.getCategory("deleting", scanner);

    System.out.print("Enter the tile of the product you want to delete:");
    title = scanner.nextLine();

    Searching searching = new Searching(db);
    Deleting deleting = new Deleting(db);
    Product p = searching.searchByTitle(category, title);

    if (p == null)
    {
      System.out.println("Cannot delete nonexistent product");
      return null;
    }

    deleting.delete(p);
    return null;
  }

  public Product deleteBySelected(Product selected)
  {
    if (selected == null)
    {
      System.out.println("You have no product selected");
      return null;
    }

    Deleting deleting = new Deleting(db);
    deleting.delete(selected);

    return null;
  }
/*---END OF DELETING--- */

  /*---START OF PRINTING--- */

  public Product print()
  {
    System.out.println("You are now printing");
    String choice;

    HashMap<String, String> commands = new HashMap<>();
    commands.put("All", "prints all products in all categories in the database");
    commands.put("Category", "prints all products in a single category");
    commands.put("Help", "prints these options again");
    commands.put("Exit", "exits to main menu");

    System.out.println("Possible commands: ");
    for (String command : commands.keySet())
    {
      System.out.println(command + ": " + commands.get(command));
    }
    System.out.println();

    System.out.print("Your command: ");

    choice = scanner.nextLine();
    choice = DecisionHandler.handleDecisions(choice, commands.keySet(), scanner);
    choice = choice.toLowerCase();

    switch(choice)
    {
      case "all":
        printAll();
        break;
      case "category":
        printCategory();
        break;
      case "help":
        print();
        break;
      case "exit":
        break;
    }
    return null;
  }

  public void printAll() {
    for (Product.ProductCategory category : Product.ProductCategory.values())
    {
      System.out.println("Printing all products in category: " + category.toString());

      db.printAll(db.getPricesList(category.toString()));
      System.out.println("Press enter to continue printing next categories");
      scanner.nextLine();
    }
  }

  public void printCategory()
  {
    System.out.println("You are now printing by category");
    System.out.println("This will print the category by price as default");

    String category;

    System.out.println("Enter the category you want print:");
    category = DecisionHandler.getCategory("printing", scanner).toString();

    System.out.println("Printing all products in category: " + category);
    System.out.println();

    db.printAll(db.getPricesList(category));
  }
  /*---END OF PRINTING--- */

}