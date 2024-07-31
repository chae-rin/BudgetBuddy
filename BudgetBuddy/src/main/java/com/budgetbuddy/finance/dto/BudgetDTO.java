package com.budgetbuddy.finance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BudgetDTO {
	private String record_id;
	private String user_id;
	private String record_type;
	private String record_dtm;
	private String record_detail;
	private String record_cd;
	private String record_out_payment_cd;
	private String record_memo;
	private String record_del_yn;
	private String upd_dt;
	private String upd_user_id;
	private int monthly_expend_sum;
	private int monthly_income_sum;
}
