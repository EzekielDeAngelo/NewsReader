package com.antho.newsreader;

import com.bumptech.glide.load.engine.Resource;

import static org.mockito.Mockito.verify;

public class TopStoriesViewModelTest {
   /* static NewsViewModel sViewModel;


    @BeforeClass
    public static void classSetUp() throws Exception {
        sViewModel = NewsViewModel.getInstance();
    }

    @Before
    public void setUp() throws Exception
    {
        //sViewModel.getNews("Top Stories").observe();
    }
    @Mock
    Observer observer = new Observer<NewsList>() {
        @Override
        public void onChanged(@Nullable NewsList topStoriesNewsList) {

        }
    };
    @Test
    void fetchTopStories()
    {
        NewsList list = new NewsList() {
            @Override
            public List<News> results() {
                return null;
            }
        };
        sViewModel.getNews("Top Stories").observeForever(observer);
        verify(observer).onChanged(list);
    }

    /*private static List<News> NEWS = Lists.newArrayList(new News("Title1", "Description1"),
            new News("Title2", "Description2"));

    private static List<News> EMPTY_NEWS = new ArrayList<>(0);


    @Mock


    */
}
/*
 static DataManager sDataManager;

    @BeforeClass
    public static void classSetUp() throws Exception {
        sDataManager = DataManager.getInstance();
    }

    @Before
    public void setUp() throws Exception {
        sDataManager.getNotes().clear();
        sDataManager.initializeExampleNotes();
    }

    @Test
    public void createNewNote() throws Exception {
        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final String noteText = "This is the body text of my test note";

        int noteIndex = sDataManager.createNewNote();
        NoteInfo newNote = sDataManager.getNotes().get(noteIndex);
        newNote.setCourse(course);
        newNote.setTitle(noteTitle);
        newNote.setText(noteText);

        NoteInfo compareNote = sDataManager.getNotes().get(noteIndex);
        assertEquals(course, compareNote.getCourse());
        assertEquals(noteTitle, compareNote.getTitle());
        assertEquals(noteText, compareNote.getText());
    }

    @Test
    public void findSimilarNotes() throws Exception {
        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final String noteText1 = "This is the body text of my test note";
        final String noteText2  = "This is the body of my second test note";

        int noteIndex1 = sDataManager.createNewNote();
        NoteInfo newNote1 = sDataManager.getNotes().get(noteIndex1);
        newNote1.setCourse(course);
        newNote1.setTitle(noteTitle);
        newNote1.setText(noteText1);

        int noteIndex2 = sDataManager.createNewNote();
        NoteInfo newNote2 = sDataManager.getNotes().get(noteIndex2);
        newNote2.setCourse(course);
        newNote2.setTitle(noteTitle);
        newNote2.setText(noteText2);

        int foundIndex1 = sDataManager.findNote(newNote1);
        assertEquals(noteIndex1, foundIndex1);

        int foundIndex2 = sDataManager.findNote(newNote2);
        assertEquals(noteIndex2, foundIndex2);
    }

    @Test
    public void createNewNoteOneStepCreation() {
        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final String noteText = "This is the body of my test note";

        int noteIndex = sDataManager.createNewNote(course, noteTitle, noteText);

        NoteInfo compareNote = sDataManager.getNotes().get(noteIndex);
        assertEquals(course, compareNote.getCourse());
        assertEquals(noteTitle, compareNote.getTitle());
        assertEquals(noteText, compareNote.getText());
    }
 */
