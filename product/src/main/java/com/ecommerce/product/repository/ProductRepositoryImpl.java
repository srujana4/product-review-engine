package com.ecommerce.product.repository;

import com.ecommerce.product.exceptions.DataInsertionException;
import com.ecommerce.product.exceptions.DataRetrievalException;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.model.ProductReview;
import com.ecommerce.product.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository implementation to handle the queries towards product catalog.
 * This implementation is based on Hibernate library and a MYSQL database.
 */
@Repository
@Qualifier("productRepository")
public class ProductRepositoryImpl implements ProductRepository {

    private static final Predicate[] PREDICATES = {};

    private SessionFactory sessionFactory;

    @Autowired
    public ProductRepositoryImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Adds Product details into SQL DB.
     *
     * @param product
     */
    @Override
    public void addProduct(Product product) {

        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        } catch (Exception ex) {
            throw new DataInsertionException("Error occurred while inserting product into DB", ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Returns the product catalog
     *
     * @return
     */
    @Override
    public List<Product> getAllProducts() {

        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.select(root);

        List<Product> productCatalog;
        try {
            productCatalog = getSession().createQuery(criteria).getResultList();
        } catch (Exception ex) {
            throw new DataRetrievalException("Error occurred while retrieving product catalog", ex);
        }

        return productCatalog;
    }

    /**
     * Gets a Product's information by Id.
     *
     * @param productId
     * @return
     */
    @Override
    public Product getProductById(String productId) {
        CriteriaBuilder builder = getSession().getCriteriaBuilder();
        CriteriaQuery<Product> criteria = builder.createQuery(Product.class);
        Root<Product> root = criteria.from(Product.class);
        criteria.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (productId != null) {
            predicates.add(builder.equal(root.get("id"), productId));
            criteria.where(predicates.toArray(PREDICATES));
        }

        List<Product> productList = getSession().createQuery(criteria).getResultList();
        if (productList.isEmpty()) {
            return null;
        }
        return productList.get(0);
    }

    /**
     * Adds a review against a product. This happens in two steps
     * 1. Adding review in the product_review table
     * 2. Updating the average rating of the product.
     *
     * @param productReview
     */
    @Override
    public void addReviewForProduct(ProductReview productReview) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Product product = productReview.getProduct();
            float totalSumOfRatings = product.getAvgRating() * product.getNumberOfRatings();
            float newSum = totalSumOfRatings + productReview.getRating();
            int newNumberOfRatings = product.getNumberOfRatings() + 1;
            float newAverage = newSum / newNumberOfRatings;

            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            product.setAvgRating(Float.parseFloat(df.format(newAverage)));
            product.setNumberOfRatings(newNumberOfRatings);

            session.save(product);
            session.save(productReview);
            transaction.commit();
        } catch (Exception ex) {
            // TODO: Add retry mechanism
            transaction.rollback();
            throw new DataInsertionException("Error occured while saving user review", ex);
        } finally {
            session.close();
        }
    }

    /**
     * Returns the reviews of a product. This query result is paginated.
     *
     * @param user
     * @param product
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public List<ProductReview> getReviewsOfProduct(User user, Product product, Integer offset, Integer limit) {
        Session session = getSession();
        CriteriaQuery<ProductReview> criteria;
        List<ProductReview> productReviews;

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            criteria = builder.createQuery(ProductReview.class);
            Root<ProductReview> root = criteria.from(ProductReview.class);
            criteria.select(root);

            List<Predicate> predicates = new ArrayList<>();

            if (product != null) {
                predicates.add(builder.equal(root.get(ProductReview.ProductReviewProperties.PRODUCT_ID), product));
                criteria.where(predicates.toArray(PREDICATES));
            }

            if (user != null) {
                predicates.add(builder.equal(root.get(ProductReview.ProductReviewProperties.USER_ID), user));
                criteria.where(predicates.toArray(PREDICATES));
            }

            if (limit != null) {
                return getSession().createQuery(criteria).setFirstResult(offset).setMaxResults(limit).getResultList();
            }
            if (offset != null) {
                return getSession().createQuery(criteria).setFirstResult(offset).getResultList();
            }

            productReviews = session.createQuery(criteria).getResultList();

        } catch (Exception ex) {
            throw new DataRetrievalException("Error occurred while retrieving the reviews for product", ex);
        }

        return productReviews;
    }
}
