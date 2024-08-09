package gist.pilldispenser.drug.userDrugInfo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_drug_info_id", nullable = false)
    private UserDrugInfo userDrugInfo;

    @Column(nullable = false)
    private LocalTime time;  // 복용 시간

    @Column(nullable = false)
    private int dosagePerTake;  // 복용 시 1회 복용량

    @Column(nullable = false)
    private int dailyDosage;  // 하루 복용 횟수

    @Column(nullable = false)
    private boolean isActive;  // 루틴 활성화 여부

    public void updateRoutine(LocalTime time, int dosagePerTake, int dailyDosage, boolean isActive) {
        this.time = time;
        this.dosagePerTake = dosagePerTake;
        this.dailyDosage = dailyDosage;
        this.isActive = isActive;
    }
}