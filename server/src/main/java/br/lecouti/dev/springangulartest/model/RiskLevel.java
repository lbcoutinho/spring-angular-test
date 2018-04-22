package br.lecouti.dev.springangulartest.model;

/**
 * Risk level enum. <br>
 * C = income <= 2000 <br>
 * B = income > 2000 && income <= 8000 <br>
 * A = income > 8000
 * 
 * @author Leandro Coutinho
 *
 */
public enum RiskLevel {
	A, B, C;
}
