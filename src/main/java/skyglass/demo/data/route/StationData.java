package skyglass.demo.data.route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import skyglass.demo.data.model.route.Station;

@Transactional(readOnly = true) 
public interface StationData extends JpaRepository<Station, Long> {
	
    public Station findByName(String name);

}
