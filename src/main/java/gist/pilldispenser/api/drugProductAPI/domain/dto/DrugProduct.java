package gist.pilldispenser.api.drugProductAPI.domain.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String mtralSn; // 자재 일련번호
    private String mtralCode; // 자재 코드
    private String mtralNm; // 자재 명칭
    private String qnt; // 자재 수량
    private String ingdUnitCd; // 자재 단위 코드
    private String itemSeq; // 품목 일련번호
  //  private String mainIngrEng; // 주요 성분(영문)
  //  private String bizrno; // 사업자 번호
  //  private String cpntCtntCont; // 구성 성분 내용
}