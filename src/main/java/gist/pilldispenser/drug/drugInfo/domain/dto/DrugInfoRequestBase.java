package gist.pilldispenser.drug.drugInfo.domain.dto;


import gist.pilldispenser.drug.drugInfo.domain.entity.DrugShape;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class DrugInfoRequestBase {

    private String name;
    private String dosage;
    private List<String> ingredients;
    private int dailyDosage;
    private List<String> timeOfDay;
    private List<String> whenToTake;
    private DrugShape shape;  // Enum 타입

}
