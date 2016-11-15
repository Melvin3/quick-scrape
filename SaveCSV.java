import java.util.*;
import java.io.*;
public class SaveCSV extends Save{
	private String[] searches;
	private Scrape s;
	//TODO: need to support multiple urls;
	String urls[] = {"http://play.typeracer.com/"};//

	public SaveCSV(String fileName){
		this.fileName = fileName;
		processCSV();
		writeFile();
	}	
	//TODO: I need to add some proper input checking. 
	//i.e if I wanted to find something that had a comma I wouldn't be able to currently
	private void processCSV(){
		String[] input = super.readFile().split(",");
		searches = new String[input.length];
 		for(int i = 0; i < input.length; i++)
 			searches[i] = input[i];		        
        s = new Scrape(urls, searches);
	}

	public void writeFile(){
		PrintWriter pw = null;
		StringBuilder sb = new StringBuilder();
		Map<String, List<String>> l = s.getFound(urls[0]);
		try{
			pw = new PrintWriter(new File(fileName));
		} catch(IOException e){
			System.err.println("file not found" + fileName);
		}

		for (int d=0;d<getBiggest();d++) {
			System.out.println(l.size());
			for (int k = 0;k<searches.length;k++){	
				if(d >= l.get(searches[k]).size()) 
				{
					sb.append(",");
					continue;
				}
				sb.append(l.get(searches[k]).get(d));
				sb.append(",");
			}				
			sb.append("\n");
		}

		pw.write(sb.toString());
		pw.close();
	}		

	private int getBiggest(){
		int currentBiggest =0;
		Map<String, List<String>> l = s.getFound(urls[0]);
		for (List<String> t : l.values()) {
			currentBiggest = currentBiggest > t.size() ? currentBiggest : t.size();
		}
		return currentBiggest;
	}
}
