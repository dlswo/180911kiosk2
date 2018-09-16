package org.aram.dao;



import org.aram.domain.MemberVO;

import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    public List<MemberVO> getList() {
        final List<MemberVO> list = new ArrayList<>();
        final String sql = "select * from tbl_member";          //sql문 뒤에 세미콜론 안됨

        new QueryExecutor() {
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    MemberVO vo = new MemberVO();
                    vo.setId(rs.getString("id"));
                    vo.setPw(rs.getString("pw"));
                    vo.setName(rs.getString("name"));
                    vo.setAddr(rs.getString("addr"));
                    list.add(vo);
                }
            }
        }.executeAll();

        return list;
    }
}
