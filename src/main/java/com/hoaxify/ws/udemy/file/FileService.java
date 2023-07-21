package com.hoaxify.ws.udemy.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hoaxify.ws.udemy.configuration.ApplicationConfiguration;

@Service
public class FileService {
	
//	// Tekil Yöntem
//	@Value("${uploaded-path}")
//	String uploadedPath;
	
	@Autowired
	ApplicationConfiguration applicationConfiguration;
	
	public String writeBase64EncodedStringToFile(String image) throws IOException {
		String fileName = generateRandomName();
//		File target = new File("picture-storage/"+fileName);   									1.Yöntem
//		File target = new File(uploadedPath+"/"+fileName);	  								    2.Yöntem
		File target = new File(applicationConfiguration.getUploadedPath()+"/"+fileName);//		3.Yöntem
		OutputStream outputStream = new FileOutputStream(target);
		byte[] base64Encoded = Base64.getDecoder().decode(image);
		outputStream.write(base64Encoded);
		
		return fileName;
	}
	
	public String generateRandomName() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

}
