//package com.group7.wipro.service;
//
//import com.group7.wipro.exception.ArticleAlreadyExistsException;
//import com.group7.wipro.exception.ArticleNotFoundException;
//import com.group7.wipro.model.Article;
//import com.group7.wipro.model.Source;
//import com.group7.wipro.model.User;
//import com.group7.wipro.repository.UserDAO;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.annotation.Rollback;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.when;
//
//class FavouritesServiceTest {
//
//    @InjectMocks
//    FavouritesService favouritesService;
//    @Mock
//    UserDAO userDAO;
//    User user;
////    List<User> userList;
//    Article article;
//    List<Article> articleList;
//
//    @BeforeEach
//    public void setUp() throws Exception {
//        MockitoAnnotations.initMocks(this);
//        articleList = new ArrayList<Article>();
//        Source s = new Source(null,"Insidesport.in");
//        article = new Article(1,s,null,"IND vs IRE LIVE: Hardik Pandya hints at debuts to new faces in team: Follow LIVE - InsideSport","https://www.insidesport.in/ind-vs-ire-live-1st-t20-indias-playing-xi-for-1st-t20-indian-captain-hardik-pandya-to-address-the-press-at-530-pm-today-follow-india-vs-ireland-1st-t20-live/",null,"2022-06-25T18:41:38Z","IND vs IRE LIVE 1st T20: Hardik Pandya hints at debuts to new faces but says 'Will field our Best XI against Ireland': Follow INDIA vs IRELAND 1st T20 LIVE","IND vs IRE LIVE 1st T20: India Playing XI 1st T20 – As has been the case for the last few years, Hardik Pandya too kept the Playing XI close to his chest. However, he has hinted at handing debuts to … [+4366 chars]");
//        Source s1 = new Source(null,"DW (English)");
//        Article a1 = new Article(2,s1,"Deutsche Welle (www.dw.com)","WHO says monkeypox does not amount to 'public health emergency of international concern' - DW (English)","https://www.dw.com/en/who-says-monkeypox-does-not-amount-to-public-health-emergency-of-international-concern/a-62231249", "https://static.dw.com/image/61999158_6.jpg", "2022-06-25T20:33:45Z","The UN health agency stopped short of giving the outbreak the label of highest alert, despite the viral disease being detected in over 40 countries.","The World Health Organization (WHO) on Saturday said monkeypox was not a \"public health emergency of international concern.\"\r\nThe label, the UN agency's highest level of alert, would have given monke… [+1977 chars]");
//        articleList.add(a1);
//        user = new User("arushi@gmail.com",articleList);
//    }
//
//    @AfterEach
//    public void tearDown() throws Exception {
//        user = null;
//        articleList = null;
//    }
//
//    @Test
//    @Rollback(true)
//    public void testAddUserSuccess() throws ArticleAlreadyExistsException {
////        when(userDAO.ins).thenReturn(null);
//        when(userDAO.save(any())).thenReturn(user);
//        assertEquals(user, favouritesService.saveArticleToFavourite(any(),any()));
////        verify(userDAO, times(1)).getOne(any());
//        verify(userDAO, times(1)).save(any());
//    }
////
//////    @Test
//////    @Rollback(true)
//////    public void testAddUserFailure() throws UserAlreadyExistsException {
////////        when(userDAO.save(user)).thenReturn(user);
//////////        when(userDAO.getOne(any())).thenReturn(user);
////////        assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(user));
//////////        verify(userDAO, times(1)).getOne(any());
////////        verify(userDAO, times(0)).save(user);
////////        when(userDAO.getOne(any())).thenReturn(user);
//////        when(userService.addUser(user)).thenReturn(user);
//////
//////        assertThrows(UserAlreadyExistsException.class, () -> userService.addUser(user));
//////
////////        verify(userDAO, times(1)).getOne(any());
////////        verify(userDAO, times(0)).save(any());
//////    }
////
////    @Test
////    @Rollback(true)
////    public void testValidateUserFailure() throws ArticleNotFoundException {
////        when(userDAO.findByEmail(user.getEmail())).thenReturn(user);
////        assertThrows(UserNotFoundException.class, () -> userService.validate(user.getEmail(), user.getPassword()));
//////        verify(userDAO, times(1)).findById(any());
////    }
////
//////    @Test
//////    @Rollback(true)
//////    public void testValidateUserSuccess() throws UserNotFoundException {
////////        when(userDAO.save(user)).thenReturn(user);
////////        when(userDAO.findByEmail(user.getEmail())).thenReturn(user);
////////        assertEquals(user, userService.validate(user.getEmail(), user.getPassword()));
////////        verify(userDAO, times(1)).findByEmail(user.getEmail());
//////        when(userDAO.findByEmail(user.getEmail())).thenReturn(user);
//////        when(userDAO.save(user)).thenReturn(user);
//////
//////        assertEquals(user, userService.validate(user.getEmail(), user.getPassword()));
//////
//////        verify(userDAO, times(1)).findByEmail(user.getEmail());
//////
//////    }
////
////    @Test
////    @Rollback(true)
////    public void testGetAllUserSuccess() {
////        when(userDAO.findAll()).thenReturn(userList);
////        assertEquals(userList, userService.getAllUsers());
////        verify(userDAO, times(1)).findAll();
////    }
////
////    @Test
////    @Rollback(true)
////    public void testUpdateUserFailure() throws ArticleNotFoundException {
////        when(userDAO.saveAndFlush(any())).thenReturn(user);
////        assertThrows(UserNotFoundException.class, () -> userService.updateUser(user, user.getEmail()));
////        verify(userDAO, times(0)).saveAndFlush(any());
////    }
////
////    @Test
////    @Rollback(true)
////    public void testUpdateUserSuccess() throws ArticleNotFoundException {
////        when(userDAO.findByEmail(user.getEmail())).thenReturn(user);
////        when(userDAO.save(user)).thenReturn(user);
////        user.setCity("Allahabad");
////        userList.add(user);
////        User fetchedUser = userService.updateUser(user, user.getEmail());
////        assertEquals(user, fetchedUser);
////
////    }
//
//}