package br.com.wtecsuprimentos.domain.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    private String razaoSocial;
    private int maiorAtraso,
            periodoDemonstrativoEmMeses, anoFundacao,
            scorePontualidade, risco, prazoMedioRecebimentoVendas, emrpesaME, restricao, cluster;

    private double diferencaPercentualRisco, percentualRisco, margemBrutaAcumulada;

    long margemBruta, custos, capitalSocial, faturamentoBruto, titulosEmAberto, limiteEmpresaAnaliseCredito;

    public Customer(){}

    public Customer(String razaoSocial, int maiorAtraso, long titulosEmAberto, long faturamentoBruto,
                    long margemBruta, int periodoDemonstrativoEmMeses, long custos, int anoFundacao,
                    long capitalSocial, int scorePontualidade, int risco, int prazoMedioRecebimentoVendas,
                    double diferencaPercentualRisco, double percentualRisco, double margemBrutaAcumulada,
                    long limiteEmpresaAnaliseCredito, int emrpesaME, int restricao, int cluster) {
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

    public long getTitulosEmAberto() {
        return titulosEmAberto;
    }

    public void setTitulosEmAberto(long titulosEmAberto) {
        this.titulosEmAberto = titulosEmAberto;
    }

    public long getFaturamentoBruto() {
        return faturamentoBruto;
    }

    public void setFaturamentoBruto(long faturamentoBruto) {
        this.faturamentoBruto = faturamentoBruto;
    }

    public long getMargemBruta() {
        return margemBruta;
    }

    public void setMargemBruta(long margemBruta) {
        this.margemBruta = margemBruta;
    }

    public int getPeriodoDemonstrativoEmMeses() {
        return periodoDemonstrativoEmMeses;
    }

    public void setPeriodoDemonstrativoEmMeses(int periodoDemonstrativoEmMeses) {
        this.periodoDemonstrativoEmMeses = periodoDemonstrativoEmMeses;
    }

    public long getCustos() {
        return custos;
    }

    public void setCustos(long custos) {
        this.custos = custos;
    }

    public int getAnoFundacao() {
        return anoFundacao;
    }

    public void setAnoFundacao(int anoFundacao) {
        this.anoFundacao = anoFundacao;
    }

    public long getCapitalSocial() {
        return capitalSocial;
    }

    public void setCapitalSocial(long capitalSocial) {
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

    public int getPrazoMedioRecebimentoVendas() {
        return prazoMedioRecebimentoVendas;
    }

    public void setPrazoMedioRecebimentoVendas(int prazoMedioRecebimentoVendas) {
        this.prazoMedioRecebimentoVendas = prazoMedioRecebimentoVendas;
    }

    public double getDiferencaPercentualRisco() {
        return diferencaPercentualRisco;
    }

    public void setDiferencaPercentualRisco(double diferencaPercentualRisco) {
        this.diferencaPercentualRisco = diferencaPercentualRisco;
    }

    public double getPercentualRisco() {
        return percentualRisco;
    }

    public void setPercentualRisco(double percentualRisco) {
        this.percentualRisco = percentualRisco;
    }

    public long getLimiteEmpresaAnaliseCredito() {
        return limiteEmpresaAnaliseCredito;
    }

    public void setLimiteEmpresaAnaliseCredito(long limiteEmpresaAnaliseCredito) {
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
