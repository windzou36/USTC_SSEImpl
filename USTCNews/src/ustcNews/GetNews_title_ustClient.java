package ustcNews;
import java.rmi.RemoteException;
public class GetNews_title_ustClient {
	/**
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		GetNewsTitle_ustcStub stub = new GetNewsTitle_ustcStub();//�½�Stub�ͻ���
		GetNewsTitle_ustcStub.GetNewsTitleListResponse response = null;//����Զ�̷��ؽ��response
		response = stub.GetNewsTitleList();//��Զ�̷��صĽ����ֵ��response
		for (String str : response.get_return()) {//ѭ�����response�Ľ��
			System.out.println(str);
		}
	}
}
