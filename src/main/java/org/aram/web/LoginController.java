package org.aram.web;



import org.aram.dao.MemberDAO;
import org.aram.domain.MemberVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/login.jsp");

        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        MemberDAO dao = new MemberDAO();
        List<MemberVO> memberVOList = dao.getList();
        System.out.println("Login post.....");
        String id = req.getParameter("id");
        String pw = req.getParameter("pw");
        System.out.println(id + " : " + pw);

        MemberVO vo = new MemberVO();
        vo.setId(id);
        vo.setPw(pw);
        for (MemberVO vo1:memberVOList) {
            if (id.equals(vo1.getId()) == true && pw.equals(vo1.getPw()) == true){
                resp.sendRedirect("/kiosk");
            }
        }

        HttpSession session = req.getSession();

        if (vo != null){
            session.setAttribute("member", vo);
        }
    }
}
