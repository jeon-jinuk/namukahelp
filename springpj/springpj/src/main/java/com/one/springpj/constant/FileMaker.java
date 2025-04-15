package com.one.springpj.constant;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

public class FileMaker {
	public static String save(MultipartFile file, HttpSession session) {
		String uploadFolder = session.getServletContext().getRealPath("/upload");
		String today = new SimpleDateFormat("yyMMdd").format(new Date());
		String saveFolder = uploadFolder + File.separator + today;

		File folder = new File(saveFolder);

		if (!folder.exists()) {
			folder.mkdirs();
		}

		String originFile = file.getOriginalFilename();
		UUID uuid = UUID.randomUUID();
		String uploadFileName = uuid.toString() + "_" + originFile;


		try {
			File saveFile = new File(saveFolder, uploadFileName);
			file.transferTo(saveFile);
			
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return File.separator+"upload"+File.separator+today+File.separator+uploadFileName;
	}
}
