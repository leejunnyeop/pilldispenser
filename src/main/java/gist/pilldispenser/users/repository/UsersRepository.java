package gist.pilldispenser.users.repository;

import gist.pilldispenser.users.domain.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    boolean existsByEmail(String email);

    default Users findFirstById(Long id){
        return findById(id).orElseThrow(() -> new RuntimeException("cannot find member by id "+id));
    }

    Users findByEmail(String email);

    Users findByHardwareNo(String hardwareNo);
}