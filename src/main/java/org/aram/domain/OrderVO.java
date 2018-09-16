package org.aram.domain;

import lombok.Data;

@Data
public class OrderVO {

    private int ono;
    private int mno;
    private int qty;

    public OrderVO(int ono,String str){
        String[] arr = str.split("_");
        if(arr.length != 2){
            return;
        }
        this.ono = ono;
        this.mno = Integer.parseInt(arr[0]);
        this.qty = Integer.parseInt(arr[1]);
    }
}