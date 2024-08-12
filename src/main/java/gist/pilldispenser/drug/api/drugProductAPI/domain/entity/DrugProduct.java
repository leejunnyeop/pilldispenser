package gist.pilldispenser.drug.api.drugProductAPI.domain.entity;

import gist.pilldispenser.drug.medication.domain.entity.FullMedicationInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DrugProduct {

    /***
     *
     *
     * <ENTRPS>(주)유한양행</ENTRPS>
     * <PRDUCT>삐콤정</PRDUCT>
     * <MTRAL_SN>2</MTRAL_SN>
     * <MTRAL_CODE>M040133</MTRAL_CODE>
     * <MTRAL_NM>리보플라빈</MTRAL_NM>
     * <QNT>6.0</QNT>
     * <INGD_UNIT_CD>밀리그램</INGD_UNIT_CD>
     * <ITEM_SEQ>196200034</ITEM_SEQ>
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // private String entrpsPrmisnNo; // 업체 허가 번호
    private String entrps; // 업체명
    private String prduct; // 제품명
    private String mtralNm; // 자재 명칭
    private String itemSeq; // 품목 일련번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medication_detail_id")
    private FullMedicationInfo fullMedicationInfo;
}