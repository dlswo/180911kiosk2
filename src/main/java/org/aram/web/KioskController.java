package org.aram.web;


import org.aram.dao.BillsDAO;
import org.aram.domain.OrderVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/kiosk")
public class KioskController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("member") == null){
            resp.sendRedirect("/login");
            return;
        }

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/menuList.jsp");

        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String[] ord = req.getParameterValues("ord");

        BillsDAO dao = new BillsDAO();
        int orderNum = dao.getOrderNum();

        List<OrderVO> orderVOList = new ArrayList<>();
        for (String str:ord) {

            OrderVO vo = new OrderVO(orderNum,str);
            if(vo.getQty() == 0){
                continue;
            }
            orderVOList.add(vo);
            dao.addBills(vo);
        }


        req.setAttribute("orderList", orderVOList);
        req.setAttribute("orderNum", orderNum);

        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/WEB-INF/orderResult.jsp");

        dispatcher.forward(req,resp);
    }
}