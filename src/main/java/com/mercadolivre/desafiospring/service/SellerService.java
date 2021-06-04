package com.mercadolivre.desafiospring.service;

import com.mercadolivre.desafiospring.entity.Seller;
import com.mercadolivre.desafiospring.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerService {

    private SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public Seller countUsers(Integer id){
        Seller seller = sellerRepository.findById(id).get();
        int i=(int)seller.getUsers().stream().count();
        seller.setFollowers_count(i);
        return  seller;
    }

    public Seller getSellerFollowedByUser(Integer userId) {
        Seller seller = sellerRepository.findById(userId).get();
        return seller;
    }
}
