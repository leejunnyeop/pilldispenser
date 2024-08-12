package gist.pilldispenser.drug.drugInfo.domain.entity;

import lombok.Getter;

@Getter
public enum DrugShape {
    ROUND("원형"),
    OVAL("타원형");

    private final String description;

    DrugShape(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static DrugShape fromDescription(String description) {
        for (DrugShape shape : DrugShape.values()) {
            if (shape.getDescription().equalsIgnoreCase(description)) {
                return shape;
            }
        }
        throw new IllegalArgumentException("No enum constant with description " + description);
    }
}
