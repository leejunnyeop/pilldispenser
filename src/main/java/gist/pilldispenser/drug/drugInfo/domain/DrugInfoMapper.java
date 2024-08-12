package gist.pilldispenser.drug.drugInfo.domain;

import gist.pilldispenser.drug.drugInfo.domain.dto.OvalDrugInfoRequest;
import gist.pilldispenser.drug.drugInfo.domain.dto.RoundDrugInfoRequest;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugShape;

public class DrugInfoMapper {
    public static DrugInfo toDrugInfo(RoundDrugInfoRequest dto) {
        return DrugInfo.builder()
                .name(dto.getName())
                .dosage(dto.getDosage())
                .ingredients(dto.getIngredients())
                .dailyDosage(dto.getDailyDosage())
                .timeOfDay(dto.getTimeOfDay())
                .whenToTake(dto.getWhenToTake())
                .shape(DrugShape.ROUND)
                .diameter(dto.getDiameter())
                .build();
    }

    public static DrugInfo toDrugInfo(OvalDrugInfoRequest dto) {
        return DrugInfo.builder()
                .name(dto.getName())
                .dosage(dto.getDosage())
                .ingredients(dto.getIngredients())
                .dailyDosage(dto.getDailyDosage())
                .timeOfDay(dto.getTimeOfDay())
                .whenToTake(dto.getWhenToTake())
                .shape(DrugShape.OVAL)
                .longAxis(dto.getLongAxis())
                .shortAxis(dto.getShortAxis())
                .build();
    }

}
