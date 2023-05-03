# Data-Structures-Project-FIX
DATA STRUCTURES PROJECT
- Jefferson Cajuste
- Michael Doyle
- Ryder Raymond

How to run:

The class/method to run is the Main.java file's main method.
It initializes the database and the UI takes over from there.

There is a second constructor for the database if you want to initialize it with different
numbers of items for each product category.

The program should be self-explanatory for the most part.

It will ask you for what action you want to do, giving you only the valid options.
- Searching includes searching by title, range, and with suggestion.
    - searching by title or suggestion selects the product that matches, allowing for deletion by selection
- Inserting includes only inserting by explicitly stating the information for each property of the product.
- Sorting includes sorting by date or price, either in order or in reverse for both.
- Deleting includes deleting by title or by the item that is selected from searching
- Printing includes printing all items or printing a specific category
    - searching by range is basically printing but only a range of a category
    - printing all items waits for the user to hit enter before printing the next category

Each option allows exiting back to the main menu or printing the possible commands again with "help"

