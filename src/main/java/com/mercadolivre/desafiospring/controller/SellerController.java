package com.mercadolivre.desafiospring.controller;

import com.mercadolivre.desafiospring.domain.dto.SellerDTORegister;
import com.mercadolivre.desafiospring.domain.entity.Seller;
import com.mercadolivre.desafiospring.service.SellerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class SellerController {

    private SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @GetMapping("/sellers")
    @ApiOperation(value= "Retorna uma lista de vendedores")
    public ResponseEntity<List<Seller>> getAll(){
        List<Seller> sellers = sellerService.getAllSellers();
        return ResponseEntity.ok(sellers);
    }


    @PostMapping("/sellers")
    @ApiOperation(value= "Cria um vendedor")
    public ResponseEntity<Seller> createSeller(@RequestBody SellerDTORegister seller){
        Seller s = sellerService.createSeller(seller);
        return ResponseEntity.status(HttpStatus.CREATED).body(s);

    }
}
