package tweettest;


import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.UUID;

public class TweetAPIClientTest {

    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI(){
        this.tweetAPIClient=new TweetAPIClient();
    }

    @Test(enabled = true)
    public void testUserCanTweetSuccessfully(){
        // 1. user send a tweet
        String tweet="Check user ID"+ UUID.randomUUID().toString();
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);
        System.out.println(response.extract().body().asString());
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = true)
    public void testUserCanNotTweetTheSameTweetTwiceInARow(){
        // 1. user send a tweet
        // String tweet="We are learning RestAPI Automation and Tweet check"+ UUID.randomUUID().toString();
        String tweet="We are learning RestAPI Automation and Tweet check";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);

        System.out.println(response.extract().body().asString());
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
        // User send the same tweet again
        response= this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet was unsuccessful
        response.statusCode(403);
        //System.out.println(response.extract().body().asString());
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, expectedMessage);
        Assert.assertNotSame("200", 403);
    }
    @Test(enabled = true)
    public void testDelete(){
        String tweet="We are learning RestAPI Automation and Tweet check";
        ValidatableResponse response=this.tweetAPIClient.deleteTweet(1311334646215593985l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    /**
     *  Create reTweet with valid data
     */
    @Test(enabled = true)
    public void testCreateRetweet(){
        String retweet="RT @cnnbrk: Nearly 130 people were arrested Wednesday in Louisville protests over the Breonna Taylor case, the city's interim police chief…";
        ValidatableResponse response=this.tweetAPIClient.createReTweet(1309188858433724422l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(retweet,actualTweet);
    }
    /**
     *  Create reTweet with valid data1
     */
    @Test(enabled = true)
    public void testCreateRetweet1(){
        String retweet="Asked by debate moderator Chris Wallace to condemn white supremacists and militia groups, Pres. Trump replies, \"Sure, I'm willing to do that.\"\n" +
                "\"Then do it, sir,\" Wallace says. http://abcn.ws/debate #Debates2020";
        ValidatableResponse response=this.tweetAPIClient.createReTweet(1311131883292459008l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(retweet,actualTweet);
    }
    /**
     * Favorites Tweet create (Like)1
     */
    @Test(enabled = true)
    public void FavoritesTweetID1(){
        String tweet="Asked by debate moderator Chris Wallace to condemn white supremacists and militia groups, Pres. Trump replies, \\\"Sure, I'm willing to do that.\\\"\\n\" +\n" +
                "                \"\\\"Then do it, sir,\\\" Wallace says. http://abcn.ws/debate #Debates2020";
        ValidatableResponse response=this.tweetAPIClient.favoritesTweet(1311131883292459008l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    /**
     * Favorites Tweet destroy (unLike)1
     */
    @Test(enabled = true)
    public void unLikeFavoritesTweet1(){
        String tweet="Asked by debate moderator Chris Wallace to condemn white supremacists and militia groups, Pres. Trump replies, \"Sure, I'm willing to do that.\"\n" +
                "\"Then do it, sir,\" Wallace says. http://abcn.ws/debate #Debates2020";
        ValidatableResponse response=this.tweetAPIClient.unlikeFavoritesTweet(1311131883292459008l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    /**
     *  Create reTweet with valid data2
     */
    @Test(enabled = true)
    public void testCreateRetweet2(){
        String retweet="There are three ways to vote this year in NY:\n" +
                "\n" +
                "1️⃣ By Absentee Ballot (You can either mail your ballot or drop it at any Early Voting or Election Day poll site, or at your local BOE by 9pm on Nov 3.)\n" +
                "\n" +
                "2️⃣ By Voting Early (Oct 24-Nov 1)\n" +
                "\n" +
                "3️⃣ By Voting In-Person on Election Day, Nov 3";
        ValidatableResponse response=this.tweetAPIClient.createReTweet(1303470769754734599l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(retweet,actualTweet);
    }
    /**
     * Favorites Tweet create (Like)2
     */
    @Test(enabled = true)
    public void FavoritesTweetID2(){
        String tweet="There are three ways to vote this year in NY:\n" +
                "\n" +
                "1️⃣ By Absentee Ballot (You can either mail your ballot or drop it at any Early Voting or Election Day poll site, or at your local BOE by 9pm on Nov 3.)\n" +
                "\n" +
                "2️⃣ By Voting Early (Oct 24-Nov 1)\n" +
                "\n" +
                "3️⃣ By Voting In-Person on Election Day, Nov 3";
        ValidatableResponse response=this.tweetAPIClient.favoritesTweet(1303470769754734599l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    /**
     * Favorites Tweet destroy (unLike)2
     */
    @Test(enabled = true)
    public void unLikeFavoritesTweet2(){
        String tweet="There are three ways to vote this year in NY:\n" +
                "\n" +
                "1️⃣ By Absentee Ballot (You can either mail your ballot or drop it at any Early Voting or Election Day poll site, or at your local BOE by 9pm on Nov 3.)\n" +
                "\n" +
                "2️⃣ By Voting Early (Oct 24-Nov 1)\n" +
                "\n" +
                "3️⃣ By Voting In-Person on Election Day, Nov 3";
        ValidatableResponse response=this.tweetAPIClient.unlikeFavoritesTweet(1303470769754734599l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    /**
     *  Create reTweet with invalid data
     */
    @Test
    public void testCreateRetweetWithInvalidData(){
        ValidatableResponse response=this.tweetAPIClient.createReTweetWithInvalidData(1309188858433724422l);
        int actualReTweet=response.extract().statusCode();
        Assert.assertEquals(404,actualReTweet);
    }
    /**
     *  Create reTweet with invalid data1
     */
    @Test
    public void testCreateRetweetWithInvalidData1(){
        ValidatableResponse response=this.tweetAPIClient.createReTweetWithInvalidData(1309178858433724422l);
        int actualReTweet=response.extract().statusCode();
        Assert.assertEquals(404,actualReTweet);
    }
    /**
     * Un reTweet with valid data
     */
    @Test(enabled = true)
    public void testUnReTweet(){
        String tweet="Nearly 130 people were arrested Wednesday in Louisville protests over the Breonna Taylor case, the city's interim police chief says";
        ValidatableResponse response=this.tweetAPIClient.unReTweet(1309188858433724422l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    /**
     * Un reTweet with invalid data
     */
    @Test(enabled = true)
    public void testUnReTweetInvalidId(){
        ValidatableResponse response=this.tweetAPIClient.unReTweetInvalidID(324236500424335363l);
        int actualUnRetweet=response.extract().statusCode();
        Assert.assertEquals(404,actualUnRetweet);
    }
    /**
     * Un reTweet with invalid data1
     */
    @Test(enabled = true)
    public void testUnReTweetInvalidId1(){
        ValidatableResponse response=this.tweetAPIClient.unReTweetInvalidID(324236500824335363l);
        int actualUnRetweet=response.extract().statusCode();
        Assert.assertEquals(404,actualUnRetweet);
    }
    /**
     * Un reTweet with invalid data2
     */
    @Test(enabled = true)
    public void testUnReTweetInvalidId2(){
        ValidatableResponse response=this.tweetAPIClient.unReTweetInvalidID(324236900824335363l);
        int actualUnRetweet=response.extract().statusCode();
        Assert.assertEquals(404,actualUnRetweet);
    }
    /**
     * Un reTweet with invalid data3
     */
    @Test(enabled = true)
    public void testUnReTweetInvalidId3(){
        ValidatableResponse response=this.tweetAPIClient.unReTweetInvalidID(324236900804335363l);
        int actualUnRetweet=response.extract().statusCode();
        Assert.assertEquals(404,actualUnRetweet);
    }
    /**
     * Favorites Tweet create (Like)
     */
    @Test(enabled = true)
    public void FavoritesTweetID(){
        String tweet="Hello";
        ValidatableResponse response=this.tweetAPIClient.favoritesTweet(1309885193134669825l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    /**
     * Favorites Tweet destroy (unLike)
     */
    @Test(enabled = true)
    public void unLikeFavoritesTweet(){
        String tweet="Hello";
        ValidatableResponse response=this.tweetAPIClient.unlikeFavoritesTweet(1309885193134669825l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    /**
     * Favorites with invalid data
     */
    @Test(enabled = true)
    public void TestCreateTweetWithWrongFavoritesEndPoint(){
        String tweet = "Check user ID042a5d91-b156-4b9d-9dfa-ca94b5638801";
        ValidatableResponse response = tweetAPIClient.favoritesTweetWithWrongFavoritesEndPoint(1308874571995664386L);
        int actualCode = response.extract().statusCode();
        Assert.assertEquals(404, actualCode);
    }
    /**
     * Show tweet id with valid data
     */
    @Test(enabled = true)
    public void testShowTweetID(){
        String tweet="Hello";
        ValidatableResponse response=this.tweetAPIClient.showTweetID(1309885193134669825l);
        response.statusCode(200);
        System.out.println(response.extract().body().asString());
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    /**
     * create Status LookUp with valid data
     */
    @Test(enabled = true)
    public void testGetStatusLookUp(){
        ValidatableResponse response=this.tweetAPIClient.getStatusLookUp(20,"EhsanKabir15");
        int actualResult=response.extract().statusCode();
        System.out.println(actualResult);
        System.out.println(response.extract().body().asString());
        Assert.assertEquals(200,actualResult);
    }
    /**
     * create Status LookUp with invalid data
     */
    @Test(enabled = true)
    public void testGetStatusLookUpWithInvalidData(){
        ValidatableResponse response=this.tweetAPIClient.getStatusLookUpWithInvalidData(20,"EhsanKabir15");
        int actualResult=response.extract().statusCode();
        Assert.assertEquals(404,actualResult);
    }
    /**
     * Favorites list with valid data
     */
    @Test(enabled = true)
    public void testFavoritesListTweet(){
        ValidatableResponse response=this.tweetAPIClient.favoritesListTweet("EhsanKabir15");
        int actualCode = response.extract().statusCode();
        System.out.println(response.extract().body().asString());
        Assert.assertEquals(200,actualCode);
    }
    /**
     * Favorites list with invalid data
     */
    @Test(enabled = true)
    public void testFavoritesListWithInvalidTweet(){
        ValidatableResponse response=this.tweetAPIClient.favoritesListWithInvalidTweet("EhsanKabir15");
        int actualCode = response.extract().statusCode();
        System.out.println(response.extract().body().asString());
        Assert.assertEquals(404,actualCode);
    }
    @Test(enabled = true)
    public void testGetRetweets(){
        String tweet="Nearly 130 people were arrested Wednesday in Louisville protests over the Breonna Taylor case, the city's interim police chief says";
        ValidatableResponse response=this.tweetAPIClient.getRetweets(1309188858433724422l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = true)
    public void testGetHomeTimeLineTweets(){
        ValidatableResponse response=this.tweetAPIClient.getHomeTimeLineTweet("EhsanKabir15");
        int actualCode = response.extract().statusCode();
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(200,actualCode);
    }
//
//
    /**
     * GET All Tweet Information with valid data
     */
    @Test
    public void testGetUserTimeTweetWithValidData(){
        ValidatableResponse response = this.tweetAPIClient.getUserTimeTweetWithValidData(10,"EhsanKabir15");
        int actualCode = response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(200,actualCode);
    }
    /**
     * GET All Tweet Information with invalid end point
     */
    @Test
    public void testGetUserTimeTweetWithInValidData(){
        ValidatableResponse response = this.tweetAPIClient.getUserTimeTweetWithInvalidPoint(0,"EhsanKabir15");
        int actualCode = response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(404,actualCode);
    }
    /**
     * Get collections entries
     */
    @Test(enabled = true)
    public void testGetCollectionsEntriesTweets(){
        ValidatableResponse response=this.tweetAPIClient.getEntriesCollectionsTweet("custom-1309755845689868288");
        int actualCode=response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(200,actualCode);
    }






}
