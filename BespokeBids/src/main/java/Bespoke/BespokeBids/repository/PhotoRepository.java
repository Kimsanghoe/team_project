package Bespoke.BespokeBids.repository;

import Bespoke.BespokeBids.domain.photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
