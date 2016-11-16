import java.io.*;
import java.util.*;
public abstract class Save{
	protected String fileName;
	
	public String readFile(){	
		//will open the file, read the viarables and scrape based on these. 
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String everything = sb.toString();
			System.out.println(everything);
		    return everything;
		} catch (IOException e){ 
			System.err.println("Something broke m8" + e.toString());
		}
		return null;
	}
}
