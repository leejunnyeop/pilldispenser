package gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity;



import lombok.Builder;
import lombok.Getter;



@Getter
@Builder
public class DrugIdentificationRequest {

    private  String itemSeq;          // 품목 기준 코드
    private  String itemName;         // 제품명
    private  String entpName;         // 업체명
    private  String drugShape;        // 약물 형태
    private  String lengLong;         // 약물 길이(긴 쪽)
    private  String lengShort;        // 약물 길이(짧은 쪽)



}
