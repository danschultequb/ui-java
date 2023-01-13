package qub;

public interface SwingUICanvasTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(SwingUICanvas.class,
            (TestResources resources) -> Tuple.create(resources.getMainAsyncRunner()),
            (AsyncScheduler mainAsyncRunner) ->
        {
            final Function0<SwingUICanvas> creator = () ->
            {
                final SwingUI ui = SwingUI.create(mainAsyncRunner);
                return ui.create(SwingUICanvas.class).await();
            };

            UICanvasTests.test(runner, creator);
            JComponentUIElementTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final SwingUICanvas canvas = creator.run();

                    final SwingUICanvas setBackgroundColorResult = canvas.setBackgroundColor(Color.red);
                    test.assertSame(canvas, setBackgroundColorResult);
                    test.assertEqual(Color.red, canvas.getBackgroundColor());
                });
            });
        });
    }
}
