//completing the FInal GUI for comapare program
//using the FilePicker GUI


//done this class
//future feature :
    //just omit the drop down & loop through all files
    //to search for the given image
package test;

//importing required packages
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
 
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
 
 
public class DatasetPopulateGUI extends JFrame {
    
    private String categorySelected;
    private JLabel resultLabel;
    
    //Constructor
    public DatasetPopulateGUI() {
        super("CompareFinal");
        
        //setLayout(new BorderLayout());
        
        Container mainContainer = new Container();
        mainContainer.setLayout(new BorderLayout());
        
        // set up a file picker component
        FilePicker filePicker = new FilePicker("Pick a file", "Browse...");
        filePicker.setMode(FilePicker.MODE_SAVE);
        filePicker.addFileTypeFilter(".jpg", "JPEG Images");
        filePicker.addFileTypeFilter(".png", "PNG images");
         
        // access JFileChooser class directly
        JFileChooser fileChooser = filePicker.getFileChooser();
        //default fileChooser browsing directory
        fileChooser.setCurrentDirectory(new File("H:\\"));
         
        
        //compare button panel
        JPanel bottomPanel = new JPanel();
        
        bottomPanel.setLayout(new FlowLayout());
        JButton saveJSONButton = new JButton("Save JSON");
        saveJSONButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                saveJSON(evt);           
            }
        });
        
        bottomPanel.add(saveJSONButton);
        
        //result showing area
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout());
        
        resultLabel = new JLabel();
        resultLabel.setText("Result Label");
        resultPanel.add(resultLabel);
         
        //adding all components to mainPanel
        mainContainer.add(filePicker, BorderLayout.NORTH);
        mainContainer.add(bottomPanel, BorderLayout.CENTER);
        mainContainer.add(resultPanel, BorderLayout.SOUTH);
        
        add(mainContainer);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 150);
        setResizable(false);
        setLocationRelativeTo(null);

    }
    
    public void saveJSON(ActionEvent evt){
        //save the image to the directoey by category selected
        //now getHash value of that image
        //now make a JSONObject class obj
        //pass it to the JSONHandler class toJSON function
        //show the generated json to the label below
    }
    
    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //creating the GUI object
                new DatasetPopulateGUI().setVisible(true);
            }
        });
    }
 
}
