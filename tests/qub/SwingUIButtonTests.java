package qub;

public interface SwingUIButtonTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(SwingUIButton.class,
            (TestResources resources) -> Tuple.create(resources.getMainAsyncRunner()),
            (AsyncScheduler mainAsyncRunner) ->
        {
            final Function0<SwingUIButton> creator = () ->
            {
                final SwingUI ui = SwingUITests.createUI(mainAsyncRunner);
                return ui.create(SwingUIButton.class).await();
            };

            UIButtonTests.test(runner, creator);
            JComponentUIElementTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final SwingUIButton uiElement = creator.run();

                    final SwingUIButton setBackgroundColorResult = uiElement.setBackgroundColor(Color.red);
                    test.assertSame(uiElement, setBackgroundColorResult);
                    test.assertEqual(Color.red, uiElement.getBackgroundColor());
                });
            });

            runner.testGroup("click()", () ->
            {
                runner.test("with no callbacks", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        final SwingUIButton button = ui.create(SwingUIButton.class).await();
                        button.click();
                    }
                });

                runner.test("with one callback", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        final List<Integer> values = List.create();
                        final SwingUIButton button = ui.create(SwingUIButton.class).await();
                        button.onClick(() -> values.add(values.getCount()));

                        button.click();
                        test.assertEqual(Iterable.create(0), values);

                        button.click();
                        test.assertEqual(Iterable.create(0, 1), values);
                    }
                });

                runner.test("with two callbacks", (Test test) ->
                {
                    try (final SwingUI ui = SwingUI.create(mainAsyncRunner))
                    {
                        final List<Integer> values = List.create();
                        final SwingUIButton button = ui.create(SwingUIButton.class).await();

                        button.onClick(() -> values.add(values.getCount()));
                        button.onClick(() -> values.add(values.getCount() + 100));

                        button.click();
                        test.assertEqual(Iterable.create(100, 1), values);

                        button.click();
                        test.assertEqual(Iterable.create(100, 1, 102, 3), values);
                    }
                });
            });
        });
    }
}
