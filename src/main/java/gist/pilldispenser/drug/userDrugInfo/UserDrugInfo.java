package gist.pilldispenser.drug.userDrugInfo;

import gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo;
import gist.pilldispenser.drug.medication.domain.FullMedicationInfo;
import gist.pilldispenser.users.domain.entity.Users;
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
public class UserDrugInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_info_id", nullable = false)
    private DrugInfo drugInfo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "full_medication_info_id", nullable = false)
    private FullMedicationInfo fullMedicationInfo;

    public static UserDrugInfo create(Users user, DrugInfo drugInfo, FullMedicationInfo fullMedicationInfo) {
        return UserDrugInfo.builder()
                .user(user)
                .drugInfo(drugInfo)
                .fullMedicationInfo(fullMedicationInfo)
                .build();
    }
}


