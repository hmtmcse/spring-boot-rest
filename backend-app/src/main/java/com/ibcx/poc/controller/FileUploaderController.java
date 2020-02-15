package com.ibcx.poc.controller;

import com.ibcx.poc.model.Employee;
import com.ibcx.poc.service.DepartmentService;
import com.ibcx.poc.service.EmployeeService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.castor.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import util.ResponseBuilder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;
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
            // Get the file and save it somewhere
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