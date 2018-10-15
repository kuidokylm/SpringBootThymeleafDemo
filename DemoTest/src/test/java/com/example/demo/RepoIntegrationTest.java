package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.dto.Client;
import com.example.repository.CountryJdbcRepository;

//https://www.baeldung.com/spring-boot-testing
//@ComponentScan on vaja lisada selle vea pärast Unit tetsil
/*
Error creating bean with name 'com.example.demo.RepoIntegrationTest': Unsatisfied dependency expressed 
through field 'repo'; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: 
No qualifying bean of type 'com.example.repository.CountryJdbcRepository' available: expected at least 
1 bean which qualifies as autowire candidate. Dependency annotations: 
{@org.springframework.beans.factory.annotation.Autowired(required=true)}
*/

@ComponentScan("com.example.repository")
@RunWith(SpringRunner.class)
@DataJpaTest
public class RepoIntegrationTest {
	
//	@Autowired
//    private TestEntityManager entityManager;
	
	@Autowired
	private CountryJdbcRepository repo;
	
	
	@Test
	public void whenFindClientById_thenReturnClient() {
		
	    // given
	    Client jyszctuna = new Client("234725472","Jyszctuna", "Kowalscyk", "287428", "Poola", "Krakow", 3L);
//	    entityManager.persist(jyszctuna);
//	    entityManager.flush();
	 
	    // when
	    Client found = repo.findByIdClient(jyszctuna.getId());
	 
	    // then
	    assertThat(found.getLastname())  
	      .isEqualTo(jyszctuna.getLastname());  //assertj library
	}

}
