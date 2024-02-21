package site.greenwave.board.dto;

import java.sql.Timestamp;

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
public class BoardDTO {
	private int boardNo;
    private int categoryNo;
    private String title;
    private String boardContent;
    private Timestamp createdDate;
    private Integer views;
    private boolean isReplied;
    private boolean isDeleted;
    private int memberNo; 

    private String memberId;
    private int farmNo; 

}
