package ustcNews;
import java.io.IOException;
import java.util.Collection;
import java.util.Vector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class GetNews_content_ustc {
	/*
	 * 该方法获取新闻的头部信息，包括标题，副标题，时间以及导言。还有新闻正文信息包含每段文字或图片
	 */
	public static Vector<String> GetNewsContList(int titleId) {
		Vector<String> news = new Vector<String>(100);//新建Vector<String>并作为全局变量
		Collection<String> c = new Vector<String>(2);// 用于去除null和""值的集合
		try {
			Document doc = Jsoup.connect(
					"http://news.ustc.edu.cn/xwbl"
							+ GetNews_href_ustc.GetNewsHrefList()[titleId])
					.get();// 从指定的新闻标题中获取源html代码
			Element title = doc.select("span.articleShowTitle").first();// 定位到span属性为articleShowTitle，从而获取标题
			news.add(title.text());// 把标题文本加入到新闻头部
			if (doc.select("span.articleShowSubtitle").hasText()) {// 定位到span属性为articleShowTitle，从而判断有无内容
				Element subtitle = doc.select("span.articleShowSubtitle")
						.first();// 获取content下的h4字体的副标题
				news.add(subtitle.text());// 把副标题文本加入到新闻头部
			}
			Element time = doc.select("span.articleShowTime").first();// 定位到span属性为articleShowTime，从而获取新闻撰写时间
			news.add("                          "+time.text());// 把作者加入到新闻头部
			if (doc.select("span.articleSummary").hasText()) {// 定位到span属性为articleShowSummary，从而判断有无内容
				Element summary = doc.select("span.articleSummary").first();
				news.add("导言:" + summary.text());// 把导言文本加入到新闻头部
			}
			Element content = doc.select("span.articleShowContent").first();// 定位到span属性为articleShowContent
			Elements paragraph;// 声明
			boolean div_p = true;// 初始化
			boolean table_p = true;// 初始化
			boolean div = content.getElementsByTag("div").isEmpty();// 判断有无div标签
			boolean table = content.getElementsByTag("table").isEmpty();// 判断有无table标签
			boolean div_table = div && table;// 当既无div标签也无table标签的时候
			boolean No_div_table = (div_table == true);// 判断是不是出现a5的情况
			if (!div)
				div_p = content.select("div").first().getElementsByTag("p")
						.isEmpty();// 判断div标签下有无p标签
			if (!table)
				table_p = content.select("table").first().getElementsByTag("p")
						.isEmpty();// 判断table标签下有无p标签
			if (((!div) && (!div_p)) || ((!table) && (!table_p))
					|| (No_div_table)) {// 如果出现div有p，table有p以及只有p的情况下就把p作为选择器
				paragraph = content.select("p");// 在content基础上选择p标签
			} else if (content.getElementsByTag("p").isEmpty()) {// 否则没有p标签就把div作为选择器
				paragraph = content.select("div");// 在content基础上选择div标签
			} else {
				paragraph = content.select("div,p");// 在content基础上选择p,div标签
			}
			int para_size = paragraph.size();// 获取p或div标签数量
			Document deal_string;// 声明处理字符串的Document
			int str_split_len;// p或div标签下以<br />分割的字符串长度
			Element para_html;// p或div标签下以<br />分割的字符串的Element
			String[] para_spilt;// p或div标签下以<br />分割的字符串数组
			String img_add;// 图片的相对地址
			String img_prefix = "http://news.ustc.edu.cn/xwbl/";// 图片的绝对地址的前缀
			int sum = 0;
			for (int n = 0; n < para_size; n++) {
				para_html = paragraph.get(n);// 获取每个p或div标签
				String str = para_html.html().replace("&nbsp;", "");// 将每个p或div标签中得&nbsp;用""来代替，是为了处理方便
				Document new_docm = Jsoup.parse(str);// 讲str生成html文档
				boolean judge1 = new_docm.hasText();// 判断new_docm有无内容
				boolean judge3 = new_docm.text().equals("");// 判断是否是刚才替换的""
				boolean judge2 = new_docm.select("IMG").hasAttr("src");// 判断是否包含IMG标签
				boolean judge = (!judge1) && (!judge2);// 排除<p></p>或<div></div>这种情况
				boolean judge4 = (judge3) && (!judge2);// 排除<p>&nbsp;</p>或<div>&nbsp;</div>这种情况
				if (judge)
					continue; // 如果碰到<p></p>或<div></div>这种情况就跳到下次循环
				if (judge4)
					continue; // 如果碰到<p>&nbsp;</p>或<div>&nbsp;</div>这种情况就跳到下次循环
				para_spilt = new_docm.getElementsByTag("body").first().html()
						.split("<br />");// 将p或div标签下以<br />分割字符串
				str_split_len = para_spilt.length;// 获取字符串长度
				for (int i = 0; i < str_split_len; i++) {
					deal_string = Jsoup.parse(para_spilt[i]);// 将每段分割的字符串重新生成Document
					if (!(para_html.select("IMG").isEmpty())) {// 判断该字符串的Document中是否包含IMG标签
						img_add = deal_string.select("IMG").attr("OLDSRC");// 获取OLDSRC属性下的图片相对地址
						news.add(img_prefix
								+ time.text().replace("-", "")
										.subSequence(0, 6) + "/" + img_add);
					} else {
						String str1 = deal_string.text();
						if (str1.equals(""))// 如果匹配则说明是这种情况<p>&nbsp;</p>或<div>&nbsp;</div>
							continue;// 如果是上述情况则跳出本次循环，继续下次循环
						else
							news.add(deal_string.text());// 没有图片则就是文本并加入新闻正文
					}
					deal_string = null;// 清空deal_string
				}
				sum += str_split_len;
			}
			c.add(null);
			c.add("");
			news.removeAll(c);
			return news;
		} catch (IOException e) {
			news.add("糟糕，获取网页信息出错!");
			return news;
		}
	}
}
