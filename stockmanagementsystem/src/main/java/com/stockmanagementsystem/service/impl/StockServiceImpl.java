package com.stockmanagementsystem.service.impl;

import com.stockmanagementsystem.dto.StockRequest;
import com.stockmanagementsystem.dto.StockDTO;
import com.stockmanagementsystem.dto.StockResponse;
import com.stockmanagementsystem.entity.Stock;
import com.stockmanagementsystem.entity.User;
import com.stockmanagementsystem.exception.ResourceNotFoundException;
import com.stockmanagementsystem.exception.StockException;
import com.stockmanagementsystem.repository.StockRepository;
import com.stockmanagementsystem.repository.UserRepository;
import com.stockmanagementsystem.service.StockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @created Barnaba Mutai
 * @created 11/ 09/ 2022 - 10:22 AM
 */
@Service
public class StockServiceImpl implements StockService {
    private StockRepository stockRepository;
    private UserRepository userRepository;

    public StockServiceImpl(StockRepository stockRepository, UserRepository userRepository) {
        this.stockRepository = stockRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public StockDTO createStock(StockRequest stockRequest) {

        User user =stockRequest.getUser();
        userRepository.save(user);


        if(user.getId() == null){
            throw new StockException("Pass user id");
        }

        Stock stock =new Stock();
        stock.setId(stockRequest.getId());
        stock.setUserid(user.getId());
        stock.setType(stockRequest.getType());
        stock.setPrice(stockRequest.getPrice());
        stock.setSymbol(stockRequest.getSymbol());
        stock.setShares(stockRequest.getShares());
        stock.setTimestamp(stockRequest.getTimestamp());
        stockRepository.save(stock);


        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setType(stock.getType());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setShares(stock.getShares());
        stockDTO.setSymbol(stock.getSymbol());
        stockDTO.setTimestamp(stock.getTimestamp());
//        stockDTO.setUser(stockRequest.getUser());
       return stockDTO;
    }

    @Override
    public StockResponse getAllStocks(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending():
                Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Stock> stocks = stockRepository.findAll(pageable);
        List<Stock> listOfPosts = stocks.getContent();
        List<StockDTO> content= listOfPosts.stream().map(stock -> mapToDTO(stock)).collect(Collectors.toList());
        StockResponse stockResponse = new StockResponse();
        stockResponse.setContent(content);
        stockResponse.setPageNo(stocks.getNumber());
        stockResponse.setPageSize(stocks.getSize());
        stockResponse.setTotalElements(stocks.getTotalElements());
        stockResponse.setTotalPages(stocks.getTotalPages());
        stockResponse.setLast(stocks.isLast());
        return stockResponse;
    }

    @Override
    public StockDTO getStockById(long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Stock", "id", id));
        return mapToDTO(stock);
    }

    @Override
    public StockDTO updateStock(StockDTO stockDTO, long id) {
        //Get post by id from the database
        Stock stock = stockRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Stock", "id", id));
        stock.setType(stockDTO.getType());
        stock.setSymbol(stockDTO.getSymbol());
        stock.setShares(stockDTO.getShares());
        stock.setPrice(stockDTO.getPrice());
        stock.setTimestamp(stockDTO.getTimestamp());
//        stock.setId(stockDTO.getUser().getId());
//        stock.setUserid(stockDTO.getUser().getName());
        Stock updatedStock = stockRepository.save(stock);
        return mapToDTO(updatedStock);
    }

    @Override
    public void deleteStockById(long id) {
        Stock stock = stockRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Stock", "id", id));
        stockRepository.delete(stock);
    }

    @Override
    public StockDTO getStockByUserId(long userid) {
        Stock stock = stockRepository.findById(userid).orElseThrow(()->new ResourceNotFoundException("User", "id", userid));
        return mapToDTO(stock);
    }

    private Stock mapToEntity(StockDTO stockDTO){
        Stock stock = new Stock();
        stock.setId(stockDTO.getId());
        stock.setType(stockDTO.getType());
        stock.setSymbol(stockDTO.getSymbol());
        stock.setShares(stockDTO.getShares());
        stock.setPrice(stockDTO.getPrice());
        stock.setTimestamp(stockDTO.getTimestamp());
//        stock.setUserid(stockDTO.getUser().getId());
        return stock;

    }
    private StockDTO mapToDTO(Stock stock){
        StockDTO stockDTO= new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setType(stock.getType());
        stockDTO.setSymbol(stock.getSymbol());
        stockDTO.setShares(stock.getShares());
        stockDTO.setPrice(stock.getPrice());
        stockDTO.setTimestamp(stock.getTimestamp());

        User user = new User();
        user.setId(user.getId());
        user.setName(user.getName());
//        stockDTO.setUser(user);
        return stockDTO;

    }

}
