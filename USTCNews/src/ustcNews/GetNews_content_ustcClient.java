package ustcNews;
import java.rmi.RemoteException;
public class GetNews_content_ustcClient {
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		GetNewsContent_ustcStub stub = new GetNewsContent_ustcStub();//����Stub�ͻ���
		GetNewsContent_ustcStub.GetNewsContList request = new GetNewsContent_ustcStub.GetNewsContList();//�½�����request
		request.setTitleId(22);//ͨ��request����Զ�̺����Ĳ���
		GetNewsContent_ustcStub.GetNewsContListResponse response = stub
				.GetNewsContList(request);//�½�Զ�̷��ؽ��response
		for (String str : response.get_return()) {//ѭ�����response�Ľ��
			if (!(str == null))
				System.out.println(str);
		}
	}
}
