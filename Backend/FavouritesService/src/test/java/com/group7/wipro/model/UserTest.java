package com.group7.wipro.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static User user;

    @BeforeAll
    public static void setUp() throws Exception{
        Source s = new Source(null,"Insidesport.in");
        Article a = new Article(s,null,"IND vs IRE LIVE: Hardik Pandya hints at debuts to new faces in team: Follow LIVE - InsideSport","https://www.insidesport.in/ind-vs-ire-live-1st-t20-indias-playing-xi-for-1st-t20-indian-captain-hardik-pandya-to-address-the-press-at-530-pm-today-follow-india-vs-ireland-1st-t20-live/",null,"2022-06-25T18:41:38Z","IND vs IRE LIVE 1st T20: Hardik Pandya hints at debuts to new faces but says 'Will field our Best XI against Ireland': Follow INDIA vs IRELAND 1st T20 LIVE","IND vs IRE LIVE 1st T20: India Playing XI 1st T20 – As has been the case for the last few years, Hardik Pandya too kept the Playing XI close to his chest. However, he has hinted at handing debuts to … [+4366 chars]");
        Source s1 = new Source(null,"DW (English)");
        a.setArticleId(1);
        Article a1 = new Article(s1,"Deutsche Welle (www.dw.com)","WHO says monkeypox does not amount to 'public health emergency of international concern' - DW (English)","https://www.dw.com/en/who-says-monkeypox-does-not-amount-to-public-health-emergency-of-international-concern/a-62231249", "https://static.dw.com/image/61999158_6.jpg", "2022-06-25T20:33:45Z","The UN health agency stopped short of giving the outbreak the label of highest alert, despite the viral disease being detected in over 40 countries.","The World Health Organization (WHO) on Saturday said monkeypox was not a \"public health emergency of international concern.\"\r\nThe label, the UN agency's highest level of alert, would have given monke… [+1977 chars]");
        a1.setArticleId(2);
        List<Article> articleList = new ArrayList<Article>();
        articleList.add(a);
        articleList.add(a1);
        user = new User();
        user.setEmail("arushi@gmail.com");
        user.setArticleList(articleList);
    }
    @Test
    public void testEmailShouldNotBeEmpty() {
        assertTrue(user.getEmail().equals("arushi@gmail.com"), "User email is arushi@gmail.com");
    }
    @Test
    public void testArticleListShouldNotBeEmpty() {
        assertTrue(user.getArticleList().size()>0,"Article list is not empty");
    }

}