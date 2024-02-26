package site.greenwave.farm.util;


import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
//import lombok.Value;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Component;
import site.greenwave.file.FileEntity;
import site.greenwave.file.FileRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomFileUtil {
    //  파일 데이터의 입출력 담당
    // upload라는 이름의 폴더 체크, 자동 생성하도록
    // @PostConstruct 이용
    // 파일 업로드 작업은 saveFiles()로 작성

    @Value("${filesave.location}")
    private String basePath;
    private String farmUploadPath;
    private String cropUploadPath;
    private String reviewUploadPath;
    private String diaryUploadPath;

    @Autowired
    private FileRepository fileRepository;

    @PostConstruct
    public void init(){
        farmUploadPath = Paths.get(basePath, "FARM").toString();
        cropUploadPath = Paths.get(basePath, "DICT").toString();

        
        //리뷰용 경로
        reviewUploadPath = Paths.get(basePath, "POST").toString();
        diaryUploadPath = Paths.get(basePath, "DIARY").toString();

        log.info(reviewUploadPath);
        log.info(farmUploadPath);
        log.info(cropUploadPath);
        log.info(diaryUploadPath);
      
        File farmTempFolder = new File(farmUploadPath);
        File cropTempFolder = new File(cropUploadPath);

        File reviewTempFolder = new File(reviewUploadPath);
        File diaryTempFolder = new File(diaryUploadPath);


        if(!farmTempFolder.exists()){
            farmTempFolder.mkdir();
        }
        if(!cropTempFolder.exists()){
            cropTempFolder.mkdir();
        }
        if(!diaryTempFolder.exists()){
        	diaryTempFolder.mkdir();
        }

        if(!reviewTempFolder.exists()) {
        	reviewTempFolder.mkdir();
        }


    }
    public List<String> saveFiles(List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> uploadNames = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            String savedName = multipartFile.getOriginalFilename();
            log.info("파일이름: " + savedName);
            // 확장자를 제외한 파일 이름만 추출
            String fileNameWithoutExtension = savedName.substring(0, savedName.lastIndexOf('.'));
            log.info("파일이름(확장자 제외): " + fileNameWithoutExtension);

            Path savePath = Paths.get(farmUploadPath, savedName);
            log.info("파일 경로? : " +savePath);
            try {
                Files.copy(multipartFile.getInputStream(), savePath);
                String contentType = multipartFile.getContentType();
                if (contentType != null && contentType.startsWith("image")) {
                    Path thumbnailPath = Paths.get(farmUploadPath, "s_" + savedName);
                    Thumbnails.of(savePath.toFile())
                            .size(200,200)
                            .toFile(thumbnailPath.toFile());
                }
                uploadNames.add(fileNameWithoutExtension);
                log.info("업로드 이름: " + uploadNames);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }return uploadNames;
    }
  

    //리뷰사진저장용
    public List<String> saveReviewFiles(List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> uploadNames = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            String savedName = multipartFile.getOriginalFilename();
            log.info("파일이름: " + savedName);
            // 확장자를 제외한 파일 이름만 추출
            String fileNameWithoutExtension = savedName.substring(0, savedName.lastIndexOf('.'));
            log.info("파일이름(확장자 제외): " + fileNameWithoutExtension);

            Path savePath = Paths.get(reviewUploadPath, savedName);
            log.info("파일 경로? : " +savePath);
            try {
                Files.copy(multipartFile.getInputStream(), savePath);
                String contentType = multipartFile.getContentType();
                if (contentType != null && contentType.startsWith("image")) {
                    Path thumbnailPath = Paths.get(reviewUploadPath, "s_" + savedName);
                    Thumbnails.of(savePath.toFile())
                            .size(200,200)
                            .toFile(thumbnailPath.toFile());
                }
                uploadNames.add(fileNameWithoutExtension);
                log.info("업로드 이름: " + uploadNames);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }return uploadNames;
    }

    

    // 농장사진 보여주기
    public ResponseEntity<Resource> getFile(String fileName) {
        String thumbnailFimeName = "s_" + fileName;
        Resource resource = new FileSystemResource(farmUploadPath + File.separator + thumbnailFimeName);
        log.info("resource: " + resource);
        if(! resource.isReadable() ){
            resource = new FileSystemResource(farmUploadPath + File.separator + "default.png");
        }
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);

    }

    // 작물사진 보여주기
    public ResponseEntity<Resource> getFileCrop(String fileName) {
        String thumbnailFimeName = fileName;
        Resource resource = new FileSystemResource(cropUploadPath + File.separator + thumbnailFimeName);
        log.info("resource: " + resource);
        if(! resource.isReadable() ){
            resource = new FileSystemResource(cropUploadPath + File.separator + "default.png");
        }
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    //리뷰사진 보여주기
    public ResponseEntity<Resource> getFileReview(String fileName) {
        String thumbnailFimeName = fileName;
        Resource resource = new FileSystemResource(reviewUploadPath + File.separator + thumbnailFimeName);
        log.info("resource: " + resource);
        if(! resource.isReadable() ){
            resource = new FileSystemResource(reviewUploadPath + File.separator + "default.png");
        }
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);

    }


    public void deleteFiles(List<String> fileNames) {
        if (fileNames == null || fileNames.size() == 0) {
            return;
        }
        fileNames.forEach(filename -> {
            String thumbnailFimeName = "s_" + filename;
            Path thumbnailPath = Paths.get(farmUploadPath, thumbnailFimeName);
            Path filePath = Paths.get(farmUploadPath, filename);

            try {
                Files.deleteIfExists(filePath);
                Files.deleteIfExists(thumbnailPath);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        });
    }
    
    //일기 저장
    public List<String> saveFilesDiary(List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> uploadNames = new ArrayList<>();
        for (MultipartFile multipartFile : files) {
            String savedName = multipartFile.getOriginalFilename();
            log.info("파일이름: " + savedName);
            // 확장자를 제외한 파일 이름만 추출
            String fileNameWithoutExtension = savedName.substring(0, savedName.lastIndexOf('.'));
            log.info("파일이름(확장자 제외): " + fileNameWithoutExtension);

            Path savePath = Paths.get(diaryUploadPath, savedName);
            log.info("파일 경로? : " +savePath);
            try {
                Files.copy(multipartFile.getInputStream(), savePath);
                String contentType = multipartFile.getContentType();
                if (contentType != null && contentType.startsWith("image")) {
                    Path thumbnailPath = Paths.get(diaryUploadPath, "s_" + savedName);
                    Thumbnails.of(savePath.toFile())
                            .size(200,200)
                            .toFile(thumbnailPath.toFile());
                }
                uploadNames.add(fileNameWithoutExtension);
                log.info("업로드 이름: " + uploadNames);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }return uploadNames;
    }
    
    // 일기사진 보여주기
    public ResponseEntity<Resource> getFileDiary(String fileName) {
        String thumbnailFimeName = fileName;
        Resource resource = new FileSystemResource(diaryUploadPath + File.separator + thumbnailFimeName);
        log.info("resource: " + resource);
        if(! resource.isReadable() ){
            resource = new FileSystemResource(diaryUploadPath + File.separator + "default.png");
        }
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);

    }
}
