package gist.pilldispenser.drug.drugInfo.domain.entity;


import gist.pilldispenser.drug.drugInfo.domain.dto.BeforeAfterMeal;
import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoRequest;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
public class DrugInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 약 ID

    @Column(nullable = false)
    private String name;  // 약 이름


    @Column(nullable = false)
    private String usage;  // 사용 용법

    @Column(nullable = false)
    private int dailyDosage;  // 하루 복용 횟수

    @ElementCollection
    @CollectionTable(name = "drug_time_of_day", joinColumns = @JoinColumn(name = "drug_id"))
    @Column(name = "time_of_day")
    private List<String> timeOfDay;  // 복용 시간대 (아침, 점심, 저녁)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BeforeAfterMeal beforeAfterMeal;  // 복용 시점 (식전, 식후, 공복, 취침 전)


    @Column(nullable = false)
    private String shape;  // 모양 (예: 원형, 타원형)

    @Column(nullable = false)
    private double diameter;  // 직경 (mm)

    @Column(nullable = false)
    private double thickness;  // 두께 (mm)

    @Column(nullable = false)
    private String color;  // 색상

    public void partialUpdate(DrugInfoRequest updatedDrugInfo) {
        if (updatedDrugInfo.getName() != null) {
            this.name = updatedDrugInfo.getName();
        }
        if (updatedDrugInfo.getUsage() != null) {
            this.usage = updatedDrugInfo.getUsage();
        }
        if (updatedDrugInfo.getDailyDosage() != 0) {
            this.dailyDosage = updatedDrugInfo.getDailyDosage();
        }
        if (updatedDrugInfo.getTimeOfDay() != null) {
            this.timeOfDay = updatedDrugInfo.getTimeOfDay();
        }
        if (updatedDrugInfo.getBeforeAfterMeal() != null) {
            this.beforeAfterMeal = updatedDrugInfo.getBeforeAfterMeal();
        }
        if (updatedDrugInfo.getShape() != null) {
            this.shape = updatedDrugInfo.getShape();
        }
        if (updatedDrugInfo.getDiameter() != 0) {
            this.diameter = updatedDrugInfo.getDiameter();
        }
        if (updatedDrugInfo.getThickness() != 0) {
            this.thickness = updatedDrugInfo.getThickness();
        }
        if (updatedDrugInfo.getColor() != null) {
            this.color = updatedDrugInfo.getColor();
        }
    }

}