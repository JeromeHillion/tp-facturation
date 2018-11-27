package service;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;

import javax.security.auth.login.Configuration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

public class ServletListener implements ServletContextListener {

    private Template listeClients;
    private Template create;
    private Template delete;
    private Template update;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();

        String user = servletContext.getInitParameter("user");
        String password = servletContext.getInitParameter("password");
        String nameBDD = servletContext.getInitParameter("nameBDD");
        String host = servletContext.getInitParameter("host");
        String port = servletContext.getInitParameter("port");
        Database database = new Database("jdbc:postgresql://" + host + ":" + port + "/" + nameBDD, user, password);
        servletContext.setAttribute("database", database);

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_25);
        configuration.setServletContextForTemplateLoading(servletContext,"/WEB-INF/templates");
        configuration.setDefaultEncoding("UTF8");

        try {
            listeClients = configuration.getTemplate("client.ftl");
            servletContext.setAttribute("listeClients", listeClients);
            create = configuration.getTemplate("create.ftl");
            servletContext.setAttribute("create", create);
            delete = configuration.getTemplate("delete.ftl");
            servletContext.setAttribute("delete", delete);
            update = configuration.getTemplate("update.ftl");
            servletContext.setAttribute("update", update);
        }

        catch (IOException ioexception){
            ioexception.printStackTrace();
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
