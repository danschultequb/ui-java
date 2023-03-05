package qub;

public interface UIButtonTests
{
    public static void test(TestRunner runner, Function0<? extends UIButton> creator)
    {
        PreCondition.assertNotNull(runner, "runner");
        PreCondition.assertNotNull(creator, "creator");

        runner.testGroup(UIButton.class, () ->
        {
            UIElementTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final UIButton uiElement = creator.run();

                    final UIButton setBackgroundColorResult = uiElement.setBackgroundColor(Color.red);
                    test.assertSame(uiElement, setBackgroundColorResult);
                    test.assertEqual(Color.red, uiElement.getBackgroundColor());
                });
            });

            runner.testGroup("setText(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIButton button = creator.run();

                    test.assertThrows(() -> button.setText(null),
                        new PreConditionFailure("text cannot be null."));

                    test.assertEqual("", button.getText());
                });

                runner.test("with empty", (Test test) ->
                {
                    final UIButton button = creator.run();

                    final UIButton setTextResult = button.setText("");
                    test.assertSame(button, setTextResult);

                    test.assertEqual("", button.getText());
                });

                runner.test("with non-empty", (Test test) ->
                {
                    final UIButton button = creator.run();

                    final UIButton setTextResult = button.setText("hello");
                    test.assertSame(button, setTextResult);

                    test.assertEqual("hello", button.getText());
                });
            });

            runner.testGroup("onClick(Action0)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIButton button = creator.run();
                    test.assertThrows(() -> button.onClick(null),
                        new PreConditionFailure("callback cannot be null."));
                });

                runner.test("with callback that throws an exception", (Test test) ->
                {
                    final UIButton button = creator.run();

                    final Disposable subscription = button.onClick(() ->
                    {
                        throw new RuntimeException("hello");
                    });
                    test.assertNotNull(subscription);

                    test.assertTrue(subscription.dispose().await());
                });

                runner.test("with callback that doesn't throw an exception", (Test test) ->
                {
                    final UIButton button = creator.run();

                    final Disposable subscription = button.onClick(Action0.empty);
                    test.assertNotNull(subscription);

                    test.assertTrue(subscription.dispose().await());
                });
            });
        });
    }
}
