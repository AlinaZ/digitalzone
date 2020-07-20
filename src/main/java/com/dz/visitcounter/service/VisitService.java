package com.dz.visitcounter.service;

import com.dz.visitcounter.dao.PageDao;
import com.dz.visitcounter.dao.UserDao;
import com.dz.visitcounter.dao.VisitDao;
import com.dz.visitcounter.model.Page;
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
import java.util.logging.Logger;

@Service
public class VisitService {

    private final VisitDao dao;
    private final UserDao userDao;
    private final PageDao pageDao;

    @Autowired
    public VisitService(VisitDao dao, UserDao userDao, PageDao pageDao) {
        this.dao = dao;
        this.userDao = userDao;
        this.pageDao = pageDao;
    }

    @Transactional
    public SuccessView add(VisitView view, String ip) {
        Visit visit = new Visit();
        SuccessView todayStats = new SuccessView();
        try {
            User user = userDao.loadByIp(ip);
        } catch (NoResultException ex) {
            User newUser = new User(ip);
            userDao.save(newUser);
        }
        visit.setUser_id(view.userid);
        Page page = pageDao.loadById(view.pageid);
        if (page != null) {
            visit.setPage_id(view.pageid);
            visit.setTime(new Date());
            visit.setIp(ip);
            dao.addVisit(visit);
        }
        else {
            todayStats.result="Error: в базе нет страницы с таким page_id";
        }
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
