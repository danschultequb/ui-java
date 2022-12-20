package qub;

public interface UIWindowTests
{
    public static void test(TestRunner runner, Function0<? extends UIWindow> creator)
    {
        runner.testGroup(UIWindow.class, () ->
        {
            UIElementTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    try (final UIWindow uiElement = creator.run())
                    {
                        final UIWindow setBackgroundColorResult = uiElement.setBackgroundColor(Color.red);
                        test.assertSame(uiElement, setBackgroundColorResult);
                        test.assertEqual(Color.red, uiElement.getBackgroundColor());
                    }
                });
            });

            runner.testGroup("setTitle(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    try (final UIWindow window = creator.run())
                    {
                        test.assertEqual("", window.getTitle());

                        test.assertThrows(() -> window.setTitle(null),
                            new PreConditionFailure("title cannot be null."));

                        test.assertEqual("", window.getTitle());
                    }
                });

                final Action1<String> setTitleTest = (String title) ->
                {
                    runner.test("with " + Strings.escapeAndQuote(title), (Test test) ->
                    {
                        try (final UIWindow window = creator.run())
                        {
                            test.assertEqual("", window.getTitle());

                            final UIWindow setTitleResult = window.setTitle(title);
                            test.assertSame(window, setTitleResult);

                            test.assertEqual(title, window.getTitle());
                        }
                    });
                };

                setTitleTest.run("");
                setTitleTest.run("hello");
            });

            runner.testGroup("setContent(UIElement)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    try (final UIWindow window = creator.run())
                    {
                        test.assertThrows(() -> window.setContent(null),
                            new PreConditionFailure("uiElement cannot be null."));
                        test.assertThrows(() -> window.getContent().await(),
                            new NotFoundException("No UIElement content has been set in this UIWindow."));
                    }
                });
            });

            runner.test("dispose()", (Test test) ->
            {
                try (final UIWindow window = creator.run())
                {
                    test.assertFalse(window.isDisposed());

                    test.assertTrue(window.dispose().await());
                    test.assertTrue(window.isDisposed());

                    test.assertFalse(window.dispose().await());
                    test.assertTrue(window.isDisposed());
                }
            });
        });
    }
}
