package gist.pilldispenser.drug.drugInfo.domain.dto;

public enum BeforeAfterMeal {

    MORNING("아침"),
    LUNCH("점심"),
    DINNER("저녁"),
    UNKNOWN("잘 모르겠어요");

    private final String description;

    BeforeAfterMeal(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
