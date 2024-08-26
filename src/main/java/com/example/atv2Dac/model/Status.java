package com.example.atv2Dac.model;

public enum Status {
    ATIVO("Ativo"),
    INATIVO("Inativo"),
    PENDENTE("Pendente"),
    CONCLUIDO("Concluído");

    private String descricao;

    Status(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
