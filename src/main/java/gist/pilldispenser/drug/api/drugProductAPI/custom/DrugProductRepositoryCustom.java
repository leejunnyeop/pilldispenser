package gist.pilldispenser.drug.api.drugProductAPI.custom;

import java.util.List;

public interface DrugProductRepositoryCustom {
    List<String> findDistinctMtralNmByPartialName(String partialName);
}
