package gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity;

import gist.pilldispenser.drug.medication.domain.entity.FullMedicationInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DrugIdentification {

    /***
     *
     *
     <ITEM_SEQ>200808876</ITEM_SEQ>
     <ITEM_NAME>가스디알정50밀리그램(디메크로틴산마그네슘)</ITEM_NAME>
     <ENTP_NAME>일동제약(주)</ENTP_NAME>
     <CHART>녹색의 원형 필름코팅정</CHART>
     <DRUG_SHAPE>원형</DRUG_SHAPE>
     <COLOR_CLASS1>연두</COLOR_CLASS1>
     <COLOR_CLASS2/>
     <LENG_LONG>7.6</LENG_LONG>
     <LENG_SHORT>7.6</LENG_SHORT>
     <THICK>3.6</THICK>
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

     // 품목기준코드
    private String itemSeq;

    // 품목명
    private String itemName;

     // 업체명
    private String entpName;

    // 형태
    private String drugShape;

    // 길이(대)
    private String lengLong;

    // 길이(소)
    private String lengShort;

    @OneToOne(mappedBy = "drugIdentification", fetch = FetchType.LAZY)
    private FullMedicationInfo fullMedicationInfo;


}
