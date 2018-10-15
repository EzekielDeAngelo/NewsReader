package com.antho.newsreader;

public class SearchViewModelTest {
}
/*public class StoriesViewModelTest {



    private StoriesViewModel storiesViewModel;



    @Before

    public void setUp() throws Exception {

        storiesViewModel = new StoriesViewModel();

    }



    @Test

    public void testLoading() throws Exception {

        TestObserver<Boolean> testObserver = storiesViewModel.loading().test();

        storiesViewModel.loadingUpdated().accept(true);

        storiesViewModel.loadingUpdated().accept(false);



        testObserver.assertValues(true, false);

    }



    @Test

    public void testStoriesLoaded() throws Exception {

        StoriesApiResponse response =

                TestUtils.loadJson("mock/get_top_stories.json", StoriesApiResponse.class);

        storiesViewModel.storyUpdated().accept(response.stories());

        storiesViewModel.stories().test().assertValue(response.stories());



    }



    @Test

    public void testPopularStoriesLoaded() throws Exception {

        PopularStoriesApiResponse response =

                TestUtils.loadJson("mock/get_popular_stories.json", PopularStoriesApiResponse.class);

        storiesViewModel.popularStoriesUpdated().accept(response.popularStories());

        storiesViewModel.popularStories().test().assertValue(response.popularStories());

    }



    @Test

    public void testErrorForStoriesForFailure() throws Exception {

        storiesViewModel.onError().accept(new IOException());

        storiesViewModel.error().test().assertValue(R.string.api_error_story);

    }



    @Test

    public void testErrorForStoriesForSuccess() throws Exception {

        storiesViewModel.storyUpdated().accept(Collections.emptyList());

        storiesViewModel.error().test().assertValue(-1);

    }



    @Test

    public void testErrorForPopularStoriesForSuccess() throws Exception {

        storiesViewModel.popularStoriesUpdated().accept(Collections.emptyList());

        storiesViewModel.error().test().assertValue(-1);

    }


*/
