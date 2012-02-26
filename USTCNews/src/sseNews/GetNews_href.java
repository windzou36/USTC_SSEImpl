package sseNews;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class GetNews_href {
	/*
	 * ������jsoup��ȡ�������ű��������б�洢��String[]��
	 */
	public static String[] GetNewsHrefList() {
		String[] newHrefList = new String[20];// �½�String[]����Ϊȫ�ֱ���
		try {
			Document doc = Jsoup.connect(
					"http://sse.ustc.edu.cn/pages/xinw.php").get();// ���ӿƴ���Ժ������ҳ��ȡhtmlԴ����
			Elements element = doc.select("ul[class*=list1 news]");// ѡ�����news��class���Ե�ul��ǩ
			Elements links = element.select("a[href]");// ѡ���������href��a��ǩ
			int i=0;//Ϊ�˸�newHrefList��ֵ�����õı���
			for (Element link : links) {// ѭ����������Ҫ���links
				String linkHref = link.attr("href");
				newHrefList[i]=linkHref;// ���������Ӽ��뵽newTitleList��
				i++;
			}
			return newHrefList;
		} catch (IOException e) {
			newHrefList[0]="��⣬��ȡ��ҳ��Ϣ����";// �����ȡhtmlԴ���������ô��ʾ������Ϣ
			return newHrefList;// ���س�����Ϣ
		}
	}
}
