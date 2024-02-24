package site.greenwave.point.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInfo {
    private String impUid;
    private String merchantUid;
    private String status;
    private int paidAmount;
    private String buyerEmail;
    private String buyerName;
    private String buyerTel;
}
