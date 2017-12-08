package sasalekic1.mynewjavaeeapp.model;

/**
 * The views read-only view of an Account.
 */
public interface ConverterAccountDTO {

    /**
     * Gets the number of this Conversion Account.
     *
     * @return the convaccount number.
     */
    int getAcctNo();

    int getBalance();

    int getcurr1rate();

    int getcurr2rate();

    String getCurrName1();

    String getCurrName2();

}
