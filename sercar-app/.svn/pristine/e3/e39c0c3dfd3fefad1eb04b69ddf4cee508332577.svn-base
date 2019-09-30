package com.qppi.crud.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import com.qppi.crud.controller.BaseController;

public class MultipartFileUtils {
	private static String projectName = "sercar-app";//项目名
	private static String beginName = "file_";//文件名前缀
	
	public static final String SERVICEPHOTO_SAVE_PATH="/src/main/webapp/servicePhotoFile";//服务顾问头像保存路径
	public static final String WORKPHOTO_SAVE_PATH="/src/main/webapp/workPhotoFile";//技师头像保存地址
	public static final String INFORELAYFILE_SAVE_PATH="/src/main/webapp/InfoRelayFile";//反馈文件保存路径
	
	public static Map<String, Object> saveFile(HttpServletRequest request,
			MultipartFile attachment, String PRODUCT_IMG_SAVE_PATH) {
		String parentPath = request.getSession().getServletContext()
				.getRealPath(java.io.File.separator);// tomcat的webapp前缀
		String subPath = PRODUCT_IMG_SAVE_PATH;// 保存路径
		String originalFilename = attachment.getOriginalFilename();// 图片原名.png
		String fileNameSuffix = originalFilename.substring(originalFilename
				.lastIndexOf("."));// .png
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String timeStamp = format.format(new Date());
		String fileName = beginName + timeStamp + fileNameSuffix.toLowerCase();// 新的图片名称.png
		Map<String, Object> saveResult = MultipartFileUtils.saveFileToDisk(
				new File(parentPath), new File(subPath), fileName, attachment,
				5 * 1024 * 1024L);
		if (!"0".equals(saveResult.get("status").toString())) {
			// 保存失败
			return BaseController.outData("0001", saveResult.get("msg")
					.toString(), null);
		}
		String save_path = (String) saveResult.get("save_path");// 保存的路径名
		saveResult.get("old_name");// 上传时的文件名 QQ图片20161226133411.png}
		return BaseController.outData("0000", save_path, null);
	}

	/**
	 * @param request
	 * @param fileUrl  删除的文件路径
	 * @throws IOException 
	 */
	public static void deleteOld(HttpServletRequest request,
			String fileUrl) throws IOException {
		String parentPath = request.getSession().getServletContext()
				.getRealPath(File.separator);
		int index = parentPath.indexOf(projectName) - 1;
		String url = parentPath.substring(0, index) + fileUrl;
		File file = new File(url);
		if(!file.isDirectory()){
			if (file.exists()) {
				file.delete();
			}
		}else {//是文件夹则删除路径下的所有文件夹
			FileUtils.deleteDirectory(file);
		}
	}

	public static Map<String, Object> deleteOldAndSaveNew(
			HttpServletRequest request, MultipartFile attachment,
			String fileUrl, String productSavePath) {
		String parentPath = request.getSession().getServletContext()
				.getRealPath(File.separator);
		int index = parentPath.indexOf(projectName) - 1;
		String url = parentPath.substring(0, index) + fileUrl;
		File file = new File(url);
		if (file.exists()) {
			file.delete();
		}
		return saveFile(request, attachment, productSavePath);
	}

	/**
	 * 保存文件到磁盘
	 */
	public static Map<String, Object> saveFileToDisk(File parentPath,
			File subFile, String fileName, MultipartFile file, long maxSize) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (file == null || file.isEmpty() || file.getSize() <= 0) {
			result.put("status", "1");
			result.put("msg", "文件为空");
			return result;
		}
		// 检查文件大小，5M
		if (file.getSize() > maxSize) {
			result.put("status", "1");
			result.put("msg", "文件过大");
			return result;
		}
		try {
			int endIndex = 0;
			String parentPathUrl = parentPath.getPath();
			endIndex = parentPathUrl.indexOf(projectName);
			System.out.println(parentPathUrl.substring(0, endIndex));
			String savePath = parentPathUrl.substring(0, endIndex);

			String filePath = savePath + subFile.getPath() + File.separator
					+ fileName;// D:\workspace\sms_boss\src\main\webapp\\sms_check_file\passway\sign_20170302062136381.png
			File saveDir = new File(filePath);
			if (saveDir.isFile() && saveDir.exists()) {
				result.put("status", "1");
				result.put("msg", "文件名重复");
				return result;
			}
			if (!saveDir.getParentFile().exists()) {
				saveDir.getParentFile().mkdirs();
			}
			System.out.println("save path ... : " + saveDir.getPath());
			// 转存文件
			file.transferTo(saveDir);
			System.out.println("save file success : " + saveDir.getPath());
			result.put("status", "0");
			result.put("save_path", subFile.getPath() + File.separator
					+ fileName);// 保存的路径名
								// \sms_check_file\passway\sign_20170302063551308.png
			result.put("old_name", file.getOriginalFilename());// 上传时的文件名
																// QQ图片20161226133411.png
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			result.put("status", "1");
			result.put("msg", "保存失败");
			return result;
		}
	}
}
