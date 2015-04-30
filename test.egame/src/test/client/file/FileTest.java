package test.client.file;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import cn.egame.client.EGameClientV2;
import cn.egame.common.util.Utils;
import cn.egame.interfaces.ExceptionCommon;
import cn.egame.interfaces.fl.FileInfo;
import cn.egame.interfaces.fl.FileUsedType;
import cn.egame.interfaces.fl.FileUtils;

public class FileTest {
	public static void main(String[] args) {
		Utils.initLog4j();
		System.out.println(Utils.getFileId("6399c276h1b8eeab", 0));
	}
	
	
	@Test
	public void getFilePath(){
		try {
			Utils.initLog4j();
			FileInfo fi = EGameClientV2.getInstance().getFileInfo(0, 0, 1806058);
			System.out.println(FileUtils.getFilePath(fi.getFileUsedType(), fi.getEFSId(), fi.getFileName()));
		} catch (ExceptionCommon e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
//	public static void main(String[] args) {
//		Utils.initLog4j();
//	}
	
//	@Test
	public void fileUsedTypeTest() throws RemoteException{
		String fileUrl = "";
//            try {
//            	FileUsedType[] types = FileUsedType.values();
            	FileUsedType fut = FileUsedType.game_photo;
//            	for(FileUsedType fut : types){
            		FileInfo fileInfo = EGameClientV2.getInstance().getGameIconByGidAndFileType(0, 0, 1, fut);
//            	}
            	throw new RuntimeException("new RuntimeException...");
            		
//			} catch (RemoteException e) {
////				logger.error(e.getMessage(), e);
//				logger.error(e);
//			}
//            fileUrl = Urls.getFileUrlByFileInfo(request, appId, loginUserId, fileInfo, fileUsedTypeStore, fileUsedTypeShow);
//        return fileUrl;
	}
}
