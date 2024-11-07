import java.util.*;

public class Book {
	/*Class:Book class
	 * Author:Arda Baran
	 * Description:
	 * this class represents book in csv file.
	 * Each book is written by an author and published by a publisher company.
	 * 
	 * 
	 */
String bookName,language,cover,paperType,isbn;
int pageNum,rating,reviewedTimes;
double discountRate,originalPrice,discountedPrice;

Author author;
Publisher publisher;

public Book(String bookName,Author author,Publisher publisher,int pageNum,String language,double discountRate,double discountedPrice,
		double originalPrice,int rating,int reviewedTimes,String cover,String paperType,String isbn) {
	this.bookName=bookName;
	this.author=author;
	this.publisher=publisher;
	this.pageNum=pageNum;
	this.language=language;
	this.discountRate=discountRate;
	this.discountedPrice=discountedPrice;
	this.originalPrice=originalPrice;
	this.rating=rating;
	this.reviewedTimes=reviewedTimes;
	this.cover=cover;
	this.paperType=paperType;
	this.isbn=isbn;
}

public String getBookName() {
	return bookName;
}

public void setBookName(String bookName) {
	this.bookName = bookName;
}

public String getLanguage() {
	return language;
}

public void setLanguage(String language) {
	this.language = language;
}

public String getCover() {
	return cover;
}

public void setCover(String cover) {
	this.cover = cover;
}

public String getPaperType() {
	return paperType;
}

public void setPaperType(String paperType) {
	this.paperType = paperType;
}

public String getIsbn() {
	return isbn;
}

public void setIsbn(String isbn) {
	this.isbn = isbn;
}

public int getPageNum() {
	return pageNum;
}

public void setPageNum(int pageNum) {
	this.pageNum = pageNum;
}

public int getRating() {
	return rating;
}

public void setRating(int rating) {
	this.rating = rating;
}

public int getReviewedTimes() {
	return reviewedTimes;
}

public void setReviewedTimes(int reviewedTimes) {
	this.reviewedTimes = reviewedTimes;
}

public double getDiscountRate() {
	return discountRate;
}

public void setDiscountRate(double discountRate) {
	this.discountRate = discountRate;
}

public double getOriginalPrice() {
	return originalPrice;
}

public void setOriginalPrice(double originalPrice) {
	this.originalPrice = originalPrice;
}

public double getDiscountedPrice() {
	return discountedPrice;
}

public void setDiscountedPrice(double discountedPrice) {
	this.discountedPrice = discountedPrice;
}

public Author getAuthor() {
	return author;
}

public void setAuthor(Author author) {
	this.author = author;
}

public Publisher getPublisher() {
	return publisher;
}

public void setPublisher(Publisher publisher) {
	this.publisher = publisher;
}
@Override

public String toString() {
    return "Book{" +
            "Title='" + getBookName() + '\'' +
            ", Author='" + (author != null ? author.getNameSurname() : "Unknown") + '\'' +
            ", Publisher='" + (publisher != null ? publisher.getPublisherName() : "Unknown") + '\'' +
            ", Page Number=" + getPageNum() +
            ", Language='" + getLanguage() + '\'' +
            ", Discount Rate=" + getDiscountRate() + "%" +
            ", Discounted Price=" + getDiscountedPrice() +
            ", Original Price=" + getOriginalPrice() +
            ", Rating=" + getRating() +
            ", Reviewed Times=" + getReviewedTimes() +
            ", Cover='" + getCover() + '\'' +
            ", Paper Type='" + getPaperType() + '\'' +
            ", ISBN='" + isbn + '\'' +
            '}';
}


}


class Author{
	/*Class:Author class
	 * Author:Arda Baran
	 * Description:
	 * this class represents author in csv file.Authors write books and these books are kept in writtenBooks hashSet.
	 * 
	 * 
	 * 
	 */
	String nameSurname;
HashSet <Book> writtenBooks;
Author(String nameSurname){
	this.nameSurname=nameSurname;
	this.writtenBooks=new HashSet<>();
	
}
public String getNameSurname() {
	return nameSurname;
}
public void setNameSurname(String nameSurname) {
	this.nameSurname = nameSurname;
}
public HashSet<Book> getWrittenBooks() {
	return writtenBooks;
}
public void setWrittenBooks(HashSet<Book> writtenBooks) {
	this.writtenBooks = writtenBooks;
}
public void addBookToAuthor(Book book) {
	if(book==null) {
		return;
	}
writtenBooks.add(book);
}
}


