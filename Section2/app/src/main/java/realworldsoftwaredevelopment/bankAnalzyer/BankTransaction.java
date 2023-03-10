package realworldsoftwaredevelopment.bankAnalzyer;

import java.time.LocalDate;
import java.util.Objects;

public final class BankTransaction {
    private final LocalDate date;
    private final double amount;
    private final String description;

    public BankTransaction(final LocalDate date, final double amount, final String description){
        this.date = date;
        this.amount = amount;
        this.description = description;
    }

    public final LocalDate getDate(){
        return date;
    }

    public final double getAmount(){
        return amount;
    }

    public final String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append("date: ");
        sb.append(date);
        sb.append(", amount: ");
        sb.append(amount);
        sb.append(", description: ");
        sb.append(description);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o == null || getClass() != o.getClass()){
            return false;
        }
        BankTransaction that = (BankTransaction)o;
        return (Double.compare(that.amount, amount) == 0) &&
               (date.equals(that.date)) &&
               (description.equals(that.description));
    }

    @Override
    public int hashCode(){
        return Objects.hash(date, amount, description);
    }
}
