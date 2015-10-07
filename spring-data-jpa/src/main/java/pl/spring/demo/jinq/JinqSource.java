package pl.spring.demo.jinq;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.jinq.jpa.JPAJinqStream;
import org.springframework.stereotype.Component;

import pl.spring.demo.model.stockDailyRecord.StockDailyRecordEntity;

@Component
public class JinqSource {
  private JinqJPAStreamProvider streams;

  @PersistenceContext(name = "hsql")
  private EntityManager entityManager;
  
  @PersistenceUnit
  public void setEntityManagerFactory(
      EntityManagerFactory emf) throws Exception {
    streams = new JinqJPAStreamProvider(emf);
  }

  // Wrapper that passes through Jinq requests to Jinq
  public <U> JPAJinqStream<U> streamAll(
      EntityManager em, Class<U>entity) {
    return streams.streamAll(em, entity);
  }
  
  public JPAJinqStream<StockDailyRecordEntity> dailyRecords() {
	
	    return streams.streamAll(entityManager, StockDailyRecordEntity.class);
}
  

  
}