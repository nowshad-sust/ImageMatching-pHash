/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crop;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;

import marvin.image.MarvinImage;
import marvin.io.MarvinImageIO;
import marvin.plugin.MarvinImagePlugin;
import marvin.util.MarvinAttributes;
import marvin.util.MarvinPluginLoader;

public class MoravecCustomized {
    public static List<int[]> cornerList = new ArrayList<int[]>();
    public static int imageHeight;
    public static int imageWidth;
	public static void main(String[] args) {
		
		MarvinImagePlugin moravec = MarvinPluginLoader.loadImagePlugin("org.marvinproject.image.corner.moravec");
		moravec.load();
                MarvinImage imageIn;
		MarvinAttributes attr;
                moravec.setAttribute("threshold", 50000);
		
		/*// 1. Figures
                imageIn = MarvinImageIO.loadImage("./src/resources/images/1000.jpg");
		attr = new MarvinAttributes();
		moravec.process(imageIn, null, attr);
		imageIn = showCorners(imageIn, attr, 6);
		MarvinImageIO.saveImage(imageIn, "./src/resources/images/1000_corner_figure.jpg");
		*/
                String src = "./src/resources/images/9250557081.jpg";
		// 2. Face
		moravec.setAttribute("threshold", 10000);
		moravec.setAttribute("matrixSize", 7);
		imageIn = MarvinImageIO.loadImage(src);
                
                imageHeight = imageIn.getHeight();
                imageWidth = imageIn.getWidth();
		System.out.println("height: "+imageHeight+" Width: "+imageWidth);
                
                attr = new MarvinAttributes();
		moravec.process(imageIn, null, attr);
		imageIn = showCorners(imageIn, attr, 3);
		MarvinImageIO.saveImage(imageIn, "./src/resources/images/1000_corner_face.jpg");
                
                /*System.out.println("Corner participants:");
                for(int i=0; i < cornerList.size();i++){
                    System.out.println("x:"+cornerList.get(i)[0]+" "+"y:"+cornerList.get(i)[1]);
                }*/
                
                //finding four corners
                // top-left
                int[] topLeft = findCorner(0,0);
                //top-right
                int[] topRight = findCorner(imageWidth,0);
                //bottom-left
                int[] bottomLeft = findCorner(0,imageHeight);
                //bottom-right
                int[] bottomRight = findCorner(imageWidth,imageHeight);
                
                System.out.println("bottom-right: "+bottomRight[0]+","+bottomRight[1]);
                
                //crop the image eith these four corners
                BufferedImage img = null;

                try 
                {
                    img = ImageIO.read(new File(src));
                } 
                catch (Exception e) 
                {
                    
                }
                img = cropImage(img,topLeft[0],topLeft[1],
                        bottomRight[0]-topLeft[0],bottomRight[1]-topLeft[1]);
                try {
                    // retrieve image
                    File outputfile = new File("./src/resources/images/1000_cropped.jpg");
                    ImageIO.write(img, "jpg", outputfile);
                } catch (Exception e) {
                    
                }
                
		/*
		// 3. House
		moravec.setAttribute("threshold", 1500);
		moravec.setAttribute("matrixSize", 7);
		imageIn = MarvinImageIO.loadImage("./src/resources/images/Bangladesh.jpg");
		attr = new MarvinAttributes();
		moravec.process(imageIn, null, attr);
		imageIn = showCorners(imageIn, attr, 3);
		MarvinImageIO.saveImage(imageIn, "./src/resources/images/1000_corner_house.jpg");
                */
        }
        public static void ImageCrop(String imageSource, String outputDestination){
            MarvinImagePlugin moravec = MarvinPluginLoader.loadImagePlugin("org.marvinproject.image.corner.moravec");
		moravec.load();
                MarvinImage imageIn;
		MarvinAttributes attr;
                //moravec.setAttribute("threshold", 50000);
		
		
                String src=imageSource;
                //src= "./src/resources/images/9250557081.jpg";
                
		// 2. Face
		moravec.setAttribute("threshold", 10000);
		moravec.setAttribute("matrixSize", 7);
		imageIn = MarvinImageIO.loadImage(src);
                
                imageHeight = imageIn.getHeight();
                imageWidth = imageIn.getWidth();
		System.out.println("height: "+imageHeight+" Width: "+imageWidth);
                
                attr = new MarvinAttributes();
		moravec.process(imageIn, null, attr);
		imageIn = showCorners(imageIn, attr, 3);
		//MarvinImageIO.saveImage(imageIn, "./src/resources/images/1000_corner_face.jpg");
                //MarvinImageIO.saveImage(imageIn, outputDestination);
                
                /*System.out.println("Corner participants:");
                for(int i=0; i < cornerList.size();i++){
                    System.out.println("x:"+cornerList.get(i)[0]+" "+"y:"+cornerList.get(i)[1]);
                }*/
                
                //finding four corners
                // top-left
                int[] topLeft = findCorner(0,0);
                //top-right
                int[] topRight = findCorner(imageWidth,0);
                //bottom-left
                int[] bottomLeft = findCorner(0,imageHeight);
                //bottom-right
                int[] bottomRight = findCorner(imageWidth,imageHeight);
                
                System.out.println("bottom-right: "+bottomRight[0]+","+bottomRight[1]);
                
                //crop the image eith these four corners
                BufferedImage img = null;

                try 
                {
                    img = ImageIO.read(new File(src));
                } 
                catch (Exception e) 
                {
                    
                }
                img = cropImage(img,topLeft[0],topLeft[1],
                        bottomRight[0]-topLeft[0],bottomRight[1]-topLeft[1]);
                try {
                    // retrieve image
                    //File outputfile = new File("./src/resources/images/1000_cropped.jpg");
                    File outputfile = new File(outputDestination);
                    ImageIO.write(img, "jpg", outputfile);
                } catch (Exception e) {
                    
                }
        }
        
