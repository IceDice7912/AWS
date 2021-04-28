package com.mulcam.ai.web.vo;

import java.io.Serializable;
import java.util.Date;

public class OrderVO implements Serializable {

	private String ISBN, ID;
	private Long ORDERNO, TOTAL_ORDERPRICE, ORDERQUANTITY, ORDER_GROUP_NO;
	private Date orderdate;

	public OrderVO(String iSBN, String iD, Long oRDERQUANTIT, Long order_group_no) {
		this(iSBN, iD, oRDERQUANTIT);
		setORDER_GROUP_NO(order_group_no);
	}

	public OrderVO(String iSBN2, String iD2, Long oRDERQUANTIT) {
		super();
		setISBN(iSBN2);
		setID(iD2);
		setORDERQUANTITY(oRDERQUANTIT);
	}

	public OrderVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Long getORDERNO() {
		return ORDERNO;
	}

	public void setORDERNO(Long oRDERNO) {
		ORDERNO = oRDERNO;
	}

	public Long getTOTAL_ORDERPRICE() {
		return TOTAL_ORDERPRICE;
	}

	public void setTOTAL_ORDERPRICE(Long tOTAL_ORDERPRICE) {
		TOTAL_ORDERPRICE = tOTAL_ORDERPRICE;
	}

	public Long getORDERQUANTITY() {
		return ORDERQUANTITY;
	}

	public void setORDERQUANTITY(Long oRDERQUANTITY) {
		ORDERQUANTITY = oRDERQUANTITY;
	}

	public Long getORDER_GROUP_NO() {
		return ORDER_GROUP_NO;
	}

	public void setORDER_GROUP_NO(Long oRDER_GROUP_NO) {
		ORDER_GROUP_NO = oRDER_GROUP_NO;
	}

	public Date getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Date orderdate) {
		this.orderdate = orderdate;
	}

	@Override
	public String toString() {
		return "OrderVO [ISBN=" + ISBN + ", ID=" + ID + ", ORDERNO=" + ORDERNO + ", TOTAL_ORDERPRICE="
				+ TOTAL_ORDERPRICE + ", ORDERQUANTITY=" + ORDERQUANTITY + ", ORDER_GROUP_NO=" + ORDER_GROUP_NO
				+ ", orderdate=" + orderdate + "]";
	}

}
