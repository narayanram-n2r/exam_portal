package com.group7.wipro.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.group7.wipro.exception.ArticleAlreadyExistsException;
import com.group7.wipro.exception.ArticleNotFoundException;
import com.group7.wipro.model.Article;
import com.group7.wipro.model.Source;
import com.group7.wipro.model.User;
import com.group7.wipro.repository.UserDAO;
import com.group7.wipro.service.FavouritesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class FavouritesControllerTest {

    private MockMvc mockMvc;

    private User user;

    @Mock
    private UserDAO userDAO;
    @MockBean
    private FavouritesService favouritesService;

    @InjectMocks
    private FavouritesController favouritesController;

    Article article;

    List<Article> articleList;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(favouritesController).build();
        articleList = new ArrayList<Article>();
        Source s = new Source(null,"Insidesport.in");
        article = new Article(s,null,"IND vs IRE LIVE: Hardik Pandya hints at debuts to new faces in team: Follow LIVE - InsideSport","https://www.insidesport.in/ind-vs-ire-live-1st-t20-indias-playing-xi-for-1st-t20-indian-captain-hardik-pandya-to-address-the-press-at-530-pm-today-follow-india-vs-ireland-1st-t20-live/",null,"2022-06-25T18:41:38Z","IND vs IRE LIVE 1st T20: Hardik Pandya hints at debuts to new faces but says 'Will field our Best XI against Ireland': Follow INDIA vs IRELAND 1st T20 LIVE","IND vs IRE LIVE 1st T20: India Playing XI 1st T20 – As has been the case for the last few years, Hardik Pandya too kept the Playing XI close to his chest. However, he has hinted at handing debuts to … [+4366 chars]");
        article.setArticleId(1);
        Source s1 = new Source(null,"DW (English)");
        Article a1 = new Article(s1,"Deutsche Welle (www.dw.com)","WHO says monkeypox does not amount to 'public health emergency of international concern' - DW (English)","https://www.dw.com/en/who-says-monkeypox-does-not-amount-to-public-health-emergency-of-international-concern/a-62231249", "https://static.dw.com/image/61999158_6.jpg", "2022-06-25T20:33:45Z","The UN health agency stopped short of giving the outbreak the label of highest alert, despite the viral disease being detected in over 40 countries.","The World Health Organization (WHO) on Saturday said monkeypox was not a \"public health emergency of international concern.\"\r\nThe label, the UN agency's highest level of alert, would have given monke… [+1977 chars]");
        a1.setArticleId(2);
        articleList.add(a1);
        user = new User("arushi@gmail.com",articleList);
    }

    @Test
    public void addArticleSuccess() throws Exception {
        when(favouritesService.saveArticleToFavourite(any(),any())).thenReturn(user);
        System.out.println("test.............");
        mockMvc.perform(post("/arushi@gmail.com/article").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addArticleFailure() throws Exception {
        when(favouritesService.saveArticleToFavourite(any(),any())).thenThrow(ArticleAlreadyExistsException.class);
        mockMvc.perform(post("/arushi@gmail.com/article")
                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isInternalServerError()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteArticleSuccess() throws Exception {
        when(favouritesService.deleteArticleFromFavorite(1,"arushi@gmail.com")).thenReturn(user);
        mockMvc.perform(delete("/arushi@gmail.com/1").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void deleteArticleFailure() throws Exception {
        when(favouritesService.deleteArticleFromFavorite(1,"arushi@gmail.com")).thenThrow(ArticleNotFoundException.class);
        mockMvc.perform(delete("/arushi@gmail.com/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
//
    @Test
    public void getAllArticlesSuccess() throws Exception {
        when(favouritesService.getArticlesList(any())).thenReturn(articleList);
        mockMvc.perform(get("/fav/arushi@gmail.com").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
//
//    @Test
//    public void loginUserSuccess() throws Exception {
//        when(userService.validate(any(),any())).thenReturn(user);
//        System.out.println("test.............");
//        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    public void loginUserFailure() throws Exception {
//        when(userService.validate(any(),any())).thenThrow(UserNotFoundException.class);
//        mockMvc.perform(post("/login")
//                        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(user)))
//                .andExpect(status().isConflict()).andDo(MockMvcResultHandlers.print());
//    }
//
    private static String asJsonString(final Object obj) {
        try {
            ObjectMapper objmapper = new ObjectMapper();
            objmapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objmapper.registerModule(new JavaTimeModule());
            return objmapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}