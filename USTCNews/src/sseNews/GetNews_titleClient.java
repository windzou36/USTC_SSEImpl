package sseNews;
import java.rmi.RemoteException;
public class GetNews_titleClient {
	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
     GetNewsTitle_sseStub stub=new GetNewsTitle_sseStub();//新建Stub客户端
      GetNewsTitle_sseStub.GetNewsTitleListResponse response=null;//声明远程返回的结果response
     response= stub.GetNewsTitleList();//给response赋值
     for(String str:response.get_return()){//循环输出返回的内容
    	 System.out.println(str);
     }
	}
}
