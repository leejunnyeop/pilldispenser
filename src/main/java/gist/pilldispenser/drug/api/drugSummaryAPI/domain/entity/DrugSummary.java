package gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity;

import gist.pilldispenser.drug.medication.domain.entity.FullMedicationInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrugSummary {

//    <entpName>(주)유한양행</entpName>
//<itemName>삐콤정</itemName>
//<itemSeq>196200034</itemSeq>
//    <efcyQesitm>이 약은 육체피로, 임신ㆍ수유기, 병중ㆍ병후의 체력 저하 시 비타민 B1, B2, B6, C의 보급에 사용합니다.</efcyQesitm>
//<useMethodQesitm>만 8세 이상 및 성인은 1회 1~3정씩, 1일 1회 복용합니다.</useMethodQesitm>
//<atpnWarnQesitm/>
//    <atpnQesitm>이 약에 과민증 환자, 만 3개월 이하의 젖먹이, 갈락토오스 불내성, Lapp 유당분해효소 결핍증 또는 포도당-갈락토오스 흡수장애 등의 유전적인 문제가 있는 환자는 이 약을 복용하지 마십시오.이 약을 복용하기 전에 고옥살산뇨증(요중에 과량의 수산염이 배설되는 상태), 임부 또는 임신하고 있을 가능성이 있는 여성 및 수유부, 미숙아, 유아, 통풍, 신장결석, 폴산이 부족한 환자는 의사 또는 약사와 상의하십시오.정해진 용법과 용량을 잘 지키십시오.어린이에게 투여할 경우 보호자의 지도 감독하에 투여하십시오.요를 황색으로 변하게 하여 임상검사치에 영향을 줄 수 있습니다.</atpnQesitm>
//    <intrcQesitm>레보도파와 함께 복용하지 마십시오.</intrcQesitm>
//<seQesitm>구역, 구토, 설사, 묽은 변 등이 나타나는 경우 복용을 즉각 중지하고 의사 또는 약사와 상의하십시오.</seQesitm>
//


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entpName; // 회사명
    private String itemName; // 알약명
    private String itemSeq; // 알약 고유번호

    @Column(length = 3000)
    private String useMethodQesitm; // 용법
    @Column(length = 2000)
    private String intrcQesitm; // 혼용 금지 성분?

    private String itemImage; // 이미지 이거 사용

    @OneToOne(mappedBy = "drugSummary", fetch = FetchType.LAZY)
    private FullMedicationInfo fullMedicationInfo;

}