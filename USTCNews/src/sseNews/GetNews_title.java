package sseNews;
import java.io.IOException;
import java.util.Vector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class GetNews_title {
	/*
	 * 把利用jsoup获取到的新闻标题列表存储到String[]中
	 */
	public static Vector<String> GetNewsTitleList() {
		Vector<String> newTitleList = new Vector<String>(20);//新建Vector<String>并作为全局变量
		try {
			Document doc = Jsoup.connect(
					"http://sse.ustc.edu.cn/pages/xinw.php").get();// 连接科大软院中文主页获取html源代码
			Elements element = doc.select("ul[class*=list1 news]");// 选择带有news的class属性的ul标签
			Elements links = element.select("a[href]");// 选择带有属性href的a标签
			links.attr("title", "");// 将刚刚获取的a标签中的title属性值设为空字符串
			for (Element title : links) {// 循环遍历满足要求的links
				String titleText = title.text();
				newTitleList.add(titleText);// 将标题的文本加入到newTitleList中
			}
			return newTitleList;
		} catch (IOException e) {
			newTitleList.add("糟糕，获取网页信息出错！");// 如果获取html源代码出错，那么提示出错信息
			return newTitleList;// 返回出错信息
		}
	}
}
