package co.edu.umanizales.manage_store.service;

import co.edu.umanizales.manage_store.controller.dto.SellerDTO;
import co.edu.umanizales.manage_store.model.Seller;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.umanizales.manage_store.service.SaleService;

import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class SellerService {
    @Autowired
    private SaleService saleService;
    private List<Seller> sellers;

    public SellerService() {

        this.sellers = new ArrayList<>();
    }

    public void addSeller(Seller seller) {
        sellers.add(seller);
    }

    public Seller getSellerById(String code) {
        for (Seller seller : sellers) {
            if (seller.getCode().equalsIgnoreCase(code)) {
                return seller;
            }
        }
        return null;
    }

    public List<SellerDTO> getSellerByQuantity(int quantity) {
        List<SellerDTO> listSellerDTO = new ArrayList<>();
        for (Seller seller : sellers) {
            SellerDTO sellerDTO = new SellerDTO(new Seller(seller.getCode(), seller.getName()), saleService.getTotalSalesBySeller(seller.getCode()));
            if (sellerDTO.getQuantity() > quantity) {
                listSellerDTO.add(sellerDTO);
            }
        }
        return listSellerDTO;
    }
}
