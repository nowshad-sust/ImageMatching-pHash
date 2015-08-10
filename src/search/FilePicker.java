//simple GUI for the Matching program
package search;
//importing requird libraries 
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
 
public class FilePicker extends JPanel {
    
    //declaring required variables
    private String textFieldLabel;
    private String buttonLabel;
     
    private JLabel label;
    public static JTextField textField;
    private JButton browseButton;
    
     
    private JFileChooser fileChooser;
    
    public static String fileName, outputDestination, filePath;
    
    private int mode;
    public static final int MODE_OPEN = 1;
    public static final int MODE_SAVE = 2;
    
    public static JLabel categoryLabel;
    
    //Constructor for the UI
    public FilePicker(String textFieldLabel, String buttonLabel) {
        
        this.textFieldLabel = textFieldLabel;
        this.buttonLabel = buttonLabel;
         
        fileChooser = new JFileChooser("H:\\");
         
        label = new JLabel(textFieldLabel);
         
        textField = new JTextField(30);
        
        browseButton = new JButton(buttonLabel);

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                browseButtonActionPerformed(evt);           
            }
        });
        
        
         
        add(label);
        add(textField);
        add(browseButton);
        
    }
    
    //browse button click event
    private void browseButtonActionPerformed(ActionEvent evt) {
        if (mode == MODE_OPEN) {
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        } else if (mode == MODE_SAVE) {
            if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                textField.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        }
        this.filePath = getSelectedFilePath();
    }
    
    
    //adding required filter for browsing files
    public void addFileTypeFilter(String extension, String description) {
        FileTypeFilter filter = new FileTypeFilter(extension, description);
        fileChooser.addChoosableFileFilter(filter);
    }
    //browsing mode
    public void setMode(int mode) {
        this.mode = mode;
    }
    //get file path of the selected file
    public static String getSelectedFilePath() {
        return textField.getText();
    }
    //get the file Chooser
    public JFileChooser getFileChooser() {
        return this.fileChooser;
    }
    
}
