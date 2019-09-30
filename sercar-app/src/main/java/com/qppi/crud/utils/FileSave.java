package com.qppi.crud.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileSave {
	public static boolean saveFile(InputStream inputStream, String path, String fileName) {
        try {
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            OutputStream os = new FileOutputStream(path + fileName);
            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            os.close();
            inputStream.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
    }
	
	public static void main(String[] args) throws FileNotFoundException {
		InputStream in = new FileInputStream(new File("D:\\test.html"));
		String path = "E:/file-server/file/34934040404004/";
		String fileName = "a.jsp";
		boolean test = saveFile(in, path, fileName);
		System.out.println(test);
	}
}