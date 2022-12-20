package qub;

public interface SwingUITextTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(SwingUIText.class,
            (TestResources resources) -> Tuple.create(resources.getMainAsyncRunner()),
            (AsyncScheduler mainAsyncRunner) ->
        {
            final Function0<SwingUIText> creator = () ->
            {
                final SwingUI ui = SwingUI.create(mainAsyncRunner);
                return ui.create(SwingUIText.class).await();
            };

            UITextTests.test(runner, creator);
            JComponentUIElementTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final SwingUIText uiElement = creator.run();

                    final SwingUIText setBackgroundColorResult = uiElement.setBackgroundColor(Color.red);
                    test.assertSame(uiElement, setBackgroundColorResult);
                    test.assertEqual(Color.red, uiElement.getBackgroundColor());
                });
            });
        });
    }
}
