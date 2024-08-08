package gist.pilldispenser.drug.drugInfo.domain.dto;

public enum BeforeAfterMeal {

    BEFORE_MEAL("식전"),
    AFTER_MEAL("식후"),
    EMPTY_STOMACH("공복"),
    BEFORE_SLEEP("취침 전");

    private final String description;

    BeforeAfterMeal(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
