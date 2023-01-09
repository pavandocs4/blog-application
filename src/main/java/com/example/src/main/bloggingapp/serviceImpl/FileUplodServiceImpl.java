package com.example.src.main.bloggingapp.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.src.main.bloggingapp.service.FileUploadService;

@Service
public class FileUplodServiceImpl implements FileUploadService{

	@Override
	public String uploadFile(String path, MultipartFile file) throws IOException {
		String fileName= file.getOriginalFilename();
		String randomFileName= UUID.randomUUID().toString();
		randomFileName= randomFileName.concat(fileName.substring(fileName.lastIndexOf(".")));
		
		String filePath= path+File.separator+randomFileName;
		
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return randomFileName;
	}

	@Override
	public InputStream getUploadedFile(String p, String fileName) throws FileNotFoundException {
		
		String path= p+File.separator+fileName;
		InputStream file= new FileInputStream(path);
		return file;
	}

}
