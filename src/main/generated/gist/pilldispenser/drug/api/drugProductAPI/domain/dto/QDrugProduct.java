package gist.pilldispenser.drug.api.drugProductAPI.domain.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;
import gist.pilldispenser.drug.api.drugProductAPI.domain.entity.DrugProduct;


/**
 * QDrugProduct is a Querydsl query type for DrugProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDrugProduct extends EntityPathBase<DrugProduct> {

    private static final long serialVersionUID = -1347072652L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDrugProduct drugProduct = new QDrugProduct("drugProduct");

    public final StringPath entrps = createString("entrps");

    public final gist.pilldispenser.drug.medication.domain.entity.QFullMedicationInfo fullMedicationInfo;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ingdUnitCd = createString("ingdUnitCd");

    public final StringPath itemSeq = createString("itemSeq");

    public final StringPath mtralCode = createString("mtralCode");

    public final StringPath mtralNm = createString("mtralNm");

    public final StringPath mtralSn = createString("mtralSn");

    public final StringPath prduct = createString("prduct");

    public final StringPath qnt = createString("qnt");

    public QDrugProduct(String variable) {
        this(DrugProduct.class, forVariable(variable), INITS);
    }

    public QDrugProduct(Path<? extends DrugProduct> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDrugProduct(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDrugProduct(PathMetadata metadata, PathInits inits) {
        this(DrugProduct.class, metadata, inits);
    }

    public QDrugProduct(Class<? extends DrugProduct> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.fullMedicationInfo = inits.isInitialized("fullMedicationInfo") ? new gist.pilldispenser.drug.medication.domain.entity.QFullMedicationInfo(forProperty("fullMedicationInfo"), inits.get("fullMedicationInfo")) : null;
    }

}

