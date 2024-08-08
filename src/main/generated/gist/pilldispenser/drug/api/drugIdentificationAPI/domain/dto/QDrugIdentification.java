package gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDrugIdentification is a Querydsl query type for DrugIdentification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDrugIdentification extends EntityPathBase<DrugIdentification> {

    private static final long serialVersionUID = 1181329088L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDrugIdentification drugIdentification = new QDrugIdentification("drugIdentification");

    public final StringPath chart = createString("chart");

    public final StringPath colorClass1 = createString("colorClass1");

    public final StringPath colorClass2 = createString("colorClass2");

    public final StringPath drugShape = createString("drugShape");

    public final StringPath entpName = createString("entpName");

    public final gist.pilldispenser.drug.medication.domain.QFullMedicationInfo fullMedicationInfo;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath itemName = createString("itemName");

    public final StringPath itemSeq = createString("itemSeq");

    public final StringPath lengLong = createString("lengLong");

    public final StringPath lengShort = createString("lengShort");

    public final StringPath thick = createString("thick");

    public QDrugIdentification(String variable) {
        this(DrugIdentification.class, forVariable(variable), INITS);
    }

    public QDrugIdentification(Path<? extends DrugIdentification> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDrugIdentification(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDrugIdentification(PathMetadata metadata, PathInits inits) {
        this(DrugIdentification.class, metadata, inits);
    }

    public QDrugIdentification(Class<? extends DrugIdentification> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fullMedicationInfo = inits.isInitialized("fullMedicationInfo") ? new gist.pilldispenser.drug.medication.domain.QFullMedicationInfo(forProperty("fullMedicationInfo"), inits.get("fullMedicationInfo")) : null;
    }

}

