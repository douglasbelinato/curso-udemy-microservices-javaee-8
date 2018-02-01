package com.acme.customers.api.rest.v1.services.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import com.acme.customers.api.mappers.CustomerMapper;
import com.acme.customers.api.models.db.CustomerEntity;
import com.acme.customers.api.rest.v1.services.CustomerService;
import com.acme.customers.api.services.exceptions.EmptyPayloadException;
import com.acme.customers.api.services.exceptions.IdMismatchException;
import com.acme.customers.api.services.exceptions.ResourceNotFoundException;
import com.acme.customers.lib.v1.Customer;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class CustomerServiceImpl implements CustomerService {

	@PersistenceContext
	private EntityManager em;
    
	@Override
	public Customer findCustomerById(String id) throws SQLException {
        CustomerEntity customerEntity = em.find(CustomerEntity.class, id);

        // Proper error handling
        if (customerEntity == null) {
        	throw new ResourceNotFoundException(Customer.class.getSimpleName(), id);
        }

        return CustomerMapper.toCustomer(customerEntity);
	}

	@Override
	public List<Customer> findCustomers(Integer limit, Integer offset) throws SQLException {
		TypedQuery<CustomerEntity> query = em.createNamedQuery("CustomerEntity.findAll", CustomerEntity.class);

        if (limit != null && limit > 0) {
            query = query.setMaxResults(limit);
        }

        if (offset != null && offset > 0) {
            query = query.setFirstResult(offset);
        }
        
        List<CustomerEntity> customerEntities = query.getResultList();
        
        return customerEntities.stream().map(CustomerMapper::toCustomer).collect(Collectors.toList());
	}

	@Override
	public Customer createCustomer(Customer customer) throws SQLException {
		// Proper error handling
    	if (customer == null) {
    		throw new EmptyPayloadException(Customer.class.getSimpleName());
    	}
    	
    	CustomerEntity customerEntity = CustomerMapper.toCustomerEntity(customer);
        customerEntity.setId(null);

        em.persist(customerEntity);

        return CustomerMapper.toCustomer(customerEntity);
	}

	@Override
    public Customer updateCustomer(String id, Customer customer) throws SQLException {
		if (customer == null) {
            throw new EmptyPayloadException(Customer.class.getSimpleName());
        }

        if (!id.equals(customer.getId())) {
            throw new IdMismatchException(id, customer.getId());
        }

        CustomerEntity customerEntity = em.find(CustomerEntity.class, id);

        if (customerEntity == null) {
            throw new ResourceNotFoundException(Customer.class.getSimpleName(), id);
        }

        CustomerEntity updatedCustomerEntity = CustomerMapper.toCustomerEntity(customer);

        updatedCustomerEntity.setId(id);
        updatedCustomerEntity.setCreatedAt(customerEntity.getCreatedAt());

        updatedCustomerEntity = em.merge(updatedCustomerEntity);

        return CustomerMapper.toCustomer(updatedCustomerEntity);
    }

    @Override
    public void deleteCustomerById(String id) throws SQLException {
    	CustomerEntity customerEntity = em.find(CustomerEntity.class, id);

        if (customerEntity == null) {

            throw new ResourceNotFoundException(Customer.class.getSimpleName(), id);
        }

        em.remove(customerEntity);
    }

}
