import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.Timer;

public class TravelController implements Observer {

    private TravellingParser travelParser;
    private TravelGUI travelGUI;
    private ConcurrentHashMap<Integer, Offer> list;
    private ConcurrentHashMap<Integer, Offer> searchList;
    private int indexOfTable = 1;
    private Timer time;
    private Properties settings;
    private InputStream loadSettings;
    private OutputStream cfg;

    /**
     * The constructor of the controller will intialize the model and
     * the view. It will also load,create and or set other attributes
     * @param tparser the model which parses the information of fritidsresor
     * @param tGUI the view which displays every information
     */
    public TravelController(TravellingParser tparser, TravelGUI tGUI) {

	travelParser = tparser;
	travelGUI = tGUI;
	settings = new Properties();
	try {
	    loadSettings = new FileInputStream("config.properties");
	    settings.load(loadSettings);
	    System.out.println(settings.getProperty("refreshRate"));
	    travelGUI.alterRefreshRateDisp(settings.getProperty("refreshRate"));
	    travelGUI.setRefreshRate(Integer.parseInt
		    		(settings.getProperty("refreshRate")));
	    loadSettings.close();

	} catch (IOException e) {
	    System.out.println("no such file");
	}

	searchList = new ConcurrentHashMap<Integer, Offer>();
	travelGUI.setActionListenerForInfo(new SearchAction());
	travelGUI.setALForRefreshMenu(new RefreshAction());
	travelGUI.setShowMoreActionListener(new MoreInfoAction());
	travelGUI.setUpRefreshRateActionList(new tickUpRefRate());
	travelGUI.setDownRefreshRateActionList(new tickDownRefRate());

    }

    /**
     * This function will clear the current JTable and then re-initialize it
     * with only elements that passed the if-statement that checks if String 2
     * is equal or contains parts of String 1.
     * 
     * @param subName
     *            will contain the entire, or part of a name
     */
    private void sortTableByCity(String subName) {
	travelGUI.clearTableData();
	int count = 0;
	for (int i = 1; i < list.size(); i++) {
	    Offer tmp = list.get(i);
	    if (tmp.getDestinationName().contains(subName)) {
		count++;

		searchList.put(count, tmp);
		travelGUI.setTable(tmp);
	    }
	}
    }

    /**
     * This method updates the programs graphical table
     * with the refresh freq, and sets a timer to refresh the
     * table with the time of that rate.
     */
    private void updateTable() {

	int delay = travelGUI.getRefreshRate();
	System.out.println(delay);
	try {
	    cfg = new FileOutputStream("config.properties");
	} catch (FileNotFoundException e2) {
	    e2.printStackTrace();
	}
	settings.setProperty("refreshRate", Integer.toString(delay));

	delay = delay * 60;
	delay = delay * 1000;
	try {
	    settings.store(cfg, null);
	} catch (IOException e1) {
	    System.out.println("file could not be saved");
	}

	time = new Timer(delay, new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		time.start();
		travelGUI.clearTableData();
		Thread update = new Thread(travelParser);
		update.start();

	    }
	});

	time.start();
    }

    /**
     * This actionlistener is used to search for specific cities to travel to.
     * 
     * @author D.Rafael
     *
     */
    public class SearchAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    String input = travelGUI.getTextFieldInput();
	    sortTableByCity(input);
	}
    }

    /**
     * This action listener will update the list of offers
     * 
     * @author c14rdo
     *
     */
    public class RefreshAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

	    travelGUI.clearTableData();
	    Thread update = new Thread(travelParser);
	    update.start();
	}
    }
    
    /**
     * This action listener class implements the action
     * of creating a pop up screen with more information 
     * of a travel offer.
     * @author c14rdl
     *
     */
    public class MoreInfoAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {

	    int sRow = travelGUI.getRowCount();

	    if (sRow != -1) {
		sRow++;
		if (searchList.size() > 0) {

		    travelGUI.popUpInfo(searchList.get(sRow));

		} else {
		    travelGUI.popUpInfo(list.get(sRow));
		}
	    }
	}
    }

    /**
     * This update method of the observable class is overriden to
     * add new offer elements into the table as they get
     * added to the list instead of waiting for the complete
     * list to be added.
     */
    @Override
    public void update(Observable arg0, Object arg1) {
	list = travelParser.getHashMap();

	for (int i = indexOfTable; i < list.size(); i++) {
	    travelGUI.setTable(list.get(i));
	    indexOfTable++;
	}

    }

    /**
     * This class is the action listener which implements
     * the action of the tick down button. It decreases the
     * refresh rate of the program.
     * @author c14rdo
     *
     */
    public class tickDownRefRate implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    int rate = travelGUI.getRefreshRate();
	    if (rate >= 60) {

		rate = rate - 30;
		travelGUI.setRefreshRate(rate);
		String upTime = Integer.toString(rate);
		travelGUI.alterRefreshRateDisp(upTime);
		updateTable();

	    }
	}
    }

    /**
     * This class is the action listener which implements
     * the action of the tick up button. It increases the
     * refresh rate of the program.
     * @author c14rdo
     *
     */
    public class tickUpRefRate implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
	    int rate = travelGUI.getRefreshRate();

	    rate = rate + 30;
	    String upTime = Integer.toString(rate);
	    travelGUI.alterRefreshRateDisp(upTime);
	    travelGUI.setRefreshRate(rate);
	    updateTable();
	}
    }

}
