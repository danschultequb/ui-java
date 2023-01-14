package qub;

public interface UIWindowTests
{
    public static void test(TestRunner runner, Function0<? extends UIWindow> creator)
    {
        runner.testGroup(UIWindow.class, () ->
        {
            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIWindow window = creator.run();
                    final Color initialColor = window.getBackgroundColor();
                    test.assertThrows(() -> window.setBackgroundColor(null),
                        new PreConditionFailure("backgroundColor cannot be null."));
                    test.assertEqual(initialColor, window.getBackgroundColor());
                });

                final Action1<Color> setBackgroundColorTest = (Color color) ->
                {
                    runner.test("with " + color, (Test test) ->
                    {
                        final UIWindow window = creator.run();
                        final UIWindow setBackgroundColorResult = window.setBackgroundColor(color);
                        test.assertSame(window, setBackgroundColorResult);
                        test.assertEqual(color, window.getBackgroundColor());
                    });
                };

                setBackgroundColorTest.run(Color.blue);
                setBackgroundColorTest.run(Color.red);
                setBackgroundColorTest.run(Color.white);
                setBackgroundColorTest.run(Color.create(1, 2, 3, Color.ComponentMax));
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
