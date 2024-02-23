package site.greenwave.board.dto;

import lombok.Builder;
import lombok.Data;
import site.greenwave.farm.dto.PageRequestDto;

@Data
public class BoardPageRequestDTO extends PageRequestDto {
    private int farmNo;
    private int categoryNo;
}