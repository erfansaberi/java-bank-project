package com.bank.models;

import java.util.ArrayList;
import java.util.Date;

public class Loan {

    private static ArrayList<Loan> allLoans = new ArrayList<>();

    private long id;
    private double loanAmount;
    private Account account;
    private short payingLengthMonths;
    private short payedMonths;
    private double installment; // ghest
    private double mustPay;
    private double totalPaid;
    private Date requestDate;
    private Date acceptDate;
    private LoanStatus status;

    // constructor

    public Loan() {
        this.id = allLoans.size() + 1;
        this.requestDate = new Date();
        this.totalPaid = 0;
        this.payedMonths = 0;
        this.status = LoanStatus.PENDING;
    }

    public Loan(double loanAmount, short payingLengthMonths, Account account) {
        this();
        this.loanAmount = loanAmount;
        this.payingLengthMonths = payingLengthMonths;
        this.account = account;
        this.mustPay = (loanAmount + (2 * loanAmount) / 10);// adding 20% interest fee to loanAmount
        this.installment = mustPay / payingLengthMonths;
    }

    // setter and getters

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setInstallment(double installment) {
        this.installment = installment;
    }

    public double getInstallment() {
        return this.installment;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public double getTotalPaid() {
        return this.totalPaid;
    }

    public void setMustPay(double mustPay) {
        this.mustPay = mustPay;
    }

    public double getMustPay() {
        return this.mustPay;
    }

    public Date getRequestDate() {
        return this.requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getPayingLengthMonths() {
        return payingLengthMonths;
    }

    public void setPayingLengthMonths(short payingLengthMonths) {
        this.payingLengthMonths = payingLengthMonths;
    }

    public String toString() {
        return "Loan :{ " +
                "id = " + this.id +
                ", installment = " + this.installment +
                ", mustPay = " + this.mustPay +
                ", totalPaid = " + this.totalPaid +
                ", status = " + this.status + " }";
    }

    public enum LoanStatus {
        PENDING,
        PAYING,
        FINISHED
    }

    public static ArrayList<Loan> getAllLoans() {
        return allLoans;
    }

    public ArrayList<Loan> getCustomerAllLoans(long customerId) {
        ArrayList<Loan> allThisCustomerLoans = new ArrayList<>();
        for (Loan loan : Loan.getAllLoans()) {
            if (loan.account.getId() == customerId) {
                allThisCustomerLoans.add(loan);
            }
        }
        return allThisCustomerLoans;
    }

    /*
     * Get Pending Loans
     */

    public static ArrayList<Loan> getPendingLoans() {
        ArrayList<Loan> allPendingLoans = new ArrayList<>();
        for (Loan loan : Loan.getAllLoans()) {
            if (loan.getStatus() == LoanStatus.PENDING) {
                allPendingLoans.add(loan);
            }
        }
        return allPendingLoans;
    }

    public ArrayList<Loan> getCustomerPendingLoans(long customerId) {
        ArrayList<Loan> allCustomerPendingLoans = new ArrayList<>();
        for (Loan loan : Loan.getPendingLoans()) {
            if (loan.account.getId() == customerId) {
            }
        }
        return allCustomerPendingLoans;
    }

    public static ArrayList<Loan> getPayingLoans() {
        ArrayList<Loan> allPayingLoans = new ArrayList<>();
        for (Loan loan : Loan.getAllLoans()) {
            if (loan.getStatus() == LoanStatus.PAYING) {
                allPayingLoans.add(loan);
            }
        }
        return allPayingLoans;
    }

    public ArrayList<Loan> getCustomerPayingLoans(long customerId) {
        ArrayList<Loan> allCustomerPayingLoans = new ArrayList<>();
        for (Loan loan : Loan.getPayingLoans()) {
            if (loan.account.getId() == customerId) {
                allCustomerPayingLoans.add(loan);
            }
        }
        return allCustomerPayingLoans;
    }

    public static ArrayList<Loan> getFinishedLoans() {
        ArrayList<Loan> allFinishedLoans = new ArrayList<>();
        for (Loan loan : Loan.getAllLoans()) {
            if (loan.getStatus() == LoanStatus.FINISHED) {
                allFinishedLoans.add(loan);
            }
        }
        return allFinishedLoans;
    }

    public ArrayList<Loan> getCustomerFinishedLoans(long customerId) {
        ArrayList<Loan> allCustomerFinishedLoans = new ArrayList<>();
        for (Loan loan : Loan.getPayingLoans()) {
            if (loan.account.getId() == customerId) {
                allCustomerFinishedLoans.add(loan);
            }
        }
        return allCustomerFinishedLoans;
    }
}
