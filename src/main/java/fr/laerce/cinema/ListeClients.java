package fr.laerce.cinema;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListeClients extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        super.doGet(httpServletRequest, httpServletResponse);

        Database database = (Database) getServletContext().getAttribute("database");
        Template listeClients = (Template) getServletContext().getAttribute("listeClients");

        try {
            // requête SQL de type SELECT
            PreparedStatement preparedStatement = database.selectAllFromClients();
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Client> clients = new ArrayList<>();
            while (resultSet.next()) {
                clients.add(new Client(resultSet.getString("clt_num"),
                        resultSet.getString("clt_nom"),
                        resultSet.getString("clt_pnom"),
                        resultSet.getString("clt_loc"),
                        resultSet.getString("clt_pays")));
            }

            Map<String, Object> datas = new HashMap<>();
            datas.put("clients", clients);
            listeClients.process(datas, httpServletRequest.getWriter());
        }
        catch(SQLException sqlexception){
            sqlexception.printStackTrace();
        }
        catch (TemplateException templateException){
            templateException.printStackTrace();
        }
    }
}
