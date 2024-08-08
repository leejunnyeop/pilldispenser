package gist.pilldispenser.drug.drugInfo.service;

import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoMapper;
import gist.pilldispenser.drug.drugInfo.repository.DrugInfoRepository;
import gist.pilldispenser.drug.drugInfo.domain.dto.DrugInfoRequest;
import gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo;
import gist.pilldispenser.global.ex.DrugInfoNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DrugInfoServiceImpl implements DrugInfoService {


    private final DrugInfoRepository drugInfoRepository;

    @Override
    @Transactional
    public void createDrugInfo(DrugInfoRequest drugInfoRequest) {
        DrugInfo drugInfo = DrugInfoMapper.toDrugInfo(drugInfoRequest);
        drugInfoRepository.save(drugInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DrugInfo> getDrugInfoById(Long drugId) {
        return Optional.ofNullable(drugInfoRepository.findById(drugId)
                .orElseThrow(() -> new DrugInfoNotFoundException(drugId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<DrugInfo> getAllDrugInfo() {
        return drugInfoRepository.findAll();
    }

    @Override
    @Transactional
    public void updateDrugInfo(Long drugId, DrugInfoRequest updatedDrugInfoRequest) {
        DrugInfo existingDrugInfo = drugInfoRepository.findById(drugId)
                .orElseThrow(() -> new DrugInfoNotFoundException(drugId));
        existingDrugInfo.partialUpdate(updatedDrugInfoRequest);
        drugInfoRepository.save(existingDrugInfo);
    }

    @Override
    @Transactional
    public void deleteDrugInfoById(Long drugId) {
        if (!drugInfoRepository.existsById(drugId)) {
            throw new DrugInfoNotFoundException(drugId);
        }
        drugInfoRepository.deleteById(drugId);
    }
}
