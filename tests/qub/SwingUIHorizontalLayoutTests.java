package qub;

public interface SwingUIHorizontalLayoutTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(SwingUIHorizontalLayout.class,
            (TestResources resources) -> Tuple.create(resources.getMainAsyncRunner()),
            (AsyncScheduler mainAsyncRunner) ->
        {
            final Function0<SwingUIHorizontalLayout> creator = () ->
            {
                final SwingUI ui = SwingUI.create(mainAsyncRunner);
                return ui.create(SwingUIHorizontalLayout.class).await();
            };

            UIHorizontalLayoutTests.test(runner, creator);
            JComponentUIElementTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final SwingUIHorizontalLayout layout = creator.run();

                    final SwingUIHorizontalLayout setBackgroundColorResult = layout.setBackgroundColor(Color.red);
                    test.assertSame(layout, setBackgroundColorResult);
                    test.assertEqual(Color.red, layout.getBackgroundColor());
                });
            });
        });
    }
}
