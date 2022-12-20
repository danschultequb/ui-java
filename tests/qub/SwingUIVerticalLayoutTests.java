package qub;

public interface SwingUIVerticalLayoutTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(SwingUIVerticalLayout.class,
            (TestResources resources) -> Tuple.create(resources.getMainAsyncRunner()),
            (AsyncScheduler mainAsyncRunner) ->
        {
            final Function0<SwingUIVerticalLayout> creator = () ->
            {
                final SwingUI ui = SwingUI.create(mainAsyncRunner);
                return ui.create(SwingUIVerticalLayout.class).await();
            };

            UIVerticalLayoutTests.test(runner, creator);
            JComponentUIElementTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final SwingUIVerticalLayout layout = creator.run();

                    final SwingUIVerticalLayout setBackgroundColorResult = layout.setBackgroundColor(Color.red);
                    test.assertSame(layout, setBackgroundColorResult);
                    test.assertEqual(Color.red, layout.getBackgroundColor());
                });
            });
        });
    }
}
