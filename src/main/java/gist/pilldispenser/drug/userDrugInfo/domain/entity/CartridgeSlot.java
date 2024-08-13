package gist.pilldispenser.drug.userDrugInfo.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CartridgeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_drug_info_id")
    private UserDrugInfo userDrugInfo;

    private Long userId;

    private int slotNumber;  // 카트리지 슬롯 번호 (1~6번)

    private boolean isOccupied;  // 해당 슬롯이 사용 중인지 여부

    private String size;  // 슬롯의 사이즈 (S, M, L)

    public CartridgeSlot updateSlot(UserDrugInfo userDrugInfo, boolean isOccupied, String size) {
        return CartridgeSlot.builder()
                .id(this.id)
                .userDrugInfo(userDrugInfo != null ? userDrugInfo : this.userDrugInfo)
                .slotNumber(this.slotNumber)
                .isOccupied(isOccupied)
                .size(size)
                .userId(this.userId)
                .build();
    }

    public CartridgeSlot updateSize(String size) {
        return CartridgeSlot.builder()
                .size(size)
                .build();
    }
}