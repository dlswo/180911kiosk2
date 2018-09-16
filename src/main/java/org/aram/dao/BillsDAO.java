package org.aram.dao;


import org.aram.domain.OrderVO;

import java.util.ArrayList;
import java.util.List;


public class BillsDAO {

    public int getOrderNum(){
        final String sql ="select seq_order.nextval from dual";

        final StringBuilder result = new StringBuilder();

        new QueryExecutor() {
            @Override
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);

                rs = stmt.executeQuery();
                while(rs.next()){
                    result.append(rs.getInt(1));
                }

            }
        }.executeAll();
        return Integer.parseInt(result.toString());
    }

    public void addBills(final OrderVO vo) {
        final String sql = "insert into tbl_order (ono, mnum, qty)\n" +
                "values (?, ?, ?)";
        //mnum, score, cmt
        new QueryExecutor() {
            public void doJob() throws Exception {
                stmt = con.prepareStatement(sql);
                stmt.setInt(1, vo.getOno());
                stmt.setInt(2, vo.getMno());
                stmt.setInt(3, vo.getQty());
                stmt.executeUpdate();
            }
        }.executeAll();
    }

//    public List<OrderVO> calcBills(final int ono){
//        final String sql = "select ono, menu.mnum, mprice, qty, menu.mname\n" +
//                "from \n" +
//                "(select mprice,mnum,mname\n" +
//                "from tbl_menu ) menu,\n" +
//                "tbl_order bill\n" +
//                "where menu.mnum=bill.mnum\n" +
//                "and ono = ?";
//
//        final List<OrderVO> list = new ArrayList<>();
//
//
//        new QueryExecutor() {
//            @Override
//            public void doJob() throws Exception {
//
//                stmt = con.prepareStatement(sql);
//                stmt.setInt(1,ono);
//                rs = stmt.executeQuery();
//
//                while(rs.next()){
//                    OrderVO vo1 = new OrderVO();
//                    vo1.setOno(rs.getInt("ono"));
//                    vo1.setMno(rs.getInt("mno"));
//                    vo1.setMname(rs.getString("mname"));
//                    vo1.setMprice(rs.getInt("mprice"));
//                    vo1.setQty(rs.getInt("qty"));
//                    vo1.setSumprice(rs.getInt("qty")*rs.getInt("mprice"));
//                    list.add(vo1);
//                }
//
//            }
//        }.executeAll();
//        return list;
//
//
//    }
}
