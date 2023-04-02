package ru.kazmin.service.abstracts;

import ru.kazmin.models.Bid;

import java.util.List;

public interface BidService {

    void createBid(Bid bid);

    boolean sendBid(Long id);

    boolean updateBid(Bid bid);

    List<Bid> getBids(int sort, int first);
    public Bid getBid(Long id);
    boolean acceptedOrRejectBid(Long id, int action);
    List<Bid> getSentBids(int sort, int first);
    List<Bid> getSentBidsByUsername(String username, int sort, int first);
}
