package ustcNews;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class GetNews_href_ustc {
	/*
	 * 把利用jsoup获取到的新闻标题链接列表存储到String[]中
	 */
	public static String[] GetNewsHrefList() {
		String[] newHrefList = new String[20];// 新建String[]并作为全局变量
		try {
			Document doc = Jsoup.connect("http://news.ustc.edu.cn/xwbl/").get();// 连接科大新闻主页获取html源代码
			Elements element = doc.select("#columnShow");// 选择带有id为xwbl的div标签
			Elements links = element.select("a[href]");// 选择带有属性href的a标签
			int news_account = 0;
			for (Element href : links) {// 循环遍历满足要求的links
				if(news_account<20){
					String hrefText = href.attr("href");// 获取a标签下的href属性中的内容
					newHrefList[news_account]=hrefText.replaceFirst(".", "");// 将获得的内容去除第一个标点符号并加上前缀，最后加入新闻链接列表中
					news_account++;
				}else
				{
					break;
				}
				} 
			return newHrefList;
		} catch (IOException e) {
			newHrefList[0]="糟糕，获取网页信息出错！";// 如果获取html源代码出错，那么提示出错信息
			return newHrefList;// 返回出错信息
		}
	}
}
