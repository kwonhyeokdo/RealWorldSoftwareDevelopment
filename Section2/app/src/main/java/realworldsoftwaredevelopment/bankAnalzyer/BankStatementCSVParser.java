package realworldsoftwaredevelopment.bankAnalzyer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public final class BankStatementCSVParser implements BankStatementParser{
    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public final BankTransaction parseFrom(final String line){
        final String[] columns = line.split(",");

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
        final String description = columns[2];

        BankTransaction bankTransaction = new BankTransaction(date, amount, description);

        return bankTransaction;
    }

    @Override
    public final List<BankTransaction> parseLinesFrom(final List<String> lines){
        final List<BankTransaction> bankTransactions = new ArrayList<>();
        for(final String line : lines){
            BankTransaction bankTransaction = parseFrom(line);
            bankTransactions.add(bankTransaction);
        }

        return bankTransactions;
    }
}