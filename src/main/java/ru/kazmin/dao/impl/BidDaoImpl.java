package ru.kazmin.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
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
    public void createBid(Long id, Bid bid) {
        User user = entityManager.find(User.class, id);
        user.addBid(bid);
        bid.setUser(user);
        entityManager.merge(bid);
        entityManager.flush();
    }

    @Override
    public void sendBid(Long id) {
        getBid(id).setState(State.SENT);
    }

    @Override
    public boolean updateBid(User user, Bid bid) {
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
                        str)
                .setFirstResult(first)
                .setMaxResults(5)
                .getResultList();
    }

    @Override
    public Bid getBid(Long id) {
        return entityManager.find(Bid.class, id);
    }
}
