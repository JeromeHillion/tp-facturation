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

public class Update extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        Database database = (Database) getServletContext().getAttribute("database");
//        Connection conn = db.getConnection();

        String id = httpServletRequest.getParameter("id");
        String nom = httpServletRequest.getParameter("nom");
        String prenom = httpServletRequest.getParameter("pnom");
        String loc = httpServletRequest.getParameter("loc");
        String pays = httpServletRequest.getParameter("pays");

        try {

            // UPDATE --------------------------------------------------------------------------------------------------

            PreparedStatement statement = database.updateClient(id, nom, prenom, loc, pays);
            statement.executeUpdate();

            // redirection
            httpServletResponse.sendRedirect("/clients");

        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {

        Database db = (Database) getServletContext().getAttribute("db");
//        Connection conn = db.getConnection();
        Template update = (Template) getServletContext().getAttribute("update");

        String id = httpServletRequest.getParameter("id");

        try {

//            Statement statement = conn.createStatement();

            // SELECT --------------------------------------------------------------------------------------------------
//            String query = "SELECT clt_num, clt_nom, clt_pnom, clt_loc, clt_pays FROM clients WHERE clt_num = '" + id + "'";
//            ResultSet rs = statement.executeQuery(query);
            PreparedStatement statement = db.selectAllFromClientsByNum(id);
            ResultSet resultSet = statement.executeQuery();
            Clients client = null;
            while (resultSet.next()) {
                client = new Clients(resultSet.getString("clt_num"),
                        resultSet.getString("clt_nom"),
                        resultSet.getString("clt_pnom"),
                        resultSet.getString("clt_loc"),
                        resultSet.getString("clt_pays"));
            }

            Map<String, Object> datas = new HashMap<>();
            datas.put("client", client);
            update.process(datas, httpServletResponse.getWriter());



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }


}