package qub;

public interface UICanvasTests
{
    public static void test(TestRunner runner, Function0<? extends UICanvas> creator)
    {
        PreCondition.assertNotNull(runner, "runner");
        PreCondition.assertNotNull(creator, "creator");

        runner.testGroup(UICanvas.class, () ->
        {
            UIElementTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final UICanvas canvas = creator.run();

                    final UICanvas setBackgroundColorResult = canvas.setBackgroundColor(Color.red);
                    test.assertSame(canvas, setBackgroundColorResult);
                    test.assertEqual(Color.red, canvas.getBackgroundColor());
                });
            });

            runner.testGroup("setPaintAction(Action1<UIPainter>)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UICanvas canvas = creator.run();

                    test.assertThrows(() -> canvas.setPaintAction(null),
                        new PreConditionFailure("paintAction cannot be null."));
                });

                runner.test("with not null", (Test test) ->
                {
                    final UICanvas canvas = creator.run();

                    final UICanvas setPaintActionResult = canvas.setPaintAction((UIPainter painter) -> {});
                    test.assertSame(canvas, setPaintActionResult);
                });
            });

            runner.testGroup("paint(UIPainter)", () ->
            {
                runner.test("with null painter", (Test test) ->
                {
                    final UICanvas canvas = creator.run();

                    test.assertThrows(() -> canvas.paint(null),
                        new PreConditionFailure("painter cannot be null."));
                });

                runner.test("with non-null painter", (Test test) ->
                {
                    final UICanvas canvas = creator.run();
                    final FakeUIPainter painter = FakeUIPainter.create();

                    // Just invoke the method to ensure that no exceptions get thrown.
                    canvas.paint(painter);
                });
            });
        });
    }
}
