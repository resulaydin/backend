package com.hoaxify.ws.udemy.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hoaxify.ws.udemy.configuration.ApplicationConfiguration;

@Service
public class FileService {

//	// Tekil Yöntem
//	@Value("${uploaded-path}")
//	String uploadedPath;

	ApplicationConfiguration applicationConfiguration;
	Tika tika;

	public FileService(ApplicationConfiguration applicationConfiguration) {
		this.applicationConfiguration = applicationConfiguration;
		tika = new Tika();
	}

	final static Logger log = LoggerFactory.getLogger(FileService.class);

	public String writeBase64EncodedStringToFile(String image) throws IOException {

		String fileName = generateRandomName();
//		File target = new File("picture-storage/"+fileName);   									1.Yöntem
//		File target = new File(uploadedPath+"/"+fileName);	  								    2.Yöntem
		File target = new File(applicationConfiguration.getUploadedPath() + "/" + fileName); // 3.Yöntem
		try (OutputStream outputStream = new FileOutputStream(target)) {
			byte[] base64Encoded = Base64.getDecoder().decode(image);
			outputStream.write(base64Encoded);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;

	}

	public String detectType(String value) {
		byte[] base64Encoded = Base64.getDecoder().decode(value);
		String fileType = tika.detect(base64Encoded);
		log.info(fileType);
		return fileType;
	}

	public String generateRandomName() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	public void deleteFile(String oldImageName) {
		if (oldImageName == null) {
			return;
		}
		try {
//			String pathURI = applicationConfiguration.getUploadedPath() +"/"+oldImageName;
//			Paths.get(applicationConfiguration.getUploadedPath(),oldImageName)
			Files.deleteIfExists(Paths.get(applicationConfiguration.getUploadedPath(), oldImageName));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
