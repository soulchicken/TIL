package dev.bank.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.bank.domain.BankTransaction;

@Component
public class BankStatementCSVParser implements BankStatementParser {

	private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	// 한 줄만 파싱, 이 클래스 내에서만 사용할 예정
	// 접근제어자 반환타입 parseFromTSV (final String line)
	@Override
	public BankTransaction parseFrom(String line) {
		String[] columns = line.split(",");
		BankTransaction bankTransaction = new BankTransaction();

		bankTransaction.setDate(LocalDate.parse(columns[0], DATE_PATTERN));
		bankTransaction.setAmount(Integer.valueOf(columns[1]));
		bankTransaction.setDescription(columns[2]);
		return bankTransaction;
	}
	// 한 줄씩 파싱 후, 리스트에 추가
	// 접근제어자 List<BankTransaction> parseLinesFromTSV(List <String> lines) {}
	@Override
	public List<BankTransaction> parseLineFrom(List <String> lines) {
		List<BankTransaction> bankTransactions = new ArrayList<>();
		for (String line : lines) {
			bankTransactions.add(parseFrom(line));
		}
		return bankTransactions;
	}
}
