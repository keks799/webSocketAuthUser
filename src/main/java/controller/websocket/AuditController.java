package controller.websocket;

import model.entity.User;
import org.apache.log4j.Logger;
import org.websocket.dao.AuditManager;
import org.websocket.dao.UserManager;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Business_Book on 10.04.2016.
 */
public class AuditController {

    @Inject
    AuditManager auditManager;

    @Inject
    UserManager userManager;

    @Inject
    UserController userController;

    private static final Logger logger = Logger.getLogger(AuditController.class);

    public List getAllAudit() {
        return auditManager.getAllAudit();
    }

    public List getAuditForUser(String userIdentifier) {
        List<User> users = userController.getUsers(userIdentifier);
        String usersId = "";
        String separator = ",";
        for(User user : users) {
            usersId += user.getId() + separator;
        }
        usersId = usersId.substring(0, usersId.lastIndexOf(separator));
        return auditManager.getAuditForUserEmail(usersId);
    }
}
