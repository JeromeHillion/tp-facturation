package fr.laerce.tpFacturation;


import model.Clients;
import service.Database;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListeClients extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {


        Database database = (Database) getServletContext().getAttribute("database");
        Template listeClients = (Template) getServletContext().getAttribute("listeClients");

        try {
            // requête SQL de type SELECT
            PreparedStatement preparedStatement = database.selectAllFromClients();
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Clients> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(new Clients(resultSet.getString("clt_num"),
                        resultSet.getString("clt_nom"),
                        resultSet.getString("clt_pnom"),
                        resultSet.getString("clt_loc"),
                        resultSet.getString("clt_pays")));
            }

            Map<String, Object> datas = new HashMap<>();
            datas.put("clients", clients);
            listeClients.process(datas, httpServletResponse.getWriter());
        }
        catch(SQLException sqlexception){
            sqlexception.printStackTrace();
        }
        catch (TemplateException templateException){
            templateException.printStackTrace();
        }
    }
}
