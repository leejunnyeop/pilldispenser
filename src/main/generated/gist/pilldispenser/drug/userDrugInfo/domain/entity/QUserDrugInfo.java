package gist.pilldispenser.drug.userDrugInfo.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserDrugInfo is a Querydsl query type for UserDrugInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserDrugInfo extends EntityPathBase<UserDrugInfo> {

    private static final long serialVersionUID = 387993598L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserDrugInfo userDrugInfo = new QUserDrugInfo("userDrugInfo");

    public final SimplePath<gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo> drugInfo = createSimple("drugInfo", gist.pilldispenser.drug.drugInfo.domain.entity.DrugInfo.class);

    public final gist.pilldispenser.drug.medication.domain.entity.QFullMedicationInfo fullMedicationInfo;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<Routine, QRoutine> routines = this.<Routine, QRoutine>createList("routines", Routine.class, QRoutine.class, PathInits.DIRECT2);

    public final gist.pilldispenser.users.domain.entity.QUsers user;

    public QUserDrugInfo(String variable) {
        this(UserDrugInfo.class, forVariable(variable), INITS);
    }

    public QUserDrugInfo(Path<? extends UserDrugInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserDrugInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserDrugInfo(PathMetadata metadata, PathInits inits) {
        this(UserDrugInfo.class, metadata, inits);
    }

    public QUserDrugInfo(Class<? extends UserDrugInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fullMedicationInfo = inits.isInitialized("fullMedicationInfo") ? new gist.pilldispenser.drug.medication.domain.entity.QFullMedicationInfo(forProperty("fullMedicationInfo"), inits.get("fullMedicationInfo")) : null;
        this.user = inits.isInitialized("user") ? new gist.pilldispenser.users.domain.entity.QUsers(forProperty("user")) : null;
    }

}

