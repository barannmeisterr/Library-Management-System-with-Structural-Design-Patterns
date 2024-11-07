import org.json.JSONObject;
interface ExternalBookData {
	/*Class:ExternalBookData Interface
	 * Author:Arda Baran
	 * Description:
	  *   The ExternalBookData interface represents the implementation of Adapter Pattern and 
	  *    defines the structure of book data coming from an external source, 
 *   such as a JSON file. It provides a uniform way to access book properties (e.g., title, author, 
 *   publisher) regardless of the data's original format, allowing various data formats to adapt 
 *   seamlessly to the application's internal `Book` class structure.
	 * 	 
	 */
	
	String getBookName();
    String getWriter();
    String getPublisher();
    int getPageNumber();
    String getLanguage();
    double getPrice();
    String getIsbn();
}

public class BookAdapter {
/*Class:BookAdapter
 * Author:Arda Baran
 * Description:
 *   The BookAdapter class serves as an adapter, converting `ExternalBookData` (JSON data)
 *   into a `Book` object that is compatible with the application. It acts as a bridge between 
 *   the external data source and the application's internal `Book` class by transforming data 
 *   into the appropriate format and structure. 
 * 	 
 */
	
	private ExternalBookData externalData;

	    public BookAdapter(ExternalBookData externalData) {
	        this.externalData = externalData;
	    }

	    public Book convertToBook() {
	        Author author = new Author(externalData.getWriter());
	        Publisher publisher = new Publisher(externalData.getPublisher());

	        return new Book(
	                externalData.getBookName(),
	                author,
	                publisher,
	                externalData.getPageNumber(),
	                externalData.getLanguage(),
	                0.0, //set to default 0.0 value due to discount rate does not exist in json file
	                externalData.getPrice(),
	                externalData.getPrice(),
	                0, //set to default 0.0 value due to rating does not exist in json file
	                0, //set to default 0.0 value due to review  does not exist in json file
	                "Default Cover", //set to "Default Cover" due to cover does not exist in json file
	                "Default Paper", //set to "Default Paper" due to cover does not exist in json file
	                externalData.getIsbn()
	        );
	    }
	}
class JsonBookData implements ExternalBookData {
	/*Class:JsonBookData
	 * Author:Arda Baran
	 * Description:
	 *   The JsonBookData class is an implementation of the ExternalBookData interface, specifically 
 *   designed to handle book data in JSON format. This class wraps a JSON object and provides 
 *   methods to extract and return each property in a way that conforms to the ExternalBookData 
 *   interface. By implementing ExternalBookData, JsonBookData allows JSON-based data to be 
 *   processed and adapted to the application's `Book` model without requiring changes to the 
 *   original JSON structure. 	 
	 */   
	
	
	private JSONObject jsonData;

    public JsonBookData(JSONObject jsonData) {
        this.jsonData = jsonData;
    }

    @Override
    public String getBookName() {
        return jsonData.getString("bookName");
    }

    @Override
    public String getWriter() {
        return jsonData.getString("writer");
    }

    @Override
    public String getPublisher() {
        return jsonData.getString("publisher");
    }

    @Override
    public int getPageNumber() {
        return jsonData.getInt("pageNumber");
    }

    @Override
    public String getLanguage() {
        return jsonData.getString("language");
    }

    @Override
    public double getPrice() {
        return jsonData.getDouble("price");
    }

    @Override
    public String getIsbn() {
        return jsonData.getString("isbn");
    }
}