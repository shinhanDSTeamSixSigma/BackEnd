package site.greenwave.farm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRequestDto {

    // 페이지 번호
    @Builder.Default
    private int page = 1;
    // 페이지당 항목 수
    @Builder.Default
    private int size = 10;

}
