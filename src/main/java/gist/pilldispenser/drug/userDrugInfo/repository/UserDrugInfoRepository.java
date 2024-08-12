package gist.pilldispenser.drug.userDrugInfo.repository;

import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface UserDrugInfoRepository extends JpaRepository<UserDrugInfo, Long> {


  Optional<UserDrugInfo> findByDrugInfoId(Long drugInfoId);

  List<UserDrugInfo> findByUserId(Long userId);

  Optional<UserDrugInfo> findByUserIdAndFullMedicationInfoItemSeq(Long userId, String itemSeq);
}
