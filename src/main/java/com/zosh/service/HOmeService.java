package com.zosh.service;

import java.util.List;

import com.zosh.modal.Home;
import com.zosh.modal.HomeCategory;

public interface HOmeService {
    public Home createHomePageData(List<HomeCategory> allCategories);

}
