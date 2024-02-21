package site.greenwave.farm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDto {
    private Integer fileNo;
    private String fileName;
    private String fileSrc;
    private String fileExtension;
    private String manageDiv;
    private Integer fileManageNo;
    @CreationTimestamp
    private Timestamp uploadDate;

    // 새로운 농장 사진과 수정 작업 시 농부가 새로운 파일 업로드 할 때 사용
    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();
    // 업로드 완료된 파일의 이름만 문자열로 보관한 리스트
    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();
}
