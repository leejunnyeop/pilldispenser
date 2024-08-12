package gist.pilldispenser.drug.medication.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import gist.pilldispenser.drug.api.drugProductAPI.domain.entity.DrugProduct;


/**
 * QFullMedicationInfo is a Querydsl query type for FullMedicationInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFullMedicationInfo extends EntityPathBase<FullMedicationInfo> {

    private static final long serialVersionUID = -1116815237L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFullMedicationInfo fullMedicationInfo = new QFullMedicationInfo("fullMedicationInfo");

    public final gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto.QDrugIdentification drugIdentification;

    public final ListPath<DrugProduct, gist.pilldispenser.drug.api.drugProductAPI.domain.dto.QDrugProduct> drugProducts = this.<DrugProduct, gist.pilldispenser.drug.api.drugProductAPI.domain.dto.QDrugProduct>createList("drugProducts", DrugProduct.class, gist.pilldispenser.drug.api.drugProductAPI.domain.dto.QDrugProduct.class, PathInits.DIRECT2);

    public final gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.QDrugSummary drugSummary;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath itemSeq = createString("itemSeq");

    public QFullMedicationInfo(String variable) {
        this(FullMedicationInfo.class, forVariable(variable), INITS);
    }

    public QFullMedicationInfo(Path<? extends FullMedicationInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFullMedicationInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFullMedicationInfo(PathMetadata metadata, PathInits inits) {
        this(FullMedicationInfo.class, metadata, inits);
    }

    public QFullMedicationInfo(Class<? extends FullMedicationInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.drugIdentification = inits.isInitialized("drugIdentification") ? new gist.pilldispenser.drug.api.drugIdentificationAPI.domain.dto.QDrugIdentification(forProperty("drugIdentification"), inits.get("drugIdentification")) : null;
        this.drugSummary = inits.isInitialized("drugSummary") ? new gist.pilldispenser.drug.api.drugSummaryAPI.domain.entity.QDrugSummary(forProperty("drugSummary"), inits.get("drugSummary")) : null;
    }

}

