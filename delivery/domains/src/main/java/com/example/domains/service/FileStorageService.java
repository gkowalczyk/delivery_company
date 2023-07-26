package com.example.domains.service;

import com.example.domains.exception.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

@Service
public class  *FileStorageService {

    @Value("${file.upload-dir}")
    private final Path fileStorageLocation;

    public FileStorageService(Path fileStorageLocation) {
        this.fileStorageLocation = fileStorageLocation;
    }

    public Resource loadFileAsResource(String fileName) throws IOException {

        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException malformedURLException) {
            throw new IOException();
        }
    }
}

