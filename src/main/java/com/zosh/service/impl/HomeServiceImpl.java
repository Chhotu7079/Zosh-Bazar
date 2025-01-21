package com.zosh.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.checkerframework.checker.units.qual.h;
import org.springframework.stereotype.Service;

import com.zosh.domain.HomeCategorySection;
import com.zosh.modal.Deal;
import com.zosh.modal.Home;
import com.zosh.modal.HomeCategory;
import com.zosh.repository.DealRepository;
import com.zosh.service.HOmeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HomeServiceImpl implements HOmeService {

    private final DealRepository dealRepository;

    @Override
    public Home createHomePageData(List<HomeCategory> allCategories) {

        List<HomeCategory> gridCategories = allCategories.stream().filter(category -> category.getSection() == HomeCategorySection.GRID).collect(Collectors.toList());
        List<HomeCategory> shopByCategories = allCategories.stream().filter(category -> category.getSection() == HomeCategorySection.SHOP_BY_CATEGORIES).collect(Collectors.toList());
        List<HomeCategory> electricCategories = allCategories.stream().filter(category -> category.getSection() == HomeCategorySection.ELECTRIC_CATEGORIES).collect(Collectors.toList());
        List<HomeCategory> dealCategories = allCategories.stream().filter(category -> category.getSection() == HomeCategorySection.DEALS).collect(Collectors.toList());

        List<Deal> createdDeals = new ArrayList<>();

        if (dealRepository.findAll().isEmpty()) {
            List<Deal> deals = allCategories.stream()
                    .filter(category -> category.getSection() == HomeCategorySection.DEALS)
                    .map(category -> new Deal(null, 10, category))
                    .collect(Collectors.toList());
            createdDeals = dealRepository.saveAll(deals);
        }else{
            createdDeals = dealRepository.findAll();
        }

        Home home = new Home();
        home.setGrid(gridCategories);
        home.setShopByCategories(shopByCategories);
        home.setElectricCategories(electricCategories);
        home.setDeals(createdDeals);
        home.setDealCategories(dealCategories);

        return home;


    }

}
