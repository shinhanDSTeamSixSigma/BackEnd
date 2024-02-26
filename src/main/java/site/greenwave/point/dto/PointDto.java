package site.greenwave.point.dto;

import jakarta.persistence.Column;
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
public class PointDto {
	private Integer pointValue;
	private Integer changeValue;
	private Integer changeCause;
	
	private Integer memberNo;
	private Integer cropNo;
	private Integer billNo;

}
