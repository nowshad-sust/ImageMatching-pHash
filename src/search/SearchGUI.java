/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;


//importing required packages
import bktree.BKTree;
import crop.MoravecCustomized;
import dataset.ImagePHash;
import distance.LevenshteinDistance;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
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
 
 
public class SearchGUI extends JFrame {
    
    private JLabel resultLabel;
    private static BKTree<String> bkTree;
    private static List<DatasetObject> datasetArray;
    public static HashMap<String, String[]> matchInfo = new HashMap<String, String[]>(); 
    //Constructor
    public SearchGUI() {
        super("Search Image");
        
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
        fileChooser.setCurrentDirectory(new File(""));
         
        
        //compare button panel
        JPanel bottomPanel = new JPanel();
        
        bottomPanel.setLayout(new FlowLayout());
        
        
        JButton cropButton = new JButton("Crop");
        cropButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Runnable searchImage = new Runnable(){
                         public void run() {
                            try{
                                String source = FilePicker.textField.getText();
                                String destination = ".\\src\\resources\\dataset\\tempImage.jpg";
                                MoravecCustomized.ImageCrop(source, destination);
                                FilePicker.textField.setText(destination);
                            }catch(Exception e){
                               
                            }
                            
                            }
		         };
		 	(new Thread(searchImage)).start();
                    
                } catch(Exception ex){
                    
                }
            }
            
        });
        
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    //first setting the result label to searching
                    resultLabel.setText("Searching ...");
                    //compareButtonActionPerformed(evt);
                    Runnable searchImage = new Runnable(){
                         public void run() {
                            try{
                                searchButtonActionPerformed(evt);                                
                            }catch(Exception e){
                               
                            }
                            
                            }
		         };
		 	(new Thread(searchImage)).start();
                } catch (Exception ex) {
                    Logger.getLogger(SearchGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        //result showing areaimport javax.swing.JOptionPane;
        /*Some piece of code*/
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                try{
                    dispose();
                }catch(Exception e){

                    e.printStackTrace();

                }
            }
        });
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new FlowLayout());
        
        resultLabel = new JLabel();
        resultLabel.setText("Result Label");
        resultPanel.add(resultLabel);
        
        bottomPanel.add(cropButton);
        bottomPanel.add(searchButton);
        
        //adding all components to mainPanel
        mainContainer.add(filePicker, BorderLayout.NORTH);
        mainContainer.add(bottomPanel, BorderLayout.CENTER);
        mainContainer.add(resultPanel, BorderLayout.SOUTH);
        
        add(mainContainer);
        
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 250);
        setResizable(false);
        setLocationRelativeTo(null);

    }
    
    //compare button clicked
    private void searchButtonActionPerformed(ActionEvent evt) throws IOException{
        String imageHashString = null;
        int thresholdValue = 10;
        //create a hash of the image
            //setting filePath & categorySelected variable
            String filePath = FilePicker.getSelectedFilePath();
        //now getHash value of that image
            ImagePHash obj = new ImagePHash();
            
            InputStream imageObject = new FileInputStream(filePath);
            try {
                imageHashString = obj.getHash(imageObject);
                
            } catch (Exception ex) {
                Logger.getLogger(SearchGUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            HashMap<String, Integer> queryMap = null;
            //now search through the bk-tree with that hash
            //find the bst matches with the lowest possible threshold
            for(thresholdValue=0;queryMap == null;++thresholdValue){
                System.out.println("serching with threshold: "+ thresholdValue);
                queryMap = bkTree.query(imageHashString, thresholdValue);
                if(queryMap.size()<=0)
                    queryMap = null;
                //System.out.println(queryMap);
            }
            
            //also show the bestmathced result by function
            System.out.println("best match "+bkTree.findBestWordMatchWithDistance(imageHashString));
            
            String[] arr = null;
            double matchPercentage = 0;
            for ( String key : queryMap.keySet() ) {
                arr = matchInfo.get(key);
                //System.out.println(queryMap.get(key));
                float temp = (float)queryMap.get(key)/64;
                matchPercentage = 100 - (100*temp);
                System.out.println("threshold: "+ (thresholdValue-1));
                System.out.println("Matched "
                        + matchPercentage
                        +"% with: "
                        + arr[1]
                        + " value : "
                        + arr[0]
                        + " taka"
                        );
            }
            
           
        //show best result
        if(queryMap!=null){
            resultLabel.setText("<html>"
                + "<h3>Result</h3><br>"
                + "Threshold: " + (thresholdValue-1)+"<br>"
                + "Matched "+matchPercentage+"%<br>"
                + "With: "+ arr[1]+"<br>"
                + "Value: "+arr[0]+"taka"
                + "</html>");
        } else
        {
            resultLabel.setText("<html>"
                + "<h3>Result</h3><br>"
                +"<p>Could not find any match</p>"
                + "</html>");
        }
        
    }
    
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    //load the JSON array
                    FromJsonToArray objRef = new FromJsonToArray();
                    datasetArray = objRef.
                                    retrieveArrayfromJson(".\\src\\resources\\dataset\\dataset.json");
                    
                    //entrying hash Strings in bk-tree
                    bkTree = new BKTree<String>(new LevenshteinDistance());
                    
                    for(int i=0; i<datasetArray.size();i++){
                        bkTree.add(datasetArray.get(i).getImagePHashString());
                        String []arr = new String[2];
                        arr[0]=datasetArray.get(i).getValueInTaka();
                        arr[1]=datasetArray.get(i).getSourceImagePath();
                        matchInfo.put(datasetArray.get(i).getImagePHashString(), arr);
                    }
                    
                    
                }catch(Exception e){

                    e.printStackTrace();

                }
                //creating the GUI object
                new SearchGUI().setVisible(true);
            }
        });
    }
 
}

