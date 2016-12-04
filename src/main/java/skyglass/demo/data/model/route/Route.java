package skyglass.demo.data.model.route;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


@Entity
@Table(name = "route")
public class Route {

	@Id @GeneratedValue
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;
	
	@ManyToMany(fetch = FetchType.EAGER)
    @Cascade({CascadeType.ALL})
	@JoinTable(name = "route_station", joinColumns = { @JoinColumn(name = "id_route", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "id_station", table = "station", referencedColumnName = "id") })
	@OrderColumn(name="ordinal")
	private List<Station> stations = new ArrayList<Station>();
	
	public Route() {
		
	}
	
	public Route(String name) {
		this.name = name;
	}
	
	public Route addStation(Station station) {
		this.stations.add(station);
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Station> getStations() {
		return stations;
	}

	public void setStations(List<Station> stations) {
		this.stations = stations;
	}		

}

