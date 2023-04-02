package ru.kazmin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kazmin.dao.abstracts.BidDao;
import ru.kazmin.models.Bid;
import ru.kazmin.models.User;
import ru.kazmin.service.abstracts.BidService;

import java.util.List;
@Service
public class BidServiceImpl implements BidService {

    private BidDao bidDao;

    @Autowired
    public BidServiceImpl(BidDao bidDao) {
        this.bidDao = bidDao;
    }

    @Override
    public void createBid(Long id, Bid bid) {
        bidDao.createBid(id, bid);
    }

    @Override
    public void sendBid(Long id) {
        bidDao.sendBid(id);
    }

    @Override
    public boolean updateBid(User user, Bid bid) {
        return bidDao.updateBid(user, bid);
    }

    @Override
    public List<Bid> getBids(int sort, int first) {
        return bidDao.getBids(sort, first);
    }

    @Override
    public Bid getBid(Long id) {
        return bidDao.getBid(id);
    }
}
