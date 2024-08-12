package gist.pilldispenser.drug.drugInfo.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrugRegistrationResponse {

    private String drugName;
    private String mainIngredient;
    private String dosageInstructions;
    private String shape;
    private String size;
    private String slotNumber;
    private String slotSize;
}