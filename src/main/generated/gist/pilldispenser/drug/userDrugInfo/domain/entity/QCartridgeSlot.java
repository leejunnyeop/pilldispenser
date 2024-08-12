package gist.pilldispenser.drug.userDrugInfo.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCartridgeSlot is a Querydsl query type for CartridgeSlot
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCartridgeSlot extends EntityPathBase<CartridgeSlot> {

    private static final long serialVersionUID = 1895522852L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCartridgeSlot cartridgeSlot = new QCartridgeSlot("cartridgeSlot");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isOccupied = createBoolean("isOccupied");

    public final StringPath size = createString("size");

    public final NumberPath<Integer> slotNumber = createNumber("slotNumber", Integer.class);

    public final QUserDrugInfo userDrugInfo;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QCartridgeSlot(String variable) {
        this(CartridgeSlot.class, forVariable(variable), INITS);
    }

    public QCartridgeSlot(Path<? extends CartridgeSlot> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCartridgeSlot(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCartridgeSlot(PathMetadata metadata, PathInits inits) {
        this(CartridgeSlot.class, metadata, inits);
    }

    public QCartridgeSlot(Class<? extends CartridgeSlot> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userDrugInfo = inits.isInitialized("userDrugInfo") ? new QUserDrugInfo(forProperty("userDrugInfo"), inits.get("userDrugInfo")) : null;
    }

}

