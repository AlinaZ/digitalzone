package com.dz.visitcounter.dao;

import com.dz.visitcounter.model.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

@Repository
public class VisitDao {
    private final EntityManager em;

    @Autowired
    public VisitDao(EntityManager em) {
        this.em = em;
    }

    public void addVisit(Visit visit) {
        em.persist(visit);
    }

    public Integer todayVisits(){
        TypedQuery<Visit> query = em.createQuery("SELECT v FROM Visit v WHERE cast(time as date) like CURRENT_Date()", Visit.class);
        return query.getResultList().size();
    }

    public Integer uniqueTodayVisits(){
        Query query = em.createNativeQuery("SELECT distinct user_id as NUM FROM Visit v WHERE cast(time as date) like CURRENT_Date()");
        return query.getResultList().size();
    }

    public Integer totalPeriodVisits(Date start, Date stop){
        TypedQuery<Visit> query = em.createQuery("SELECT v FROM Visit v WHERE cast(time as date) between '"+start+"' AND '"+stop+"'", Visit.class);
        return query.getResultList().size();
    }

    public Integer uniquePeriodVisits(Date start, Date stop){
        String sql="SELECT distinct user_id as NUM FROM Visit v WHERE cast(time as date) between '"+start+"' AND '"+stop+"'";
        Query query = em.createNativeQuery(sql);
        return query.getResultList().size();
    }

    public Integer totalRegularVisitors (Date start, Date stop){
        String sql="select user_id, count (distinct page_id) as up from visit where time between '"
                +start+"' and '"+stop+"'  group by user_id having up>=10;";
        System.out.println(sql);
        Query query = em.createNativeQuery(sql);
        return query.getResultList().size();
    }





}
