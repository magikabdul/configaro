package cloud.cholewa.configaro.user.repository;

import cloud.cholewa.configaro.user.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findRoleByName(String roleName);
}
