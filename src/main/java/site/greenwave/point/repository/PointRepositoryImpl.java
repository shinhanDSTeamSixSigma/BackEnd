package site.greenwave.point.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;

import site.greenwave.member.entity.QMemberEntity;
import site.greenwave.point.entity.PointEntity;
import site.greenwave.point.entity.QPointEntity;

public class PointRepositoryImpl extends QuerydslRepositorySupport {
	public PointRepositoryImpl() {
		super(PointEntity.class);
	}
	
	public List<Object[]> findPointsByMemberNoAndChangeValueAndPointDate(Integer memberNo, Integer changeValue, Integer year, Integer month) {
		
		QPointEntity point = QPointEntity.pointEntity;
        QMemberEntity member = QMemberEntity.memberEntity;
        
        JPQLQuery<PointEntity> query = from(point);
        JPQLQuery<Tuple> tuple = query.select(point.pointNo, point.pointValue, point.pointDate, point.changeValue, point.changeCause);
        
        tuple.leftJoin(member)
        	  .on(point.memberEntity.memberNo.eq(member.memberNo))
	          .where(
	                member.memberNo.eq(memberNo),
	                
	                Expressions.booleanTemplate("YEAR({0}) = {1}", point.pointDate, year),
	                Expressions.booleanTemplate("MONTH({0}) = {1}", point.pointDate, month)
	          );

        if (changeValue == 2) {
        	tuple = tuple.where(point.changeValue.in(0, 1));
        } else {
        	tuple = tuple.where(point.changeValue.eq(changeValue));
        }
        
        tuple.orderBy(point.pointDate.desc());
        
        List<Tuple> list = tuple.fetch();
        List<Object[]> resultList = new ArrayList<>();
        list.forEach(t->{
        	resultList.add(t.toArray());
        });
        return resultList;
	}
    
}
