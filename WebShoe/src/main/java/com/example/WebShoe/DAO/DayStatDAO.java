package com.example.WebShoe.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.example.WebShoe.Model.DayStat;

public class DayStatDAO extends DAO{

	public DayStatDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<DayStat> selectStatByDay(Date st, Date et){
		List<DayStat> ds = new ArrayList<>();
		try (Connection con = getConnection();
				PreparedStatement ps = con.prepareStatement("select tblbill.paymenttime as day, sum(tblitembill.amount) as total, "
						+ "sum(tblitembill.price) + sum(distinct tblbill.fee) as revenue from tblbill, tblitembill "
						+ "where tblbill.id = tblitembill.bill_id and tblbill.paymenttime >= ? and tblbill.paymenttime <= ?"
						+ "group by tblbill.paymenttime order by tblbill.paymenttime")){
			ps.setDate(1, st);
			ps.setDate(2, et);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DayStat d = new DayStat();
				d.setDay(rs.getDate("day"));
				d.setAmount(rs.getInt("total"));
				d.setRevenue(rs.getInt("revenue"));
				ds.add(d);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ds;
	}
}
