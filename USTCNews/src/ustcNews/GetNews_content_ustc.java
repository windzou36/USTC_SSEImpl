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
	 * �÷�����ȡ���ŵ�ͷ����Ϣ���������⣬�����⣬ʱ���Լ����ԡ���������������Ϣ����ÿ�����ֻ�ͼƬ
	 */
	public static Vector<String> GetNewsContList(int titleId) {
		Vector<String> news = new Vector<String>(100);//�½�Vector<String>����Ϊȫ�ֱ���
		Collection<String> c = new Vector<String>(2);// ����ȥ��null��""ֵ�ļ���
		try {
			Document doc = Jsoup.connect(
					"http://news.ustc.edu.cn/xwbl"
							+ GetNews_href_ustc.GetNewsHrefList()[titleId])
					.get();// ��ָ�������ű����л�ȡԴhtml����
			Element title = doc.select("span.articleShowTitle").first();// ��λ��span����ΪarticleShowTitle���Ӷ���ȡ����
			news.add(title.text());// �ѱ����ı����뵽����ͷ��
			if (doc.select("span.articleShowSubtitle").hasText()) {// ��λ��span����ΪarticleShowTitle���Ӷ��ж���������
				Element subtitle = doc.select("span.articleShowSubtitle")
						.first();// ��ȡcontent�µ�h4����ĸ�����
				news.add(subtitle.text());// �Ѹ������ı����뵽����ͷ��
			}
			Element time = doc.select("span.articleShowTime").first();// ��λ��span����ΪarticleShowTime���Ӷ���ȡ����׫дʱ��
			news.add("                          "+time.text());// �����߼��뵽����ͷ��
			if (doc.select("span.articleSummary").hasText()) {// ��λ��span����ΪarticleShowSummary���Ӷ��ж���������
				Element summary = doc.select("span.articleSummary").first();
				news.add("����:" + summary.text());// �ѵ����ı����뵽����ͷ��
			}
			Element content = doc.select("span.articleShowContent").first();// ��λ��span����ΪarticleShowContent
			Elements paragraph;// ����
			boolean div_p = true;// ��ʼ��
			boolean table_p = true;// ��ʼ��
			boolean div = content.getElementsByTag("div").isEmpty();// �ж�����div��ǩ
			boolean table = content.getElementsByTag("table").isEmpty();// �ж�����table��ǩ
			boolean div_table = div && table;// ������div��ǩҲ��table��ǩ��ʱ��
			boolean No_div_table = (div_table == true);// �ж��ǲ��ǳ���a5�����
			if (!div)
				div_p = content.select("div").first().getElementsByTag("p")
						.isEmpty();// �ж�div��ǩ������p��ǩ
			if (!table)
				table_p = content.select("table").first().getElementsByTag("p")
						.isEmpty();// �ж�table��ǩ������p��ǩ
			if (((!div) && (!div_p)) || ((!table) && (!table_p))
					|| (No_div_table)) {// �������div��p��table��p�Լ�ֻ��p������¾Ͱ�p��Ϊѡ����
				paragraph = content.select("p");// ��content������ѡ��p��ǩ
			} else if (content.getElementsByTag("p").isEmpty()) {// ����û��p��ǩ�Ͱ�div��Ϊѡ����
				paragraph = content.select("div");// ��content������ѡ��div��ǩ
			} else {
				paragraph = content.select("div,p");// ��content������ѡ��p,div��ǩ
			}
			int para_size = paragraph.size();// ��ȡp��div��ǩ����
			Document deal_string;// ���������ַ�����Document
			int str_split_len;// p��div��ǩ����<br />�ָ���ַ�������
			Element para_html;// p��div��ǩ����<br />�ָ���ַ�����Element
			String[] para_spilt;// p��div��ǩ����<br />�ָ���ַ�������
			String img_add;// ͼƬ����Ե�ַ
			String img_prefix = "http://news.ustc.edu.cn/xwbl/";// ͼƬ�ľ��Ե�ַ��ǰ׺
			int sum = 0;
			for (int n = 0; n < para_size; n++) {
				para_html = paragraph.get(n);// ��ȡÿ��p��div��ǩ
				String str = para_html.html().replace("&nbsp;", "");// ��ÿ��p��div��ǩ�е�&nbsp;��""�����棬��Ϊ�˴�����
				Document new_docm = Jsoup.parse(str);// ��str����html�ĵ�
				boolean judge1 = new_docm.hasText();// �ж�new_docm��������
				boolean judge3 = new_docm.text().equals("");// �ж��Ƿ��Ǹղ��滻��""
				boolean judge2 = new_docm.select("IMG").hasAttr("src");// �ж��Ƿ����IMG��ǩ
				boolean judge = (!judge1) && (!judge2);// �ų�<p></p>��<div></div>�������
				boolean judge4 = (judge3) && (!judge2);// �ų�<p>&nbsp;</p>��<div>&nbsp;</div>�������
				if (judge)
					continue; // �������<p></p>��<div></div>��������������´�ѭ��
				if (judge4)
					continue; // �������<p>&nbsp;</p>��<div>&nbsp;</div>��������������´�ѭ��
				para_spilt = new_docm.getElementsByTag("body").first().html()
						.split("<br />");// ��p��div��ǩ����<br />�ָ��ַ���
				str_split_len = para_spilt.length;// ��ȡ�ַ�������
				for (int i = 0; i < str_split_len; i++) {
					deal_string = Jsoup.parse(para_spilt[i]);// ��ÿ�ηָ���ַ�����������Document
					if (!(para_html.select("IMG").isEmpty())) {// �жϸ��ַ�����Document���Ƿ����IMG��ǩ
						img_add = deal_string.select("IMG").attr("OLDSRC");// ��ȡOLDSRC�����µ�ͼƬ��Ե�ַ
						news.add(img_prefix
								+ time.text().replace("-", "")
										.subSequence(0, 6) + "/" + img_add);
					} else {
						String str1 = deal_string.text();
						if (str1.equals(""))// ���ƥ����˵�����������<p>&nbsp;</p>��<div>&nbsp;</div>
							continue;// ����������������������ѭ���������´�ѭ��
						else
							news.add(deal_string.text());// û��ͼƬ������ı���������������
					}
					deal_string = null;// ���deal_string
				}
				sum += str_split_len;
			}
			c.add(null);
			c.add("");
			news.removeAll(c);
			return news;
		} catch (IOException e) {
			news.add("��⣬��ȡ��ҳ��Ϣ����!");
			return news;
		}
	}
}
