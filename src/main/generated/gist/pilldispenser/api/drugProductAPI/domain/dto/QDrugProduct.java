package gist.pilldispenser.api.drugProductAPI.domain.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDrugProduct is a Querydsl query type for DrugProduct
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDrugProduct extends EntityPathBase<DrugProduct> {

    private static final long serialVersionUID = 1714546792L;

    public static final QDrugProduct drugProduct = new QDrugProduct("drugProduct");

    public final StringPath entrps = createString("entrps");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath ingdUnitCd = createString("ingdUnitCd");

    public final StringPath itemSeq = createString("itemSeq");

    public final StringPath mtralCode = createString("mtralCode");

    public final StringPath mtralNm = createString("mtralNm");

    public final StringPath mtralSn = createString("mtralSn");

    public final StringPath prduct = createString("prduct");

    public final StringPath qnt = createString("qnt");

    public QDrugProduct(String variable) {
        super(DrugProduct.class, forVariable(variable));
    }

    public QDrugProduct(Path<? extends DrugProduct> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDrugProduct(PathMetadata metadata) {
        super(DrugProduct.class, metadata);
    }

}

