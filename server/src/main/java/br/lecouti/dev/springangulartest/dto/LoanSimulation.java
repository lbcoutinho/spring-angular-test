package br.lecouti.dev.springangulartest.dto;

/**
 * DTO for Loan Simulation
 * 
 * @author Leandro Coutinho
 */
public class LoanSimulation {

	private Long clientId;
	private Double loanValue;
	private Integer numMonths;
	private String total;
	private String perMonth;

	public LoanSimulation() {
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Double getLoanValue() {
		return loanValue;
	}

	public void setLoanValue(Double loanValue) {
		this.loanValue = loanValue;
	}

	public Integer getNumMonths() {
		return numMonths;
	}

	public void setNumMonths(Integer numMonths) {
		this.numMonths = numMonths;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getPerMonth() {
		return perMonth;
	}

	public void setPerMonth(String perMonth) {
		this.perMonth = perMonth;
	}
}
