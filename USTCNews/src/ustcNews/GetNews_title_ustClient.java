package ustcNews;
import java.rmi.RemoteException;
public class GetNews_title_ustClient {
	/**
	 * @param args
	 * @throws RemoteException
	 */
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		GetNewsTitle_ustcStub stub = new GetNewsTitle_ustcStub();//新建Stub客户端
		GetNewsTitle_ustcStub.GetNewsTitleListResponse response = null;//声明远程返回结果response
		response = stub.GetNewsTitleList();//把远程返回的结果赋值给response
		for (String str : response.get_return()) {//循环输出response的结果
			System.out.println(str);
		}
	}
}
