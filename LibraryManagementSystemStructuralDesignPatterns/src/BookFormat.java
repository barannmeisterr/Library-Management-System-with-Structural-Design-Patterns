
public interface BookFormat {
	/*Class:BookFormat Interface
	 * Author:Arda Baran
	 * Description:
	*   The BookFormat interface defines the blueprint for various book formats, such as printed books 
 *   and e-books. It represents the "implementation" side of the Bridge Pattern, where different 
 *   format-specific behaviors can be defined independently of the Book abstraction.
 *
 *   This interface provides a method, `printFormatDetails(Book book)`, which concrete format 
 *   classes (like PrintedBookFormat and EBookFormat) will implement to display format-specific details.
	 * 
	 * 
	 * 
	 */
	void printFormatDetails(Book book);
}
class FormattedBook {
	/*Class:FormattedBook class
	 * Author:Arda Baran
	 * Description:
	 *   The FormattedBook class acts as the "abstraction" side of the Bridge Pattern, bridging 
 *   the Book object and the BookFormat interface. This class allows a Book to be displayed 
 *   with its specific format, enabling format-specific details to be dynamically applied 
 *   to a Book without modifying the Book class itself.
 *
 *   By using the BookFormat interface, FormattedBook allows switching between various formats 
 *   (e.g., printed and e-book) while still representing the same Book object.
	 * 
	 * 
	 * 
	 */
	private Book book;
    private BookFormat format;

    public FormattedBook(Book book, BookFormat format) {
        this.book = book;
        this.format = format;
    }

    public void showDetailsWithFormat() {
        System.out.println(book);  
        format.printFormatDetails(book); 
    }
}

class PrintedBookFormat implements BookFormat {
	/*Class:PrintedBookFormat class
	 * Author:Arda Baran
	 * Description:
	 *   The PrintedBookFormat class implements the BookFormat interface and provides specific 
 *   details for printed books. This class includes attributes unique to printed books, 
 *   such as cover type and paper quality, which are displayed alongside general book details.
 *
 *   PrintedBookFormat represents one possible implementation of the BookFormat interface, 
 *   adding flexibility to display the same Book instance in different formats. 
	 * 
	 * 
	 * 
	 */
	@Override
    public void printFormatDetails(Book book) {
        System.out.println("Format: Printed Book");
        System.out.println("Cover: " + book.getCover());
        System.out.println("Paper Type: " + book.getPaperType());
    }
}

class EBookFormat implements BookFormat {
	/*Class:EBookFormat class
	 * Author:Arda Baran
	 * Description:
	*   The EBookFormat class implements the BookFormat interface and provides specific 
 *   details for e-books. This format highlights digital-specific attributes, such as 
 *   download options and compatibility, which are unique to e-books.
 *
 *   EBookFormat is another implementation of the BookFormat interface, demonstrating 
 *   how the Bridge Pattern allows books to be displayed in various formats independently 
 *   of the Book class. 
	 * 
	 * 
	 * 
	 */    
	
	@Override
    public void printFormatDetails(Book book) {
        System.out.println("Format: e-Book");
        System.out.println("This book is available in digital format with download options.");
    }
}
