package site.greenwave.point.dto;

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
public class BillDto {
	private String merchantUid;
	private Integer finalValue;
	private Integer originValue;
	private Integer discountValue;
	private Integer billDiv;
	
	private Integer memberNo;
}
