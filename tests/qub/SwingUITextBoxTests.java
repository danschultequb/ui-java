package qub;

public interface SwingUITextBoxTests
{
    public static void test(TestRunner runner)
    {
        runner.testGroup(SwingUITextBox.class,
            (TestResources resources) -> Tuple.create(resources.getMainAsyncRunner()),
            (AsyncScheduler mainAsyncRunner) ->
        {
            final Function0<SwingUITextBox> creator = () ->
            {
                final SwingUI ui = SwingUI.create(mainAsyncRunner);
                return ui.create(SwingUITextBox.class).await();
            };

            UITextBoxTests.test(runner, creator);
            JComponentUIElementTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final SwingUITextBox textBox = creator.run();

                    final SwingUITextBox setBackgroundColorResult = textBox.setBackgroundColor(Color.red);
                    test.assertSame(textBox, setBackgroundColorResult);
                    test.assertEqual(Color.red, textBox.getBackgroundColor());
                });
            });

            runner.testGroup("onTextChanged(Action0)", () ->
            {
                runner.test("with one registration with multiple edits", (Test test) ->
                {
                    final UITextBox textBox = creator.run();
                    test.assertEqual("", textBox.getText());

                    final List<String> newTextBoxTexts = List.create();
                    final Disposable registration = textBox.onTextChanged(() ->
                    {
                        final String newTextBoxText = textBox.getText();
                        newTextBoxTexts.add(newTextBoxText);
                    });
                    test.assertNotNull(registration);

                    textBox.setText("hello 1");
                    test.assertEqual("hello 1", textBox.getText());
                    test.assertEqual(Iterable.create("hello 1"), newTextBoxTexts);

                    textBox.setText("hello 2");
                    test.assertEqual("hello 2", textBox.getText());
                    // The additional "" value is because in Swing setting the text first removes
                    // the old value and then applies the new value.
                    test.assertEqual(Iterable.create("hello 1", "", "hello 2"), newTextBoxTexts);

                    registration.dispose().await();
                    textBox.setText("hello 3");
                    test.assertEqual("hello 3", textBox.getText());
                    test.assertEqual(Iterable.create("hello 1", "", "hello 2"), newTextBoxTexts);
                });
            });
        });
    }
}
