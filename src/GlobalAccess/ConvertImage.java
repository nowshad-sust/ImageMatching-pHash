/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GlobalAccess;

/**
 *
 * @author nowshad
 */
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
 
 
public class ConvertImage {
	public static void main(String[] args) throws IOException{
		String dirName="H:\\java\\ImageMatching-pHash(modified)\\src\\resources\\dataset";
		ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
		BufferedImage img = ImageIO.read(new File(dirName,"tempImage.jpg"));
		ImageIO.write(img, "jpg", baos);
		baos.flush();
 
		String base64String = Base64.encode(baos.toByteArray());
		baos.close();
 
		byte[] bytearray = Base64.decode(base64String);
                
                for(int i=0;i<bytearray.length;i++){
                    System.out.println(bytearray[i]);
                }
                
		BufferedImage imag = ImageIO.read(new ByteArrayInputStream(bytearray));
		ImageIO.write(imag, "jpg", new File(dirName,"snap.jpg"));
	}
        
        public static void writeConvertedImage(BufferedImage imag, String imageWritePath) throws IOException{
            ImageIO.write(imag, "jpg", new File(imageWritePath));
        }
        public static byte[] imageToByteArray(String imagePath)throws IOException{
            ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
            BufferedImage img = ImageIO.read(new File(imagePath));
            ImageIO.write(img, "jpg", baos);
            baos.flush();
 
            String base64String = Base64.encode(baos.toByteArray());
            baos.close();
 
            byte[] bytearray = Base64.decode(base64String);
            
            return bytearray;
        }
        public static BufferedImage byteArrayToBufferedImage(byte[] byteArray) throws IOException{
            BufferedImage imag = ImageIO.read(new ByteArrayInputStream(byteArray));
            return imag;
        }
}