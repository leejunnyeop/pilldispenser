package gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDrugSummary is a Querydsl query type for DrugSummary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDrugSummary extends EntityPathBase<DrugSummary> {

    private static final long serialVersionUID = 1151714416L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDrugSummary drugSummary = new QDrugSummary("drugSummary");

    public final StringPath atpnQesitm = createString("atpnQesitm");

    public final StringPath depositMethodQesitm = createString("depositMethodQesitm");

    public final StringPath efcyQesitm = createString("efcyQesitm");

    public final StringPath entpName = createString("entpName");

    public final gist.pilldispenser.drug.medication.domain.QFullMedicationInfo fullMedicationInfo;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath intrcQesitm = createString("intrcQesitm");

    public final StringPath itemImage = createString("itemImage");

    public final StringPath itemName = createString("itemName");

    public final StringPath itemSeq = createString("itemSeq");

    public final StringPath seQesitm = createString("seQesitm");

    public final StringPath useMethodQesitm = createString("useMethodQesitm");

    public QDrugSummary(String variable) {
        this(DrugSummary.class, forVariable(variable), INITS);
    }

    public QDrugSummary(Path<? extends DrugSummary> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDrugSummary(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDrugSummary(PathMetadata metadata, PathInits inits) {
        this(DrugSummary.class, metadata, inits);
    }

    public QDrugSummary(Class<? extends DrugSummary> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fullMedicationInfo = inits.isInitialized("fullMedicationInfo") ? new gist.pilldispenser.drug.medication.domain.QFullMedicationInfo(forProperty("fullMedicationInfo"), inits.get("fullMedicationInfo")) : null;
    }

}

