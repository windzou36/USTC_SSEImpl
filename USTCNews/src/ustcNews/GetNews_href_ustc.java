package ustcNews;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class GetNews_href_ustc {
	/*
	 * ������jsoup��ȡ�������ű��������б�洢��String[]��
	 */
	public static String[] GetNewsHrefList() {
		String[] newHrefList = new String[20];// �½�String[]����Ϊȫ�ֱ���
		try {
			Document doc = Jsoup.connect("http://news.ustc.edu.cn/xwbl/").get();// ���ӿƴ�������ҳ��ȡhtmlԴ����
			Elements element = doc.select("#columnShow");// ѡ�����idΪxwbl��div��ǩ
			Elements links = element.select("a[href]");// ѡ���������href��a��ǩ
			int news_account = 0;
			for (Element href : links) {// ѭ����������Ҫ���links
				if(news_account<20){
					String hrefText = href.attr("href");// ��ȡa��ǩ�µ�href�����е�����
					newHrefList[news_account]=hrefText.replaceFirst(".", "");// ����õ�����ȥ����һ�������Ų�����ǰ׺�����������������б���
					news_account++;
				}else
				{
					break;
				}
				} 
			return newHrefList;
		} catch (IOException e) {
			newHrefList[0]="��⣬��ȡ��ҳ��Ϣ����";// �����ȡhtmlԴ���������ô��ʾ������Ϣ
			return newHrefList;// ���س�����Ϣ
		}
	}
}
