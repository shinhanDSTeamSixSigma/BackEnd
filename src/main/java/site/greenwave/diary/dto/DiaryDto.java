package site.greenwave.diary.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "memberNo")
public class DiaryDto {
	private Integer diaryNo;
	private Timestamp diaryDate;
	private String content;
	
	private Integer memberNo;
	private Integer cropNo;
}
