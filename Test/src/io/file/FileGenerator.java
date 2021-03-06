package io.file;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Random;

import org.apache.log4j.Logger;
import org.junit.Test;

import cn.egame.common.efs.IFileSystem;
import cn.egame.common.efs.SFileSystemClient;
import cn.egame.common.util.Utils;
import cn.egame.common.web.WebUtils;

public class FileGenerator {
	
	private static Logger LOGGER = Logger.getLogger(FileGenerator.class);
	
	@Test
	public void generator721() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
					"C:\\Users\\yuchao\\Desktop\\testImsi.txt")));
			for (long i = 100000000000000L; i < 100000000000000L+200000; i++) {
				String imsi = "T"+i;
//				String str = "/api/v2/mobile/channel/content.json?channel_id=721&terminal_id=2&current_page=0&rows_of_page=20&imsi="
//						+randomImsi();
				pw.println(imsi);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}

	}
	
	private static String randomImsi(){
		Random random = new Random();
//		460036660132243
		String imsi = "";
		for(int i=0; i<15; i++){
			imsi = imsi + random.nextInt(10);
		}
		return imsi;
	}
	
	@Test
	public void generatorHallUpgrade() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
					"out.txt")));
			for (int i = 100; i < 100000 + 100; i++) {
				String str = "/api/v2/mobile/hall/check_version.json?model="
						+ i
						+ "&screen_px=320*480&os=android&memory=256&version_code=11&api_level=13";
				pw.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}

	}
	
	@Test
	public void generatorIpFile() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
					"ip.txt")));
			for (long i = 0; i < 5000; i++) {
				String ip = "";
				for(int j = 0; j < 4; j++){
					int ipVal = new Random().nextInt(255);
					ip = ip + ipVal +".";
				}
				ip = ip.substring(0, ip.length()-1);
				pw.println(ip);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
	}
	
	
	@Test
	public void generatorImsi() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
					"imsiblack.txt")));
			long initImsi = 460027529035881L;
			for (long i = 1000000; i < 2000000; i++) {
				String str = initImsi + i + "";
				pw.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pw.flush();
			pw.close();
		}
	}
	
	@Test
	public void generatorFileForRsyncTest() {
		long currentTimeMillis = System.currentTimeMillis();
		//??????????????????????????????????????????,??????rsync???inotify??????
//		String basePath = "E:/data/cdn";
		String basePath = "/test/src";
		String filePath = basePath +"/file/" + Utils.toPath("/", currentTimeMillis) + "/" + Utils.toFileName(currentTimeMillis) + "/"+currentTimeMillis+".txt";
		LOGGER.info("filePath:" + filePath);
		IFileSystem fileSystem = SFileSystemClient.getInstance("egame");
		PrintWriter pw = null;
		try {
			fileSystem.mkdirs(filePath);
			pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
					filePath)));
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<80; i++){
				sb = sb.append(currentTimeMillis);
			}
			pw.println(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(pw!=null){
				pw.flush();
				pw.close();
			}
		}
	}
	
	public static void main(String[] args) {
		/*
		Utils.initLog4j();
		//????????????????????????

		new Thread(new Runnable() {
			
			@Override
			public void run() {
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
							"/test/src/666.txt")));
					StringBuilder sb = new StringBuilder();
					for(int i=0; i<10; i++){
						sb = sb.append(6);
						Thread.sleep(1000);
					}
					pw.println(sb.toString());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if(pw!=null){
						pw.flush();
						pw.close();
					}
				}
			}
		}).start();;
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				PrintWriter pw = null;
				try {
					pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(
							"/test/src/777.txt")));
					StringBuilder sb = new StringBuilder();
					for(int i=0; i<10; i++){
						sb = sb.append(7);
						Thread.sleep(100);
					}
					pw.println(sb.toString());
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if(pw!=null){
						pw.flush();
						pw.close();
					}
				}
			}
		}).start();;
		*/
		while(true){
			new FileGenerator().generatorFileForRsyncTest();
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				LOGGER.error("", e);
			}
		}
	}
	
	
	@Test
	public void sendGetToImsi() {
		try {
			long initImsi = 5000000000000L;
			for (long i = 0; i < 5000; i++) {
				String str = initImsi + i + "";
				System.out.println(str);
				String urlStr = "http://192.168.70.159:8808/api/v2/push/sdk/memory_msg.json?imsi="+str;
				try {
					WebUtils.http(urlStr, "GET", "UTF-8", null, null, null);
				} catch (Exception e) {
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
