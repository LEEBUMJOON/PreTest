package com.KakaopayPreTest.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.KakaopayPreTest.insurance.domain.Amount;

public interface InstituteAmountRepository extends JpaRepository< Amount , Long> {
	
	
	@Query(value = "SELECT AMT.YEAR, SUM(AMT.AMOUNT) \n"+
											"FROM AMOUNT AS AMT \n"+
											"GROUP BY AMT.YEAR",  nativeQuery =  true)
	List<Object[]> getAmountTotalYear();
	
	
	@Query(value = "SELECT AMT.YEAR, INS.NAME, SUM(AMT.AMOUNT) \n" +
										   "FROM AMOUNT AS AMT INNER JOIN INSTITUTE AS INS \n" +
										   "ON AMT.INSTITUTIE_ID  = INS.ID \n"+
										   "GROUP BY AMT.YEAR,  INS.NAME \n" + 
										   "ORDER BY SUM(AMT.AMOUNT) DESC",  nativeQuery =  true)
	List<Object[]> getDetailAmount();
	
	
	@Query(value =  "SELECT AMT.YEAR,  INS.CODE, AVG(AMT.AMOUNT)\n" + 
					             			"FROM AMOUNT AS AMT INNER JOIN INSTITUTE AS INS \n" + 
					             		    "ON AMT.INSTITUTIE_ID  = INS.ID\n" + 
					             		   "WHERE INS.CODE = ?1 \n" + 
					             		   "GROUP BY AMT.YEAR,  INS.CODE, INS.NAME \n" + 
					             		   "ORDER BY AVG(AMT.AMOUNT) ASC" , nativeQuery =  true)
	List<Object[]> getInstitueMinMaxAvg(String bankCode);
}
