package sasalekic1.mynewjavaeeapp.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * A persistent representation of an convaccount.
 */
@Entity
public class ConverterAccount implements ConverterAccountDTO, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int acctNo;
    private float balance;
    private String CurrName1;
    private String CurrName2;
    private float curr1rate;
    private float curr2rate;

    /**
     * Creates a new instance of Account
     */
    public ConverterAccount() {
    }

    /**
     * Creates a new instance of Account
     *
     * @param balance
     * @param CurrName1
     * @param CurrName2
     * @param curr1rate
     * @param curr2rate
     */
    public ConverterAccount(float balance, String CurrName1, String CurrName2, float curr1rate, float curr2rate) {
        this.balance = balance;
        this.CurrName1 = CurrName1;
        this.CurrName2 = CurrName2;
        this.curr1rate = curr1rate;
        this.curr2rate = curr2rate;
    }

    @Override
    public String getCurrName2() {
        return CurrName2;
    }

    @Override
    public String getCurrName1() {
        return CurrName1;
    }

    /**
     * Get the value of balance
     *
     * @return the value of balance
     */
    @Override
    public float getBalance() {
        return balance;
    }

    /**
     * Get the value of convaccount number.
     *
     * @return the value of convaccount number.
     */
    @Override
    public int getAcctNo() {
        return acctNo;
    }

    @Override
    public float getcurr1rate() {
        return curr1rate;
    }

    @Override
    public float getcurr2rate() {
        return curr1rate;
    }

    public void convv(float amount) throws NegativeVException {
        if (amount < 0) {
            throw new NegativeVException(
                    "Amount must be positive!");
        }
        balance = curr2rate * amount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        return new Integer(acctNo).hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ConverterAccount)) {
            return false;
        }
        ConverterAccount other = (ConverterAccount) object;
        return this.acctNo == other.acctNo;
    }

    @Override
    public String toString() {
        return "converter.model.Account[id=" + acctNo + "]";
    }
}
