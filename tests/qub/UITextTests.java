package qub;

public interface UITextTests
{
    public static void test(TestRunner runner, Function0<? extends UIText> creator)
    {
        PreCondition.assertNotNull(runner, "runner");
        PreCondition.assertNotNull(creator, "creator");

        runner.testGroup(UIText.class, () ->
        {
            UIElementTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final UIText uiElement = creator.run();

                    final UIText setBackgroundColorResult = uiElement.setBackgroundColor(Color.red);
                    test.assertSame(uiElement, setBackgroundColorResult);
                    test.assertEqual(Color.red, uiElement.getBackgroundColor());
                });
            });

            runner.testGroup("setText(String)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIText text = creator.run();
                    test.assertEqual("", text.getText());

                    test.assertThrows(() -> text.setText(null),
                        new PreConditionFailure("text cannot be null."));

                    test.assertEqual("", text.getText());
                });

                runner.test("with empty", (Test test) ->
                {
                    final UIText text = creator.run();
                    test.assertEqual("", text.getText());

                    final UIText setTextResult = text.setText("");
                    test.assertSame(text, setTextResult);

                    test.assertEqual("", text.getText());
                });

                runner.test("with non-empty", (Test test) ->
                {
                    final UIText text = creator.run();
                    test.assertEqual("", text.getText());

                    final UIText setTextResult = text.setText("hello");
                    test.assertSame(text, setTextResult);

                    test.assertEqual("hello", text.getText());
                });
            });
        });
    }
}
