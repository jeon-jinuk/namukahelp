package com.shop.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemSearchDto;
import com.shop.entity.Item;
import com.shop.entity.QItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    // 상품 판매 조건이 null(전체 다 보기)일 경우 null을 리턴
    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {
        return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
    }

    // 검색 조건 : 시간
//    private BooleanExpression regDtsAfter(String searchDateType) {
//        LocalDateTime dateTime = LocalDateTime.now();
//
//        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
//            return null;
//        } else if (StringUtils.equals("1d", searchDateType)) {
//            dateTime = dateTime.minusDays(1);
//        } else if (StringUtils.equals("1w", searchDateType)) {
//            dateTime = dateTime.minusWeeks(1);
//        } else if (StringUtils.equals("1m", searchDateType)) {
//            dateTime = dateTime.minusMonths(1);
//        } else if (StringUtils.equals("6m", searchDateType)) {
//            dateTime = dateTime.minusMonths(6);
//        }
//        return QItem.item.regTime.after(dateTime);
//    }

    private BooleanExpression searchByLike(String searchBy, String searchQuery) {

        if (StringUtils.equals("itemNm", searchBy)) {
            return QItem.item.itemNm.like("%" + searchQuery + "%");
        } else if (StringUtils.equals("type", searchBy)) {
            return QItem.item.type.like("%" + searchQuery + "%");
        }
        return null;
    }

    // SearchByLike의 값에 따라서 상품명에 검색어를 포함하고 있는 상품을 조회하도록 조건값을 반환
    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        QueryResults<Item> results = queryFactory
                .selectFrom(QItem.item)
//                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
//                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
//                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                .orderBy(QItem.item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        List<Item> content = results.getResults();
        long total = results.getTotal();
        return new PageImpl<>(content, pageable, total);
    }
}
