package sasalekic1.mynewjavaeeapp.view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sasalekic1.mynewjavaeeapp.controller.CashierFacade;
import sasalekic1.mynewjavaeeapp.model.ConverterAccountDTO;

/**
 * Handles all interaction with the convaccount JSF page.
 */
@Named("acctManager")
@ConversationScoped
public class AcctManager implements Serializable {

    @EJB
    private CashierFacade cashierFacade;
    private ConverterAccountDTO currentAcct;
    private String CurrencyName1;
    private String CurrencyName2;
    private Integer CurrencyRate1;
    private Integer CurrencyRate2;
    private Integer newAccountBalance;
    private Integer transactionAmount;
    private Integer searchedAcct;
    private Exception transactionFailure;
    @Inject
    private Conversation conversation;

    private void startConversation() {
        if (conversation.isTransient()) {
            conversation.begin();
        }
    }

    private void stopConversation() {
        if (!conversation.isTransient()) {
            conversation.end();
        }
    }

    private void handleException(Exception e) {
        stopConversation();
        e.printStackTrace(System.err);
        transactionFailure = e;
    }

    private void readAcctData() {
        searchedAcct = currentAcct.getAcctNo();
        findAccount();
    }

    /**
     * @return <code>true</code> if the latest transaction succeeded, otherwise
     * <code>false</code>.
     */
    public boolean getSuccess() {
        return transactionFailure == null;
    }

    /**
     * Returns the latest thrown exception.
     */
    public Exception getException() {
        return transactionFailure;
    }

    public void convv() {
        try {
            transactionFailure = null;
            cashierFacade.convv(currentAcct.getAcctNo(), transactionAmount);
            readAcctData();
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Searches for the convaccount specified by the latest call to
     * <code>setSearchedAcct</code>.
     */
    public void findAccount() {
        try {
            startConversation();
            transactionFailure = null;
            currentAcct = cashierFacade.findAccount(searchedAcct);
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Creates a new conversion convaccount.
     */
    public void createAccount() {
        try {
            startConversation();
            transactionFailure = null;
            currentAcct = cashierFacade.createAccount(CurrencyName1,
                    CurrencyName2, CurrencyRate1, CurrencyRate2, 0);
        } catch (Exception e) {
            handleException(e);
        }
    }

    /**
     * Set the value of searchedAcct
     *
     * @param searchedAcct new value of searchedAcct
     */
    public void setSearchedAcct(Integer searchedAcct) {
        this.searchedAcct = searchedAcct;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public Integer getSearchedAcct() {
        return null;
    }

    /**
     * Set the value of transactionAmount
     *
     * @param transactionAmount new value of transactionAmount
     */
    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public Integer getTransactionAmount() {
        return null;
    }

    /**
     * Set the value of newAccountBalance
     *
     * @param newAccountBalance new value of newAccountBalance
     */
    public void setNewAccountBalance(Integer newAccountBalance) {
        this.newAccountBalance = newAccountBalance;
    }

    public void setCurrencyRate1(Integer CurrencyRate1) {
        this.CurrencyRate1 = CurrencyRate1;
    }

    public void setCurrencyRate2(Integer CurrencyRate2) {
        this.CurrencyRate2 = CurrencyRate2;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public Integer getNewAccountBalance() {
        return null;
    }

    /**
     * Set the value of CurrencyName2
     *
     * @param CurrencyName2
     */
    public void setCurrencyName2(String CurrencyName2) {
        this.CurrencyName2 = CurrencyName2;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getCurrencyName2() {
        return null;
    }

    /**
     * Set the value of CurrencyName1
     *
     * @param CurrencyName1
     */
    public void setCurrencyName1(String CurrencyName1) {
        this.CurrencyName1 = CurrencyName1;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public String getCurrencyName1() {
        return null;
    }

    public Integer getCurrencyRate1() {
        return null;
    }

    public Integer getCurrencyRate2() {
        return null;
    }

    /**
     * Get the value of currentAcct
     *
     * @return the value of currentAcct
     */
    public ConverterAccountDTO getCurrentAcct() {
        return currentAcct;
    }
}
