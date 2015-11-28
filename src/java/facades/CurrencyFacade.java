/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import deploy.DeploymentConfiguration;
import entity.CurrencyRate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Bancho
 */
public class CurrencyFacade {
    
    private EntityManagerFactory emf;

    public CurrencyFacade() {
        emf = Persistence.createEntityManagerFactory(DeploymentConfiguration.PU_NAME);
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public CurrencyRate addCurrencyRate(CurrencyRate rate){
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(rate);
            em.getTransaction().commit();
        }finally{
            em.close();
        }
        return rate;
    }
    
    public List<CurrencyRate> getAllCurrencyRates(){
        EntityManager em = getEntityManager();
        List<CurrencyRate> rates = new ArrayList<>();
        try{
            Query query = em.createQuery("SELECT cr FROM CurrencyRate cr");
            rates = query.getResultList();
        }finally{
            em.close();
        }
        return rates;
    }
    
    public List<CurrencyRate> getCurrencyRatesByDate(Date date){
        EntityManager em = getEntityManager();
        List<CurrencyRate> rates = new ArrayList<>();
        try{
            Query query = em.createQuery("SELECT cr FROM CurrencyRate cr WHERE cr.date = :date");
            query.setParameter("date", date);
            rates = query.getResultList();
        }finally{
            em.close();
        }
        return rates;
    }
}
