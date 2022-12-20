package qub;

public interface JComponentUIElementTests
{
    public static void test(TestRunner runner, Function0<? extends JComponentUIElement> creator)
    {
        runner.testGroup(JComponentUIElement.class, () ->
        {
            UIElementTests.test(runner, creator);

            runner.testGroup("setBackgroundColor(Color)", () ->
            {
                runner.test("check return value type", (Test test) ->
                {
                    final JComponentUIElement uiElement = creator.run();

                    final JComponentUIElement setBackgroundColorResult = uiElement.setBackgroundColor(Color.red);
                    test.assertSame(uiElement, setBackgroundColorResult);
                    test.assertEqual(Color.red, uiElement.getBackgroundColor());
                });

                runner.testGroup("setBackgroundColor(Color)", () ->
                {
                    runner.testGroup("with transparent", () ->
                    {
                        final Action1<Color> setBackgroundColorTest = (Color color) ->
                        {
                            runner.test("with " + color, (Test test) ->
                            {
                                final UIElement uiElement = creator.run();
                                final UIElement setBackgroundColorResult = uiElement.setBackgroundColor(color);
                                test.assertSame(uiElement, setBackgroundColorResult);
                                test.assertEqual(color, uiElement.getBackgroundColor());
                            });
                        };

                        setBackgroundColorTest.run(Color.create(1, 2, 3, Color.ComponentMin));
                        setBackgroundColorTest.run(Color.create(5, 6, 7, 8));
                    });
                });
            });

            runner.test("getJComponent()", (Test test) ->
            {
                final JComponentUIElement jComponentUIElement = creator.run();
                test.assertNotNull(jComponentUIElement);

                final javax.swing.JComponent jComponent = jComponentUIElement.getJComponent();
                test.assertNotNull(jComponent);
                test.assertSame(jComponent, jComponentUIElement.getJComponent());
            });
        });
    }
}
