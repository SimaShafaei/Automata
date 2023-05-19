import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
	
	public FileHandler() {
		super();

	}

	private int MaxSize = 5000;
    private String fileName;
    private int lineNumber;

    public String[] readLineByLine() {
        String[] out = new String[MaxSize];
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(fileName);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            int i = 0;
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                out[i++] = strLine;
            }
            lineNumber = i;
            //Close the input stream
            in.close();
        } catch (Exception e) {//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }

        return out;
    }

    public boolean writeLineByLine(String[] in) {
        BufferedWriter bufferedWriter = null;
        try {

            //Construct the BufferedWriter object
            bufferedWriter = new BufferedWriter(new FileWriter(fileName));

            for (int i=0;i<lineNumber;++i){
                bufferedWriter.write(in[i]);
                bufferedWriter.newLine();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.err.println("Error: " + ex.getMessage());

        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Error: " + ex.getMessage());
        } finally {
            //Close the BufferedWriter
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                System.err.println("Error: " + ex.getMessage());

            }
        }
        return true;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

   /* public static void main(String args[]) {
        FileHandler fileHandler = new FileHandler();
        fileHandler.setFileName("g:\\holland\\activity.doc");
        String[] out = fileHandler.readLineByLine();
        for (int i = 0; i < fileHandler.getLineNumber(); ++i)
            System.out.println(out[i]);
    } */ 

}
