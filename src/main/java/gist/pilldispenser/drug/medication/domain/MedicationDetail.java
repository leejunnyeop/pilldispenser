package gist.pilldispenser.drug.medication.domain;


import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto.DrugIdentification;
import gist.pilldispenser.drug.api.drugProductAPI.domain.dto.DrugProduct;
import gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.DrugSummary;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo;
import gist.pilldispenser.users.domain.entity.Users;
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
        name = "MedicationDetail.withRelations",
        attributeNodes = {
                @NamedAttributeNode("drugSummary"),
                @NamedAttributeNode("drugIdentification"),
                @NamedAttributeNode("drugProducts")
        }
)
public class MedicationDetail {

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "medicationDetail")
    private List<DrugProduct> drugProducts;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_info_id")
    private DrugInfo drugInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;
}