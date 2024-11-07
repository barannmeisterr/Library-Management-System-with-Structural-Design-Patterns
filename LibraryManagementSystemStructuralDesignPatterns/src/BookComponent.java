
public interface BookComponent {
	/*Class:BookComponent Interface
	 * Author:Arda Baran
	 * Description:
	  *   The BookComponent interface defines the core structure of the book component in the 
 *   Decorator Pattern. It provides an abstract contract for operations that can be extended 
 *   with additional functionality by concrete decorators.
 *
 *   This interface is implemented by both the ConcreteBook and BookDecorator classes, enabling 
 *   decorators to wrap ConcreteBook objects to dynamically add new behaviors without modifying 
 *   the original class.
	 * 
	 * 
	 * 
	 */
	public void display();
}
class ConcreteBook extends Book implements BookComponent {
	/*Class:ConcreteBook
	 * Author:Arda Baran
	 * Description:
	 *   The ConcreteBook class implements the BookComponent interface and represents a real 
 *   book object with detailed information such as title, author, publisher, price, discount, 
 *   and more. This class serves as the core object that can be decorated to add extra 
 *   functionalities dynamically.
 *
 *   ConcreteBook holds the primary data and functionality for a book, which decorators 
 *   can enhance without altering this class directly.
	 * 
	 * 
	 * 
	 */
	public ConcreteBook(String bookName, Author author, Publisher publisher, int pageNum, String language,
                        double discountRate, double discountedPrice, double originalPrice, int rating,
                        int reviewedTimes, String cover, String paperType, String isbn) {
        super(bookName, author, publisher, pageNum, language, discountRate, discountedPrice, originalPrice, rating, reviewedTimes, cover, paperType, isbn);
    }

    public double calculateNewDiscountedPrice(double extraDiscountRate) {
        double newDiscountRate = this.discountRate + extraDiscountRate;
        return newDiscountRate;
    }

    @Override
    public void display() {
        System.out.println(this.toString());
    }
}

abstract class BookDecorator implements BookComponent {
	/*Class:BookDecorator
	 * Author:Arda Baran
	 * Description:
	*   The BookDecorator class is an abstract class that implements the BookComponent interface 
 *   and serves as a base class for all decorators. This class holds a reference to a 
 *   BookComponent object, enabling decorators to wrap around ConcreteBook objects to 
 *   add additional behaviors.
 *
 *   By extending BookDecorator, subclasses can add extra functionality by overriding the 
 *   display() method without modifying the ConcreteBook itself.
	 * 
	 * 
	 * 
	 */
	protected BookComponent book;

    public BookDecorator(BookComponent book) {
        this.book = book;
    }

    @Override
    public void display() {
        book.display();
    }
}
class SpecialDiscountDecorator extends BookDecorator {
	/*Class:SpecialDiscountDecorator
	 * Author:Arda Baran
	 * Description:
	 *   The SpecialDiscountDecorator class extends the BookDecorator to add an extra discount 
 *   feature to the book's discount rate. This decorator allows users to apply additional 
 *   discounts to books without modifying the original ConcreteBook class.
	 * 
	 * 
	 * 
	 */
	
	double extraDiscountRate;

     SpecialDiscountDecorator(BookComponent book, double extraDiscountRate) {
        super(book);
        this.extraDiscountRate = extraDiscountRate;
    applyExtraDiscount();
     }

    @Override
    public void display() {
        super.display();
       
    }

    public void applyExtraDiscount() {
        if (book instanceof ConcreteBook) {
            double newDiscountRate = ((ConcreteBook) book).calculateNewDiscountedPrice(extraDiscountRate);
            ((ConcreteBook) book).setDiscountRate(newDiscountRate);
            
            double updatedDiscountRate = ((ConcreteBook) book).getDiscountRate(); 
            double newDiscountedPrice = ((ConcreteBook) book).getOriginalPrice() - ((((ConcreteBook) book).getOriginalPrice() * updatedDiscountRate) / 100);            
            
            ((ConcreteBook) book).setDiscountedPrice(newDiscountedPrice);
            System.out.println("Total Discount Rate with Extra Discount: "+((ConcreteBook) book).getDiscountRate());
            System.out.println("Total Discounted Price with Extra Discount: " + ((ConcreteBook) book).getDiscountedPrice());
        } else {
            System.out.println("No extra discount applied...");
        }
    }
}

class UserReviewDecorator extends BookDecorator {
	/*Class:UserReviewDecorator
	 * Author:Arda Baran
	 * Description:
	 *   The UserReviewDecorator class extends the BookDecorator to add user review functionality 
 *   to a book. This decorator allows users to attach a review to the book without altering 
 *   the original ConcreteBook class.
	 * 
	 * 
	 * 
	 */
	private String review;
    public UserReviewDecorator(BookComponent book, String review) {
        super(book);
        this.review = review;
    }

    @Override
    public void display() {
        super.display();
        addReview();
    }

    private void addReview() {
        System.out.println("User Review: " + review);
    }
}

