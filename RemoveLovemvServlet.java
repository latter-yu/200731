package OnlineMusic.servlet;

import OnlineMusic.Dao.MVDao;
import OnlineMusic.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/GaoBoMusic/removeLovemvServlet")
public class RemoveLovemvServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");

        String idStr = req.getParameter("id");
        int mvId = Integer.parseInt(idStr);

        User user = (User) req.getSession().getAttribute("user");
        int user_id = user.getId();

        MVDao mvDao = new MVDao();
        int ret = mvDao.removeLovemv(user_id, mvId);

        Map<String ,Object> return_map = new HashMap<>();

        if(ret == 1) {
            return_map.put("msg",true);
        }else {
            return_map.put("msg",false);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getWriter(),return_map);
    }
}
