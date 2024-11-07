import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
public abstract class SearchStrategy {
/*Class:SearchStrategy class
 * Author:Arda Baran
 * Description:
  *   The SearchStrategy class serves as an abstract base class for implementing different 
 *   search strategies. It defines the structure for a strategy that can be used to search 
 *   for books based on various criteria, such as ISBN, author, or publisher. 
 *
 *   This class supports the Strategy design pattern, allowing interchangeable search 
 *   strategies to be used in the BookSearch context.For example, AVL data structure is used when searching by ISBN and 
 *   HashMap and HashSet data structures are used when searching by Publisher Name or Author Name. Subclasses of SearchStrategy will 
 *   implement specific search logic based on different criteria.* 
 * 
 * 
 * 
 */
	  protected static String[] parseCSVLine(String line) {
	    
	        boolean inQuotes = false;
	        StringBuilder sb = new StringBuilder();
	        java.util.List<String> fields = new java.util.ArrayList<>();

	        for (char c : line.toCharArray()) {
	            if (c == '"') {
	                inQuotes = !inQuotes; 
	            } else if (c == ',' && !inQuotes) {
	                fields.add(sb.toString().trim());
	                sb.setLength(0); 
	            } else {
	                sb.append(c);
	            }
	        }
	        fields.add(sb.toString().trim()); 

	        return fields.toArray(new String[0]);
	    }
        public abstract void loadBooks();//There are 101417 books in the csv file. reads all books and places them into avl tree or hashmap
	    public abstract void search(String query);
	}
class BookSearch {
	/*Class:BookSearch class
	 * Author:Arda Baran
	 * Description:
	*   The BookSearch class is responsible for performing searches on a collection of books 
 *   using a specified search strategy. This class accepts a SearchStrategy object, allowing 
 *   it to execute searches based on the provided strategy. This approach enables dynamic 
 *   selection of search criteria.
 *
 *   The BookSearch class acts as the context in the Strategy pattern, where different 
 *   search behaviors (strategies) can be applied at runtime. 
	 * 
	 * 
	 * 
	 */  
	
	
	
	private SearchStrategy strategy;

    public BookSearch(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public SearchStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(SearchStrategy strategy) {
        this.strategy = strategy;
    }

    public void search(String query) {
        strategy.search(query);
    }
}
class IsbnSearchStragety extends SearchStrategy{
	/*Class:IsbnSearchStragety class
	 * Author:Arda Baran
	 * Description:
	 *   The IsbnSearchStrategy class implements the SearchStrategy interface and provides 
 *   specific logic for searching books by their ISBN. This class is responsible for 
 *   locating books based on the exact ISBN match, making it suitable for users who 
 *   need to locate a book with a known ISBN number.It uses AVL tree for searching books by their ISBN.
	 * 
	 * 
	 * 
	 */  
	
	
	
