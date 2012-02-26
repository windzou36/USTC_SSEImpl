package sseNews;
import java.io.IOException;
import java.util.Vector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class GetNews_title {
	/*
	 * ������jsoup��ȡ�������ű����б�洢��String[]��
	 */
	public static Vector<String> GetNewsTitleList() {
		Vector<String> newTitleList = new Vector<String>(20);//�½�Vector<String>����Ϊȫ�ֱ���
		try {
			Document doc = Jsoup.connect(
					"http://sse.ustc.edu.cn/pages/xinw.php").get();// ���ӿƴ���Ժ������ҳ��ȡhtmlԴ����
			Elements element = doc.select("ul[class*=list1 news]");// ѡ�����news��class���Ե�ul��ǩ
			Elements links = element.select("a[href]");// ѡ���������href��a��ǩ
			links.attr("title", "");// ���ոջ�ȡ��a��ǩ�е�title����ֵ��Ϊ���ַ���
			for (Element title : links) {// ѭ����������Ҫ���links
				String titleText = title.text();
				newTitleList.add(titleText);// ��������ı����뵽newTitleList��
			}
			return newTitleList;
		} catch (IOException e) {
			newTitleList.add("��⣬��ȡ��ҳ��Ϣ����");// �����ȡhtmlԴ���������ô��ʾ������Ϣ
			return newTitleList;// ���س�����Ϣ
		}
	}
}
