package skyglass.demo.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import skyglass.demo.service.error.ServiceException;

public class GenericServiceImpl<E, ID extends Serializable, R extends CrudRepository<E, ID>> 
	implements GenericService<E, ID, R> {

    @Autowired
    protected R repository;

    protected Class<E> entityClass;

    @SuppressWarnings("unchecked")
    public GenericServiceImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[0];
    }

	@Override
    public E findOne(ID id) {
        return repository.findOne(id);
    }

	@Override
    public Iterable<E> findAll() {
		return repository.findAll();
    }
    
	@Override
	@Transactional
    public E save(E entity) throws ServiceException {
        return repository.save(entity);
    }

	@Override
	public boolean exists(ID id) {
		return repository.exists(id);
	}
	
	@Override
	@Transactional
	public void delete(E entity) {
		repository.delete(entity);
	}
	
	@Override
	@Transactional
	public void delete(ID id) {
		repository.delete(id);
	}

}
