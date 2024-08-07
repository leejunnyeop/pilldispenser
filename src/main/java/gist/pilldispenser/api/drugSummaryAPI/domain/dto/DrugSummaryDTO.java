package gist.pilldispenser.api.drugSummaryAPI.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DrugSummaryDTO {

    private String itemName; // 알약명
    private String itemSeq; // 알약 고유번호
    private String itemImage; // 이미지 URL

}