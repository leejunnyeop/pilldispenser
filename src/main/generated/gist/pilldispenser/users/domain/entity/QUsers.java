package gist.pilldispenser.users.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUsers is a Querydsl query type for Users
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUsers extends EntityPathBase<Users> {

    private static final long serialVersionUID = -817299932L;

    public static final QUsers users = new QUsers("users");

    public final gist.pilldispenser.common.entity.QBaseEntity _super = new gist.pilldispenser.common.entity.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final StringPath hardwareNo = createString("hardwareNo");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath nickname = createString("nickname");

    public final StringPath password = createString("password");

    public final StringPath profileImage = createString("profileImage");

    public final EnumPath<gist.pilldispenser.common.entity.enums.RoleType> roleType = createEnum("roleType", gist.pilldispenser.common.entity.enums.RoleType.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final ListPath<gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo, gist.pilldispenser.drug.userDrugInfo.domain.entity.QUserDrugInfo> userDrugInfos = this.<gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo, gist.pilldispenser.drug.userDrugInfo.domain.entity.QUserDrugInfo>createList("userDrugInfos", gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo.class, gist.pilldispenser.drug.userDrugInfo.domain.entity.QUserDrugInfo.class, PathInits.DIRECT2);

    public QUsers(String variable) {
        super(Users.class, forVariable(variable));
    }

    public QUsers(Path<? extends Users> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUsers(PathMetadata metadata) {
        super(Users.class, metadata);
    }

}

