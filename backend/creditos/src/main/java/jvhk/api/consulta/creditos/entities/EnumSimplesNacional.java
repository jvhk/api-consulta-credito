package jvhk.api.consulta.creditos.entities;

public enum EnumSimplesNacional {

    SIM("Sim", true),
    NAO("Não", false);

    private final String descricao;
    private final boolean valor;

    EnumSimplesNacional(String descricao, boolean valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public static String getDescricaoByValor(boolean valor) {
        return valor ? SIM.getDescricao() : NAO.getDescricao();
    }
}
