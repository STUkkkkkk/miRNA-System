package stukk;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import stukk.Pojo.Disease;
import stukk.Pojo.User;
import stukk.mapper.SearchDiseaseMapper;
import stukk.service.DiseaseService;
import stukk.service.MirnaService;
import stukk.service.UserService;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	DiseaseService diseaseService;


	@Autowired
	MirnaService mirnaService;

	@Test
	void contextLoads() {
		User user = new User();
		user.setAge(1);
		user.setPassword("123456");
		System.out.println(userService.save(user));
	}

	@Test
	void DisTest(){
		System.out.println("啊啊啊啊啊"+diseaseService.selectPage(new Page<>(1,2),null).getRecords().get(0).getDescription());
	}

	@Test
	void RNATest(){
		System.out.println("aaa"+ mirnaService.selectPage(new Page<>(1,2),null).getRecords().get(0).getDescription());
	}

	@Autowired
	SearchDiseaseMapper searchDiseaseMapper;

	@Test
	void SearchTest(){
		System.out.println(searchDiseaseMapper.selectByDisease("%新冠%"));
	}


	@Test
	public void findPageTest(){
		System.out.println(userService.findPage("",2,5));
	}
}
