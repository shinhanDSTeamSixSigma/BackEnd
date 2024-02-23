package site.greenwave.point.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VerifyIamportRequest {
    private BillDto billDto;
    private PointDto pointDto;

}
