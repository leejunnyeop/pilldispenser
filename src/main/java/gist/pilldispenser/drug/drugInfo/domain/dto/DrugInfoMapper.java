package gist.pilldispenser.drug.drugInfo.domain.dto;

import gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo;

public class DrugInfoMapper {

    /**
     * DrugInfoRequest DTO를 DrugInfo 엔티티로 변환합니다.
     *
     * @param drugInfoRequest DrugInfoRequest DTO
     * @return DrugInfo 엔티티
     */
    public static DrugInfo toDrugInfo(DrugInfoRequest drugInfoRequest) {
        return DrugInfo.builder()
                .id(drugInfoRequest.getId())
                .name(drugInfoRequest.getName())
                .usage(drugInfoRequest.getUsage())
                .dailyDosage(drugInfoRequest.getDailyDosage())
                .timeOfDay(drugInfoRequest.getTimeOfDay())
                .beforeAfterMeal(drugInfoRequest.getBeforeAfterMeal())
                .shape(drugInfoRequest.getShape())
                .diameter(drugInfoRequest.getDiameter())
                .thickness(drugInfoRequest.getThickness())
                .color(drugInfoRequest.getColor())
                .build();
    }

    /**
     * DrugInfo 엔티티를 DrugInfoRequest DTO로 변환합니다.
     *
     * @param drugInfo DrugInfo 엔티티
     * @return DrugInfoRequest DTO
     */
    public static DrugInfoRequest toDrugInfoRequest(DrugInfo drugInfo) {
        return DrugInfoRequest.builder()
                .id(drugInfo.getId())
                .name(drugInfo.getName())
                .usage(drugInfo.getUsage())
                .dailyDosage(drugInfo.getDailyDosage())
                .timeOfDay(drugInfo.getTimeOfDay())
                .beforeAfterMeal(drugInfo.getBeforeAfterMeal())
                .shape(drugInfo.getShape())
                .diameter(drugInfo.getDiameter())
                .thickness(drugInfo.getThickness())
                .color(drugInfo.getColor())
                .build();

    }
}
