package com.mm.controller;

import com.mm.entity.FileInfo;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件上传
 *
 * @author lwl
 */
@RestController
@RequestMapping("/file")
public class FileController {

    String folder = "D:\\chroot";

    @PostMapping
    public FileInfo upload(MultipartFile file) throws IOException {
        String oldFileName = file.getOriginalFilename();
        File localFile = new File(folder, System.currentTimeMillis() + oldFileName.substring(oldFileName.lastIndexOf(".")));
        file.transferTo(localFile);
        return FileInfo.builder().path(localFile.getAbsolutePath()).build();
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try(InputStream inputStream = new FileInputStream(new File(folder, id+".txt"));
            OutputStream outputStream = response.getOutputStream()) {
            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition", "attachment;filename=text.txt");
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        }
    }
}
