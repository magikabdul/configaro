package cloud.cholewa.configaro.user.repository;

import cloud.cholewa.configaro.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findUserByEmail(String email);

    List<UserEntity> findUserByRoleEntity_Name(String name);

    Optional<UserEntity> findUserById(Long id);
}
