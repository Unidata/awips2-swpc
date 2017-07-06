package gov.noaa.nws.ncep.edex.swpcrefdb.dao;

import java.util.Vector;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;

import com.raytheon.uf.edex.database.dao.CoreDao;
import com.raytheon.uf.edex.database.dao.DaoConfig;

import gov.noaa.nws.ncep.common.swpcrefdb.ReportStatus;

/**
 * Provides access to the SWPC_REPORT_STATUS database table.
 * 
 * <pre>
 *
 * SOFTWARE HISTORY
 *
 * Date         Ticket#    Engineer    Description
 * ------------ ---------- ----------- --------------------------
 * Jan 12, 2016  NA     jtravis     Initial creation
 *
 * </pre>
 *
 * @author jtravis
 * @version 1.0
 */
public class ReportStatusDao extends CoreDao {

    /**
     * Creates a new ReportStatusDao
     */
    public ReportStatusDao() {
        super(DaoConfig.forClass(ReportStatusDao.class));

    }

    /**
     * Retrieves Vector of all ReportStatus instances
     * 
     * @param type
     *            the ReportStatus
     * @return Vector of ReportStatus instances
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Vector<ReportStatus> getAllReportStatuses() {
        return (Vector<ReportStatus>) txTemplate
                .execute(new TransactionCallback() {
                    @Override
                    public Object doInTransaction(TransactionStatus status) {
                        Session sess = getCurrentSession();
                        Criteria crit = sess.createCriteria(ReportStatus.class);

                        Vector<ReportStatus> results = new Vector<ReportStatus>();
                        results.addAll(crit.list());

                        return results;
                    }
                });
    }

}
