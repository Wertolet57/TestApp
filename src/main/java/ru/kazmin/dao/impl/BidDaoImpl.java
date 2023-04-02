package ru.kazmin.dao.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kazmin.dao.abstracts.BidDao;
import ru.kazmin.models.Bid;
import ru.kazmin.models.User;
import ru.kazmin.util.State;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Repository
@Transactional
public class BidDaoImpl implements BidDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createBid(Bid bid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long id = ((User) authentication.getPrincipal()).getId();
        User user = entityManager.find(User.class, id);
        user.addBid(bid);
        bid.setUser(user);
        entityManager.merge(bid);
        entityManager.flush();
    }

    @Override
    public boolean sendBid(Long id) {
        getBid(id).setState(State.SENT);
        return true;
    }

    @Override
    public boolean acceptedOrRejectBid(Long id, int action) {
        Bid bid = getBid(id);
        if (bid.getState().equals(State.SENT)) {
            switch (action) {
                case 1:
                    bid.setState(State.ACCEPTED);
                    break;
                case 2:
                    bid.setState(State.REJECTED);
                    break;
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBid(Bid bid) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        List<Bid> bidOld = entityManager.createQuery("from Bid b where b.id = :id")
                .setParameter("id",bid.getId()).getResultList();
        if (!bidOld.isEmpty() &&
                bidOld.get(0).getState().equals(State.DRAFT) &&
                bidOld.get(0).getUser().getId().equals(user.getId())) {
            bid.setUser(user);
            entityManager.merge(bid);
            entityManager.flush();
            return true;
        }
        return false;
    }

    @Override
    public List<Bid> getBids(int sort, int first) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        String conditions = "where b.user.id = " + user.getId();
        return getBids(sort, first, conditions);
    }

    @Override
    public Bid getBid(Long id) {
        return entityManager.find(Bid.class, id);
    }

    @Override
    public List<Bid> getSentBids(int sort, int first) {
        String conditions = "where b.state = 'SENT'";
        return getBids(sort,first, conditions);
    }

    @Override
    public List<Bid> getSentBidsByUsername(String username, int sort, int first) {
        StringBuilder conditions = new StringBuilder();
        conditions.append("where b.state = 'SENT' and b.user.username = '");
        conditions.append(username);
        conditions.append("'");
        return getBids(sort,first, conditions.toString());
    }

    private List<Bid> getBids(int sort, int first, String conditions) {
        String str = "";
        switch (sort) {
            case (1):
                str = "order by b.time asc";
                break;
            case (-1):
                str = "order by b.time desc";
                break;
        }
        return entityManager.createQuery("select b.id, b.user.id, b.state, b.essence, b.time " +
                        "from Bid b " +
                        conditions +
                        str)
                .setFirstResult(first)
                .setMaxResults(5)
                .getResultList();
    }
}
