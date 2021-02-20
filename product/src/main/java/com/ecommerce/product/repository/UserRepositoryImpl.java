package com.ecommerce.product.repository;

import com.ecommerce.product.dto.response.AddProductResponse;
import com.ecommerce.product.exceptions.DataRetrievalException;
import com.ecommerce.product.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository implementation to handle the queries towards user's information
 * This implementation is based on Hibernate library and a MYSQL database.
 */
@Repository
@Qualifier("userRepository")
public class UserRepositoryImpl extends AbstractRepository implements UserRepository {

    private static final Predicate[] PREDICATES = {};

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Adds a user into the system.
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Returns all the users in the system.
     *
     * @return
     */
    @Override
    public List<User> getAllUsers() {
        Session session = getSession();
        List<User> users;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root);
            users = session.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            throw new DataRetrievalException("Error occurred while fetching the users information", ex);
        }

        return users;
    }

    /**
     * Returns user's information queried by Id.
     *
     * @param userId
     * @return
     */
    @Override
    public User getUserById(String userId) {
        Session session = getSession();
        List<User> userList;
        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteria = builder.createQuery(User.class);
            Root<User> root = criteria.from(User.class);
            criteria.select(root);

            List<Predicate> predicates = new ArrayList<>();
            if (userId != null) {
                predicates.add(builder.equal(root.get("id"), userId));
                criteria.where(predicates.toArray(PREDICATES));
            }

            userList = session.createQuery(criteria).getResultList();
        } catch (Exception ex) {
            throw new DataRetrievalException("Error occurred while fetching the user's information", ex);
        }

        return userList.get(0);
    }

}