package ru.kazmin.service.abstracts;

import ru.kazmin.models.Bid;
import ru.kazmin.models.User;

import java.util.List;

public interface BidService {

    void createBid(Bid bid);

    void sendBid(Long id);

    boolean updateBid(Bid bid);

    List<Bid> getBids(int sort, int first);
    public Bid getBid(Long id);
}
