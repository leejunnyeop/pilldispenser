package gist.pilldispenser.api.drugIdentificationAPI.domain.dto;

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

    // 업체 기준 코드
 //   private String entpSeq;

     // 업체명
    private String entpName;

    // 성상
    private String chart;

    // 이미지
  //  private String itemImage;

    // 인쇄 앞
   // private String printFront;

    // 형태
    private String drugShape;

    // 색상 1
    private String colorClass1;

    // 색상 2
    private String colorClass2;

    // 분할선 앞
  //  private String lineFront;

    // 분할선 뒤
    //private String lineBack;

    // 길이(대)
    private String lengLong;

    // 길이(소)
    private String lengShort;

    // 두께
    private String thick;

    // 이미지 등록 일자
   // private String imgRegistTs;

    // 분류 번호
    //private String classNo;

    // 분류명

   // private String className;

    // 전문/일반 구분
    //private String etcOtcName;

    // 허가일자
   // private String itemPermitDate;

    // 제형 코드명
  //  private String formCodeName;

    // 마크 코드 앞 분석
   // private String markCodeFrontAnal;

    // 마크 코드 뒤 분석
    //private String markCodeBackAnal;

    // 마크 코드 앞 이미지
    //rivate String markCodeFrontImg;

    // 마크 코드 뒤 이미지
   // private String markCodeBackImg;

    // 품목 영문명
  //  private String itemEngName;

    // 변경 일자// private String changeDate;

    // 마크 코드 앞

    // 마크 코드 뒤
//    private String markCodeBack;

    // EDI 코드
// // 사업자 등록번호
   // private String bizrno;
}
