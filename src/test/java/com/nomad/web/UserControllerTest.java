package com.nomad.web;

import com.nomad.dao.UserRepository;
import com.nomad.domain.User;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class UserControllerTest {

    @Test
    public void shouldShowRecentUsers() throws Exception {
        List<User> expectedUsers = createUserList(20);
        UserRepository mockRepository =
                mock(UserRepository.class);
        when(mockRepository.findUsers(Long.MAX_VALUE, 20))
                .thenReturn(expectedUsers);

        UserController controller = new UserController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(
                        new InternalResourceView("/WEB-INF/views/users.jsp")
                ).build();

        mockMvc.perform(get("/users"))
                .andExpect(view().name("users"))
                .andExpect(model().attributeExists("userList"))
                .andExpect(model().attribute("userList",
                        hasItems(expectedUsers.toArray())));

    }

    private List<User> createUserList(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(new User("user" + i, "m", 20));
        }
        return users;
    }

    @Test
    public void shouldShowRegistration() throws Exception {
        UserController controller = new UserController();
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(get("/register"))
                .andExpect(view().name("registerForm"));
    }

    @Test
    public void shouldProcessRegistration() throws Exception {
        UserRepository mockRepository = mock(UserRepository.class);
        User unsaved = new User("zhangsan", "m", 20);
        User saved = new User(1L, "zhangsan", "m", 20);
        when(mockRepository.save(unsaved)).thenReturn(saved);

        UserController controller = new UserController(mockRepository);
        MockMvc mockMvc = standaloneSetup(controller).build();

        mockMvc.perform(post("/register")
                .param("username", "zhangsan")
                .param("sex", "m")
                .param("age", "20"))
                .andExpect(redirectedUrl("/user/zhangsan"));

        verify(mockRepository, atLeastOnce()).save(unsaved);
    }
}
