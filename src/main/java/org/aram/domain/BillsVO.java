package org.aram.domain;


import lombok.Data;

@Data
public class BillsVO {
    private int ono, mnum, mprice, qty;
    private String mname;
}
