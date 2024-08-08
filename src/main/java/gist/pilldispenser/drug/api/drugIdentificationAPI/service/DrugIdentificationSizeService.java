package gist.pilldispenser.drug.api.drugIdentificationAPI.service;

import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto.DrugIdentification;
import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto.DrugSizeCategory;
import gist.pilldispenser.drug.api.drugIdentificationAPI.repository.DrugIdentificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrugIdentificationSizeService {

    private final DrugIdentificationRepository drugIdentificationRepository;

    @Transactional(readOnly = true)
    public Optional<DrugSizeCategory> findDrugSizeByItemSeq(String itemSeq) {
        Optional<DrugIdentification> drug = drugIdentificationRepository.findByItemSeq(itemSeq);
        return drug.map(this::categorizeDrugSize);
    }

    private DrugSizeCategory categorizeDrugSize(DrugIdentification drug) {
        String size = categorizeSize(drug.getDrugShape(), drug.getLengLong(), drug.getLengShort(), drug.getThick());
        return new DrugSizeCategory(drug.getDrugShape(), size);
    }

    private String categorizeSize(String drugShape, String lengLong, String lengShort, String thick) {
        double length = Math.max(Double.parseDouble(lengLong), Double.parseDouble(lengShort));
        double thickness = Double.parseDouble(thick);

        if (drugShape.equalsIgnoreCase("원형")) {
            if (length <= 5.0 && thickness <= 2.0) {
                return "S";
            } else if (length <= 10.0 && thickness <= 5.0) {
                return "M";
            } else {
                return "L";
            }
        } else if (drugShape.equalsIgnoreCase("타원형")) {
            if (length <= 7.0 && thickness <= 3.0) {
                return "S";
            } else if (length <= 14.0 && thickness <= 6.0) {
                return "M";
            } else {
                return "L";
            }
        } else {
            return "Unknown";
        }
    }