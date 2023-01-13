package qub;

public interface EveryoneSwingUITests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(EveryoneSwingUI.class,
            (TestResources resources) -> Tuple.create(resources.getMainAsyncRunner()),
            (AsyncScheduler mainAsyncRunner) ->
        {
            final Function0<EveryoneSwingUI> creator = () ->
            {
                return EveryoneSwingUI.create(mainAsyncRunner);
            };

            UITests.test(runner, creator);

            runner.test("createUIWindow()", (Test test) ->
            {
                try (final EveryoneSwingUI ui = creator.run();
                     final UIWindow window = ui.createUIWindow().await())
                {
                    test.assertFalse(window.isDisposed());
                    test.assertEqual(Color.create(238, 238, 238), window.getBackgroundColor());
                    test.assertEqual("", window.getTitle());
                    test.assertThrows(() -> window.getContent().await(),
                        new NotFoundException("No UIElement content has been set in this UIWindow."));
                }
            });

            runner.test("createEveryoneUIWindow()", (Test test) ->
            {
                try (final EveryoneSwingUI ui = creator.run();
                     final EveryoneSwingUIWindow window = ui.createEveryoneUIWindow().await())
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
                    try (final EveryoneSwingUI ui = creator.run())
                    {
                        final EveryoneSwingUIWindow window = ui.createEveryoneUIWindow().await();
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