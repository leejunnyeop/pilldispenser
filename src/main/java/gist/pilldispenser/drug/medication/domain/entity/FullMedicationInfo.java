package gist.pilldispenser.drug.medication.domain.entity;


import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto.DrugIdentification;
import gist.pilldispenser.drug.api.drugProductAPI.domain.dto.DrugProduct;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.DrugSummary;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedEntityGraph(
        name = "FullMedicationInfo.withRelations",
        attributeNodes = {
                @NamedAttributeNode("drugSummary"),
                @NamedAttributeNode("drugIdentification"),
                @NamedAttributeNode("drugProducts")
        }
)
public class FullMedicationInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String itemSeq;  // 알약 고유번호 추가

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_summary_id")
    private DrugSummary drugSummary;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_identification_id")
    private DrugIdentification drugIdentification;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fullMedicationInfo")
    private List<DrugProduct> drugProducts;

}