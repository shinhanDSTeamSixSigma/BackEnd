package site.greenwave.board.dto;

import java.sql.Timestamp;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
	private int reviewNo;
    private String reviewContent;
    private int rating;
    private Timestamp createdDate;
    private boolean isDeleted;
    private int memberNo; 
    private String nickname;
    private String memberId;
    private int farmNo; 
    private String farmName;
    private String imagePath;
    private List<String> imagePaths;
}
