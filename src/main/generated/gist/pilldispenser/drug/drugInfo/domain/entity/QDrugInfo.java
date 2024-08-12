package gist.pilldispenser.drug.drugInfo.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDrugInfo is a Querydsl query type for DrugInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDrugInfo extends EntityPathBase<DrugInfo> {

    private static final long serialVersionUID = 368329310L;

    public static final QDrugInfo drugInfo = new QDrugInfo("drugInfo");

    public final NumberPath<Integer> dailyDosage = createNumber("dailyDosage", Integer.class);

    public final StringPath dosage = createString("dosage");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> longAxis = createNumber("longAxis", Double.class);

    public final StringPath mtralNm = createString("mtralNm");

    public final StringPath name = createString("name");

    public final EnumPath<DrugShape> shape = createEnum("shape", DrugShape.class);

    public final NumberPath<Double> shortAxis = createNumber("shortAxis", Double.class);

    public QDrugInfo(String variable) {
        super(DrugInfo.class, forVariable(variable));
    }

    public QDrugInfo(Path<? extends DrugInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDrugInfo(PathMetadata metadata) {
        super(DrugInfo.class, metadata);
    }

}

