package com.bank.models;

import java.util.ArrayList;
import java.util.Date;

public class Loan {

    private static ArrayList<Loan> allLoans = new ArrayList<>();

    private long Id;
    private long moneyForLoan;
    private int payingLengthMonths;
    private Account account;
    private double installment; // ghest
    private double totalPaid;
    private double mustPaid;
    private Date recieveDate;
    private LoanStatus status;

    // constructor

    public Loan() {
        this.Id = allLoans.size() + 1;
        // TODO: assigned date .
    }

    public Loan(long moneyForLoan, int payingLengthMonths, Account account) {
        this.moneyForLoan = moneyForLoan;
        this.payingLengthMonths = payingLengthMonths;
        this.account = account;
    }

    // setter and getters

    public void setId(long Id) {
        this.Id = Id;
    }

    public long getId() {
        return this.Id;
    }

    public void setInstallment(double installment) {
        this.installment = installment;
    }

    public double getInstallment() {
        return this.installment;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid += installment;
    }

    public double getTotalPaid() {
        return this.totalPaid;
    }

    public void setMustPaid(double mustPaid) {
        this.mustPaid = moneyForLoan - totalPaid;
    }

    public double getMustPaid() {
        return this.mustPaid;
    }

    public Date getRecieveDate() {
        return this.recieveDate;
    }

    public void setRecieveDate(Date recieveDate) {
        this.recieveDate = recieveDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public long getMoneyForLoan() {
        return moneyForLoan;
    }

    public void setMoneyForLoan(long moneyForLoan) {
        this.moneyForLoan = moneyForLoan;
    }

    public int getPayingLengthMonths() {
        return payingLengthMonths;
    }

    public void setPayingLengthMonths(int payingLengthMonths) {
        this.payingLengthMonths = payingLengthMonths;
    }

    public String toString() {
        return "Loan{" +
                "id=" + this.Id +
                ", installment='" + this.installment + '\'' +
                ", totalPaid='" + this.totalPaid + '\'' +
                ", mustPaid='" + this.mustPaid + '\'' +
                ", status=" + this.status +
                '}';
    }

    public enum LoanStatus {
        PENDING,
        PAYING,
        FINISHED
    }

    public static ArrayList<Loan> getAllLoans() {
        return allLoans;
    }

    public ArrayList<Loan> getCustomerAllLoans(String Id) {
        ArrayList<Loan> allThisCustomerLoans = new ArrayList<>();
        for (Loan loan : Loan.getAllLoans()) {
            if (loan.getId() == this.Id) {
                allThisCustomerLoans.add(loan);
            }
        }
        return allThisCustomerLoans;
    }

    /*
     * TODO: method for being pending paying or finished loans
     * 
     * public ArrayList<Loan> statusLoans() {
     * for(Loan loan : Loan.getAllLoans())
     * if(loan.getMustPaid() == 0)
     * loan.status = LoanStatus.FINISHED ;
     * else if(loan.getTotalPaid() > 0)
     * loan.status = LoanStatus.PAYING ;
     * else
     * loan.status = LoanStatus.PENDING ;
     * }
     */

    public static ArrayList<Loan> getPendingLoans() {
        {
            ArrayList<Loan> allPendingLoans = new ArrayList<>();
            for (Loan loan : Loan.getAllLoans()) {
                if (loan.getStatus() == LoanStatus.PENDING) {
                    allPendingLoans.add(loan);
                }
            }
            return allPendingLoans;
        }
    }

    public ArrayList<Loan> getCustomerPendingLoans(String Id) {
        {
            ArrayList<Loan> allCustomerPendingLoans = new ArrayList<>();
            for (Loan loan : Loan.getPendingLoans()) {
                if (loan.getId() == this.Id) {
                    allCustomerPendingLoans.add(loan);
                }
            }
            return allCustomerPendingLoans;
        }
    }

    public static ArrayList<Loan> getPayingLoans() {
        {
            ArrayList<Loan> allPayingLoans = new ArrayList<>();
            for (Loan loan : Loan.getAllLoans()) {
                if (loan.getStatus() == LoanStatus.PAYING) {
                    allPayingLoans.add(loan);
                }
            }
            return allPayingLoans;
        }
    }

    public ArrayList<Loan> getCustomerPayingLoans(String Id) {
        {
            ArrayList<Loan> allCustomerPayingLoans = new ArrayList<>();
            for (Loan loan : Loan.getPayingLoans()) {
                if (loan.getId() == this.Id) {
                    allCustomerPayingLoans.add(loan);
                }
            }
            return allCustomerPayingLoans;
        }
    }

    public static ArrayList<Loan> getFinishedLoans() {
        {
            ArrayList<Loan> allFinishedLoans = new ArrayList<>();
            for (Loan loan : Loan.getAllLoans()) {
                if (loan.getStatus() == LoanStatus.FINISHED) {
                    allFinishedLoans.add(loan);
                }
            }
            return allFinishedLoans;
        }
    }

    public ArrayList<Loan> getCustomerFinishedLoans(String Id) {
        {
            ArrayList<Loan> allCustomerFinishedLoans = new ArrayList<>();
            for (Loan loan : Loan.getPayingLoans()) {
                if (loan.getId() == this.Id) {
                    allCustomerFinishedLoans.add(loan);
                }
            }
            return allCustomerFinishedLoans;
        }
    }
}
