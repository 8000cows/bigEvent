package example.horse.controller;

import example.horse.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by LiuSheng at 2024/1/31 14:02
 */

@RestController
public class FileUploadController {

    @PostMapping("/upload")
    public Result<String> uploadImg(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        Result<String> result = new Result<>();
        String filename = multipartFile.getOriginalFilename();

        if (filename == null) {
            result.setMessage("网络异常, 请稍后再试...");
            return result;
        }

        String fileSuffix = filename.substring(filename.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + fileSuffix;
        multipartFile.transferTo(new File("E:\\download\\file\\" + newFileName));
        result.setMessage("url路径...");
        return result;
    }
}
