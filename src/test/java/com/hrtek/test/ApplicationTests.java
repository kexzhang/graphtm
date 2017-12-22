package com.hrtek.test;

import com.hrtek.controller.LoginController;
import com.hrtek.controller.TempController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 *
 * @author zkx
 * @version 1.0.0
 *
 *
 */
@SpringBootTest
@WebAppConfiguration
public class ApplicationTests {

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(
               new TempController(),new LoginController()).build();
    }

    @Test
    public void getHello() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("11111111111")));
    }

    @Test
    public void testUserController() throws Exception {
//  	测试UserController
        RequestBuilder request = null;

        // 1、get查一下user列表，应该为空
        request = get("/temp/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("")));

        // 2、post提交一个user
        request = post("/temp/")
                .param("did", "1")
                .param("ename", "测试大师")
                .param("sex", "20");
        mvc.perform(request)
//				.andDo(MockMvcResultHandlers.print())
                .andExpect(content().string(equalTo("success")));

        // 3、get获取user列表，应该有刚才插入的数据
        request = get("/temp/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"did\":1,\"ename\":\"测试大师\",\"age\":20}]")));

        // 4、put修改id为1的user
        request = put("/temp/1")
                .param("ename", "测试终极大师")
                .param("sex", "30");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 5、get一个id为1的user
        request = get("/temp/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("{\"did\":1,\"ename\":\"测试终极大师\",\"sex\":30}")));

        // 6、del删除id为1的user
        request = delete("/temp/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

        // 7、get查一下user列表，应该为空
        request = get("/temp/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[]")));

    }

}