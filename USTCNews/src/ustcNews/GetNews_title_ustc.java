package ustcNews;
import java.io.IOException;
import java.util.Vector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class GetNews_title_ustc {
	/*
	 * ������jsoup��ȡ�������ű����б�洢��String[]��
	 */
	public static Vector<String> GetNewsTitleList() {
		Vector<String> newTitleList = new Vector<String>(20);//�½�Vector<String>����Ϊȫ�ֱ���
		String[] deal;//Ϊ�˼����ڶ����õ��ַ�������
		try {
			Document doc = Jsoup.connect("http://news.ustc.edu.cn/xwbl/").get();// ���ӿƴ�������ҳ��ȡhtmlԴ����
			Elements element = doc.select("#columnShow");// ѡ�����idΪxwbl��div��ǩ
			Elements links = element.select("li");// ѡ���li��ǩ
			int counter=0;//Ϊ�˸�newTitleList[]��ֵ�����õı���
			for (Element title : links) {// ѭ����������Ҫ���links
				if(counter<20){
					String titleText = title.text();
					int length=titleText.length();
				    deal=titleText.split("\\d{4}-\\d{2}-\\d{2}");//���ַ�����xxxx-xx-xx�����տ�ʼ�����ַ����ֳ��������ַ���
					titleText=deal[0]+"["+ titleText.substring(length-10)+"]";//���ű�����������[xxxx-xx-xx]ʱ��
					newTitleList.add(titleText);// ��������ı����뵽list��
					counter++;
				}
				else{
					break;
				}
				} 
			return newTitleList;
		} catch (IOException e) {
			newTitleList.add("��⣬��ȡ��ҳ��Ϣ����");// �����ȡhtmlԴ���������ô��ʾ������Ϣ
			return newTitleList;// ���س�����Ϣ
		}
	}
}
