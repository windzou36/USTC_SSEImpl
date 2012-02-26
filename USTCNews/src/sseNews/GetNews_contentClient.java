package sseNews;
import java.rmi.RemoteException;
public class GetNews_contentClient {
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		GetNewsContent_sseStub stub = new GetNewsContent_sseStub();//生成Stub客户端
		GetNewsContent_sseStub.GetNewsContList request = new GetNewsContent_sseStub.GetNewsContList();//新建request请求
		request.setTitleId(0);//通过request设置远程函数的参数
		sseNews.GetNewsContent_sseStub.GetNewsContListResponse response = stub.GetNewsContList(request);//新建远程返回的结果response
		for (String str : response.get_return()) {//循环获取response的结果
			if (!(str == null))
				System.out.println(str);//输出结果
		}
	}
}
