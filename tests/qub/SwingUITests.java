package qub;

public interface SwingUITests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(SwingUI.class,
            (TestResources resources) -> Tuple.create(resources.getMainAsyncRunner()),
            (AsyncScheduler mainAsyncRunner) ->
        {
            UITests.test(runner, () -> SwingUI.create(mainAsyncRunner));

            runner.test("createUIWindow()", (Test test) ->
            {
                try (final SwingUI ui = SwingUI.create(mainAsyncRunner);
                     final UIWindow window = ui.createUIWindow().await())
                {
                    test.assertFalse(window.isDisposed());
                    test.assertEqual(Color.create(238, 238, 238), window.getBackgroundColor());
                    test.assertEqual("", window.getTitle());
                    test.assertThrows(() -> window.getContent().await(),
                        new NotFoundException("No UIElement content has been set in this UIWindow."));
                }
            });

            runner.test("createSwingUIWindow()", (Test test) ->
            {
                try (final SwingUI ui = SwingUI.create(mainAsyncRunner);
                     final SwingUIWindow window = ui.createSwingUIWindow().await())
                {
                    test.assertFalse(window.isDisposed());
                    test.assertEqual(Color.create(238, 238, 238), window.getBackgroundColor());
                    test.assertEqual("", window.getTitle());
                    test.assertThrows(() -> window.getContent().await(),
                        new NotFoundException("No UIElement content has been set in this UIWindow."));
                }
            });

            runner.testGroup("dispose()", () ->
            {
                runner.test("with one window", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        final SwingUIWindow window = ui.createSwingUIWindow().await();
                        test.assertFalse(window.isDisposed());
                        test.assertFalse(ui.isDisposed());

                        test.assertTrue(ui.dispose().await());
                        test.assertTrue(ui.isDisposed());
                        test.assertTrue(window.isDisposed());

                        test.assertFalse(ui.dispose().await());
                        test.assertTrue(ui.isDisposed());
                        test.assertTrue(window.isDisposed());
                    }
                });
            });
        });
    }
}
