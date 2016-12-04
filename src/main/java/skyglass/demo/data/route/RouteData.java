package skyglass.demo.data.route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import skyglass.demo.data.model.route.Route;

@Transactional(readOnly = true) 
public interface RouteData extends JpaRepository<Route, Long>, JpaSpecificationExecutor<Route> {
	
    public Route findByName(String name);

}
