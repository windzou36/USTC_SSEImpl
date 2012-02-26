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
	 * �÷�����ȡ���ŵ�ͷ����Ϣ���������⣬�����⣬���ߣ�ʱ���Լ����������������������Ϣ����ÿ�����ֻ�ͼƬ
	 */
	public static Vector<String> GetNewsContList(int titleId) {
		Vector<String> news=new Vector<String>(100);//�½�Vector<String>����Ϊȫ�ֱ���
		 Collection<String> c=new Vector<String>(2);//����ȥ��null��""ֵ�ļ���
		try {
			Document doc = Jsoup.connect(
					"http://sse.ustc.edu.cn/pages/"
							+ GetNews_href.GetNewsHrefList()[titleId])
					.get();// ��ָ�������ű����л�ȡԴhtml����
			Element title = doc.select("div.content >h3").first();// ��ȡcontent�µ�h3����ı���
			news.add(title.text());// �ѱ����ı����뵽����ͷ��
			if (doc.select("div.content").html().contains("h4")) {// �ж�content����h4����ĸ�����
				Element subtitle = doc.select("div.content >h4").first();// ��ȡcontent�µ�h4����ĸ�����
				news.add(subtitle.text());// �Ѹ������ı����뵽����ͷ��
			} 
			Element author = doc.select("span.source").first();// ��ȡspan�µ�source����Ԫ��
			Element time = doc.select("span.time").first();// ��ȡspan�µ�time����Ԫ��
			Element click = doc.select("span.click").first();// ��ȡspan�µ�click����Ԫ��
			news.add(author.text()+"         "+time.text()+"        "+click.text());// ������,ʱ��,��������뵽����ͷ��
			Elements content = doc.select("div.content");// ��ȡdiv�µ�content����Ԫ��
			Elements paragraph=Jsoup.parse(content.html().replace("</p>", "<p>")).select("p");//��div�е�</p>����<p>����Ϊ�˷�ֹ<p>...</p>...</p>����������֡����ѡ��p��ǩ
			int para_size = paragraph.size();// ��ȡp��ǩ����
			Document deal_string;// ���������ַ�����Document
			int str_split_len;// p��ǩ����<br />�ָ���ַ�������
			Element para_html;// p��ǩ����<br />�ָ���ַ�����Element
			String[] para_spilt;// p��ǩ����<br />�ָ���ַ�������
			String img_add;// ͼƬ����Ե�ַ
			String img_prefix = "http://sse.ustc.edu.cn/";// ͼƬ�ľ��Ե�ַ��ǰ׺
			int sum=0;
			for (int n = 0; n < para_size; n++) {
				para_html = paragraph.get(n);// ��ȡÿ��p��ǩ
				if ((!(para_html.hasText()) && !(para_html.select("IMG")
						.hasAttr("src"))))// �������<p></p>��������������´�ѭ��
					continue;
				para_spilt = para_html.html().split("<br />");// ��p��ǩ����<br
																// />�ָ��ַ���
				str_split_len = para_spilt.length;// ��ȡ�ַ�������
				for (int i = 0; i < str_split_len; i++) {
					deal_string = Jsoup.parse(para_spilt[i].replace("&nbsp;",
							""));// ��ÿ�ηָ���ַ����е�&nbsp;ȥ��
					if (!(deal_string.select("IMG").isEmpty())) {// �жϸ��ַ�����Document���Ƿ����IMG��ǩ
						img_add = deal_string.select("IMG").attr("src");// ��ȡsrc�����µ�ͼƬ��Ե�ַ
						news.add(img_prefix + img_add);
					} else {
						news.add(deal_string.text());// û��ͼƬ������ı���������������
					}
					deal_string = null;// ���deal_string
				}
				sum+=str_split_len;
			}
            c.add(null);
		    c.add("");
			news.removeAll(c);    
			return news;
		} 
			catch (IOException e) {
			news.add("��⣬��ȡ��ҳ��Ϣ����!");
			return news;
		}
	}
}
