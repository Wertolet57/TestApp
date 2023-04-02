package ru.kazmin.dao.abstracts;

import ru.kazmin.models.Bid;
import ru.kazmin.models.User;

import java.util.List;

public interface BidDao {
    void createBid(Long id, Bid bid);

    void sendBid(Long id);

    boolean updateBid(User user, Bid bid);

    List<Bid> getBids(int sort, int first);

    Bid getBid(Long id);
}
