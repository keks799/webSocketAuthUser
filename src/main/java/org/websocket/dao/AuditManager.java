package org.websocket.dao;

import model.dto.AuditDTO;
import org.apache.log4j.Logger;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Business_Book on 10.04.2016.
 */

@Stateless
public class AuditManager extends AbstractManager implements Serializable {

    private static final Logger logger = Logger.getLogger(AuditManager.class);

    public List getAllAudit() {
        Query query = manager.createNativeQuery(
                "SELECT u.login email, t.token_guid token, t.expiration_date expireDate, t.active active, ta.date dateOfIssue " +
                "FROM token_audit ta JOIN user u on u.id = ta.user_id " +
                "JOIN token t on t.id = ta.token_id", AuditDTO.class
        );
        return getSafeResultList(query);
    }

    public List getAuditForUserEmail(String ids) {
        Query query = manager.createNativeQuery(
                "SELECT u.login email, t.token_guid token, t.expiration_date expireDate, t.active active, ta.date dateOfIssue " +
                        "FROM token_audit ta JOIN user u on u.id = ta.user_id " +
                        "JOIN token t on t.id = ta.token_id " +
                        "WHERE u.id in :ids", AuditDTO.class
        );
        query.setParameter("ids", ids);
        return getSafeResultList(query);
    }
}
