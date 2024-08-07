package gist.pilldispenser.api.drugSummaryAPI.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDrugSummary is a Querydsl query type for DrugSummary
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDrugSummary extends EntityPathBase<DrugSummary> {

    private static final long serialVersionUID = 1931072764L;

    public static final QDrugSummary drugSummary = new QDrugSummary("drugSummary");

    public final StringPath atpnQesitm = createString("atpnQesitm");

    public final StringPath depositMethodQesitm = createString("depositMethodQesitm");

    public final StringPath efcyQesitm = createString("efcyQesitm");

    public final StringPath entpName = createString("entpName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath intrcQesitm = createString("intrcQesitm");

    public final StringPath itemImage = createString("itemImage");

    public final StringPath itemName = createString("itemName");

    public final StringPath itemSeq = createString("itemSeq");

    public final StringPath seQesitm = createString("seQesitm");

    public final StringPath useMethodQesitm = createString("useMethodQesitm");

    public QDrugSummary(String variable) {
        super(DrugSummary.class, forVariable(variable));
    }

    public QDrugSummary(Path<? extends DrugSummary> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDrugSummary(PathMetadata metadata) {
        super(DrugSummary.class, metadata);
    }

}

