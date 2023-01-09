package com.example.src.main.bloggingapp.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
	String uploadFile(String path, MultipartFile file) throws IOException;

	InputStream getUploadedFile(String p, String fileName) throws FileNotFoundException;
}
