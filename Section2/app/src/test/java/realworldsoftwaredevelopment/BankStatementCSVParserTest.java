package realworldsoftwaredevelopment;


import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import realworldsoftwaredevelopment.bankAnalzyer.BankStatementCSVParser;
import realworldsoftwaredevelopment.bankAnalzyer.BankStatementParser;
import realworldsoftwaredevelopment.bankAnalzyer.BankTransaction;

public final class BankStatementCSVParserTest {
    private final BankStatementParser csvParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() throws Exception{
        final String line = "2023-01-30,-50,Tesco";
        final BankTransaction bankTransaction = csvParser.parseFrom(line);

        final LocalDate date = LocalDate.of(2023, 1, 30);
        final double amount = -50L;
        final String description = "Tesco";
        final BankTransaction compareTarget = new BankTransaction(date, amount, description);

        Assertions.assertEquals(bankTransaction.getDate(), compareTarget.getDate());
        Assertions.assertEquals(bankTransaction.getAmount(), compareTarget.getAmount());
        Assertions.assertEquals(bankTransaction.getDescription(), compareTarget.getDescription());
    }
}
