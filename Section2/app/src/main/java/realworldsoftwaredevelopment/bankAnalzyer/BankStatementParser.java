package realworldsoftwaredevelopment.bankAnalzyer;

import java.util.List;

public interface BankStatementParser {
    public BankTransaction parseFrom(final String line);
    public List<BankTransaction> parseLinesFrom(final List<String> lines);
}