import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
	
	SwingUtilities.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		
		TravelGUI tgui = new TravelGUI();
		TravellingParser parse = new TravellingParser();
		Thread t = new Thread(parse);
		t.start();
		
		TravelController tc = new TravelController(parse, tgui);
		parse.addObserver(tc);
		
		

	    }
	});
	
    }

}
