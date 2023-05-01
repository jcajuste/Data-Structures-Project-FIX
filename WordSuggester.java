/*
Word Suggester for the bonus problem.

Used in UI's method to search title with word suggestion (this class's functionality is limited to that part of UI
so if this has any bugs, it does not impact the entire rest of the code.)

The text file with the list of the 1000 most common English words is from this GitHub post: https://gist.github.com/deekayen/4148741
from the user "deekayen" (David Norman)

proper MLA citation for the page:

Norman, David. "1,000 most common US English words." GitHub, https://gist.github.com/deekayen/4148741

Everything else is our own code.

 */


 import java.io.FileNotFoundException;
 import java.util.*;
 import java.io.File;
 
 public class WordSuggester {
     private static ArrayList<String> commonWords;  //list of all words
     private static HashSet<String> wordSet;       //set for quick lookups
     private static final int min_diff = 1;
 
     private final int max_diff; //maximum difference between words for the suggester to still recommend
 
 
     public WordSuggester(int diff)
     {
         //the minimum difference must be at least 2
         max_diff = Math.max(min_diff, diff);
         commonWords = new ArrayList<>(1000);
         wordSet = new HashSet<>(2000);
 
         //adding words to the list from the file. Try/catch is for if the file is not found
         try {
             String path = "Words.txt";
 
             //getting the root and adding ./src/ is only necessary for in intellij, should not be an issue otherwise
             File root = new File("./");
             for (String s : Objects.requireNonNull(root.list()))
             {
 //                System.out.println(s);
                 if (s.equals("src"))
                     path = "./src/" + path;
             }
             File words = new File(path);
             Scanner getWords = new Scanner(words);  //Scanner to read the file
 
             //add each line (each word)
             while (getWords.hasNext())
             {
                 commonWords.add(getWords.nextLine());
             }
 
             getWords.close();  //remember to close
         }
         catch (FileNotFoundException f)
         {
             System.out.println("There was a problem reading the most common words.");
             System.out.println(f);
             System.out.println("Please do not use search with suggestion until this is fixed.");
         }
 
         wordSet.addAll(commonWords);
 
         //add the categories in case they are not in the list already
         for (Product.ProductCategory category : Product.ProductCategory.values())
         {
             String cat = category.toString().toLowerCase();
             if (!wordSet.contains(cat))
             {
                 wordSet.add(cat);
                 commonWords.add(0, cat);  //add it first so in case there is an issue the categories are recommended first
             }
         }
     }
 
     //word suggestion based on most common words
     public String suggest(String input, Scanner scanner)
     {
         String parsed = "";
 
         //if this is in the most common words, this is a real word so return it
         if (wordSet.contains(input.toLowerCase()))
             return input;
 
         String[] parts = parseInput(input);
 
         for (String part : parts)
         {
             if (!Character.isDigit(part.charAt(0)))
                 part = autocorrectPart(part, scanner);
             parsed += part;
         }
 
         System.out.println("The result of suggestion is " + parsed);
         return parsed;
     }
 
     private String autocorrectPart(String input, Scanner scanner)
     {
         if (wordSet.contains(input.toLowerCase()))
             return input;
 
         ArrayList<String> possibleWords = new ArrayList<>();
         int differences = 0;
         String choice;
 
         //compare the input to all common words
         for (String word : commonWords)
         {
             differences = 0;  //number of differences between the two
 
             if (word.length() != input.length()) continue;  //only compare words that are the same length for simplicity
 
             //compare each character at the same position in both words
             for (int i = 0; i < input.length(); i++)
             {
                 char inputChar = input.charAt(i);
                 char secondChar = word.charAt(i);
 
                 if (inputChar != secondChar)  //increase differences if the two characters are not the same
                     differences++;
 
                 if (differences > max_diff)  //if there are too many differences, this is not likely the word they meant to use
                     break;
             }
 
             //only add words that may be the word they meant to use (not too many differences)
             if (differences <= max_diff)
                 possibleWords.add(word);
 
         }
 
         System.out.println("The input \"" + input + "\" may not be a proper English word");
         System.out.println("Possible replacements: ");
         for (String word : possibleWords)
         {
             if (word.length() < 2)
                 System.out.print(word + ", ");
             else
                 System.out.print(word.substring(0,1).toUpperCase() + word.substring(1) + ", ");
 
         }
 
         System.out.println("\nEnter the original input \"" + input + "\" or one of the suggested words to continue:");
 
         choice = scanner.nextLine();
 
         while (!(choice.equalsIgnoreCase(input) || wordSet.contains(choice.toLowerCase()))) {
             System.out.println("\nEnter the original input \"" + input + "\" or one of the suggested words to continue:");
             for (String word : possibleWords){
                 if (word.length() < 2)
                     System.out.print(word + ", ");
                 else
                     System.out.print(word.substring(0,1).toUpperCase() + word.substring(1) + ", ");
             }
             System.out.println();
             choice = scanner.nextLine();
         }
 
         return choice;
     }
 
     private String[] parseInput(String input)
     {
         String curr = "";
         ArrayList<String> parts = new ArrayList<>();
 
         for (Character c : input.toCharArray()){
             if (c == ' ')
             {
                 parts.add(curr);
 
             }
             else if (Character.isDigit(c) && (curr.length() == 0 || Character.isDigit(curr.charAt(0))))
             {
                 curr += c;
             }
             else if (Character.isDigit(c) && (curr.length() > 0 && !Character.isDigit(curr.charAt(0))))
             {
                 parts.add(curr);
                 curr = "" + c;
             }
             else if (!Character.isDigit(c))
             {
                 curr += c;
             }
         }
         if (curr.length() > 0)
             parts.add(curr);
 
         return parts.toArray(new String[0]);
     }
 
 }
 