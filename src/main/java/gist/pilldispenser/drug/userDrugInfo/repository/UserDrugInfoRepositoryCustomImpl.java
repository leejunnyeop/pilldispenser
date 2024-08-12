package gist.pilldispenser.drug.userDrugInfo.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gist.pilldispenser.drug.drugInfo.domain.entity.QDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.QRoutine;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.QUserDrugInfo;
import gist.pilldispenser.users.domain.entity.QUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDrugInfoRepositoryCustomImpl implements UserDrugInfoRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Tuple findUserDrugInfoAndDrugInfoAndUserByRoutineId(Long routineId) {

        QRoutine qRoutine = QRoutine.routine;
        QUserDrugInfo qUserDrugInfo = QUserDrugInfo.userDrugInfo;
        QDrugInfo qDrugInfo = QDrugInfo.drugInfo;
        QUsers qUsers = QUsers.users;

        return queryFactory.select(qUserDrugInfo, qDrugInfo, qUsers)
                .from(qRoutine)
                .join(qRoutine.userDrugInfo, qUserDrugInfo)
                .join(qUserDrugInfo.drugInfo, qDrugInfo)
                .join(qUserDrugInfo.user, qUsers)
                .where(qRoutine.id.eq(routineId))
                .fetchOne();
    }
}
