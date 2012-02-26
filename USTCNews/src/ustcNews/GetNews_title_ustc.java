package ustcNews;
import java.io.IOException;
import java.util.Vector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class GetNews_title_ustc {
	/*
	 * 把利用jsoup获取到的新闻标题列表存储到String[]中
	 */
	public static Vector<String> GetNewsTitleList() {
		Vector<String> newTitleList = new Vector<String>(20);//新建Vector<String>并作为全局变量
		String[] deal;//为了加日期而设置的字符串数组
		try {
			Document doc = Jsoup.connect("http://news.ustc.edu.cn/xwbl/").get();// 连接科大新闻主页获取html源代码
			Elements element = doc.select("#columnShow");// 选择带有id为xwbl的div标签
			Elements links = element.select("li");// 选择带li标签
			int counter=0;//为了给newTitleList[]赋值而设置的变量
			for (Element title : links) {// 循环遍历满足要求的links
				if(counter<20){
					String titleText = title.text();
					int length=titleText.length();
				    deal=titleText.split("\\d{4}-\\d{2}-\\d{2}");//从字符串的xxxx-xx-xx年月日开始，把字符串分成两个子字符串
					titleText=deal[0]+"["+ titleText.substring(length-10)+"]";//新闻标题后面加上了[xxxx-xx-xx]时间
					newTitleList.add(titleText);// 将标题的文本加入到list中
					counter++;
				}
				else{
					break;
				}
				} 
			return newTitleList;
		} catch (IOException e) {
			newTitleList.add("糟糕，获取网页信息出错！");// 如果获取html源代码出错，那么提示出错信息
			return newTitleList;// 返回出错信息
		}
	}
}
