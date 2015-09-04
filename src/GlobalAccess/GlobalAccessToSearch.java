/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GlobalAccess;

import bktree.BKTree;
import crop.MoravecCustomized;
import dataset.ImagePHash;
import distance.LevenshteinDistance;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import test.GSON.Example.DatasetObject;
import test.GSON.Example.FromJsonToArray;

/**
 *
 * @author nowshad
 */
public class GlobalAccessToSearch {
    
    private String targetPath;
    private String tempName;
    private String currentFullPath;
    private String resultString;
    private static BKTree<String> bkTree;
    private static List<DatasetObject> datasetArray;
    public static HashMap<String, String[]> matchInfo = new HashMap<String, String[]>();
    
    public GlobalAccessToSearch(byte[] byteArray, boolean crop) {
        targetPath = ".\\src\\resources\\dataset\\tempStorage\\";
        tempName = "tempImage.jpg";
        //convert byte array into an jpg image & strong it
        //to a temporary directory
        convertAndWriteImage(byteArray);
        //checking the cropping requirements & act in respect
        if(crop == true){
            crop(currentFullPath, targetPath + "cropped.jpg");
            currentFullPath = targetPath + "cropped.jpg";
        } else if(crop == false)
        {
            //do nothing
        }
        
    }
    
    public String search() throws IOException{
        String imageHashString = null;
        int thresholdValue = 10;
        //create a hash of the image
        //setting filePath & categorySelected variable
        String filePath = currentFullPath;
        //now getHash value of that image
            ImagePHash obj = new ImagePHash();
            
            InputStream imageObject = new FileInputStream(filePath);
            try {
                imageHashString = obj.getHash(imageObject);
                
            } catch (Exception ex) {
        
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
            resultString = ("<html>"
                + "<h3>Result</h3><br>"
                + "Threshold: " + (thresholdValue-1)+"<br>"
                + "Matched "+matchPercentage+"%<br>"
                + "With: "+ arr[1]+"<br>"
                + "Value: "+arr[0]+"taka"
                + "</html>");
        } else
        {
            resultString = ("<html>"
                + "<h3>Result</h3><br>"
                +"<p>Could not find any match</p>"
                + "</html>");
        }
        return resultString;
    }
    
    private void convertAndWriteImage(byte[] byteArray){
        try {
            BufferedImage img = ConvertImage.byteArrayToBufferedImage(byteArray);
            currentFullPath = targetPath + tempName;
            ConvertImage.writeConvertedImage(img, currentFullPath);
            
        } catch (IOException ex) {
            Logger.getLogger(GlobalAccessToSearch.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void crop(String source, String destination) {
        try{
                MoravecCustomized.ImageCrop(source, destination);
            }catch(Exception e){
                System.out.println("image cropping failed");
            }
    }
		
    
    public void preloadData(){
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
    }
    
}
