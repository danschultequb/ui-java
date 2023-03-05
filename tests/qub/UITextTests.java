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

            runner.testGroup("setHorizontalAlignment(HorizontalAlignment)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIText uiText = creator.run();
                    final HorizontalAlignment horizontalAlignment = uiText.getHorizontalAlignment();
                    test.assertThrows(() -> uiText.setHorizontalAlignment(null),
                        new PreConditionFailure("horizontalAlignment cannot be null."));
                    test.assertEqual(horizontalAlignment, uiText.getHorizontalAlignment());
                });

                final Action1<HorizontalAlignment> setHorizontalAlignmentTest = (HorizontalAlignment horizontalAlignment) ->
                {
                    runner.test("with " + horizontalAlignment, (Test test) ->
                    {
                        final UIText uiText = creator.run();
                        final UIText setHorizontalAlignmentResult = uiText.setHorizontalAlignment(horizontalAlignment);
                        test.assertSame(uiText, setHorizontalAlignmentResult);
                        test.assertEqual(horizontalAlignment, uiText.getHorizontalAlignment());
                    });
                };

                for (final HorizontalAlignment horizontalAlignment : HorizontalAlignment.values())
                {
                    setHorizontalAlignmentTest.run(horizontalAlignment);
                }
            });

            runner.testGroup("setVerticalAlignment(VerticalAlignment)", () ->
            {
                runner.test("with null", (Test test) ->
                {
                    final UIText uiText = creator.run();
                    final VerticalAlignment verticalAlignment = uiText.getVerticalAlignment();
                    test.assertThrows(() -> uiText.setVerticalAlignment(null),
                        new PreConditionFailure("verticalAlignment cannot be null."));
                    test.assertEqual(verticalAlignment, uiText.getVerticalAlignment());
                });

                final Action1<VerticalAlignment> setVerticalAlignmentTest = (VerticalAlignment verticalAlignment) ->
                {
                    runner.test("with " + verticalAlignment, (Test test) ->
                    {
                        final UIText uiText = creator.run();
                        final UIText setVerticalAlignmentResult = uiText.setVerticalAlignment(verticalAlignment);
                        test.assertSame(uiText, setVerticalAlignmentResult);
                        test.assertEqual(verticalAlignment, uiText.getVerticalAlignment());
                    });
                };

                for (final VerticalAlignment verticalAlignment : VerticalAlignment.values())
                {
                    setVerticalAlignmentTest.run(verticalAlignment);
                }
            });
        });
    }
}
