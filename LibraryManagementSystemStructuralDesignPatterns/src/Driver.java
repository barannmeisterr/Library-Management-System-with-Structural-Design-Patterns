import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.charset.StandardCharsets;
import java.util.*;
public class Driver {
	/*
	 * Class: Driver
	 * Author: Arda Baran
	 * Description:
	 *   The Driver class serves as the main entry point of the application. It demonstrates the 
	 *   functionality of different design patterns such as Singleton, Strategy, Decorator, Adaptor and Bridge 
	 *   by setting up and executing various operations on a collection of book data.
	 *
	 *   The Driver class initializes the book collection, applies different search strategies to 
	 *   search for books by ISBN, author, and publisher, and showcases the dynamic addition 
	 *   of functionality using decorators. Additionally, it demonstrates the flexibility 
	 *   of the Bridge Pattern by displaying book details in different formats.
	 *   
	 */
    public static void main(String[] args) {
      //STRAGETY PATTERN
    	 BookSearch bookSearch = new BookSearch(new IsbnSearchStragety());
    	 IsbnSearchStragety isbnSearch = (IsbnSearchStragety) bookSearch.getStrategy(); // access the correct strategy with Down Casting
         isbnSearch.loadBooks(); // load books to AVL Tree in order to search by ISBN number
         System.out.println("Searhing by ISBN Number(9786052180358)...");
         bookSearch.search("9786052180358"); // search by ISBN
         
         
    	System.out.println("--------------------------------------------------------------------------------------");
    	System.out.println();
    	
    	
    	bookSearch = new BookSearch(new AuthorSearchStrategy());
    	AuthorSearchStrategy authorSearch = (AuthorSearchStrategy) bookSearch.getStrategy(); 
    	authorSearch.loadBooks();
    	System.out.println("Searching By Author Name(Paulo Coelho)...");
    	bookSearch.search("Paulo Coelho");//search by author name
    	System.out.println();
    	System.out.println("--------------------------------------------------------------------------------------");
    	System.out.println();
    	bookSearch = new BookSearch(new PublisherSearchStrategy());
    	PublisherSearchStrategy publisherSearch = (PublisherSearchStrategy) bookSearch.getStrategy();
    	publisherSearch.loadBooks();
    	System.out.println("Searching By Publisher(İMAM RIZA DERGAHI KÜLTÜR ve ARAŞTIRMA DERNEĞİ)...");
    	bookSearch.search("İMAM RIZA DERGAHI KÜLTÜR ve ARAŞTIRMA DERNEĞİ");//search by publisher
    	System.out.println();
    	System.out.println("--------------------------------------------------------------------------------------");
    	System.out.println();
    	
    	
    	Book foundBook=isbnSearch.getBookByIsbn("9786052180358");
    	
    	System.out.println("Before Applying Decorators...");
    	System.out.println(foundBook);
    	
    	
        if (foundBook != null) {
        	
        	//BRIDGE PATTERN
        	
        	System.out.println("\nBook found. Applying Bridge Pattern With Formats...");
        	
        	
        	BookFormat printedFormat = new PrintedBookFormat();
             FormattedBook formattedPrintedBook = new FormattedBook(foundBook, printedFormat);

             // create bridge by using e book format
             BookFormat ebookFormat = new EBookFormat();
             FormattedBook formattedEBook = new FormattedBook(foundBook, ebookFormat);

             // show book details with different book formats
             System.out.println("\nPrinted Book Format:");
             formattedPrintedBook.showDetailsWithFormat();

             System.out.println("\nE-Book Format:");
             formattedEBook.showDetailsWithFormat();
        	
        	
           //DECORATOR PATTERN
        	System.out.println("\nBook found. Applying decorators...");

            // convert found book to BookComponent object
            BookComponent bookWithDecorators = new ConcreteBook(
                foundBook.getBookName(),
                foundBook.getAuthor(),
                foundBook.getPublisher(),
                foundBook.getPageNum(),
                foundBook.getLanguage(),
                foundBook.getDiscountRate(),
                foundBook.getDiscountedPrice(),
                foundBook.getOriginalPrice(),
                foundBook.getRating(),
                foundBook.getReviewedTimes(),
                foundBook.getCover(),
                foundBook.getPaperType(),
                foundBook.getIsbn()
            );

            // add extra discount to book
            bookWithDecorators = new SpecialDiscountDecorator(bookWithDecorators, 15.0); // %15 extra discount,%5 previous discount
                                                                                         
            // add user comment to book
            bookWithDecorators = new UserReviewDecorator(bookWithDecorators, "This book is awesome!");
           
            // display all features and recent added features of book
            System.out.println("\nBook Details with Applied Decorators:");
            bookWithDecorators.display();
           
        }  else {
            System.out.println("Book with ISBN not found.");
        }
        System.out.println("------------------------------------------------------------------------------");
       System.out.println("Adapter Pattern Applied For Loading Books Into The Avl Tree From JSON File...");
       List<Book> books = new ArrayList<>();
       
       // loads JSON file from src/resources path 
       ClassLoader classLoader = Driver.class.getClassLoader();
       try (InputStream inputStream = classLoader.getResourceAsStream("resources/bookDataForAdapterPattern.json")) { 
           if (inputStream == null) {
               throw new IllegalArgumentException("File not found!");
           }
           
           // Converts InputStream to JSON
           String jsonContent = new Scanner(inputStream, StandardCharsets.UTF_8).useDelimiter("\\A").next();
           JSONArray jsonArray = new JSONArray(jsonContent);

           // Each item in the json file converted to book object
           for (int i = 0; i < jsonArray.length(); i++) {
               JSONObject jsonData = jsonArray.getJSONObject(i);
               
               
               ExternalBookData externalBookData = new JsonBookData(jsonData);
               
               // converts to book object by using adapter pattern
               BookAdapter adapter = new BookAdapter(externalBookData);
               Book book = adapter.convertToBook();
               isbnSearch.avl.insertBookToAvl(book);//json file content get inserted into the avl tree.
              
             
           }

        isbnSearch.search("9780451524935");//search by isbn which is from json file.
           
       } catch (Exception e) {
           e.printStackTrace();
       }  
       
        
}
}