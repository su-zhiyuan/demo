package com.qppi.crud.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.qppi.crud.service.DictionariesService;
import com.qppi.crud.utils.Msg;
import com.qppi.crud.utils.MySelfUtil;

@Controller
@RequestMapping("filedemo")
public class FileController extends BaseController{
	
	@Autowired
	private DictionariesService dictionariesService;
	
	// 显示文件
	@RequestMapping("xainshi")
	@ResponseBody
	public Msg XianFlie(String fileName, HttpServletRequest request,String filePath){
		
		String URL = dictionariesService.selectURL();
		String Path = dictionariesService.selectPATH();
		
		String username = getCurrentSysUser(request).getUsername();
		if(username == null){
			return Msg.success().add("result", "没有登录");
		}
		String basePath = Path + username + "/";
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		
		String path = "";
		if (filePath == null ) {
			if(fileName == null){
				path = basePath;
				File f = new File(path);
				if(!f.isDirectory()){
			    	f.mkdirs();
				}
			}else{
				path = basePath + fileName;
			}
		} else {
			if(fileName == null){
				path = basePath + File.separator + filePath;
			}else{
				path = basePath + File.separator + filePath + File.separator + fileName;
			}
		}

		File file = new File(path);
		double dirSize = getDirSize(file);
		
		if (file.isDirectory()) {
			File templist[] = file.listFiles();
			if (templist.length > 0) {
				for (int i = 0; i < templist.length; i++) {
					Map<String, String> map = new HashMap<String, String>();
					String filepath = "";
					String FileName = templist[i].getName();
					int lastIndexOf = FileName.lastIndexOf(".");
					System.out.println(lastIndexOf);
					if(lastIndexOf == -1){
						FileName = FileName +".dir";
					}
					if(filePath == null || "".equals(filePath)){
						filepath = URL+ username + "/" + FileName;
					}else{
						filepath = URL + username + filePath + "/" + FileName;
					}
					map.put("filename", FileName);
					map.put("filepath", filepath);
					list.add(map);
				}
			}
			
			return Msg.success().add("result", list).add("size", dirSize);
		} else {
			return Msg.success().add("result", "");
		}
	}

	//上传文件
	@RequestMapping(value = "/upload")
	@ResponseBody
	public Msg upload(HttpServletRequest request, HttpServletResponse response,String filePath) {
		String Path = dictionariesService.selectPATH();
		
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		
		String username = getCurrentSysUser(request).getUsername();
		if(username == null){
			return Msg.success().add("result", "没有登录");
		}
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
			String basePath = Path + username + "/";
			MultipartFile file = mRequest.getFile("filename");
			try {
				if (file.isEmpty()) {
					return Msg.fail().add("result", "文件为空");
				}

				// 获取文件名
				String fileName = file.getOriginalFilename();
				// 获取文件的后缀名
//				String suffixName = fileName.substring(fileName.lastIndexOf("."));
				String path = "";
				if(filePath == null){
					path = basePath+ File.separator + fileName;
				}else{
					path = basePath+ File.separator + filePath + File.separator + fileName;
				}
				

				File dest = new File(path);
				// 检测是否存在目录
				if (!dest.getParentFile().exists()) {
					dest.getParentFile().mkdirs();// 新建文件夹
				}
				file.transferTo(dest);// 文件写入
				return Msg.success().add("result", "上传成功");
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return Msg.fail().add("result", "上传失败");
		} else {
			return Msg.fail().add("result", "没有选择文件");
		}

	}

	//删除文件
	@RequestMapping("/delete")
	@ResponseBody
	public Msg deleteFilesTest(HttpServletRequest request, HttpServletResponse res, String fileName, String filePath){
		String username = getCurrentSysUser(request).getUsername();
		String Path = dictionariesService.selectPATH();
		
		if(username == null){
			return Msg.success().add("result", "没有登录");
		}
		String basePath = Path + username + "/";
		String path = "";
		if(filePath == null){
			path = basePath + File.separator + fileName;
		}else{
			path = basePath + File.separator + filePath + File.separator + fileName;
		}
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("删除文件失败:" + path + "不存在！");
			return Msg.fail().add("result", "删除失败");
		} else {
			if (file.isFile()) {
				boolean deleteFile = deleteFile(path);
				if (deleteFile == true) {
					return Msg.success().add("result", "删除成功");
				} else {
					return Msg.fail().add("result", "删除失败");
				}
			} else {
				boolean deleteDirectory = deleteDirectory(path);
				if (deleteDirectory == true) {
					return Msg.success().add("result", "删除成功");
				} else {
					return Msg.fail().add("result", "删除失败");
				}
			}
		}

	}
	
	//图片转换
	@RequestMapping("/changephoto")
	@ResponseBody
	public Msg changephoto(HttpServletRequest request,String imgStr,String id,String styte,String orderid){
		imgStr = imgStr.replaceAll(" ", "+");
		String URL = dictionariesService.selectURL();
		String basePath = dictionariesService.selectPATH();
		String url = basePath + "/" + "print/"+ orderid +"/"+ id + ".jpg" ;
		if (imgStr.indexOf("data:image/") != -1) {
	        try {
	        	imgStr = imgStr.substring(imgStr.indexOf(",")+1);
				if(!MySelfUtil.GenerateImage(imgStr,url)){
					return Msg.fail().add("result", "图片上传失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
				return Msg.fail().add("result", "图片上传失败");
			}
		}
		String lpath = URL + "/" + "print/"+ orderid +"/"+ id + ".jpg" ;
		return Msg.success().add("result", lpath);
	}
	
	// 删除单个文件
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}
	}

	// 删除目录及目录下的文件
	public static boolean deleteDirectory(String dir) {
		// 如果dir不以文件分隔符结尾，自动添加文件分隔符
		if (!dir.endsWith(File.separator))
			dir = dir + File.separator;
		File dirFile = new File(dir);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
			System.out.println("删除目录失败：" + dir + "不存在！");
			return false;
		}
		boolean flag = true;
		// 删除文件夹中的所有文件包括子目录
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			// 删除子文件
			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
			// 删除子目录
			else if (files[i].isDirectory()) {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag)
					break;
			}
		}
		if (!flag) {
			System.out.println("删除目录失败！");
			return false;
		}
		// 删除当前目录
		if (dirFile.delete()) {
			System.out.println("删除目录" + dir + "成功！");
			return true;
		} else {
			return false;
		}
	}
	
	//创建文件
	@RequestMapping("/createDir")
	@ResponseBody
	public Msg createDirTest(HttpServletRequest request, HttpServletResponse res, String filePath){
		String username = getCurrentSysUser(request).getUsername();
		String Path = dictionariesService.selectPATH();
		if(username == null){
			return Msg.success().add("result", "没有登录");
		}
		String basePath = Path + username + "/";
    	String path = basePath + File.separator + filePath;
    	File file = new File(path);
    	file.mkdirs();
		return Msg.success().add("result", "创建成功");
	}
	
	//获取指定文件夹大小
	public static double getDirSize(File file) {     
        //判断文件是否存在     
        if (file.exists()) {     
            //如果是目录则递归计算其内容的总大小    
            if (file.isDirectory()) {     
                File[] children = file.listFiles();     
                double size = 0;     
                for (File f : children)     
                    size += getDirSize(f);     
                return size;     
            } else {//如果是文件则直接返回其大小,以“兆”为单位   
                double size = (double) file.length() / 1024 / 1024;        
                return size;     
            }     
        } else {     
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");     
            return 0.0;     
        }     
    }

}
