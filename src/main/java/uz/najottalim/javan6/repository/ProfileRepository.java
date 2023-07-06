package uz.najottalim.javan6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.najottalim.javan6.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
}
