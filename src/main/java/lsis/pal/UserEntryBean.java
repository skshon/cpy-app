package lsis.pal;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class UserEntryBean {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void addUserEntry(UserEntry user) { entityManager.persist(user);}

    public UserEntry find(long id) { return entityManager.find(UserEntry.class, id); }

    public List<UserEntry> getUserEntries() {
        CriteriaQuery<UserEntry> cq = entityManager.getCriteriaBuilder().createQuery(UserEntry.class);
        cq.select(cq.from(UserEntry.class));
        return entityManager.createQuery(cq).getResultList();
    }

    @Transactional
    public void deleteUserEntry(UserEntry userEntry) {
        entityManager.remove(userEntry);
    }

    @Transactional
    public void updateUserEntry(UserEntry userEntry) {
        entityManager.merge(userEntry);
    }
}
