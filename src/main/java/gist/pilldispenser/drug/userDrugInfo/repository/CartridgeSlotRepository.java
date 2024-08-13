package gist.pilldispenser.drug.userDrugInfo.repository;

import gist.pilldispenser.drug.userDrugInfo.domain.entity.CartridgeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartridgeSlotRepository extends JpaRepository<CartridgeSlot, Long> {

    // 비어있는 슬롯을 번호 순으로 조회
    List<CartridgeSlot> findByIsOccupiedFalseOrderBySlotNumberAsc();

    // 특정 크기의 비어있는 슬롯을 조회
    Optional<CartridgeSlot> findBySizeAndIsOccupiedFalse(String size);

    Optional<CartridgeSlot> findFirstByUserIdAndIsOccupiedFalseOrderBySlotNumberAsc(Long userId);

    List<CartridgeSlot> findAllByUserId(Long userId);

    boolean existsByUserIdAndIsOccupiedFalse(Long userId);
}