        private static BufferedImage cropImage(BufferedImage src,int x, int y, int width, int height) {
            BufferedImage dest = src.getSubimage(x, y, width, height);
            return dest; 
        }
        private static int[] findCorner(int x, int y){
            int[] arr = new int[2];
            int index = smallestIndex(getDistanceList(x,y));
            arr[0] = cornerList.get(index)[0];
            arr[1] = cornerList.get(index)[1];
            return arr;
        }
        private static double measureDistance(int x1, int y1, int x2, int y2){
             return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
        }
        
        private static double[] getDistanceList(int x, int y){
            
            double[] distanceList = new double[cornerList.size()];
                
            for(int i=0; i < cornerList.size();i++){
                distanceList[i] = measureDistance(x, y, cornerList.get(i)[0], cornerList.get(i)[1]);
            }
            return distanceList;
        }
        
	public static int smallestIndex (double[] array) {
            int index=0;
            double currentValue=array[0];
            int tempIndex=0;
            for(int i=1;i<array.length;i++){
                if(array[i]<currentValue){
                    currentValue = array[i];
                    tempIndex = i;
                }                    
            }
            
            return tempIndex;
	}
	private static MarvinImage showCorners(MarvinImage image, MarvinAttributes attr, int rectSize){
		MarvinImage ret = image.clone();
		int[][] cornernessMap = (int[][]) attr.get("cornernessMap");
		int rsize=0;
		for(int x=0; x<cornernessMap.length; x++){
			for(int y=0; y<cornernessMap[0].length; y++){
				// Is it a corner?
				if(cornernessMap[x][y] > 0){
                                    cornerList.add(new int[] {x, y});
					rsize = Math.min(Math.min(Math.min(x, rectSize), Math.min(cornernessMap.length-x, rectSize)), Math.min(Math.min(y, rectSize), Math.min(cornernessMap[0].length-y, rectSize)));
					ret.fillRect(x, y, rsize, rsize, Color.red);
				}				
			}
		}
		
		return ret;
	}
        
}

