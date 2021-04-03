package br.com.wtecsuprimentos.domain.entities;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String razaoSocial;
    private int maiorAtraso, titulosEmAberto, faturamentoBruto, margemBruta,
            periodoDemonstrativoEmMeses, custos, anoFundacao, capitalSocial,
            scorePontualidade, risco;
    private float margemBrutaAcumulada, prazoMedioRecebimentoVendas,
            diferencaPercentualRisco, percentualRisco;

    public Customer(){}

    public Customer(String razaoSocial, int maiorAtraso, int titulosEmAberto, int faturamentoBruto,
                    int margemBruta, int periodoDemonstrativoEmMeses, int custos, int anoFundacao,
                    int capitalSocial, int scorePontualidade, int risco, float margemBrutaAcumulada,
                    float prazoMedioRecebimentoVendas, float diferencaPercentualRisco, float percentualRisco) {
        this.razaoSocial = razaoSocial;
        this.maiorAtraso = maiorAtraso;
        this.titulosEmAberto = titulosEmAberto;
        this.faturamentoBruto = faturamentoBruto;
        this.margemBruta = margemBruta;
        this.periodoDemonstrativoEmMeses = periodoDemonstrativoEmMeses;
        this.custos = custos;
        this.anoFundacao = anoFundacao;
        this.capitalSocial = capitalSocial;
        this.scorePontualidade = scorePontualidade;
        this.risco = risco;
        this.margemBrutaAcumulada = margemBrutaAcumulada;
        this.prazoMedioRecebimentoVendas = prazoMedioRecebimentoVendas;
        this.diferencaPercentualRisco = diferencaPercentualRisco;
        this.percentualRisco = percentualRisco;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public int getMaiorAtraso() {
        return maiorAtraso;
    }

    public void setMaiorAtraso(int maiorAtraso) {
        this.maiorAtraso = maiorAtraso;
    }

    public int getTitulosEmAberto() {
        return titulosEmAberto;
    }

    public void setTitulosEmAberto(int titulosEmAberto) {
        this.titulosEmAberto = titulosEmAberto;
    }

    public int getFaturamentoBruto() {
        return faturamentoBruto;
    }

    public void setFaturamentoBruto(int faturamentoBruto) {
        this.faturamentoBruto = faturamentoBruto;
    }

    public int getMargemBruta() {
        return margemBruta;
    }

    public void setMargemBruta(int margemBruta) {
        this.margemBruta = margemBruta;
    }

    public int getPeriodoDemonstrativoEmMeses() {
        return periodoDemonstrativoEmMeses;
    }

    public void setPeriodoDemonstrativoEmMeses(int periodoDemonstrativoEmMeses) {
        this.periodoDemonstrativoEmMeses = periodoDemonstrativoEmMeses;
    }

    public int getCustos() {
        return custos;
    }

    public void setCustos(int custos) {
        this.custos = custos;
    }

    public int getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(int anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    public int getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(int capitalSocial) {
        this.capitalSocial = capitalSocial;
    }

    public int getScorePontualidade() {
        return scorePontualidade;
    }

    public void setScorePontualidade(int scorePontualidade) {
        this.scorePontualidade = scorePontualidade;
    }

    public int getRisco() {
        return risco;
    }

    public void setRisco(int risco) {
        this.risco = risco;
    }

    public float getMargemBrutaAcumulada() {
        return margemBrutaAcumulada;
    }

    public void setMargemBrutaAcumulada(float margemBrutaAcumulada) {
        this.margemBrutaAcumulada = margemBrutaAcumulada;
    }

    public float getPrazoMedioRecebimentoVendas() {
        return prazoMedioRecebimentoVendas;
    }

    public void setPrazoMedioRecebimentoVendas(float prazoMedioRecebimentoVendas) {
        this.prazoMedioRecebimentoVendas = prazoMedioRecebimentoVendas;
    }

    public float getDiferencaPercentualRisco() {
        return diferencaPercentualRisco;
    }

    public void setDiferencaPercentualRisco(float diferencaPercentualRisco) {
        this.diferencaPercentualRisco = diferencaPercentualRisco;
    }

    public float getPercentualRisco() {
        return percentualRisco;
    }

    public void setPercentualRisco(float percentualRisco) {
        this.percentualRisco = percentualRisco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return razaoSocial.equals(customer.razaoSocial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(razaoSocial);
    }
}
