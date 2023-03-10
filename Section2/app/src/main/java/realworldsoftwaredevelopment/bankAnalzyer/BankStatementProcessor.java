package realworldsoftwaredevelopment.bankAnalzyer;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
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

    public final Double getMaxAmountInMonth(final Month from, final Month to){
        if(bankStatement.isEmpty()){
            return null;
        }else{
            double maxBankStatement = Double.NEGATIVE_INFINITY;
            boolean isEmpty = true;
            for(final BankTransaction bankTransaction : bankStatement){
                final Month bankTransactionMonth = bankTransaction.getDate().getMonth();
                final double bankTransactionAmount = bankTransaction.getAmount();
                if(bankTransactionMonth.getValue() >= from.getValue() &&
                   bankTransactionMonth.getValue() <= to.getValue()
                ){
                    isEmpty = false;
                    maxBankStatement = Math.max(maxBankStatement, bankTransactionAmount);
                }
            }
            return isEmpty ? null : maxBankStatement;
        }
    }

    public final Double getMinAmountInMonth(final Month from, final Month to){
        if(bankStatement.isEmpty()){
            return null;
        }else{
            double minBankStatement = Double.POSITIVE_INFINITY;
            boolean isEmpty = true;
            for(final BankTransaction bankTransaction : bankStatement){
                final Month bankTransactionMonth = bankTransaction.getDate().getMonth();
                final double bankTransactionAmount = bankTransaction.getAmount();
                if(bankTransactionMonth.getValue() >= from.getValue() &&
                   bankTransactionMonth.getValue() <= to.getValue()
                ){
                    isEmpty = false;
                    minBankStatement = Math.min(minBankStatement, bankTransactionAmount);
                }
            }
            return isEmpty ? null : minBankStatement;
        }
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
}