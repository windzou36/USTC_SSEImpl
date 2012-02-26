package ustcNews;
import java.rmi.RemoteException;
public class GetNews_content_ustcClient {
	public static void main(String[] args) throws RemoteException {
		// TODO Auto-generated method stub
		GetNewsContent_ustcStub stub = new GetNewsContent_ustcStub();//生成Stub客户端
		GetNewsContent_ustcStub.GetNewsContList request = new GetNewsContent_ustcStub.GetNewsContList();//新建请求request
		request.setTitleId(22);//通过request设置远程函数的参数
		GetNewsContent_ustcStub.GetNewsContListResponse response = stub
				.GetNewsContList(request);//新建远程返回结果response
		for (String str : response.get_return()) {//循环输出response的结果
			if (!(str == null))
				System.out.println(str);
		}
	}
}
