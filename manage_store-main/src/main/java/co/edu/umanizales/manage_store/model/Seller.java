package co.edu.umanizales.manage_store.model;

import lombok.Data;

@Data
public class Seller {
    private String code;
    private String name;

    public Seller() {
    }

    public Seller(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
