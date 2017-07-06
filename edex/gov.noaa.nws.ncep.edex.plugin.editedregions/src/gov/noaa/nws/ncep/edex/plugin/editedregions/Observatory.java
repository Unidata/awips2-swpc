package gov.noaa.nws.ncep.edex.plugin.editedregions;

/**
 * Class representing an Observatory.
 * 
 *
 * <pre>
 * 
 * SOFTWARE HISTORY
 * 
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Nov 9, 2015  R9583       jtravis     Initial creation
 * 
 * </pre>
 * 
 * @author jtravis
 * @version 1.0
 */
public class Observatory {

    /**
     * The observatory 3 character code
     */
    private String code = null;

    /**
     * The local noon time
     */
    private int localNoon = 0;

    private boolean isRSTNSite = false;

    /**
     * 
     */
    public Observatory(String code, int localNoonTime, boolean isRSTNSite) {

        this.code = code;
        this.localNoon = localNoonTime;
        this.isRSTNSite = isRSTNSite;

    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the localNoon
     */
    public int getLocalNoon() {
        return localNoon;
    }

    /**
     * @return the isRSTNSite
     */
    public boolean isRSTNSite() {
        return isRSTNSite;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuffer buff = new StringBuffer();
        ;

        buff.append("<Observatory>");
        buff.append("<code>" + this.getCode() + "</code>");
        buff.append("<localnoon>" + this.getLocalNoon() + "</localnoon>");
        buff.append("<RSTNSite>" + this.isRSTNSite() + "</RSTNSite>");
        buff.append("</Observatory>");

        return buff.toString();
    }

}
