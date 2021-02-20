package com.ecommerce.product.repository;

import com.ecommerce.product.dto.response.AddProductResponse;
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

@Repository
@Qualifier("userRepository")
public class UserRepositoryImpl extends AbstractRepository implements UserRepository {

    private static final Predicate[] PREDICATES = {};

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);

        return getSession().createQuery(criteria).getResultList();
    }

    @Override
    public User getUserById(String userId) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root);

        List<Predicate> predicates = new ArrayList<>();
        if (userId != null) {
            predicates.add(builder.equal(root.get("id"), userId));
            criteria.where(predicates.toArray(PREDICATES));
        }

        List<User> userList = getSession().createQuery(criteria).getResultList();
        if (userList.isEmpty()) {
            return null;
        }

        return userList.get(0);
    }

}