package sasalekic1.mynewjavaeeapp.controller;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityNotFoundException;
import sasalekic1.mynewjavaeeapp.integration.ConverterDAO;
import sasalekic1.mynewjavaeeapp.model.ConverterAccount;
import sasalekic1.mynewjavaeeapp.model.NegativeVException;
import sasalekic1.mynewjavaeeapp.model.ConverterAccountDTO;

/**
 * A controller. All calls to the model that are executed because of an action
 * taken by the cashier pass through here.
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class CashierFacade {

    @EJB
    ConverterDAO converterDB;

    /**
     * Creates a new converter with the specified data.
     *
     * @param CurrName1
     * @param CurrName2
     * @param curr1rate
     * @param curr2rate
     * @param balance
     */
    public ConverterAccountDTO createAccount(String CurrName1, String CurrName2, int curr1rate, int curr2rate, int balance) {
        ConverterAccount newAcct = new ConverterAccount(balance, CurrName1, CurrName2, curr1rate, curr2rate);
        converterDB.storeAccount(newAcct);
        return newAcct;
    }

    /**
     * Search for the specified converter(convaccount).
     *
     * @param acctNo The convaccount number of the searched converter.
     * @return The convaccount if it was found.
     * @throws EntityNotFoundException If the convaccount was not found.
     */
    public ConverterAccountDTO findAccount(int acctNo) {
        return converterDB.findAccountByAcctNo(acctNo);
    }

    public void convv(int acctNo, int amount) throws NegativeVException {
        ConverterAccount acct = converterDB.findAccountByAcctNo(acctNo);
        acct.convv(amount);
    }

}
