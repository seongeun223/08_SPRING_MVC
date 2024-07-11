package com.ohgiraffers.fileupload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
@Slf4j // 로그
public class FileUploadController {

    @Autowired
    private ResourceLoader resourceLoader;

    @PostMapping("/single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile,
                                   @RequestParam String singleFileDescription,
                                   Model model) throws IOException {

        log.info("[FileUploadController] singleFileUpload : =====> {}", singleFile);
        log.info("singleFileDescription : =======> {}", singleFileDescription);

        // 파일 저장 공간 지정

        // 인텔리제이 root 폴더 지정
        Resource resource = resourceLoader.getResource("classpath:static/img/single");
        // classpath: : 클래스의 resources 까지의 경로로 가져다 준다.

        String filePath = null;

        // 폴더가 있을 때, 없을 때에 따라 저장 경로를 지정해줘야 한다.
        if(!resource.exists()) {
            String root = "src/main/resources/static/img/single";
            File file = new File(root); // 메소드 사용하기 위해 파일 선언
            file.mkdir(); // 폴더 만들어주는 메소드

            filePath = file.getAbsolutePath(); // 절대경로
            log.info("폴더 생성 성공, 경로 : {}", filePath);
        } else {
            filePath = resourceLoader
                    .getResource("classpath:static/img/single")
                    .getFile().getAbsolutePath();
            log.info("폴더 존재함, 경로 : {}", filePath);
        }

        // 기존 파일명
        String originFileName = singleFile.getOriginalFilename();
        log.info("originFileName : {}", originFileName); // 다운로드.jfif

        // 확장자
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        log.info("ext : {}", ext); // ext : .jfif

        // 저장할 파일명
        String savedName = UUID.randomUUID().toString().replace("-", "");
        log.info("savedName : {}", savedName); // savedName : e9bbbb5d-9420-486a-8177-e3d2db122f22

        try {
            // 파일 저장
            // 업로드된 파일을 서버의 특정 경로에 savedName으로 저장
            singleFile.transferTo(new File(filePath + "/" + savedName));

            model.addAttribute("message", "파일 업로드 성공!");
            model.addAttribute("img", "static/img/single/" + savedName);

        } catch (Exception e) {
            e.printStackTrace();

            new File(filePath + "/" + savedName).delete();
            model.addAttribute("message", "파일 업로드 실패!");
        }

        return "result";

    }
}
