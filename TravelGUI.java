import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.table.DefaultTableModel;

public class TravelGUI {

    private JFrame f;
    private JTable table;
    private JPanel mainPanel;
    private JPanel eastPanel;
    private JPanel lowerWestPanel;
    private DefaultTableModel model;
    private JButton search;
    private JTextField textfield;
    private JMenuBar menuBar;
    private JMenuItem refresh;
    private JMenu opts;
    private JMenuItem quit;
    private JMenuItem help;
    private JButton showInfo;
    private JTextField refreshRate;
    private JPanel centerPanel;
    private BasicArrowButton up;
    private BasicArrowButton down;
    private int rate = 30;

    /**
     * The constructor calls on the createGUI method which sets up the frame of
     * the program and all it's components.
     */
    public TravelGUI() {

	createGUI();

    }

    /**
     * Private method only to be called on construction of the class. This
     * method sets and creates all the components and adds them to the frame.
     */
    private void createGUI() {

	f = new JFrame("TravelInfo");
	f.setMinimumSize(new Dimension(800, 640));
	f.setLocationRelativeTo(null);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	mainPanel = createTableEntry();
	eastPanel = createEastPanel();
	lowerWestPanel = createWestPanel();
	centerPanel = createCenterPanel();

	menuBar = new JMenuBar();
	f.setJMenuBar(menuBar);
	opts = new JMenu("Options");
	menuBar.add(opts);
	refresh = new JMenuItem("Refresh");
	quit = new JMenuItem("Exit");
	help = new JMenuItem("Help");

	help.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null,
			"The Show Details button lets" +
			"a user to inspect a specific\nOffer."
			+ " Note you have to click"
			+ "a offer to show details. \n"
			+ "The Searchbar is used to find specific "
			+ "destination.\nIf the user is unsure what"
			+ " to search for,\n"
			+ "typing only"
			+ " a letter such as D will show every destination"
			+ " that starts with D.");
	    }
	});

	quit.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent e) {
		System.exit(0);

	    }
	});

	opts.add(refresh);
	opts.addSeparator();
	opts.add(help);
	opts.addSeparator();
	opts.add(quit);
	f.add(centerPanel, BorderLayout.CENTER);
	f.add(lowerWestPanel, BorderLayout.WEST);
	f.add(mainPanel, BorderLayout.NORTH);
	f.add(eastPanel, BorderLayout.EAST);
	f.setBackground(Color.GRAY);
	f.pack();
	f.setVisible(true);
    }

    @SuppressWarnings("serial")
    /**
     * This method creates a JPanel to be placed in the central region of the
     * screen
     * 
     * @return the created JPanel with all it's components
     */
    private JPanel createTableEntry() {
	JPanel centerP = new JPanel();

	model = new DefaultTableModel();
	model.addColumn("Hotel Name");
	model.addColumn("Hotel Grade");
	model.addColumn("Destination");
	model.addColumn("Departure Date");
	model.addColumn("Price");

	table = new JTable(model) {
	    public boolean isCellEditable(int row, int cell) {
		return false;
	    }

	    @SuppressWarnings({"unchecked", "rawtypes"})
		public Class getColumnClass(int column) {
		return getValueAt(0, column).getClass();
	    }
	};

	table.getTableHeader().setReorderingAllowed(false);
	JScrollPane jsp = new JScrollPane(table);
	jsp.setPreferredSize(new Dimension(640, 400));
	centerP.add(jsp, BorderLayout.CENTER);
	centerP.setBackground(Color.GRAY);
	return centerP;
    }

    /**
     * Creates the lower western panel with all it's components inside.
     * 
     * @return the JPanel to be placed in the gui.
     */
    private JPanel createWestPanel() {

	JPanel wPan = new JPanel();
	showInfo = new JButton("Show Details");
	wPan.add(showInfo, BorderLayout.WEST);
	wPan.setBackground(Color.GRAY);
	up = new BasicArrowButton(BasicArrowButton.NORTH);
	down = new BasicArrowButton(BasicArrowButton.SOUTH);
	textfield.setPreferredSize(new Dimension(100, 35));
	refreshRate = new JTextField();
	refreshRate.setPreferredSize(new Dimension(35, 25));
	wPan.add(refreshRate, BorderLayout.SOUTH);

	wPan.add(up, BorderLayout.NORTH);
	wPan.add(down, BorderLayout.SOUTH);

	return wPan;
    }

    /**
     * creates the cenral lower panel of the gui with all the components added
     * to the JPanel
     * 
     * @return the created JPanel of the method
     */
    private JPanel createCenterPanel() {
	JPanel cPan = new JPanel();
	JLabel freqlabel = new JLabel("Update Frequency (minutes)");
	cPan.setLayout(new BorderLayout());
	cPan.add(freqlabel, BorderLayout.NORTH);
	cPan.setBackground(Color.GRAY);
	return cPan;

    }

    /**
     * Creates the lower eastern panel of the gui with all the components added
     * to it.
     * 
     * @return the JPanel created from the method.
     */
    private JPanel createEastPanel() {
	JPanel eastP = new JPanel();
	search = new JButton("Search");
	search.setPreferredSize(new Dimension(75, 35));
	textfield = new JTextField();
	eastP.add(search, BorderLayout.CENTER);
	eastP.add(textfield, BorderLayout.WEST);
	eastP.setBackground(Color.GRAY);
	return eastP;
    }

    /**
     * This method takes a Offer as param and sets information within the table
     * with it.
     * 
     * @param tableItem
     *            A Offer to show in the GUI.
     */
    public void setTable(Offer tableItem) {
	int x = 0;
	try {
	    x = NumberFormat.getInstance().parse(
		    		tableItem.getHotelGrade()).intValue();
	} catch (ParseException e) {
	    System.out.println("Something went wrong with parsing");
	}
	String hotelGrade = tableItem.getHotelGrade();

	if (x == 0) {

	    hotelGrade = "N/A";
	}

	model.addRow(new Object[] { tableItem.getHotelName(),
		hotelGrade, tableItem.getDestinationName(),
		tableItem.getOutDate(), tableItem.getCurrentPrice(), });
    }

    /**
     * The action listener used to implement the search buttons action
     * 
     * @param action
     *            specific action listener for search button
     */
    public void setActionListenerForInfo(ActionListener action) {

	search.addActionListener(action);

    }

    /**
     * Creates a new string to save the string written into the text
     *  field then clears the text field and then returns the string.
     * 
     * @return The written input of the user
     */
    public String getTextFieldInput() {

	String s = textfield.getText();
	textfield.setText(null);
	return s;
    }

    /**
     * Clears the table by setting the row count to 0.
     */
    public void clearTableData() {
	model.setRowCount(0);
    }

    /**
     * Sets a action listener to the refresh menu in the program
     * 
     * @param action
     *            a class
     */
    public void setALForRefreshMenu(ActionListener action) {
	refresh.addActionListener(action);
    }

    /**
     * returns the selected row of the table
     * 
     * @return a integer corresponding the selected row
     */
    public int getRowCount() {
	return table.getSelectedRow();
    }

    /**
     * Adds a action listener to the button.
     * 
     * @param action
     *            a action listener for showInfo button
     */
    public void setShowMoreActionListener(ActionListener action) {
	showInfo.addActionListener(action);
    }

    /**
     * Creates a popup containing the information of a specific offer.
     * 
     * @param data
     *            is the offer to be shown
     */
    public void popUpInfo(Offer data) {
	String outp = convertOfferInfoToString(data);

	URL url = null;
	try {
	    url = new URL(data.getHotelImage());
	} catch (MalformedURLException e) {
	    System.out.println("Incorrect url!");
	}
	BufferedImage img = null;
	try {
	    img = ImageIO.read(url);
	} catch (IOException e) {
	    System.out.println("Corrupt image?");
	}
	ImageIcon hotelImg = new ImageIcon(img);
	JOptionPane.showMessageDialog(f, outp, "Details",
			JOptionPane.OK_OPTION, hotelImg);
    }

    /**
     * This method is used for the Pop-up screen where the details of a offer is
     * shown
     * 
     * @param data
     *            the offer to display
     * @return returns the string of the data to display.
     */
    private String convertOfferInfoToString(Offer data) {

	String outp = new String();
	outp = "Campaign                : "
		+ data.getCampaignName() +
		"\n" + "City Name                 : "
		+ data.getCityName() +
		"\n" + "Original Price          : " +
		data.getOriginalPrice() + "\n"
		+ "Travel Duration        : " +
		data.getJourneyLen() + "\n" +
		"Room Description  :  "
		+ data.getRoomDesc() + "\n" +
		"Seats Left                : " +
		data.getNoSeatsRemaining() + "\n"
		+ "Departure Name    : " + data.getDepartName();

	return outp;
    }

    /**
     * returns the refresh rate of the refresh rate that is shown on the gui
     * 
     * @return a integer beign the rate
     */
    public int getRefreshRate() {
	return rate;
    }

    /**
     * Sets the rate to the rate which was used when the program ran last from a
     * properties file.
     * 
     * @param properties
     *            the amount of minutes to wait before refreshing
     */
    public void setRefreshRate(int properties) {
	rate = properties;
	refreshRate.setText(Integer.toString(rate));
    }

    /**
     * Sets the action listener of the up-arrow button that adds 30 minutes to
     * timer.
     * 
     * @param action
     *            the actionlistener to add
     */
    public void setUpRefreshRateActionList(ActionListener action) {
	up.addActionListener(action);
    }

    /**
     * Sets the action listener of the down-arrow button that removes 30 minutes
     * from timer.
     * 
     * @param action
     *            the actionlistener to add
     */
    public void setDownRefreshRateActionList(ActionListener action) {
	down.addActionListener(action);
    }

    /**
     * Changes the displayed value of the timer
     * 
     * @param val
     *            is the string used to set the display
     */
    public void alterRefreshRateDisp(String val) {
	refreshRate.setText(val);
    }

}
