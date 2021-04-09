package br.com.wtecsuprimentos.domain.entities;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String razaoSocial;
    private int maiorAtraso, titulosEmAberto, margemBruta,
            periodoDemonstrativoEmMeses, anoFundacao, capitalSocial,
            scorePontualidade, risco;
    private float prazoMedioRecebimentoVendas, custos,
            diferencaPercentualRisco, percentualRisco;
    private double margemBrutaAcumulada;

    //Additional parameters for regression
    private float limiteEmpresaAnaliseCredito, faturamentoBruto;
    private int emrpesaME;
    private int restricao;
    private int cluster;

    public Customer(){}

    public Customer(String razaoSocial, int maiorAtraso, int titulosEmAberto, float faturamentoBruto,
                    int margemBruta, int periodoDemonstrativoEmMeses, float custos, int anoFundacao,
                    int capitalSocial, int scorePontualidade, int risco, double margemBrutaAcumulada,
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

    public Customer(String razaoSocial, int maiorAtraso, int titulosEmAberto, float faturamentoBruto,
                    int margemBruta, int periodoDemonstrativoEmMeses, float custos, int anoFundacao,
                    int capitalSocial, int scorePontualidade, int risco, float prazoMedioRecebimentoVendas,
                    float diferencaPercentualRisco, float percentualRisco, double margemBrutaAcumulada,
                    float limiteEmpresaAnaliseCredito, int emrpesaME, int restricao, int cluster) {
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
        this.prazoMedioRecebimentoVendas = prazoMedioRecebimentoVendas;
        this.diferencaPercentualRisco = diferencaPercentualRisco;
        this.percentualRisco = percentualRisco;
        this.margemBrutaAcumulada = margemBrutaAcumulada;
        this.limiteEmpresaAnaliseCredito = limiteEmpresaAnaliseCredito;
        this.emrpesaME = emrpesaME;
        this.restricao = restricao;
        this.cluster = cluster;
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

    public float getFaturamentoBruto() {
        return faturamentoBruto;
    }

    public void setFaturamentoBruto(float faturamentoBruto) {
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

    public float getCustos() {
        return custos;
    }

    public void setCustos(float custos) {
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

    public double getMargemBrutaAcumulada() {
        return margemBrutaAcumulada;
    }

    public void setMargemBrutaAcumulada(double margemBrutaAcumulada) {
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

    public float getLimiteEmpresaAnaliseCredito() {
        return limiteEmpresaAnaliseCredito;
    }

    public void setLimiteEmpresaAnaliseCredito(float limiteEmpresaAnaliseCredito) {
        this.limiteEmpresaAnaliseCredito = limiteEmpresaAnaliseCredito;
    }

    public int getEmrpesaME() {
        return emrpesaME;
    }

    public void setEmrpesaME(int emrpesaME) {
        this.emrpesaME = emrpesaME;
    }

    public int getRestricao() {
        return restricao;
    }

    public void setRestricao(int restricao) {
        this.restricao = restricao;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
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

    @Override
    public String toString() {
        return "Customer{" +
                "razaoSocial='" + razaoSocial + '\'' +
                ", maiorAtraso=" + maiorAtraso +
                ", titulosEmAberto=" + titulosEmAberto +
                ", faturamentoBruto=" + faturamentoBruto +
                ", margemBruta=" + margemBruta +
                ", periodoDemonstrativoEmMeses=" + periodoDemonstrativoEmMeses +
                ", custos=" + custos +
                ", anoFundacao=" + anoFundacao +
                ", capitalSocial=" + capitalSocial +
                ", scorePontualidade=" + scorePontualidade +
                ", risco=" + risco +
                ", prazoMedioRecebimentoVendas=" + prazoMedioRecebimentoVendas +
                ", diferencaPercentualRisco=" + diferencaPercentualRisco +
                ", percentualRisco=" + percentualRisco +
                ", margemBrutaAcumulada=" + margemBrutaAcumulada +
                '}';
    }
}
