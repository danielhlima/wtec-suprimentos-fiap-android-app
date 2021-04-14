package br.com.wtecsuprimentos.device.data.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.math.BigInteger;

import br.com.wtecsuprimentos.domain.entities.Customer;

@Entity(tableName = "customer")
public class CustomerModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String razaoSocial;
    private int maiorAtraso,
            periodoDemonstrativoEmMeses, anoFundacao,
            scorePontualidade, risco, prazoMedioRecebimentoVendas;

    private double diferencaPercentualRisco, percentualRisco,
            margemBrutaAcumulada;

    long margemBruta, custos, capitalSocial, faturamentoBruto, titulosEmAberto, limiteEmpresaAnaliseCredito;

    //Additional parameters for regression
    private int emrpesaME;
    private int restricao;
    private int cluster;

    public CustomerModel(){}

    @Ignore
    public CustomerModel(Customer customer) {
        this.razaoSocial = customer.getRazaoSocial();
        this.maiorAtraso = customer.getMaiorAtraso();
        this.titulosEmAberto = customer.getTitulosEmAberto();
        this.faturamentoBruto = customer.getFaturamentoBruto();
        this.margemBruta = customer.getMargemBruta();
        this.periodoDemonstrativoEmMeses = customer.getPeriodoDemonstrativoEmMeses();
        this.anoFundacao = customer.getAnoFundacao();
        this.capitalSocial = customer.getCapitalSocial();
        this.scorePontualidade = customer.getScorePontualidade();
        this.risco = customer.getRisco();
        this.margemBrutaAcumulada = customer.getMargemBrutaAcumulada();
        this.prazoMedioRecebimentoVendas = customer.getPrazoMedioRecebimentoVendas();
        this.diferencaPercentualRisco = customer.getDiferencaPercentualRisco();
        this.percentualRisco = customer.getPercentualRisco();
        this.limiteEmpresaAnaliseCredito = customer.getLimiteEmpresaAnaliseCredito();
        this.emrpesaME = customer.getEmrpesaME();
        this.restricao = customer.getRestricao();
        this.cluster = customer.getCluster();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
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
}
