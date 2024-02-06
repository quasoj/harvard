package edu.harvard.utils.controller;

import edu.harvard.utils.AliOSSUtils;
import edu.harvard.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private final AliOSSUtils aliOSSUtils;

    @Autowired
    public UploadController(AliOSSUtils aliOSSUtils) {
        this.aliOSSUtils = aliOSSUtils;
    }

    @PostMapping
    public Result uploadImage(MultipartFile image, String pathname) throws Exception {
        String url = aliOSSUtils.upload(image, pathname);
        return Result.success(url);
    }

}
