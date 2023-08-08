package br.ce.wcaquino.taskbackend.utils;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class DateUtilsTest {

	@Test
	public void deveRetornarTrueParaDatasFuturas() {
		LocalDate data = LocalDate.of(2030, 8, 8);
		assertTrue(DateUtils.isEqualOrFutureDate(data));
	}
	
	@Test
	public void deveRetornarTrueParaDataAtual() {
		LocalDate data = LocalDate.now();
		assertTrue(DateUtils.isEqualOrFutureDate(data));
	}
	
	@Test
	public void deveRetornarFalseParaDatasPassadas() {
		LocalDate data = LocalDate.of(2010, 8, 8);
		assertFalse(DateUtils.isEqualOrFutureDate(data));
	}

}
