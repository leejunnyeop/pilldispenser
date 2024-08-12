package gist.pilldispenser.drug.drugInfo.domain.entity;


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
}