	AVL avl = new AVL();
	@Override
	public void loadBooks() {
        String csvFile = "resources/books.csv";
        ClassLoader classLoader = Driver.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(csvFile);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            br.readLine(); 

            while ((line = br.readLine()) != null) {
                String[] bookData = parseCSVLine(line);

                if (bookData.length < 13) {
                   
                    continue;
                }

                try {
                    
                   
                	 String title = bookData[0];
             	    String authorName = bookData[1];
             	    String publisherName = bookData[2];
             	    int pageNum = Integer.parseInt(bookData[3].trim());
             	    String language = bookData[4];
             	    double discountRate = Double.parseDouble(bookData[5].replace("%", "").trim());
             	    
             	    
             	    double discountedPrice = bookData[6].equalsIgnoreCase("nan") ? 0.0 : Double.parseDouble(bookData[6].replace(".", "").trim());
             	    double originalPrice = bookData[7].equalsIgnoreCase("nan") ? 0.0 : Double.parseDouble(bookData[7].replace(".", "").trim());
             	    
             	    int rating = Integer.parseInt(bookData[8].trim());
             	    int reviewedTimes = Integer.parseInt(bookData[9].trim());
             	    String cover = bookData[10];
             	    String paperType = bookData[11];
             	    String isbn = bookData[12];

             	   Book book = new Book(title, new Author(authorName), new Publisher(publisherName), pageNum, language, discountRate, discountedPrice,
           	            originalPrice, rating, reviewedTimes, cover, paperType, isbn);
                    avl.insertBookToAvl(book);
                } catch (NumberFormatException e) {
                    System.err.println("Incorrect Format at line: " + line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public Book getBookByIsbn(String query) {
		return avl.getBookByIsbn(query);
	}
	
	@Override
	public void search(String query) {
	avl.searchBookByIsbn(query);
	
	}
	
}

	class AuthorSearchStrategy extends SearchStrategy {
		/*Class:AuthorSearchStragety class
		 * Author:Arda Baran
		 * Description:
		 *   The AuthorSearchStrategy class implements the SearchStrategy interface and provides 
 *   specific logic for searching books by their author’s name. This class enables users 
 *   to locate books written by a particular author.It uses HashMap and HashSet to searching books by Author name.
		 * 
		 * 
		 * 
		 */  
		
		
		
		@Override
		public void loadBooks() {
	        String csvFile = "resources/books.csv";
	        ClassLoader classLoader = Driver.class.getClassLoader();

	        try (InputStream inputStream = classLoader.getResourceAsStream(csvFile);
	             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

	            String line;
	            br.readLine(); 

	            while ((line = br.readLine()) != null) {
	                String[] bookData = parseCSVLine(line);

	                if (bookData.length < 13) {
	                  
	                    continue;
	                }

	                try {
	                    
	                   
	                	 String title = bookData[0];
	             	    String authorName = bookData[1];
	             	    String publisherName = bookData[2];
	             	    int pageNum = Integer.parseInt(bookData[3].trim());
	             	    String language = bookData[4];
	             	    double discountRate = Double.parseDouble(bookData[5].replace("%", "").trim());
	             	    
	             	 
	             	    double discountedPrice = bookData[6].equalsIgnoreCase("nan") ? 0.0 : Double.parseDouble(bookData[6].replace(".", "").trim());
	             	    double originalPrice = bookData[7].equalsIgnoreCase("nan") ? 0.0 : Double.parseDouble(bookData[7].replace(".", "").trim());
	             	    
	             	    int rating = Integer.parseInt(bookData[8].trim());
	             	    int reviewedTimes = Integer.parseInt(bookData[9].trim());
	             	    String cover = bookData[10];
	             	    String paperType = bookData[11];
	             	    String isbn = bookData[12];
	                	
	             	   Book book = new Book(title, new Author(authorName), new Publisher(publisherName), pageNum, language, discountRate, discountedPrice,
	           	            originalPrice, rating, reviewedTimes, cover, paperType, isbn);
	             	  SingletonAuthorManager.getInstance().addBookToAuthor(new Author(authorName), book);
	                } catch (NumberFormatException e) {
	                    System.err.println("Incorrect Format at line: " + line);
	                }
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

		@Override
	    public void search(String author) {
			HashSet<Book> writtenBooks = SingletonAuthorManager.getInstance().getBooksByAuthorName(author);
			for (Book b : writtenBooks) {
				System.out.println(b);
			}
	        
	    }
	}
	class PublisherSearchStrategy extends SearchStrategy {
		/*Class:PublisherSearchStragety class
		 * Author:Arda Baran
		 * Description:
		*   The PublisherSearchStrategy class implements the SearchStrategy interface and provides 
 *   specific logic for searching books by their publisher’s name. This class is ideal 
 *   for locating books published by a particular publisher.It uses HashMap and HashSet for searching books by publisher name.
		 * 
		 * 
		 * 
		 */  
		@Override
		public void loadBooks(){
			String csvFile = "resources/books.csv";
	        ClassLoader classLoader = Driver.class.getClassLoader();

	        try (InputStream inputStream = classLoader.getResourceAsStream(csvFile);
	             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

	            String line;
	            br.readLine(); 

	            while ((line = br.readLine()) != null) {
	                String[] bookData = parseCSVLine(line);

	                if (bookData.length < 13) {
	                    
	                    continue;
	                }

	                try {
	                  
	                   
	                	 String title = bookData[0];
	             	    String authorName = bookData[1];
	             	    String publisherName = bookData[2];
	             	    int pageNum = Integer.parseInt(bookData[3].trim());
	             	    String language = bookData[4];
	             	    double discountRate = Double.parseDouble(bookData[5].replace("%", "").trim());
	             	    
	             	   
	             	    double discountedPrice = bookData[6].equalsIgnoreCase("nan") ? 0.0 : Double.parseDouble(bookData[6].replace(".", "").trim());
	             	    double originalPrice = bookData[7].equalsIgnoreCase("nan") ? 0.0 : Double.parseDouble(bookData[7].replace(".", "").trim());
	             	    
	             	    int rating = Integer.parseInt(bookData[8].trim());
	             	    int reviewedTimes = Integer.parseInt(bookData[9].trim());
	             	    String cover = bookData[10];
	             	    String paperType = bookData[11];
	             	    String isbn = bookData[12];         	
	                	
	             	   Book book = new Book(title, new Author(authorName), new Publisher(publisherName), pageNum, language, discountRate, discountedPrice,
	           	            originalPrice, rating, reviewedTimes, cover, paperType, isbn);
	             	  SingletonPublisherManager.getInstance().addBookToPublisher(new Publisher(publisherName), book);
	                } catch (NumberFormatException e) {
	                    System.err.println("Incorrect Format at line: " + line);
	                }
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }	
		}
		
		@Override
	    public void search(String publisherName) {
			HashSet<Book> publishedBooks = SingletonPublisherManager.getInstance().getBooksByPublisherName(publisherName);
			for (Book b : publishedBooks) {
				System.out.println(b);
			}
	    }
	}

	
	

