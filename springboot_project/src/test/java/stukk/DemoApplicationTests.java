package stukk;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import stukk.Pojo.Disease;
import stukk.Pojo.Mirna;
import stukk.Pojo.User;
import stukk.mapper.SearchDiseaseMapper;
import stukk.mapper.SearchMirnaMapper;
import stukk.service.CodeService;
import stukk.service.DiseaseService;
import stukk.service.MirnaService;
import stukk.service.UserService;

import java.util.Date;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	UserService userService;

	@Autowired
	DiseaseService diseaseService;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Autowired
	MirnaService mirnaService;

	@Autowired
	CodeService codeService;


	@Test
	void DisTest(){
		System.out.println();
	}
//
//	@Test
//	void RNATest(){
//		System.out.println("aaa"+ mirnaService.selectPage(new Page<>(1,2),null).getRecords().get(0).getDescription());
//	}

	@Autowired
	SearchDiseaseMapper searchDiseaseMapper;

	@Autowired
	SearchMirnaMapper searchMirnaMapper;

//	@Test
//	void SearchTest1()
//	{
//		System.out.println(searchMirnaMapper.GetByMirna("hsa-mir-29a"));
//	}




	@Test
	public void findPageTest(){
		System.out.println(userService.findPage("",2,5));
	}

	@Autowired
	JavaMailSender javaMailSender;
	@Test
	public void sendSimpleMail() {
		long startTime = System.currentTimeMillis();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("这是一封测试邮件");
		message.setFrom("stukk123@163.com");
		message.setTo("2682548155@qq.com");
//        message.setCc("37xxxxx37@qq.com");
//        message.setBcc("14xxxxx098@qq.com");
		message.setSentDate(new Date());
		message.setText("这是测试邮件的正文");
		javaMailSender.send(message);
		long startTime1 = System.currentTimeMillis();
		System.out.println(startTime1 - startTime + " ms");
	}


	//很重要的加载缓存数据
	@Test
	public void HuanCun(){

		List<Mirna> list = mirnaService.query().list();
		String s = JSON.toJSONString(list);
		stringRedisTemplate.opsForValue().set("AllMirna",s);


		List<Disease> list1 = diseaseService.query().list();
		String s1 = JSON.toJSONString(list1);
		stringRedisTemplate.opsForValue().set("AllDisease",s1);

	}

	@Test
	public void TestRedis(){  //释放缓存
		String allDisease = stringRedisTemplate.opsForValue().get("AllDisease");
		List<Disease> diseases = (List<Disease>)JSON.parse(allDisease);
		System.out.println(diseases);
	}


}
