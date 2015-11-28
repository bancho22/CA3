package deploy;

import entity.User;
import facades.CurrencyFacade;
import facades.UserFacade;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
import schedule.XMLTask;
import security.PasswordHash;

//@WebListener
public class DeploymentConfiguration implements ServletContextListener {

    public static String PU_NAME = "CA3PU"; //USE the RIGHT name here
    private ScheduledExecutorService scheduler;

    public void contextInitialized(ServletContextEvent sce) {
        Map<String, String> env = System.getenv();     //If we are running in the OPENSHIFT environment change the pu-name     
        if (env.keySet().contains("OPENSHIFT_MYSQL_DB_HOST")) {
            PU_NAME = "pu_OPENSHIFT";
            try {
//                initOpenshiftTables();
                UserFacade uf = new UserFacade();

                User admin = new User();
                admin.setUserName("admin");
                admin.setPasswordHash(PasswordHash.createHash("test"));
                admin.addRole("User");
                admin.addRole("Admin");
                uf.addUser(admin);

                User user = new User();
                user.setUserName("user");
                user.setPasswordHash(PasswordHash.createHash("test"));
                user.addRole("User");
                uf.addUser(user);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(DeploymentConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeySpecException ex) {
                Logger.getLogger(DeploymentConfiguration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //scheduled task
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new XMLTask(new CurrencyFacade()), 0, 24, TimeUnit.HOURS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        scheduler.shutdownNow();
    }

//    private void initOpenshiftTables() throws NoSuchAlgorithmException, InvalidKeySpecException {
//        
//    }
}
