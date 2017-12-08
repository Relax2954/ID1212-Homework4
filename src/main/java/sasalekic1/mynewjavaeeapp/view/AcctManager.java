package sasalekic1.mynewjavaeeapp.view;

import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sasalekic1.mynewjavaeeapp.controller.Facade;
import sasalekic1.mynewjavaeeapp.model.ConverterAccountDTO;

/**
 * Handles all interaction with the convaccount JSF page.
 */
@Named("acctManager")
@ConversationScoped
public class AcctManager implements Serializable {

    @EJB
    private Facade cFacade;
    private ConverterAccountDTO currentAcct;
    private String CurrencyName1;
    private String CurrencyName2;
    private Float CurrencyRate1;
    private Float CurrencyRate2;
    private Float newAccountBalance;
    private Float transactionAmount;
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
            cFacade.convv(currentAcct.getAcctNo(), transactionAmount);
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
            currentAcct = cFacade.findAccount(searchedAcct);
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
            currentAcct = cFacade.createAccount(CurrencyName1,
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
    public void setTransactionAmount(Float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public Float getTransactionAmount() {
        return null;
    }

    /**
     * Set the value of newAccountBalance
     *
     * @param newAccountBalance new value of newAccountBalance
     */
    public void setNewAccountBalance(Float newAccountBalance) {
        this.newAccountBalance = newAccountBalance;
    }

    public void setCurrencyRate1(Float CurrencyRate1) {
        this.CurrencyRate1 = CurrencyRate1;
    }

    public void setCurrencyRate2(Float CurrencyRate2) {
        this.CurrencyRate2 = CurrencyRate2;
    }

    /**
     * Never used but JSF does not support write-only properties.
     */
    public Float getNewAccountBalance() {
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

    public Float getCurrencyRate1() {
        return null;
    }

    public Float getCurrencyRate2() {
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
