
package sasalekic1.mynewjavaeeapp.integration;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import  sasalekic1.mynewjavaeeapp.model.ConverterAccount;

/**
 * Handles all interaction with the entity manager. No code outside of this class, except for the
 * JPA entities, shall have anything to do with JPA.
 */
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless
public class ConverterDAO {
    @PersistenceContext(unitName = "converterPU")
    private EntityManager em;

    /**
     * Searches for the convaccount with the specified convaccount number.
     *
     * @param acctNo The number of the searched convaccount.
     * @return The convaccount with the specified number, if such an convaccount was found.
     * @throws EntityNotFoundException If no convaccount with the specified number was found.
     */
    public ConverterAccount findAccountByAcctNo(int acctNo) {
        ConverterAccount convaccount = em.find(ConverterAccount.class, acctNo);
        if (convaccount == null) {
            throw new EntityNotFoundException("No convaccount with number " + acctNo);
        }
        return convaccount;
    }
    
    /**
     * Stores the specified convaccount in the database.
     * @param acct The convaccount to store.
     */
    public void storeAccount(ConverterAccount acct) {
        em.persist(acct);
    }

}
