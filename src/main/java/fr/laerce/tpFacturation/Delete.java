package fr.laerce.tpFacturation;


import freemarker.template.Template;
import freemarker.template.TemplateException;
import model.Clients;
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

public class Delete  extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Database database = (Database) getServletContext().getAttribute("database");
        String id = httpServletRequest.getParameter("id");

        try{
            //requête SQL de type DELETE
            PreparedStatement preparedStatement = database.deleteClient(id);
            preparedStatement.executeUpdate();

        }

        catch(SQLException sqlexception){
            sqlexception.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        Database database = (Database) getServletContext().getAttribute("database");
        Template delete = (Template) getServletContext().getAttribute("delete");
        String id = httpServletRequest.getParameter("id");

        try{
            //requête SQL de type SELECT
            PreparedStatement preparedStatement = database.selectAllFromClientsByNum(id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Clients client = null;
            while( resultSet.next()){
                client = new Clients(resultSet.getString("clt_num"),
                       resultSet.getString("clt_nom"),
                        resultSet.getString("clt_pnom"),
                        resultSet.getString("clt_loc"),
                        resultSet.getString("clt_pays"));
            }
            Map<String, Object> datas = new HashMap<>();
            datas.put("client", client);
            delete.process(datas, httpServletResponse.getWriter());
        }

        catch(SQLException sqlexception){
            sqlexception.printStackTrace();
        }
        catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
