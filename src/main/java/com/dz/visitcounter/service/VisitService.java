package com.dz.visitcounter.service;

import com.dz.visitcounter.dao.UserDao;
import com.dz.visitcounter.dao.VisitDao;
import com.dz.visitcounter.model.User;
import com.dz.visitcounter.model.Visit;
import com.dz.visitcounter.view.PeriodStats;
import com.dz.visitcounter.view.PeriodView;
import com.dz.visitcounter.view.SuccessView;
import com.dz.visitcounter.view.VisitView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Date;

@Service
public class VisitService {

    private final VisitDao dao;
    private final UserDao userDao;

    @Autowired
    public VisitService(VisitDao dao, UserDao userDao) {
        this.dao = dao;
        this.userDao = userDao;
    }

    @Transactional
    public SuccessView add(VisitView view, String ip) {
        Visit visit = new Visit();
        try {
            User user = userDao.loadByIp(ip);
        } catch (NoResultException ex) {
            User newUser = new User(ip);
            userDao.save(newUser);
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!");

        }
        visit.setUser_id(view.userid);
        visit.setPage_id(view.pageid);
        visit.setTime(new Date());
        visit.setIp(ip);
        dao.addVisit(visit);
        SuccessView todayStats = new SuccessView();
        todayStats.todayVisits = dao.todayVisits();
        todayStats.uniqueVisitersToday = dao.uniqueTodayVisits();
        return todayStats;
    }

    @Transactional
    public PeriodStats getStatsForPeriod(PeriodView view) {

        PeriodStats periodStats = new PeriodStats();
        periodStats.totalVisits = dao.totalPeriodVisits(view.firstDate, view.secondDate);
        periodStats.totalRegularVisitors = dao.totalRegularVisitors(view.firstDate, view.secondDate);
        periodStats.totalUniqueVisits = dao.uniquePeriodVisits(view.firstDate, view.secondDate);
        ;
        return periodStats;
    }
}
