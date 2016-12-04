package skyglass.demo.data.route;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import skyglass.demo.data.model.route.Route;
import skyglass.demo.data.model.route.Route_;
import skyglass.demo.data.model.route.Station;

public class RouteSpecifications {

	  public static Specification<Route> isMember(Station station) {
		    return new Specification<Route>() {
		      public Predicate toPredicate(Root<Route> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		        return cb.isMember(station, root.get(Route_.stations));
		      }
		    };
	  }
}
