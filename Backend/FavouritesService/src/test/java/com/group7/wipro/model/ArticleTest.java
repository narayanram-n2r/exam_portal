package com.group7.wipro.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    private static Article article;
    @BeforeAll
    public static void setUp() throws Exception {
        Source s = new Source(null,"Insidesport.in");
        article = new Article(s,null,"IND vs IRE LIVE: Hardik Pandya hints at debuts to new faces in team: Follow LIVE - InsideSport","https://www.insidesport.in/ind-vs-ire-live-1st-t20-indias-playing-xi-for-1st-t20-indian-captain-hardik-pandya-to-address-the-press-at-530-pm-today-follow-india-vs-ireland-1st-t20-live/",null,"2022-06-25T18:41:38Z","IND vs IRE LIVE 1st T20: Hardik Pandya hints at debuts to new faces but says 'Will field our Best XI against Ireland': Follow INDIA vs IRELAND 1st T20 LIVE","IND vs IRE LIVE 1st T20: India Playing XI 1st T20 – As has been the case for the last few years, Hardik Pandya too kept the Playing XI close to his chest. However, he has hinted at handing debuts to … [+4366 chars]");
        article.setArticleId(1);
    }
    @Test
    public void testArticleIdShouldNotBeEmpty() {
        assertTrue(article.getArticleId() == 1, "Article Id should be 1");
    }
    @Test
    public void testSourceIdShouldNotBeEmpty() {
        assertTrue(article.getSource().getId()==null, "Article source id should be null");
    }
    @Test
    public void testSourceNameShouldNotBeEmpty() {
        assertTrue(article.getSource().getName().equals("Insidesport.in"), "Article source name should be Insidesport.in");
    }
    @Test
    public void testAuthorShouldNotBeEmpty() {
        assertTrue(article.getAuthor()==null, "Author name should be null");
    }
    @Test
    public void testTitleShouldNotBeEmpty() {
        assertTrue(article.getTitle().equals("IND vs IRE LIVE: Hardik Pandya hints at debuts to new faces in team: Follow LIVE - InsideSport"), "Title should be IND vs IRE LIVE: Hardik Pandya hints at debuts to new faces in team: Follow LIVE - InsideSport");
    }
    @Test
    public void testContentShouldNotBeEmpty() {
        assertTrue(article.getContent().equals("IND vs IRE LIVE 1st T20: India Playing XI 1st T20 – As has been the case for the last few years, Hardik Pandya too kept the Playing XI close to his chest. However, he has hinted at handing debuts to … [+4366 chars]"), "Content should be IND vs IRE LIVE 1st T20: India Playing XI 1st T20 – As has been the case for the last few years, Hardik Pandya too kept the Playing XI close to his chest. However, he has hinted at handing debuts to … [+4366 chars]");
    }
    @Test
    public void testPublishedAtShouldNotBeEmpty() {
        assertTrue(article.getPublishedAt().equals("2022-06-25T18:41:38Z"), "Published at should be 2022-06-25T18:41:38Z");
    }
    @Test
    public void testDescriptionShouldNotBeEmpty() {
        assertTrue(article.getDescription().equals("IND vs IRE LIVE 1st T20: Hardik Pandya hints at debuts to new faces but says 'Will field our Best XI against Ireland': Follow INDIA vs IRELAND 1st T20 LIVE"), "Description should be IND vs IRE LIVE 1st T20: Hardik Pandya hints at debuts to new faces but says 'Will field our Best XI against Ireland': Follow INDIA vs IRELAND 1st T20 LIVE");
    }
    @Test
    public void testUrlShouldNotBeEmpty() {
        assertTrue(article.getUrl().equals("https://www.insidesport.in/ind-vs-ire-live-1st-t20-indias-playing-xi-for-1st-t20-indian-captain-hardik-pandya-to-address-the-press-at-530-pm-today-follow-india-vs-ireland-1st-t20-live/"), "Url should be https://www.insidesport.in/ind-vs-ire-live-1st-t20-indias-playing-xi-for-1st-t20-indian-captain-hardik-pandya-to-address-the-press-at-530-pm-today-follow-india-vs-ireland-1st-t20-live/");
    }
    @Test
    public void testUrlToImgShouldNotBeEmpty() {
        assertTrue(article.getUrlToImage()==null, "Url to image should be null");
    }

}