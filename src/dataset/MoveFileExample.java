/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataset;

/**
 *
 * @author nowshad
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MoveFileExample 
{
    public void copyFile(String source, String destination){
        File src = new File(source);
        File dest = new File(destination);
        try {
            copyFileUsingJava7Files(src, dest);
        } catch (IOException ex) {
            Logger.getLogger(MoveFileExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void copyFileUsingJava7Files(File source, File dest)
		throws IOException {
	Files.copy(source.toPath(), dest.toPath());
        
    }
}
