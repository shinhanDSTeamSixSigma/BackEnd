package site.greenwave.farm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import site.greenwave.farm.entity.FarmEntity;
import site.greenwave.file.FileEntity;
import site.greenwave.member.MemberEntity;

import java.util.Optional;

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

    // 조인컬럼이기 때문에 tb_member_no로 나타내야함
    @JsonProperty("tb_member_no")
    private Integer memberNo;

//    private FileEntity fileEntity;



//    public static FarmDto toFarmDto(FarmEntity farmEntity, Optional<MemberEntity> memberEntity){
//        return new FarmDto(
//                farmEntity.getFarmNo(),
//                farmEntity.getFarmName(),
//                farmEntity.getFarmAddress(),
//                farmEntity.getFarmContent(),
//                farmEntity.getFarmDescription(),
//                farmEntity.getFarmPhone(),
//                farmEntity.getFarmSize(),
//                farmEntity.getFarmCareer(),
//                farmEntity.getFarmOrderNum(),
//                farmEntity.getFarmConnect(),
//                farmEntity.getFarmCategory(),
//                farmEntity.getFarmRating(),
//                farmEntity.getReviewCnt(),
//                memberEntity.get().getMemberNo()
////                fileEntity
//        );
//    }
}
