package site.greenwave.crop;

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
public class CropDto {
	private String cropNickname;
	private Integer cropState;
	
	private Integer farmNo;
	private Integer memberNo;
	private Integer dictNo;
}
