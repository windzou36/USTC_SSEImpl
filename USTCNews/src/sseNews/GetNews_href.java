package sseNews;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class GetNews_href {
	/*
	 * 把利用jsoup获取到的新闻标题链接列表存储到String[]中
	 */
	public static String[] GetNewsHrefList() {
		String[] newHrefList = new String[20];// 新建String[]并作为全局变量
		try {
			Document doc = Jsoup.connect(
					"http://sse.ustc.edu.cn/pages/xinw.php").get();// 连接科大软院中文主页获取html源代码
			Elements element = doc.select("ul[class*=list1 news]");// 选择带有news的class属性的ul标签
			Elements links = element.select("a[href]");// 选择带有属性href的a标签
			int i=0;//为了给newHrefList赋值而设置的变量
			for (Element link : links) {// 循环遍历满足要求的links
				String linkHref = link.attr("href");
				newHrefList[i]=linkHref;// 将标题链接加入到newTitleList中
				i++;
			}
			return newHrefList;
		} catch (IOException e) {
			newHrefList[0]="糟糕，获取网页信息出错！";// 如果获取html源代码出错，那么提示出错信息
			return newHrefList;// 返回出错信息
		}
	}
}
