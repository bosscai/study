package com.example.test.model;

/**
 * author：  caichengxuan
 * email：   caichengxuan@faw.com.cn
 * time：    2024/8/28
 * describe:
 **/
public class TexItem2 {
    private String month;
    private String title;
    private String company;
    private String income;
    private String tax;

    public TexItem2(String month, String title, String company, String income, String tax) {
        this.month = month;
        this.title = title;
        this.company = company;
        this.income = income;
        this.tax = tax;
    }

    public String getMonth() {
        return month;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getIncome() {
        return income;
    }

    public String getTax() {
        return tax;
    }
}
