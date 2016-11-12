import java.util.*;
import java.io.*;
public class SaveCSV extends Save{
	private String[] searches;
	private Scrape s;
	String urls[] = {"http://www.hoax-slayer.com/"};

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
        //read these from a file. Probably in the main SAVE class
        s = new Scrape(urls, searches);
	}

	public void writeFile(){	
		PrintWriter pw = null;
		try{
			pw = new PrintWriter(new File(fileName));
		} catch(IOException e){
			System.err.println("file not found" + fileName);
		}
		StringBuilder sb = new StringBuilder();
		Map<String, List<String>> l = s.getFound(urls[0]);

		//System.out.println(l.get(searches[1])); 

		//I HAVE ABSOLUTLY NO FUCKING IDEA WHY THIS IS WORKNIG THE WAY IT IS
		for(List<String> li : l.values()){
			for (int d=0;d<li.size();d++) {
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
			//break;
		}
		pw.write(sb.toString());
		pw.close();
	}		

	public void writeFound(){}//probably a uselses method
}
