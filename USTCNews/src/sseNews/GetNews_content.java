package sseNews;
import java.io.IOException;
import java.util.Collection;
import java.util.Vector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class GetNews_content {
	/*
	 * 该方法获取新闻的头部信息，包括标题，副标题，作者，时间以及点击量。还有新闻正文信息包含每段文字或图片
	 */
	public static Vector<String> GetNewsContList(int titleId) {
		Vector<String> news=new Vector<String>(100);//新建Vector<String>并作为全局变量
		 Collection<String> c=new Vector<String>(2);//用于去除null和""值的集合
		try {
			Document doc = Jsoup.connect(
					"http://sse.ustc.edu.cn/pages/"
							+ GetNews_href.GetNewsHrefList()[titleId])
					.get();// 从指定的新闻标题中获取源html代码
			Element title = doc.select("div.content >h3").first();// 获取content下的h3字体的标题
			news.add(title.text());// 把标题文本加入到新闻头部
			if (doc.select("div.content").html().contains("h4")) {// 判断content有无h4字体的副标题
				Element subtitle = doc.select("div.content >h4").first();// 获取content下的h4字体的副标题
				news.add(subtitle.text());// 把副标题文本加入到新闻头部
			} 
			Element author = doc.select("span.source").first();// 获取span下的source属性元素
			Element time = doc.select("span.time").first();// 获取span下的time属性元素
			Element click = doc.select("span.click").first();// 获取span下的click属性元素
			news.add(author.text()+"         "+time.text()+"        "+click.text());// 把作者,时间,点击量加入到新闻头部
			Elements content = doc.select("div.content");// 获取div下的content属性元素
			Elements paragraph=Jsoup.parse(content.html().replace("</p>", "<p>")).select("p");//把div中的</p>换成<p>，是为了防止<p>...</p>...</p>这种情况出现。最后选择p标签
			int para_size = paragraph.size();// 获取p标签数量
			Document deal_string;// 声明处理字符串的Document
			int str_split_len;// p标签下以<br />分割的字符串长度
			Element para_html;// p标签下以<br />分割的字符串的Element
			String[] para_spilt;// p标签下以<br />分割的字符串数组
			String img_add;// 图片的相对地址
			String img_prefix = "http://sse.ustc.edu.cn/";// 图片的绝对地址的前缀
			int sum=0;
			for (int n = 0; n < para_size; n++) {
				para_html = paragraph.get(n);// 获取每个p标签
				if ((!(para_html.hasText()) && !(para_html.select("IMG")
						.hasAttr("src"))))// 如果碰到<p></p>这种情况就跳到下次循环
					continue;
				para_spilt = para_html.html().split("<br />");// 将p标签下以<br
																// />分割字符串
				str_split_len = para_spilt.length;// 获取字符串长度
				for (int i = 0; i < str_split_len; i++) {
					deal_string = Jsoup.parse(para_spilt[i].replace("&nbsp;",
							""));// 将每段分割的字符串中的&nbsp;去掉
					if (!(deal_string.select("IMG").isEmpty())) {// 判断该字符串的Document中是否包含IMG标签
						img_add = deal_string.select("IMG").attr("src");// 获取src属性下的图片相对地址
						news.add(img_prefix + img_add);
					} else {
						news.add(deal_string.text());// 没有图片则就是文本并加入新闻正文
					}
					deal_string = null;// 清空deal_string
				}
				sum+=str_split_len;
			}
            c.add(null);
		    c.add("");
			news.removeAll(c);    
			return news;
		} 
			catch (IOException e) {
			news.add("糟糕，获取网页信息出错!");
			return news;
		}
	}
}
