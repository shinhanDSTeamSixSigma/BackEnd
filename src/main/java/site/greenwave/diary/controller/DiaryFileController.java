package site.greenwave.diary.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import site.greenwave.farm.util.CustomFileUtil;
import site.greenwave.file.FileEntity;
import site.greenwave.file.FileRepository;
import site.greenwave.file.FileUtil;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/diary")
public class DiaryFileController {
	
    private final CustomFileUtil customFileUtil;
    private final FileRepository fileRepository;
    private final FileUtil fileUtil;
    
    @PostMapping("/registFile")
    public ResponseEntity<Map<String, String>> registerFile(@RequestParam("files") MultipartFile[] files, @RequestParam("manageDiv") String manageDiv, @RequestParam("fileManageNo") Integer fileManageNo) {
        log.info("받은 파일이름: " + files);
        List<String> uploadFileNames = customFileUtil.saveFilesDiary(Arrays.asList(files));
        log.info("업로드 파일이름: " + uploadFileNames);
        log.info(uploadFileNames);

        // db에 파일 정보 저장
        for (String fileName :
                uploadFileNames) {
            FileEntity file = FileEntity.builder()
                    .fileName(fileName)
                    .manageDiv(manageDiv)
                    .fileExtension("png")
                    .fileSrc(fileName)
                    .fileManageNo(fileManageNo)
                    .uploadDate(Timestamp.from(Instant.now()))
                            .build();

            fileRepository.save(file);
        }
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("result: ", "success"));
    }
    
    // 일기사진 보여주기
    @GetMapping("/view/DIARY/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName) {

        return customFileUtil.getFileDiary(fileName);
    }
    
    // 삭제
    @Transactional
    @DeleteMapping("/deleteImage/{manage_div}/{file_manage_no}/{fileName}")
    public ResponseEntity<String> deleteFile(@PathVariable String manage_div, @PathVariable int file_manage_no, @PathVariable String fileName){

        fileRepository.deleteByManageDivAndFileManageNoAndFileName(manage_div, file_manage_no, fileName);
        log.info(fileName);
        log.info(manage_div);

        return ResponseEntity.status(HttpStatus.OK).body( "삭제완료");
    }

}
