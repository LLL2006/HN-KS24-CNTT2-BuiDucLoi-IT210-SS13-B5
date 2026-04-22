package com.re.ss13b5.repository;


import com.re.ss13b5.model.entity.Prescription;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class PrescriptionRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Prescription> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT DISTINCT p FROM Prescription p LEFT JOIN FETCH p.details", Prescription.class)
                .getResultList();
    }

    public void save(Prescription p) {
        Session session = sessionFactory.getCurrentSession();
        if (p.getDetails() != null) {
            p.getDetails().forEach(d -> d.setPrescription(p));
        }
        session.merge(p);
    }

    public List<Prescription> search(String code) {
        Session session = sessionFactory.getCurrentSession();

        String hql = "SELECT DISTINCT p FROM Prescription p " +
                "LEFT JOIN FETCH p.details " +
                "WHERE p.patientCode LIKE :code";

        return session.createQuery(hql, Prescription.class)
                .setParameter("code", "%" + code + "%")
                .getResultList();
    }
}