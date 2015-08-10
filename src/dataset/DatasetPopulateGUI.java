package dataset;

//importing required packages
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
 
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import test.GSON.Example.DatasetObject;
import test.GSON.Example.FromJsonToArray;
import test.GSON.Example.ToJSONFromArray;
 
 
public class DatasetPopulateGUI extends JFrame {
    
    private String categorySelected;
    private JLabel resultLabel;
    private String filePath;
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
        try {
            //setting filePath & categorySelected variable
            filePath = FilePicker.getSelectedFilePath();
            categorySelected = FilePicker.getSelectedCategory();
            //retrieving file name
            String fullPath = filePath;
            int index = fullPath.lastIndexOf("\\");
            String imageName = fullPath.substring(index + 1);
            
            String imageHashString = null;
            String movedImagePath = ".\\src\\resources\\images\\"+imageName;
            
            //copy the image to the directoey by category selected
            MoveFileExample moveObj = new MoveFileExample();
            moveObj.copyFile(filePath, movedImagePath);
            
            //now getHash value of that image
            ImagePHash obj = new ImagePHash();
            
            InputStream imageObject = new FileInputStream(movedImagePath);
            try {
                imageHashString = obj.getHash(imageObject);
                
            } catch (Exception ex) {
                Logger.getLogger(DatasetPopulateGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            //now load the JSON array
            FromJsonToArray objRef = new FromJsonToArray();
            List<DatasetObject> datasetArray = objRef.
                            retrieveArrayfromJson(".\\src\\resources\\dataset\\dataset.json");
            
            //make DatasetObject
            DatasetObject DO1 = new DatasetObject();
            DO1.setImagePHashString(imageHashString);
            DO1.setSourceImagePath(movedImagePath);
            DO1.setValueInTaka(categorySelected);
            //add it to the JSON array
            ToJSONFromArray jsonObjRef = new ToJSONFromArray();
            datasetArray.add(DO1);
            jsonObjRef.setMainJsonArray(datasetArray);
            //wtite it as JSON
            jsonObjRef.writeJsonArray(".\\src\\resources\\dataset\\dataset.json");
            
            setResultLabel("<html>"
                    + "<p>Image added to dataset</p>"
                    + "<p>ImagePath: "+movedImagePath+"</p>"
                    + "<p>Value: "+categorySelected+"</p>"
                    + "<p>Hash: "+imageHashString+"</p>"
                    + "</html>");
            
        } catch (Exception ex) {
            Logger.getLogger(DatasetPopulateGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public JLabel getResultLabel() {
        return resultLabel;
    }

    public void setResultLabel(String resultText) {
        this.resultLabel.setText(resultText);
    }
 
}
