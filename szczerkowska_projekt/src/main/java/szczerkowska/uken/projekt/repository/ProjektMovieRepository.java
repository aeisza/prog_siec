package szczerkowska.uken.projekt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import szczerkowska.uken.projekt.model.ProjektMovie;

@Repository
public interface ProjektMovieRepository extends JpaRepository<ProjektMovie, Long> {
    
}
