package site.greenwave.farm.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import site.greenwave.farm.dto.FileDto;
import site.greenwave.farm.util.CustomFileUtil;
import site.greenwave.file.FileEntity;
import site.greenwave.file.FileRepository;
import site.greenwave.file.FileUtil;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
    @RequestMapping("/api/farm")
public class FarmFileController {

    private final CustomFileUtil customFileUtil;
    private final FileRepository fileRepository;
    private final FileUtil fileUtil;

    @PostMapping("/registFile")
    public ResponseEntity<Map<String, String>> registerFile(@RequestParam("files") MultipartFile[] files, @RequestParam("manageDiv") String manageDiv, @RequestParam("fileManageNo") Integer fileManageNo) {
        log.info("받은 파일이름: " + files);
        List<String> uploadFileNames = customFileUtil.saveFiles(Arrays.asList(files));
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

    // 농장사진 보여주기
    @GetMapping("/FARM/{fileName}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String fileName) {

        return customFileUtil.getFile(fileName);
    }


    // 사진 여러장 보내기
    @GetMapping("/view/{manage_div}/{file_manage_no}")
    public ResponseEntity<List<String>> viewFilesGet(@PathVariable String manage_div, @PathVariable int file_manage_no) {
        List<String> filePaths = fileUtil.getFilesFrom(manage_div, file_manage_no);
        if (filePaths == null || filePaths.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(filePaths);
    }


    // 사진 1개 보내기
    @GetMapping("/view/profile/{manage_div}/{file_manage_no}")
    public ResponseEntity<String> viewFileGet(@PathVariable String manage_div, @PathVariable int file_manage_no) {
        String filePath = fileUtil.getFileFrom(manage_div, file_manage_no);

        if (filePath == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(filePath);
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
