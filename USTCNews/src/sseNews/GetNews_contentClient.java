package sseNews;
import java.rmi.RemoteException;
public class GetNews_contentClient {
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		GetNewsContent_sseStub stub = new GetNewsContent_sseStub();//����Stub�ͻ���
		GetNewsContent_sseStub.GetNewsContList request = new GetNewsContent_sseStub.GetNewsContList();//�½�request����
		request.setTitleId(0);//ͨ��request����Զ�̺����Ĳ���
		sseNews.GetNewsContent_sseStub.GetNewsContListResponse response = stub.GetNewsContList(request);//�½�Զ�̷��صĽ��response
		for (String str : response.get_return()) {//ѭ����ȡresponse�Ľ��
			if (!(str == null))
				System.out.println(str);//������
		}
	}
}
