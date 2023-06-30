package kr.three.spring.item.repository;

import static kr.three.spring.item.entity.QItem.item;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import kr.three.spring.item.constant.ItemSellStatus;
import kr.three.spring.item.dto.ItemSearchDto;
import kr.three.spring.item.entity.Item;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

	private JPAQueryFactory queryFactory; // Make Query
	
	public ItemRepositoryCustomImpl(EntityManager em) {
		queryFactory = new JPAQueryFactory(em);
	}
	
	// Check Sell Status
	private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus){
        return searchSellStatus == null ? null : item.itemSellStatus.eq(searchSellStatus);
        // return: WHERE itemSellStatus = 'SELL'('SOLD_OUT')
    }
	
	// Select items within certain period
    private BooleanExpression regDtsAfter(String searchDateType){
        LocalDateTime dateTime = LocalDateTime.now();

        if(StringUtils.equals("all", searchDateType) || searchDateType == null){
            return null;
        } else if(StringUtils.equals("1d", searchDateType)){ // recent 1d
            dateTime = dateTime.minusDays(1);
        } else if(StringUtils.equals("1w", searchDateType)){ // recent 1w
            dateTime = dateTime.minusWeeks(1);
        } else if(StringUtils.equals("1m", searchDateType)){ // recent 1m
            dateTime = dateTime.minusMonths(1);
        } else if(StringUtils.equals("6m", searchDateType)){ // recent 6m
            dateTime = dateTime.minusMonths(6);
        }

        return item.regTime.after(dateTime);
    }

    // Search item containing searchTerm in searchField
    private BooleanExpression searchByLike(String searchBy, String searchQuery){
        if(StringUtils.equals("itemNm", searchBy)){
            return item.itemNm.like("%" + searchQuery + "%");
        } else if(StringUtils.equals("createdBy", searchBy)){
            return item.createdBy.like("%" + searchQuery + "%");
        }
        return null;
    }
	
	@Override
	public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
		
		List<Item> content = queryFactory
			.selectFrom(item)
			.where(regDtsAfter(itemSearchDto.getSearchDateType()),
                   searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                   searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
			.orderBy(item.id.desc())
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();
		
		long total = queryFactory
				.select(Wildcard.count)
				.from(item)
                .where(regDtsAfter(itemSearchDto.getSearchDateType()),
                       searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                       searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery()))
                .fetchOne();
		
		return new PageImpl<Item>(content, pageable, total);
	}
}
