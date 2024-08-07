package gist.pilldispenser.api.drugIdentificationAPI.domain.dto;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDrugIdentification is a Querydsl query type for DrugIdentification
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDrugIdentification extends EntityPathBase<DrugIdentification> {

    private static final long serialVersionUID = -1762523468L;

    public static final QDrugIdentification drugIdentification = new QDrugIdentification("drugIdentification");

    public final StringPath chart = createString("chart");

    public final StringPath colorClass1 = createString("colorClass1");

    public final StringPath colorClass2 = createString("colorClass2");

    public final StringPath drugShape = createString("drugShape");

    public final StringPath entpName = createString("entpName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath itemName = createString("itemName");

    public final StringPath itemSeq = createString("itemSeq");

    public final StringPath lengLong = createString("lengLong");

    public final StringPath lengShort = createString("lengShort");

    public final StringPath thick = createString("thick");

    public QDrugIdentification(String variable) {
        super(DrugIdentification.class, forVariable(variable));
    }

    public QDrugIdentification(Path<? extends DrugIdentification> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDrugIdentification(PathMetadata metadata) {
        super(DrugIdentification.class, metadata);
    }

}

