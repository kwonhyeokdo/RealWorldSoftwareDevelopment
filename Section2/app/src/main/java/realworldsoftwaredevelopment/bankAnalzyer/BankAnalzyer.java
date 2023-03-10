package realworldsoftwaredevelopment.bankAnalzyer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

public final class BankAnalzyer {
    public final void analyze(final String filePath) throws IOException{
        final Path path = Paths.get(filePath);
        final List<String> lines = Files.readAllLines(path);

        final BankStatementParser bankStatementParser = new BankStatementCSVParser();
        final List<BankTransaction> bankStatement = bankStatementParser.parseLinesFrom(lines);
        final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankStatement);
        
        collectSummary(bankStatementProcessor);
    }

    public final void collectSummary(BankStatementProcessor bankStatementProcessor){
        System.out.println("모든 트랜잭션의 합계: " + bankStatementProcessor.calculateTotalAmount());
        System.out.println("1월의 트랜잭션 합계: " + bankStatementProcessor.calculateTotalInMonth(Month.JANUARY));
        System.out.println("2월의 트랜잭션 합계: " + bankStatementProcessor.calculateTotalInMonth(Month.FEBRUARY));
        System.out.println("salary의 합계: " + bankStatementProcessor.calculateTotalForCategory("Salary"));
        System.out.println("1월의 최대 금액: " + bankStatementProcessor.getMaxAmountInMonth(Month.JANUARY, Month.JANUARY));
        System.out.println("1월~2월의 최소 금액: " + bankStatementProcessor.getMinAmountInMonth(Month.JANUARY, Month.FEBRUARY));
        System.out.println("6월~12월의 최소 금액: " + bankStatementProcessor.getMinAmountInMonth(Month.JUNE, Month.DECEMBER));
        bankStatementProcessor.showDescriptionHistogramInMonth("Tesco");
    }
}
