package sseNews;
import java.rmi.RemoteException;
public class GetNews_titleClient {
	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
     GetNewsTitle_sseStub stub=new GetNewsTitle_sseStub();//�½�Stub�ͻ���
      GetNewsTitle_sseStub.GetNewsTitleListResponse response=null;//����Զ�̷��صĽ��response
     response= stub.GetNewsTitleList();//��response��ֵ
     for(String str:response.get_return()){//ѭ��������ص�����
    	 System.out.println(str);
     }
	}
}
