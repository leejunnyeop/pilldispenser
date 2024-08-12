package gist.pilldispenser.drug.drugInfo.domain.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrugInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    private String dosage;

    @ElementCollection
    @CollectionTable(name = "drug_ingredients", joinColumns = @JoinColumn(name = "drug_info_id"))
    private List<String> ingredients;

    private int dailyDosage;

    @ElementCollection
    @CollectionTable(name = "drug_time_of_day", joinColumns = @JoinColumn(name = "drug_info_id"))
    private List<String> timeOfDay;

    @ElementCollection
    @CollectionTable(name = "drug_when_to_take", joinColumns = @JoinColumn(name = "drug_info_id"))
    private List<String> whenToTake;

    @Enumerated(EnumType.STRING)
    private DrugShape shape;

    private Double diameter;  // 원형일 때 사용
    private Double longAxis;  // 타원형일 때 사용
    private Double shortAxis; // 타원형일 때 사용
}
