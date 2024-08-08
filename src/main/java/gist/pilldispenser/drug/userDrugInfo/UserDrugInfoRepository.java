package gist.pilldispenser.drug.userDrugInfo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDrugInfoRepository extends JpaRepository<UserDrugInfo, Long> {

    List<UserDrugInfo> findByUserId(Long userId);
}
