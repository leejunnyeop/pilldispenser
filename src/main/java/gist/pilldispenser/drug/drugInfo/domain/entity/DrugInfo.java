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
    private int dailyDosage;

    @Enumerated(EnumType.STRING)
    private DrugShape shape;

    private Double longAxis;  // 타원형일 때 사용
    private Double shortAxis; // 타원형일 때 사용
}
