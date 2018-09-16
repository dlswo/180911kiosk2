package org.aram.listener;



import org.aram.dao.CafeDAO;
import org.aram.domain.CafeVO;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//메뉴리스트를 공유해 주는 애

@WebListener
public class MenuLoader implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        CafeDAO dao = new CafeDAO();
        List<CafeVO> CafeVOList = dao.getList();
        Map<Integer, CafeVO> CafeVOMap = new HashMap<>();


        for (int i = 0; i < CafeVOList.size(); i++){
            CafeVOMap.put(i + 1, CafeVOList.get(i));
        }

//        for(int i = 0; i < 10; i++){
//            CafeVO vo = new CafeVO();
//            CafeVOList.add(vo);
//            CafeVOMap.put(i, vo);
//        }
        sce.getServletContext().setAttribute("menuList", CafeVOList);
        sce.getServletContext().setAttribute("menuMap", CafeVOMap);
    }
}
