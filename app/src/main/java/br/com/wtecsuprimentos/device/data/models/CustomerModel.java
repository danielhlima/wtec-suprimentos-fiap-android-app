package br.com.wtecsuprimentos.device.data.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import br.com.wtecsuprimentos.domain.entities.Customer;

@Entity(tableName = "customer")
public class CustomerModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String razaoSocial;
    private int maiorAtraso, titulosEmAberto, faturamentoBruto, margemBruta,
            periodoDemonstrativoEmMeses, custos, anoFundacao, capitalSocial,
            scorePontualidade, risco, cluster;
    private float margemBrutaAcumulada, prazoMedioRecebimentoVendas,
            diferencaPercentualRisco, percentualRisco;

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

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }
}
