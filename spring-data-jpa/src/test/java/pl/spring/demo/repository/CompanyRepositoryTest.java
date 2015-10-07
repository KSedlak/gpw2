package pl.spring.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.spring.demo.entity.CompanyEntity;
import pl.spring.demo.mapper.CompanyMapper;
import pl.spring.demo.to.CompanyTo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository compRepository;

    
    @Test
    public void testShouldSaveCompany() {
        // given
        final String name="TPSA";
        // when
        CompanyTo x=new CompanyTo(1L,name);
        CompanyEntity comp=   compRepository.save(CompanyMapper.map(x));
        // then
        assertNotNull(comp);
        assertEquals(name,comp.getName());
    }
    
   
}
