import java.util.*;
import java.util.regex.*;
import java.io.*;
import java.net.*;
public class Scrape {
	private List<URL> url;
	private String find[];
	private Map<String, Map<String, List<String>>> found;

	/**
	* Scrape
	* @param url takes an array of string containing all the urls you want to search
	* @param find contians an array of string with the contents you wish to find. This does support regex
	**/
	public Scrape(String[] url, String find[]){
		this.url = new ArrayList<URL>();
		try{
			for (int i = 0;i<url.length; i++) {
				this.url.add(new URL(url[i]));
			}
		} catch(Exception e){
			System.err.println(e.toString()); //create a better exception for this
		}
		this.find = find;
		found = new HashMap<>();
		getPage();
		// for (int i = 0;i<found.get(url[0]).size(); i++) {
		// 	System.out.println(found.get(url[0]).get(i));
		// }
	}


	public Map<String, List<String>> getFound(String url){
		return found.get(url);
	}
	/**
	* Fetches the pages specified in the url array
	* will call searchPage() to find the corrosponding matches and
	* stores any foind matches in the map(found)
	* @see searchPage
	**/
	public void getPage(){
		for(URL u : url){
			try{
				found.put(u.toString(), searchPage(loadPage(u)));
			} catch(Exception e){ //needs less general exceptions
				System.err.println("hello");
			}
		}
	}
	/**
	* helper method for getPage() which process the url
	* @param url takes a url and extracts the contents and puts it into a string
	* @see getPage
	**/
	private String loadPage(URL url) throws Exception{//rewrite with a less general exception
		URLConnection c = url.openConnection();
		InputStream in = c.getInputStream();
		String encoding = c.getContentEncoding();
		encoding = encoding == null ? "UTF-8" : encoding;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buf = new byte[8192];
		int len = 0;
		while ((len = in.read(buf)) != -1) {
			baos.write(buf, 0, len);
		}
		String body = new String(baos.toByteArray(), encoding);
		return body;
	}

	/**
	* searchs the given page for any matches it finds
	* returns a string that corrosponds to the match, other wise null if no match in found
	* @param body the html code of the loaded page
	* @return String
	**/
	private Map<String, List<String>> searchPage(String body){
		Map<String, List<String>> l = new HashMap<>();
		for(String reg : find){
			List<String> list = new ArrayList<>();
			try{
				Pattern regex = Pattern.compile(reg);
				Matcher regexMatcher = regex.matcher(body);
				while (regexMatcher.find()) {
					for (int i = 1; i <= regexMatcher.groupCount(); i++) {
						list.add(regexMatcher.group(i));
					}
				}
			} catch(Exception e){//less general exception
			}
			l.put(reg, list);
		}
		return l;
	}

}
