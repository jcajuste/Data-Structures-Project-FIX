import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class DecisionHandler{
    //
    public static Product.ProductCategory getCategory (String task, Scanner scanner){
//        Scanner scanner = new Scanner(System.in);
//        categoryType();
        System.out.print("Possible categories: ");
        for (Product.ProductCategory c : Product.ProductCategory.values()){
            System.out.print(String.valueOf(c) + ", ");
        }
        System.out.println();

        System.out.print("Please choose the category for " + task + ": ");
        String category = scanner.nextLine();
        category = category.toLowerCase();

        switch(category)
        {
            case "jeans":
                return Product.ProductCategory.Jeans;
//                break;
            case "hat":
                return Product.ProductCategory.Hat;
//                break;
            case "jacket":
                return Product.ProductCategory.Jacket;
//                break;
            case "shirt":
                return Product.ProductCategory.Shirt;
//                break;
            case "tshirt":
                return Product.ProductCategory.TShirt;
//                break;
            case "shoes":
                return Product.ProductCategory.Shoes;
//                break;
            case "shorts":
                return Product.ProductCategory.Shorts;
//                break;
            default:
                System.out.println("That was not a valid category");
                System.out.print("Categories: [");

                for (Product.ProductCategory c : Product.ProductCategory.values()){
                    System.out.print(String.valueOf(c) + ", ");
                }
                System.out.print("]");
                System.out.println();
                return getCategory(task, scanner);
        }
    }



    //method for handling a question that has a decision based from an array like their backpack and ext.
    public static String handleDecisions(String decision, String[] validDecisions, Scanner scanner)
    {
//        Scanner keyboard = new Scanner(System.in);
        ArrayList<String> valid = new ArrayList<String>();
        ArrayList<String> valid2 = new ArrayList<String>();
        for (String s : validDecisions)
        {
            valid.add(s);
            valid2.add(s.toLowerCase());
        }

        if (decision.equals("")) decision = scanner.nextLine();

        while(!valid.contains(decision) && !valid2.contains(decision.toLowerCase()))
        {
            System.out.print("That was not a valid decision." +
                    "\nInput a valid decision [" + getArrayContents(validDecisions) + "]: ");
            decision = scanner.nextLine();
        }
//        keyboard.close();
        return decision;
    }

    //method for handling a question that has a decision based from an array like their backpack and ext.
    public static String handleDecisions(String decision, Set<String> validDecisions, Scanner scanner)
    {
//        Scanner keyboard = new Scanner(System.in);
        ArrayList<String> valid2 = new ArrayList<String>();
        for (String s : validDecisions)
        {
            valid2.add(s.toLowerCase());
        }

        if (decision.equals("")) decision = scanner.nextLine();

        while(!validDecisions.contains(decision) && !valid2.contains(decision.toLowerCase()))
        {
            System.out.print("That was not a valid decision." +
                    "\nInput a valid decision [" + getArrayContents(validDecisions) + "]: ");
            decision = scanner.nextLine();
        }
//        scanner.close();
        return decision;
    }

    public static String getArrayContents(String[] valid)
    {
        String result = "";

        for (String option : valid) result += option + ", ";

        return result;
    }

    public static String getArrayContents(Set<String> valid)
    {
        String result = "";

        for (String option : valid) result += option + ", ";

        return result;
    }

    //get category with use of WordSuggester
    public static Product.ProductCategory getCategoryWithSuggestion (WordSuggester suggester, Scanner scanner){

        System.out.print("Possible categories: ");
        for (Product.ProductCategory c : Product.ProductCategory.values()){
            System.out.print(String.valueOf(c) + ", ");
        }
        System.out.println();

        System.out.print("Please choose the category: ");
        String category = scanner.nextLine();
        category = suggester.suggest(category, scanner);
        category = category.toLowerCase();

        switch(category)
        {
            case "jeans":
                return Product.ProductCategory.Jeans;
//                break;
            case "hat":
                return Product.ProductCategory.Hat;
//                break;
            case "jacket":
                return Product.ProductCategory.Jacket;
//                break;
            case "shirt":
                return Product.ProductCategory.Shirt;
//                break;
            case "tshirt":
                return Product.ProductCategory.TShirt;
//                break;
            case "shoes":
                return Product.ProductCategory.Shoes;
//                break;
            case "shorts":
                return Product.ProductCategory.Shorts;
//                break;
            default:
                System.out.println("That was not a valid category");
                System.out.print("Categories: [");

                for (Product.ProductCategory c : Product.ProductCategory.values()){
                    System.out.print(String.valueOf(c) + ", ");
                }
                System.out.print("]");
                System.out.println();
                return getCategoryWithSuggestion(suggester, scanner);
        }
    }

    // public static ProperINT (int value, Scanner scanner){
    //     //write code that checks if the given value input is acually a int value

    //     //and if it is, return the int value
    //     //if not, return the proper error message
    //     //write code that prints out the proper error message
    //     // if (!value == int){
    //     //     System.out.println("That was not a valid number");
    //     // }
    //     return null;
    // }

}