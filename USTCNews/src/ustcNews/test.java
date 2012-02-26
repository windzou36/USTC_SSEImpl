package ustcNews;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test {
 public static void main(String args[]){
	 /*Document d=Jsoup.parse("<body><span><div><P align=justify>&nbsp;</P><div></span></body>");
	
		Element para_html=d.select("body").first();*/
		//Elements l=para_html.select("FONT");
		//for(Element l1:l){
			//l1.;
		//}
		// Document d1=Jsoup.parse(para_html.html().replace("&nbsp;","tag"));
		//para_html=d1.getElementsByTag("body").first();
		
	//	System.out.println(para_html.select("p").isEmpty());
		//System.out.println(para_html.select("span").html());
		/* Document d1=Jsoup.parse(para_html.html().replace("&nbsp;","tag"));
			boolean judge1=para_html.html().equals("");
			//System.out.println(judge1);
			boolean judge2=para_html.select("IMG").hasAttr("src");
			//System.out.println(judge2);
			boolean judge= (!judge1)&&(!judge2);
			//System.out.println(judge);*/
		//System.out.println(d1.text().equals("tag"));
	//	System.out.println(d.select("p").html());
	//	Element para_html1=d.select("div").first();
	//	System.out.println(para_html1.html());
	//	boolean judge= (!(para_html.hasText()))&&(!(para_html.select("IMG").hasAttr("src")));
		//System.out.println(judge);
	 String[] news_body = new String[80];
     for(String str:news_body){
    	 System.out.println(str);
     }
 }
}
