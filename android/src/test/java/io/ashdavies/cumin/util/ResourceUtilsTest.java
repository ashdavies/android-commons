package io.ashdavies.cumin.util;

@RunWith(ApplicationTestRunner.class)
public class ResourceUtilsTest {

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Mock
    Context context;

    @Mock
    Resources resources;

    @Before
    public void setUp() {
        when(context.getResources()).thenReturn(resources);
    }

    @Test
    public void assertGetDimensionPixelSize() {
        ResourceUtil.getDimensionPixelSize(context, R.dimen.textSizePrimary);
        verify(resources, times(1)).getDimensionPixelSize(R.dimen.textSizePrimary);
    }
}
