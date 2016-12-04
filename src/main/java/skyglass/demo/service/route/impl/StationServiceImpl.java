package skyglass.demo.service.route.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import skyglass.demo.data.model.route.Station;
import skyglass.demo.data.route.StationData;
import skyglass.demo.service.GenericServiceImpl;
import skyglass.demo.service.error.ServiceException;
import skyglass.demo.service.route.StationService;

@Repository
@Transactional(readOnly = true)
public class StationServiceImpl extends GenericServiceImpl<Station, Long, StationData> 
			implements StationService {
	
	@Override
	@Transactional
	public Station save(Station station) throws ServiceException {
    	if (station.getId() != null) {
    		Station oldStation = findOne(station.getId());
    		if (!oldStation.getName().equals(station.getName())) {
    			checkNameExists(station);
    		}
    	} else {
			checkNameExists(station);    		
    	}
    	return super.save(station);
	}
	
	private void checkNameExists(Station station) throws ServiceException {
		Station test = repository.findByName(station.getName());
		if (test != null) {
	        throw new ServiceException("saveStationError",
	        		"Station with the name '" + station.getName() + "' already exists");
		}		
	}	

}
