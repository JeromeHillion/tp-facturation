package fr.laerce.cinema;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import freemarker.template.TemplateException;
import model.Client;
import service.Database;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Create extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Database database = (Database) getServletContext().getAttribute("database");
        String id = httpServletRequest.getParameter("id");
        String nom = httpServletRequest.getParameter("nom");
        String prenom = httpServletRequest.getParameter("prenom");
        String loc = httpServletRequest.getParameter("loc");
        String pays = httpServletRequest.getParameter("pays");

        try{
            PreparedStatement statement = database.createClient(id, nom, prenom, loc, pays);
            statement.executeUpdate();

            //redirection
            httpServletResponse.sendRedirect("/clients.html");
        }
        catch (SQLException sqlexception){
            sqlexception.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Database database = (Database ) getServletContext().getAttribute("database");
        Template create = (Template) getServletContext().getAttribute("create");
        String id = httpServletRequest.getParameter("id");

        try{
            PreparedStatement statement = database.selectAllFromClientsByNum(id);
            ResultSet resultSet = statement.executeQuery();
            Client client = null;
            while (resultSet.next()){
                client = new Client(resultSet.getString("clt_num"),
                        resultSet.getString("clt_nom"),
                        resultSet.getString("clt_pnom"),
                        resultSet.getString("clt_loc"),
                        resultSet.getString("clt_pays"));
            }
            Map<String, Object> datas = new HashMap<>();
            datas.put("client", client);
            create.process(datas, httpServletResponse.getWriter())
        }
        catch (SQLException sqlexception){
            sqlexception.printStackTrace();
        }
        catch (TemplateException templateException) {
            templateException.printStackTrace();
        }
    }
}
