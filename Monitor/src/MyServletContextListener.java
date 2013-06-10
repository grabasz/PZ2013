import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener implements ServletContextListener {

    private SensorReciver myThread = null;

    public void contextInitialized(ServletContextEvent sce) {
        if ((myThread == null) || (!myThread.isAlive())) {
            myThread = new SensorReciver();
            myThread.start();
        }
    }

    public void contextDestroyed(ServletContextEvent sce){
        try {
            //myThread.doShutdown();
            myThread.interrupt();
        } catch (Exception ex) {
        }
    }
}