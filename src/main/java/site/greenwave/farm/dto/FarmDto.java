package site.greenwave.farm.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "memberNo")
public class FarmDto {
    private Integer farmNo;
    // 농장 이름
    private String farmName;
    private String farmAddress;
    private String farmContent;
    private String farmDescription;
    private String farmPhone;
    private String farmSize;
    private String farmCareer;
    private Integer farmOrderNum;
    private String farmConnect;
    private String farmCategory;
    private Double farmRating;
    private Integer reviewCnt;

    private Integer memberNo;
    // 사진 관련 필드 추가
    private List<FileDto> photos;

}
