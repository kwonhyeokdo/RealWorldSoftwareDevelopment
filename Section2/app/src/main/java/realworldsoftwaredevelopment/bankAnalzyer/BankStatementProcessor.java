package realworldsoftwaredevelopment.bankAnalzyer;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public final class BankStatementProcessor{
    private List<BankTransaction> bankStatement;

    public BankStatementProcessor(List<BankTransaction> bankStatement){
        this.bankStatement = bankStatement;
    }

    public final Double calculateTotalAmount(){
        if(bankStatement.isEmpty()){
            return null;
        }else{
            double totalAmount = 0d;
            for(final BankTransaction bankTransaction : bankStatement){
                totalAmount += bankTransaction.getAmount();
            }
            return totalAmount;
        }
    }

    public final Double calculateTotalInMonth(final Month month){
        if(bankStatement.isEmpty()){
            return null;
        }else{
            double totalAmount = 0d;
            for(final BankTransaction bankTransaction : bankStatement){
                final LocalDate date = bankTransaction.getDate();
                if(date.getMonth() == month){
                    totalAmount += bankTransaction.getAmount();
                }
            }
            return totalAmount;
        }
    }

    public final Double calculateTotalForCategory(final String category){
        if(bankStatement.isEmpty()){
            return null;
        }else{
            double totalAmount = 0d;
            for(final BankTransaction bankTransaction : bankStatement){
                final String description = bankTransaction.getDescription();
                if(category.equals(description)){
                    totalAmount += bankTransaction.getAmount();
                }
            }
            return totalAmount;
        }
    }

    public final BankTransaction getMaxAmountInMonth(final Month from, final Month to){
        BankTransaction maxBankTransaction = null;
        double maxBankStatementAmount = Double.NEGATIVE_INFINITY;
        
        for(final BankTransaction bankTransaction : bankStatement){
            final Month bankTransactionMonth = bankTransaction.getDate().getMonth();
            final double bankTransactionAmount = bankTransaction.getAmount();
            if(bankTransactionMonth.getValue() >= from.getValue() &&
                bankTransactionMonth.getValue() <= to.getValue()
            ){
                if(maxBankStatementAmount < bankTransactionAmount){
                    maxBankStatementAmount = bankTransactionAmount;
                    maxBankTransaction = bankTransaction;
                }
            }
        }
        return maxBankTransaction;
    }

    public final BankTransaction getMinAmountInMonth(final Month from, final Month to){
        BankTransaction minBankTransaction = null;
        double minBankStatementAmount = Double.POSITIVE_INFINITY;
        for(final BankTransaction bankTransaction : bankStatement){
            final Month bankTransactionMonth = bankTransaction.getDate().getMonth();
            final double bankTransactionAmount = bankTransaction.getAmount();
            if(bankTransactionMonth.getValue() >= from.getValue() &&
                bankTransactionMonth.getValue() <= to.getValue()
            ){
                if(minBankStatementAmount > bankTransactionAmount){
                    minBankStatementAmount = bankTransactionAmount;
                    minBankTransaction = bankTransaction;
                }
            }
        }

        return minBankTransaction;
    }

    public final void showDescriptionHistogramInMonth(final String description){
        if(bankStatement.isEmpty()){
            System.out.println(description + " 항목의 입출금 내역이 없습니다.");
            return;
        }else{
            final double[] months = new double[13];
            double maxAmount = Double.NEGATIVE_INFINITY;
            boolean isExist = false;

            for(final BankTransaction bankTransaction : bankStatement){
                final String bankTransactionDescription = bankTransaction.getDescription();
                if(description.equals(bankTransactionDescription)){
                    final Month bankTransactionMonth = bankTransaction.getDate().getMonth();
                    final double bankTransactionAmount = bankTransaction.getAmount();
                    months[bankTransactionMonth.getValue()] += bankTransactionAmount;
                    isExist = true;
                    maxAmount = Math.max(maxAmount, Math.abs(bankTransactionAmount));
                }
            }

            if(isExist){
                final double range = maxAmount / 10;
                for(int i = 1; i < months.length; i++){
                    final int progress = (int)(Math.abs(months[i]) / range);
                    System.out.printf("%2d월: ", i);
                    if(months[i] < 0){
                        for(int j = 0; j < progress; j++){
                            System.out.printf("□");
                        }
                    }else{
                        for(int j = 0; j < progress; j++){
                            System.out.printf("■");
                        }
                    }
                    System.out.printf("(%.2f)", months[i]);
                    System.out.println();
                }
            }else{
                System.out.println(description + " 항목의 입출금 내역이 없습니다.");
            }
        }
    }

    public final Integer countBankStatementInMonth(final Month month){
        int cnt = 0;
        for(final BankTransaction bankTransaction : bankStatement){
            if(bankTransaction.getDate().getMonth() == month){
                cnt++;
            }
        }

        return cnt == 0 ? null : cnt;
    }
}