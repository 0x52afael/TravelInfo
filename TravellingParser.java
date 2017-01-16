
import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author c14rdo
 *
 */
public class TravellingParser extends Observable implements Runnable {

    private ConcurrentHashMap<Integer, Offer> offerL;
    private Offer tmpOffer;
    private StringBuilder tmpValue = null;
    private int count = 0;
    private DefaultHandler dHandler;

    public TravellingParser() {
	offerL = new ConcurrentHashMap<Integer, Offer>();
	tmpValue = new StringBuilder();

	dHandler = new DefaultHandler() {

	    /**
	     * at the start of every element the string value is renewed and if
	     * the element name is Offer, a new Offer is created.
	     */
	    public void startElement(String uri, String localName,
		    		     String elementName, Attributes attributes)
		    			     throws SAXException {
		
		if (elementName.equalsIgnoreCase("Offer")) {
		    tmpOffer = new Offer();
		}
		tmpValue.setLength(0);
	    } // end of startElement

	    /**
	     * This method appends and builds a string by going through each
	     * character
	     */
	    public void characters(char ch[], int i, int j) throws SAXException{

		tmpValue.append(new String(ch, i, j));

	    }

	    /**
	     * overwritten method of defaulthandler, in this method the default
	     * handler will check if the element string is equal to some of the
	     * attributes inside the Offer class. if the element matches the
	     * offer attribute, that means the default handler will save the
	     * tmpOffer into that attribute. else if it matches with offer that
	     * means it reached the end of offer and that offer can be added to
	     * the list of complete offers.
	     */
	    public void endElement(String uri, String localName,
		    		   String elementName) throws SAXException {

		if (elementName.equalsIgnoreCase("Offer")) {
		    count++;
		    offerL.put(count, tmpOffer);
		    setChanged();
		    notifyObservers();
		}
		if (elementName.equalsIgnoreCase("DestinationName")) {
		    tmpOffer.setDestinationName(tmpValue.toString());

		}
		if (elementName.equalsIgnoreCase("OutDate")) {
		    tmpOffer.setOutDate(tmpValue.toString());
		}

		if (elementName.equalsIgnoreCase("CityName")) {
		    tmpOffer.setCityName(tmpValue.toString());
		}

		if (elementName.equalsIgnoreCase("CurrentPrice")) {
		    tmpOffer.setCurrentPrice(tmpValue.toString());
		}

		if (elementName.equalsIgnoreCase("OriginalPrice")) {
		    tmpOffer.setOriginalPrice(tmpValue.toString());
		}

		if (elementName.equalsIgnoreCase("PriceCurrency")) {
		    tmpOffer.setPriceCurrency(tmpValue.toString());
		}

		if (elementName.equalsIgnoreCase("HotelName")) {
		    tmpOffer.setHotelName(tmpValue.toString());
		}

		if (elementName.equalsIgnoreCase("HotelImage")) {
		    tmpOffer.setHotelImage(tmpValue.toString());
		}

		if (elementName.equalsIgnoreCase("HotelGrade")) {
		    tmpOffer.setHotelGrade(tmpValue.toString());
		}
		if (elementName.equalsIgnoreCase("RoomDescription")) {
		    tmpOffer.setRoomDesc(tmpValue.toString());
		}
		if (elementName.equalsIgnoreCase("JourneyLength")) {
		    tmpOffer.setJourneyLen(tmpValue.toString());
		}
		if (elementName.equalsIgnoreCase("NoOfSeatsRemaining")) {
		    tmpOffer.setNoSeatsRemaining(tmpValue.toString());
		}
		if (elementName.equalsIgnoreCase("DepartureName")) {
		    tmpOffer.setDepartName(tmpValue.toString());
		}
		if (elementName.equalsIgnoreCase("CampaignName")) {
		    tmpOffer.setCampaignName(tmpValue.toString());
		}
	    }
	};

    }

    /**
     * this method creates the parsing of the website fritidsresor.
     */
    private void parseDocument() {
	URL url = null;
	try {
	    // retrieving the xml-file
	    url = new URL
		   ("http://www.fritidsresor.se/Blandade-Sidor/feeds/tradera/");
	} catch (MalformedURLException e1) {
	    System.out.println("Bad url for xml-file");
	}
	URLConnection conn = null;
	try {
	    conn = url.openConnection();

	} catch (IOException e1) {
	    System.out.println("Bad connection to website");
	}
	try {
	    SAXParserFactory spf = SAXParserFactory.newInstance();
	    // obtain the sax parser object.
	    SAXParser saxParser = spf.newSAXParser();
	    saxParser.parse(conn.getInputStream(), dHandler);

	} catch (ParserConfigurationException | SAXException | IOException e) {

	    System.out.println(e.getCause());
	}
    }

    // This method is called every time the parser gets an open tag '<'
    // identifies which tag is being open at time by assigning an open flag
    // Only reads for the elements specified in the startElement

    public ConcurrentHashMap<Integer, Offer> getHashMap() {
	return offerL;
    }

    @Override
    public void run() {
	parseDocument();

    }

}
