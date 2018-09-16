package org.aram.dao;


import org.aram.domain.CafeVO;

import java.util.ArrayList;
import java.util.List;

public class CafeDAO {
    public List<CafeVO> getList() {
        final List<CafeVO> list = new ArrayList<CafeVO>();
        final String sql = "select * from tbl_menu order by mnum";          //sql문 뒤에 세미콜론 안됨

        new QueryExecutor() {
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    CafeVO vo = new CafeVO();
                    vo.setMnum(rs.getInt("mnum"));
                    vo.setMimg(rs.getString("mimg"));
                    vo.setMname(rs.getString("mname"));
                    vo.setMprice(rs.getInt("mprice"));
                    list.add(vo);
                }
            }
        }.executeAll();

        return list;
    }


}
