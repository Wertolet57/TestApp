package ru.kazmin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kazmin.dao.abstracts.BidDao;
import ru.kazmin.models.Bid;
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
    public void createBid(Bid bid) {
        bidDao.createBid(bid);
    }

    @Override
    public boolean sendBid(Long id) {
        return bidDao.sendBid(id);
    }

    @Override
    public boolean acceptedOrRejectBid(Long id, int action) {
        return bidDao.acceptedOrRejectBid(id, action);
    }

    @Override
    public boolean updateBid(Bid bid) {
        return bidDao.updateBid(bid);
    }

    @Override
    public List<Bid> getBids(int sort, int first) {
        return bidDao.getBids(sort, first);
    }

    @Override
    public Bid getBid(Long id) {
        return bidDao.getBid(id);
    }

    @Override
    public List<Bid> getSentBids(int sort, int first) {
        return bidDao.getSentBids(sort, first);
    }

    @Override
    public List<Bid> getSentBidsByUsername(String username, int sort, int first) {
        return bidDao.getSentBidsByUsername(username, sort, first);
    }
}
