package com.ibcx.poc.controller;

import com.ibcx.poc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.ResponseBuilder;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
public class FileUploaderController {

    @Autowired
    EmployeeService employeeService;
    @Autowired
    ServletContext context;
    @Autowired
    private Environment environment;

    private static String EMPLOYEE_IMAGES_FOLDER = "/employee";

    @PostMapping("fileUploader/employee-photo")
    public ResponseEntity<Object> singleFileUpload(@RequestParam("file") MultipartFile file,
                                                   RedirectAttributes redirectAttributes) {
        String rootPath;
        String newFileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        try {

            byte[] bytes = file.getBytes();

            String staticPath = environment.getProperty("static.resource.path");
            rootPath = staticPath + EMPLOYEE_IMAGES_FOLDER;
            String absolutePath = context.getRealPath(rootPath);
            File uploadedFile = new File(absolutePath, newFileName);

            Path path = Paths.get(uploadedFile.getAbsolutePath());
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<Object>(ResponseBuilder.makeResponse(ResponseBuilder.STATUS_SUCCESS, "Successfully Deleted!"), HttpStatus.EXPECTATION_FAILED);

        }

        String staticPublicPath = environment.getProperty("static.public.resource.path");

        HashMap<String, Object> map = new HashMap<>();
        map.put("path", staticPublicPath + EMPLOYEE_IMAGES_FOLDER + File.separator + newFileName);
        return new ResponseEntity<Object>(ResponseBuilder.makeResponse(map), HttpStatus.OK);

    }

}