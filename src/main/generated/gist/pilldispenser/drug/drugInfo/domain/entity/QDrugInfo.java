package gist.pilldispenser.drug.drugInfo.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDrugInfo is a Querydsl query type for DrugInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDrugInfo extends EntityPathBase<DrugInfo> {

    private static final long serialVersionUID = 368329310L;

    public static final QDrugInfo drugInfo = new QDrugInfo("drugInfo");

    public final StringPath color = createString("color");

    public final NumberPath<Integer> dailyDosage = createNumber("dailyDosage", Integer.class);

    public final NumberPath<Double> diameter = createNumber("diameter", Double.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath shape = createString("shape");

    public final NumberPath<Double> thickness = createNumber("thickness", Double.class);

    public final ListPath<String, StringPath> timeOfDay = this.<String, StringPath>createList("timeOfDay", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath usage = createString("usage");

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

