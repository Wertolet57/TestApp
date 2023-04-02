package ru.kazmin.dao.abstracts;

import ru.kazmin.models.Bid;

import java.util.List;

public interface BidDao {
    void createBid(Bid bid);

    boolean sendBid(Long id);

    boolean updateBid(Bid bid);

    List<Bid> getBids(int sort, int first);

    Bid getBid(Long id);
    boolean acceptedOrRejectBid(Long id, int action);
    List<Bid> getSentBids(int sort, int first);
    List<Bid> getSentBidsByUsername(String username, int sort, int first);
}