class Publisher{
	/*Class:Publisher class
	 * Author:Arda Baran
	 * Description:
	 * this class represents Publisher companies.All books in the csv file are published by a publisher company.So,a publisher can
	 * be defined as with name of publisher company and the set of books that published by a publisher company.
	 * 
	 * 
	 */	
	String publisherName;
	HashSet<Book> publishedBooks;
	Publisher(String publisherName){
		this.publisherName=publisherName;
		this.publishedBooks=new HashSet<>();
	}
	public String getPublisherName() {
		return publisherName;
	}
	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}
	public HashSet<Book> getPublishedBooks() {
		return publishedBooks;
	}
	public void setPublishedBooks(HashSet<Book> publishedBooks) {
		this.publishedBooks = publishedBooks;
	}
public void addBookToPublisher(Book book) {
	if(book==null) {
		return;
	}
publishedBooks.add(book);
}

}
class SingletonAuthorManager {
	/*Class:SingletonAuthorManager
	 * Author:Arda Baran
	 * Description:
	 *   The SingletonAuthorManager class manages author-related operations in the application. 
     *   This class uses the Singleton design pattern, ensuring that only one instance of the class 
     *   is created during the application's lifecycle. This structure allows efficient management 
     *   and access to the same collection of authors throughout the application.
   
     *   The class maintains a `HashMap<String, HashSet<Book>>` where each author's name is mapped 
     *   to a set of books associated with that author. It allows adding authors, updating 
     *   the list of books associated with each author, and retrieving author information.
	 * 
	 * 
	 */
    private static SingletonAuthorManager instance;   
    private HashMap<String, HashSet<Book>> authorList;
    private SingletonAuthorManager() {
        authorList = new HashMap<>();
    }

    public static SingletonAuthorManager getInstance() {
        if (instance == null) {
            instance = new SingletonAuthorManager();
        }
        return instance;
    }
    
    public void addBookToAuthor(Author author, Book book) {
        if (author == null || book == null) {
            return;
        }
        String authorName = author.getNameSurname();
        
        authorList.computeIfAbsent(authorName, k -> new HashSet<>()).add(book);
        author.addBookToAuthor(book); 
    }
   
    public HashSet<Book> getBooksByAuthorName(String authorName) {
        return authorList.getOrDefault(authorName, new HashSet<>());
    }

  
    public HashMap<String, HashSet<Book>> getAllAuthors() {
        return authorList;
    }
}
class SingletonPublisherManager {
	/*Class:SingletonPublisherManager
	 * Author:Arda Baran
	 * Description:
 *   The SingletonPublisherManager class is responsible for managing publisher-related operations 
 *   within the application. By implementing the Singleton design pattern, this class ensures that 
 *   only one instance of the publisher manager exists throughout the application's lifecycle. 
 *   This approach allows for efficient and consistent access to the same collection of publishers 
 *   and their published books across the application.
 *
 *   The class uses a `HashMap<String, HashSet<Book>>` to store each publisher's name as a key, 
 *   mapping it to a set of books associated with that publisher. This structure provides an 
 *   efficient way to add, update, and retrieve publisher information and their books.
	 * 
	 * 
	 * 
	 * 
	 */
    private static SingletonPublisherManager instance; 
    private HashMap<String, HashSet<Book>> publisherList;  
    private SingletonPublisherManager() {
        publisherList = new HashMap<>();
    }  
    public static SingletonPublisherManager getInstance() {
        if (instance == null) {
            instance = new SingletonPublisherManager();
        }
        return instance;
    } 
    public void addBookToPublisher(Publisher publisher, Book book) {
        if (publisher == null || book == null) {
            return;
        }
        String publisherName = publisher.getPublisherName();            
        publisherList.computeIfAbsent(publisherName, k -> new HashSet<>()).add(book);
        publisher.addBookToPublisher(book); 
    }
 
    public HashSet<Book> getBooksByPublisherName(String publisherName) {
        return publisherList.getOrDefault(publisherName, new HashSet<>());
    }
    public HashMap<String, HashSet<Book>> getAllPublishers() {
        return publisherList;
    }
}