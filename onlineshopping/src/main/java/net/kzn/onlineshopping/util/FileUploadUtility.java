package net.kzn.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {
	// absolute path is the path in our development environment
	// real path is where the servlet container deployed the application
	private static final String ABS_PATH = "D:\\projects\\spring\\online-shopping\\onlineshopping\\src\\main\\webapp\\assets\\images\\";
	private static String REAL_PATH = "";
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {
		// get the real path(ie, real path is where the servlet container deployed the application
		REAL_PATH = request.getSession().getServletContext().getRealPath("/assets/images/");
		logger.info(REAL_PATH);
		
		//to make sure all the direcotry exists
		//if not exist, please create the directories
		if(!new File(ABS_PATH).exists()) {
			//create the directories
			new File(ABS_PATH).mkdirs();
		}
		if(!new File(REAL_PATH).exists()) {
			//create the directories
			new File(REAL_PATH).mkdirs();
		}
		
		try {
			//for server upload
			file.transferTo(new File(REAL_PATH+ code + ".jpg"));
			//for project directory upload
			file.transferTo(new File(ABS_PATH+ code + ".jpg"));
		}
		catch(IOException ex) {
			
		}
		
		
	}

}
