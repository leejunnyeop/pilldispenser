package gist.pilldispenser.drug.userDrugInfo.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity.DrugIdentification;
import gist.pilldispenser.drug.api.drugIdentificationAPI.domain.entity.QDrugIdentification;
import gist.pilldispenser.drug.drugInfo.domain.entity.QDrugInfo;
import gist.pilldispenser.drug.medication.domain.entity.FullMedicationInfo;
import gist.pilldispenser.drug.medication.domain.entity.QFullMedicationInfo;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.QRoutine;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.QUserDrugInfo;
import gist.pilldispenser.drug.userDrugInfo.domain.entity.UserDrugInfo;
import gist.pilldispenser.users.domain.entity.QUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDrugInfoRepositoryCustomImpl implements UserDrugInfoRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Tuple findUserDrugInfoAndFullMedicationInfoByRoutineId(Long routineId) {

        QRoutine qRoutine = QRoutine.routine;
        QUserDrugInfo qUserDrugInfo = QUserDrugInfo.userDrugInfo;
        QUsers qUsers = QUsers.users;
        QFullMedicationInfo qFullMedicationInfo = QFullMedicationInfo.fullMedicationInfo;

        return queryFactory.select(qUserDrugInfo, qFullMedicationInfo, qUsers)
                .from(qRoutine)
                .join(qRoutine.userDrugInfo, qUserDrugInfo)
                .join(qUserDrugInfo.user, qUsers)
                .leftJoin(qUserDrugInfo.fullMedicationInfo, qFullMedicationInfo)
                .where(qRoutine.id.eq(routineId))
                .fetchOne();
    }

    public Tuple findUserDrugInfoAndDrugInfoByRoutineId(Long routineId) {

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

    @Override
    public UserDrugInfo getUserDrugInfoByRoutine(Long routineId) {

        QRoutine qRoutine = QRoutine.routine;
        QUserDrugInfo qUserDrugInfo = QUserDrugInfo.userDrugInfo;

        return queryFactory.select(qUserDrugInfo)
                .from(qRoutine)
                .join(qRoutine.userDrugInfo, qUserDrugInfo)
                .where(qRoutine.id.eq(routineId))
                .fetchOne();
    }

    @Override
    public DrugIdentification getDrugIdentificationByFullMedicationInfo(FullMedicationInfo fullMedicationInfo) {

        QFullMedicationInfo qfullMedicationInfo = QFullMedicationInfo.fullMedicationInfo;
        QDrugIdentification qDrugIdentification = QDrugIdentification.drugIdentification;

        return queryFactory.select(qDrugIdentification)
                .from(qfullMedicationInfo)
                .join(qfullMedicationInfo.drugIdentification, qDrugIdentification)
                .where(qfullMedicationInfo.eq(fullMedicationInfo))
                .fetchOne();
    }


}
