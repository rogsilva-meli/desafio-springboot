package com.mercadolivre.desafiospring.service;

import com.mercadolivre.desafiospring.domain.dto.*;
import com.mercadolivre.desafiospring.domain.entity.Seller;
import com.mercadolivre.desafiospring.repository.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SellerService {

    private SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public List<Seller> getAllSellers() {
        List<Seller> all = sellerRepository.findAll();
        Collections.reverse(all);
        return all;
    }

    public Seller createSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public SellerDTO countUsers(Integer id){
        Seller seller = sellerRepository.findById(id).get();
        int i=(int)seller.getUsers().stream().count();

        SellerDTO sellerDTO = SellerDTO.builder()
                .userId(seller.getId())
                .userName(seller.getSellerName())
                .followers_count(i)
                .build();
        return  sellerDTO;
    }

    public Seller getSellerById(Integer id){
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new IndexOutOfBoundsException("Seller "+id +" not found"));
        return seller;
    }

    public SellerDTOUS0003 convertSellerToSellerDTOUS0003 (Seller s){
        SellerDTOUS0003 sellerDTOUS0003 = SellerDTOUS0003.builder()
                .userId(s.getId())
                .userName(s.getSellerName())
                .followers(s.getUsers())
                .build();
        return sellerDTOUS0003;
    }


}
